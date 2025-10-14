console.log("Level 1 ----------------------------");
let n = 5;
for (let i = 0; i <= n; i++) {
    console.log(i);
}
console.log("--------------");

for (let i = 1; i <= n; i++) {
    let row = "*".repeat(i);
    console.log(row);
}

console.log("-------");

let output = "";
for (let i = 1; i <= n; i++) {
    for (let j = 1; j <= i; j++) {
        output += "*";
    }
    output += "\n";
}
console.log(output);

console.log("--------------");

for (let i = 0; i <= n; i++) {
    console.log(`${i} x ${i} = ${i * i}`);
}

console.log("--------------");
console.log("i\t" + "i^2\t" + "i^3");
for (let i = 0; i <= n; i++) {
    console.log(`${i}\t${i * i}\t${i * i * i}`);
}

console.log("Level 2 ----------------------------");

const RandomID = [];
while (RandomID.length < n) {
    let randnum = Math.floor(Math.random() * 11);
    if (!RandomID.includes(randnum)) {
        RandomID.push(randnum);
    }
}
console.log(RandomID);

console.log("--------------");

let RandomID2 = [];
const chars = "0123456789abcdefghijklmnopqrstuvwxyz"

while (RandomID2.length < n) {
    let randIndex = Math.floor(Math.random() * chars.length);
    let randChar = chars[randIndex]; // Get a random character
    if (!RandomID2.includes(randChar)) {
        RandomID2.push(randChar);
    }
}
console.log(RandomID2.join(''));

console.log("--------------");
//Random Hexadecimal
/*
    Math.floor(Math.random() * 0xFFFFFF):
    - Scales the random number to a range between 0 and 0xFFFFFF 
      (decimal: 16777215), which represents the largest 6-digit 
      hexadecimal value.
    .toString(16) is radix, means num will be change into hexadecimal (base-16)
    .padStart(6, '0'):
    - Ensures the string is always 6 characters long by padding 
      with leading zeros.
*/
let randomHex = Math.floor(
    Math.random() * 0xFFFFFF).toString(16).padStart(6, '0');
console.log("#"+ randomHex);

let randColor = [];
while(randColor.length < 3) {
    let randcol = Math.floor(Math.random() * 100);
    if(randcol >= 0 && randcol <= 255) {
        randColor.push(randcol);
    }
}
let backroundCol = randColor.toString();
console.log("Background Color: rgb(" + backroundCol + ")");
document.body.style.backgroundColor = `rgb(${backroundCol})`;
console.log("Level 3 ----------------------------");
