/*
 * ARDUINO B
 * 
 * Connect to RFID RC522:
 * SDA (SS) -> 10
 * SCK      -> 13
 * MOSI     -> 11
 * MISO     -> 12
 * RST      -> 9
 * 3.3V     -> 3.3V
 * GND      -> GND
 *
 * Connect to Ultrasonic HY-SRF05:
 * VCC      -> 5V
 * GND      -> GND
 * Trig     -> 4
 * Echo     -> 5
 *
 * Connect to Master (Adruino A):
 * A4 -> A4
 * A5 -> A5
 * GND -> GND
 */

#include <SPI.h>
#include <MFRC522.h>
#include <Wire.h>

// RFID Config
#define SS_PIN 10
#define RST_PIN 9
MFRC522 rfid(SS_PIN, RST_PIN);

// Ultrasonic Config
#define TRIG_PIN 4
#define ECHO_PIN 5
#define DISTANCE_THRESHOLD 12 // cm

// Data buffers
byte bufferUID[4];
bool hasCard = false;
bool personPresent = false;

unsigned long lastUltrasonicCheck = 0;
const int ULTRASONIC_INTERVAL = 100; // every 100ms

void setup() {
  Serial.begin(9600); // enable Serial for debugging
  
  // initialize RFID
  SPI.begin();
  rfid.PCD_Init();
  
  // initialize Ultrasonic
  pinMode(TRIG_PIN, OUTPUT); // send sound pulse
  pinMode(ECHO_PIN, INPUT); // receive
  digitalWrite(TRIG_PIN, LOW); // set trig is low at start
  
  // initialize I2C with address 8
  Wire.begin(8);
  Wire.onRequest(requestEvent); // register response function when Master requests data
  
  Serial.println(F("Slave Ready (RFID + Ultrasonic)"));
  Serial.println(F("Address: 8"));
}

void loop() {
  // Read distance from Ultrasonic
  if (millis() - lastUltrasonicCheck > ULTRASONIC_INTERVAL) {
    float distance = measureDistance();
    
    if (distance > 0 && distance < DISTANCE_THRESHOLD) {
      personPresent = true;
    } else {
      personPresent = false;
    }
    
    lastUltrasonicCheck = millis();
  }
  
  // Read RFID only when no card is waiting (avoid overwrite)
  if (!hasCard) {
    if (rfid.PICC_IsNewCardPresent() && rfid.PICC_ReadCardSerial()) {
      // copy 4 UID bytes from library to buffer
      memcpy(bufferUID, rfid.uid.uidByte, 4);
      hasCard = true;

      // debug
      Serial.print(F("Card: "));
      for (byte i = 0; i < 4; i++) {
        if (bufferUID[i] < 0x10) Serial.print("0");
        Serial.print(bufferUID[i], HEX);
      }
      Serial.println();
      
      rfid.PICC_HaltA(); // put card to halt to avoid repeated reading
      rfid.PCD_StopCrypto1(); // stop encryption
    }
  }
}

// Ultrasonic distance measurement function
float measureDistance() {
  // Send trigger pulse (10 micros)
  digitalWrite(TRIG_PIN, LOW);
  delayMicroseconds(2);
  digitalWrite(TRIG_PIN, HIGH); // start pulse
  delayMicroseconds(10);
  digitalWrite(TRIG_PIN, LOW); // end pulse
  
  // Measure echo return time on ECHO pin
  long duration = pulseIn(ECHO_PIN, HIGH, 30000); // 30ms timeout
  
  if (duration == 0) {
    return -1; // measurement failed
  }
  
  // calculate distance (cm)
  float distance = duration * 0.034 / 2.0;
  
  return distance;
}

// this function runs automatically when Master requests data
void requestEvent() {
  // Send 6 bytes:
  // Byte 1: RFID status (1 = card present, 0 = none)
  // Byte 2-5: UID (4 bytes)
  // Byte 6: Ultrasonic flag (1 = person detected, 0 = none)
  
  if (hasCard) {
    Wire.write(1); // card detected
    Wire.write(bufferUID, 4); // send 4 UID bytes
    hasCard = false; // clear flag
    memset(bufferUID, 0, 4); // clear buffer
  } else {
    Wire.write(0); // no card
    Wire.write(0); Wire.write(0); Wire.write(0); Wire.write(0); // 4 empty bytes
  }
  
  // send Ultrasonic status
  Wire.write(personPresent ? 1 : 0);
}
