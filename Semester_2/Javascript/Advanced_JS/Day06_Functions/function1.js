//I. Function Declaration
//Function without a parameter and return
function addTwoNum() {
    let numOne = 10;
    let numTwo = 20;
    console.log(numOne + numTwo);
}

addTwoNum(); //30

console.log("-------------------------");
//Function returning value
function addTwoNum2() {
    let numOne = 30;
    let numTwo = 20;

    return numOne + numTwo;
}

console.log(addTwoNum2()); //50

console.log("-------------------------");
//Function with parameter(s)
//1. One parameter
function areaOfCircle(r) {
    let Area = Math.PI * r * r;
    return Area;
}
let r = 10;
console.log(areaOfCircle(r).toFixed(4)); //314.1593

//2. Two parameters
function addTwoNum3(numOne, numTwo) {
    return numOne + numTwo;
}
let numOne = 15, numTwo = 5;
console.log(addTwoNum3(numOne, numTwo)); //20

//3. Many parameters
function sumOfArray(arr) {
    let sum = 0;
    for(let i = 0; i < arr.length; i++) {
        sum += arr[i];
    }
    return sum;
}
const numbers = [1, 2, 3, 4, 5];
console.log(sumOfArray(numbers)); //15

console.log("-------------");
//4. Function with unlimited number of parameters
//Unlimited number of parameters in regular function
function sumAllNums() {
    let sum = 0;
    /* `arguments` is a built-in array-like object available in non-arrow 
    functions that contains all the arguments passed to the function. */
    for(let i = 0; i < arguments.length; i++) {
        sum += arguments[i];
    }
    return sum;
}
console.log(sumAllNums(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)); //55
console.log(sumAllNums(10, 20, 15, 40, 23)); //108
console.log(sumAllNums(20, 109, 38)); //167

console.log("-------------");

//Unlimited number of parameters in arrow function
const sumAllNums2 = (...args) => {
    let sum = 0;
    for(const element of args) {
        sum += element;
    }
    return sum;
}
console.log(sumAllNums2(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)); //55
console.log(sumAllNums2(10, 20, 15, 40, 23)); //108
console.log(sumAllNums2(20, 109, 38)); //167

console.log("-------");

const sumAllNums3 = (...shopping) => {
    let salary = 2000;
    for(const element of shopping) {
        /* 
            `reduce` is a method for arrays that processes all elements to produce
            a single output value. It takes a 'callback function' and an 'initial 
            value' as arguments.
            Parameters:
                `sum`: Accumulator that stores the cumulative result as the array 
                       is iterated.
                `num`: The current element of the array being processed.
            Operation: Adds the current element (`num`) to the accumulator (`sum`).
        */
        return shopping.reduce((salary, shopping) => salary - shopping, salary)
    }
}
console.log(sumAllNums3(200, 90, 170, 399, 21)); //The amount after shopping: 1120$
console.log(sumAllNums3(1000, 20)); //980$
console.log(sumAllNums3(20, 109, 38)); //1833$