//II. Anonymous Function & Express Function
//Anonymous function or without name function
/*
    Anonymous functions are functions without a name, used for temporary purposes 
    or when reuse is unnecessary.

    Uses:
    1. Callbacks: For functions like `map`, `filter`, `reduce`.
    2. Conciseness: Shorten code, especially with arrow functions.
    3. Temporary Use: In events or one-time logic (`setTimeout`, etc.).
    4. IIFE: For immediate execution to create local scope.
    5. Closures: Within objects or complex expressions.

    When to use:
    - No need for reuse.
    - Simplify code.
    - Use in callbacks or closures.
*/
const anonymousFunc = function() {
    console.log(
        "I am an anonymous function and my value \
is stored in anonymousFunc.");
}
anonymousFunc();

// Expression Function
/* 
    Expression functions are anonymous functions. After we create a function 
    without a name and we assign it to a variable. To return a value from the 
    function we should call the variable. Look at the example below. 
*/
const square = function(n) {
    return n ** 2;
}

console.log(square(3)); //9

// Self Invoking Function
/* 
    Self invoking functions are anonymous functions which do not need to be 
    called to return a value.
*/
(function(n) {
    console.log(n ** 2)
}) (2) //4, but instead of just printing if we want to return and store the data, we do as shown below

let squaredNum = (function(n) {
    return n ** 2;
}) (10)
console.log(squaredNum); //100

console.log("-------------------------");

// III. Arrow Function
/* 
    Arrow function uses arrow instead of the keyword function to declare a function.
    Let us see both function declaration and arrow function.
*/
function square2(n) {
    return n * n;
}
console.log(square2(2)); //4

const square3 = n => {
    return n * n;
}

console.log(square3(2)); //4

//or it can be like this:

const square3_1 = n => n * n;
console.log(square3(2)); //4

console.log("-------------");

const changeToLowerCase = arr => {
    const newArr = [];
    for(const element of arr) {
        newArr.push(element.toLowerCase());
    } 
    return newArr;
}
const countries = ['VietNam', 'ThaiLand', 'Japan', 'Cuba', 'USA'];
console.log(changeToLowerCase(countries));

console.log("-------");

const printFullName = (firstName, lastName) => `${firstName} ${lastName}`
console.log(printFullName('Dat', 'Tien'));

console.log("-------------------------");

// Function with default parameters

function greetings(name = 'DatIT') {
    let message = `${name}, welcome to FireHelper Team!`
    return message;
}

console.log(greetings());
console.log(greetings('Peter'));

console.log("-------");

function generateFullName(firstName = 'Dat', lastName = 'Tien') {
    return firstName + " " + lastName;
}

console.log('Welcome', generateFullName());
console.log('Welcome', generateFullName('Peter', 'Parker'));

console.log("-------------");

//Arrow Function with Default Parameters

const greetings2 = (name = 'DatIT') => {
    return name + ', welcome to FireHelper Team!';
}
console.log(greetings2());
console.log(greetings2('Peter'));

console.log("-------");

const generateFullName2 = (firstName = 'Dat', lastName = 'Tien') => firstName + " " + lastName;
console.log('Welcome', generateFullName2());
console.log('Welcome', generateFullName2('Peter', 'Parker'));

//Function declaration vs Arrow function (Coming Soon)