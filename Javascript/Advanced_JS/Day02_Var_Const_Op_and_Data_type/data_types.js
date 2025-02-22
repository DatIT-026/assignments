let hello = "Hello, World";
console.log(hello);

let firstName = "Dat", lastname = "IT";
let age = 18, address = "HCM";
const PI1 = 3.141592654;

console.log("My name is", firstName + lastname);
console.log("I am", age, "and live in " + address);
console.log("Do you know PI? It's", PI1);

//Một số cách làm tròn số

/*  Cách 1:
    Bước 1: Nhân với 100: Đưa phần thập phân đến số nguyên
        Ex: 3.141592654 * 100 = 314.1592654
    Bước 2: Dùng Math.round() làm tròn đến số nguyên gần nhất
        Ex: Math.round(314.1592...) = 314
    Bước 3: Chia lại cho 100 => 314 / 100 = 3.14
*/
function roundTo2Decimals(num) {
    return Math.round(num * 100) / 100;
}

let roundedPI = roundTo2Decimals(PI1);
console.log("PI after rounded = " + roundedPI); //3.14

// Cách 2:
let roundedPI2 = PI1.toFixed(3);
console.log("PI2 after rounded = " + roundedPI2); //3.142

//Changing Data Type (Casting)

//1. String to Int, String to Float, Float to Int (using parseInt() only)
/*
    parseInt(), parseFloat()
    Number()
    Plus sign(+)
*/
let num = '10'
let numInt = parseInt(num);
let numInt2 = Number(num);
let numInt3 = +num;
console.log(numInt); //10
console.log(numInt2); //10
console.log(numInt3); //10