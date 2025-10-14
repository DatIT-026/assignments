console.log("Level 1 ----------------------------");

let named = "30 Days of JavaScript";
console.log(named);
console.log(named.length);

console.log(named.toUpperCase());
console.log(named.toLowerCase());

console.log(named.substr(0, 2));
console.log(named.substring(0, 3));
console.log(named.substr(3, 18));

console.log(named.includes("Script"));

console.log(named.split());

console.log(named.split(" "));

let txt = "Facebook, Google, Microsoft, Apple, IBM, Oracle, Amazon"
console.log(txt.split(" "));

console.log(named.replace("JavaScript", "C++"));

console.log(named.charAt(15));

console.log(named.charCodeAt(11));

let txt1 = "You cannot end a sentence with because because because is a conjunction."
console.log(txt1.indexOf("because"));
console.log(txt1.lastIndexOf("because"));
console.log(txt1.search("because"));

let named1 = "      30 Days of JavaScript   ";
console.log(named1);
console.log(named1.trim());

console.log(named.startsWith("30"));
console.log(named.endsWith("JavaScript"));

let pattern = /a/gi;
console.log(named.match(pattern));

let txt2 = "30 days of ";
console.log(txt2.concat("JavaScript"));

let txt3 = "30 Days of JavaScript\n"
console.log(txt3.repeat(5));

console.log("Level 2 ----------------------------");

console.log(
    'The quote \"There is no exercise better for the heart than reaching down and lifting people up.\" by John Holmes teaches us to help one another.')

let num = '10';
let IntNum = 10;
console.log(num == IntNum);
console.log(num === IntNum);
console.log(+num === IntNum);

console.log(Math.ceil(9.8) == 10);

let python = "Python", jagon = "Jagon";
console.log(python.includes("on") == jagon.includes("on"));

let txt4 = "I hope this course is not full of jargon."
console.log(txt4.includes("jargon"));

console.log(Math.round(Math.random() * 101));
console.log(Math.round(Math.random() * 256));

let str = "JavaScript";
//Math.random() ra số thập phân từ 0 -> < 1 => * str.length sẽ cho ra khoảng 0 -> < str.length
let randIndex = Math.floor(Math.random() * str.length); //rounding down (0 -> str.length - 1)
let randChar = str[randIndex]; //Lấy ký tự tại vị trí randIndex trong chuỗi str.
//Ex: "JavaScript"[4] trả về 'S'
console.log(`Random character from \"JavaScript"\: ${randChar}`);

console.log("1 1 1 1 1\
            \n2 1 2 4 8\
            \n3 1 3 9 27\
            \n4 1 4 16 64\
            \n5 1 5 25 125");

console.log(txt1.substr(31, 23));

console.log("Level 3 ----------------------------");

txt5 = "Love is the best thing in this world.\
 Some found their love and some are still\
 looking for their love."
console.log(txt5.match(/love/gi)); //including the case insensitive

console.log(txt1.match(/because/gi));

const sentence = '%I $am@% a %tea@cher%, &and& I lo%#ve %te@a@ching%;.\
 The@re $is no@th@ing; &as& mo@re rewarding as educa@ting &and& @emp%o@weri@ng peo@ple.;\
 I found tea@ching m%o@re interesting tha@n any ot#her %jo@bs.%Do@es thi%s mo@tiv#ate yo@u\
 to be a tea@cher!? %Th#is 30#Days&OfJavaScript &is al@so $the $resu@lt of &love& of tea&ching'

const cleanedSentence = sentence
    .replace(/[^a-zA-Z\s]/g, '') // Remove non-alphabetic characters
    .toLowerCase() // Convert to lowercase
    .replace(/\s+/g, ' '); // Replace multiple spaces with a single space
    /*
        `/\s+/` is a regular expression used to match one or more consecutive whitespace characters, including spaces, tabs, and line breaks.
    */
console.log(cleanedSentence);

let txt6 = "He earns 5000 euro from salary per month, 10000 euro annual bonus, 15000 euro online courses per month."
const salary = txt6.match(/\d+/g).map(Number);

const annualIncome = salary[0] * 12 + salary[1] + salary[2] * 12;

const SimpleSum = salary.reduce((a, b) => a + b, 0);

/*
    map(Number) turns all items in `salary` into numbers.

    You cannot use `salary.map(parseInt)` because `parseInt` doesn't just read the number;
    it also tries to interpret the radix (base). Use `Number` because it only converts strings
    to numbers without interpreting the index.

    `reduce((a, b) => a + b, 0)` sums up all elements in the array starting from 0.
    a = 0 => a = 0 + 5000 = 5000 
          => a = 5000 + 10000 = 15000
          => a = 15000 + 15000 = 30000

    In short: `parseInt` does too much work, while `Number` focuses only on the numbers.
              `.reduce((a, b) => a + b, 0)` sums up all elements in the array starting from 0.
*/
console.log(annualIncome);
console.log(SimpleSum);

