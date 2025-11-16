// =========== DO NOT EDIT THE GIVEN CONTENT OF THIS FILE ============
class Car {
  String place;
  int weight,color;
  Car() {
   }
  Car(String xPlace, int xWeight, int xColor){
    place=xPlace;weight=xWeight; color=xColor;
   }
  public String toString(){
    return("(" + place +","+ weight + "," + color + ")");
   }
 }
