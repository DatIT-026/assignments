//Higher Order Function (Hàm bậc cao)
//Function that takes another function as an argument
function greet(name, callbackme) {
    console.log("Hello, " + name);
    callbackme();
}

function sayLove() {
    console.log("We love you!");
}

function sayGoodbye() {
    console.log("Goodbye!");
}
greet("DatIT", sayLove);
greet("Dat", sayGoodbye);

console.log("=========================");
//Function that return another function
function multiplyBy(num) { //Higher-order function
    return function(x) { //anonymous function
        return x * num;
    };
}
const multiplyByTwo = multiplyBy(2);
console.log(multiplyByTwo(5)); //10
const multiplyBySix = multiplyBy(6);
console.log(multiplyBySix(3)); //18

console.log("=========================");
//Built-in higher-order functions in JS
const numbers = [1, 2, 3, 4, 5];

//map: Double the numbers
const doubled = numbers.map(num => num * 2);
console.log(doubled); //[2, 4, 6, 8, 10]
//filter: Get even numbers
const evens = numbers.filter(num => num % 2 === 0);
console.log(evens); // [2, 4]
//reduce: Calculate the sum, ...
//ACCumulator: lưu giá trị tích luỹ sau mỗi lần lặp
//num: giá trị hiện tại trong mảng
//Khi acc = 0, num = 1 -> acc + num = 1 => acc = 1, num = 2 -> acc + num = 2 => ...
const sum = numbers.reduce((acc, num) => acc + num, 0);
console.log(sum); //15
//more
const product = numbers.reduce((acc, num) => acc * num, 1);
console.log(product); //5! = 120
const words = ["Hello,", "World", "!"];
const sentence = words.reduce((acc, word) => acc + " " + word, "");
console.log(sentence.trim());

console.log("=========================");
//Callback
//arrow func nhận tham số n & trả về n**2
const callback = (n) => {
    return n ** 2; //n^2
}
//Nhận 2 tham số callback & n. Gọi callback(n) tính n^2 rồi *n
function cube(callback, n) {
    return callback(n) * n; //n^2 * n
}
console.log(cube(callback, 3)); //3^3 = 27
console.log(cube(callback, 6)); //6^3 = 216

console.log("=========================");
//Returning function
const higherOrder = n => {
    const doSomething = m => {
        const doWhatEver = t => {
            return 2 * n + 3 * m + t;
        }
        return doWhatEver;
    }
    return doSomething;
}
console.log(higherOrder(2)(3)(10)); //2*2+3*3+10=23

console.log("============");
//Demo with arrow func
const num = [5, 6, 7, 8, 9];
const sumArr = arr => {
    let sum = 0;
    const callback = function(i) {
        sum += i;
    }
    //forEach được dùng để duyệt qua từng phần tử trong mảng 
    //và thực hiện một hàm callback với từng phần tử đó.
    arr.forEach(callback); 
    return sum;
}
console.log(sumArr(num));

const num_1 = [5, 6, 7, 8, 9];
const SimplierSumArr = arr => {
    let sum = 0;
    arr.forEach(function(i) {
        sum += i;
    })
    return sum;
}
console.log(SimplierSumArr(num_1));
console.log("=========================");
//Setting time: setInterval, setTimeout
//Setting Interval
let i1 = 0
function sayHello1() {
    console.log("Hello1");
    i1++;
    if(i1 === 5) {
        clearInterval(intervalID_1); //break after 5 seconds
    }
} const intervalID_1 = setInterval(sayHello1, 1000);

let i = 0;
const intervalID = setInterval(() => {
    console.log("This message appears every 1 second!");
    i++;
    if(i === 10) {
        clearInterval(intervalID); //break after 10 seconds
    }
}, 1000);

//Setting a time
function sayHello2(){
    console.log("Hello2");
} const clearTimeoutID = setTimeout(sayHello2, 5000);
clearTimeout(clearTimeoutID); //break

setTimeout(() => {
    console.log("This message appears after 5 seconds!");
}, 5000);

