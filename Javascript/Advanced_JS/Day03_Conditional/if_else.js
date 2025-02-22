let a = 5, b = 9, c = 9;
if (a > b && a > c) {
    console.log(`${a} is the largest number.`);
} else if (b > c) {
    console.log(`${b} is the largest number.`);
} else {
    console.log(`${c} is the largest number.`)
}

console.log("----------------------")

let weather = 'cloudy'
switch(weather) {
    case 'rainy':
        console.log("You need a rain coat.");
        break;
    case 'cloudy':
        console.log("It might be cold. You need a jacket.");
        break;
    case 'sunny':
        console.log("It might be sunny. You should bring a cap.");
        break;
    default:
        console.log("No need for rain coat.");
}

console.log("----------------------")

let num = prompt("Enter number: ");
num = Number(num);
//str = String(num); //from number to string 

switch(true) {
    case num > 0:
        console.log("Number is positive.");
        break;
    case num < 0:
        console.log("Number is negative.");
        break;
    case num == 0:
        console.log("Number is zero.");
        break;
    default:
        console.log("Invalid Input!");
}