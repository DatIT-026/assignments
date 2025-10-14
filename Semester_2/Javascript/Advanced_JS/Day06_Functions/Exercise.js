console.log("Level 1 ----------------------------");

let firstName = 'Dat', midName = 'Nguyen Tien', lastName = "Ha";
function fullName() {
    console.log(lastName, midName, firstName);
}
fullName();

console.log("--------------");

function fullName1(lastName1, firstName1) {
    let fullName1 = lastName1 + " " + firstName1;
    return fullName1;
}
console.log(fullName1('Dat', 'Tien'));

const volume_of_rectangular_prism = (length, width, height) => {
    let volume = length * width * height;
    return volume;
}
console.log(volume_of_rectangular_prism(2, 3, 4));

console.log("-------");
// Body Mass Index (BMI)
const BMI = (user_bmi) => {
    if (user_bmi && typeof user_bmi === 'number') {
        if (0 < user_bmi && user_bmi < 18.5) {
            return "Underweight";
        } else if (18.5 <= user_bmi && user_bmi < 25) {
            return "Normal weight";
        } else if (25 <= user_bmi && user_bmi < 30) {
            return "Overweight";
        } else {
            return "Obese"
        }
    } else {
        return "Invalid Input!";
    }
}
console.log(BMI(Number('23')));

console.log("Level 2 ----------------------------");
console.log("Level 3 ----------------------------");