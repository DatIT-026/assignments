/*
    In JS, the Math Object provides lots of methods
    to work with numbers
*/
const PI = Math.PI
console.log(PI);

//Rounding to the closest num.
//If above .5 -> up. If less, down rounding
console.log(Math.round(PI)); //round val to the nearest num: 3 
console.log(Math.floor(PI)); //rounding down: 3
console.log(Math.ceil(PI));  //rounding up: 4

//max, min
console.log( "The largest val of 3: ", Math.max(3, 4, 5));
console.log( "The smallest val of 3: ", Math.min(3, 4, 5));

const randNum = Math.random(); //random num between 0 to 0.999999
console.log(randNum);
const randNum1 = Math.floor(Math.random() * 11); //random num between 0 to 10
console.log(randNum1);

console.log("The absolute value of -10 is", Math.abs(-10)); //Absolute Value
console.log("Square root of 121 is", Math.sqrt(121)); //11
console.log("2 to the power of 4 is", Math.pow(2, 4)); //2*2*2*2
console.log(Math.log(2)); //natural logarit with base e of x
//To get the logarit base x of y
function getBaseLog(x, y) {
    return Math.log(y) / Math.log(x);
}
console.log(getBaseLog(2, 8).toFixed(3)); //Logarit base 2 of 8

//Trigonometry
console.log(Math.sin(PI/2));
console.log(Math.cos(PI));

//Escape Sequences in Strings
/*
    \n: new line
    \t: Tab, means 8 spaces
    \\: Back slash
    \': Single quote (')
    \": Double quote (")
*/
console.log("I hope everyone is enjoying the course.\nDo you?");
console.log("Days\tTopics\tExercises\n" +
            "Day 1 \t3 \t\t5 \n" +
            "Day 2 \t3 \t\t5 \n" +
            "Day 3 \t3 \t\t5");
            console.log('This is a backslash  symbol (\\)') // To write a backslash
            console.log('In every programming language it starts with \"Hello, World!\"')
            console.log("In every programming language it starts with \'Hello, World!\'")
            console.log('The saying \'Seeing is Believing\' isn\'t correct in 2020')
            
//Template Literals (Template Strings)
let a = 2;
let b = 3;
console.log(`The number of a is ${a} and ${b} is ${a + b}`); // Using ` ` instead of " " or ' '

//String Methods
let js = "JavaScript";
console.log(js.length); //10
console.log(js.toUpperCase()); //JAVASCRIPT
console.log(js.toLowerCase()); //javascript

//1. substr and substring
console.log(js.substr(4, 6)); //take from the start and number of chars to slice
console.log(js.substring(0, 4)); //take from the start to the char of end-1

//2. split()
let js1 = "JavaScript is fun!"
console.log(js1.split()); //Changes to an array -> ['JavaScript']
console.log(js1.split(' ')); //Split to an array of space

//3. trim()
let string = '  DatIT is great!         ';
console.log(string);
console.log(string.trim());

//4. includes() takes a substring argument and checks if substring argument exists in the string
//if exist, return true, else false
let string1 = "I love coding with my Fumo!";
console.log(string1.includes("coding")); //true
console.log(string1.includes('Love')); //false

//5. replace()
let orgstring = "DatIT is great";
console.log(orgstring.replace("great", "perfect"));

//6. charAt() takes index and it returns the val at that index
console.log(string1.charAt(7)); // I love c.. (c is the 7th element - counting from 0)
let lastIndex = string1.length-1;
console.log(string1.charAt(lastIndex)); //"!" is at the end of the string1
//6.1. charCodeAt()
console.log(string1.charCodeAt(22)); //F ASCII number is 70 (Binary: 01000110)

//7. indexOf()
console.log(string1.indexOf("o")); //o is the 3th element of the string1 - counting from 0
console.log(string1.indexOf("a")); //No "a" in the string1 so the output is -1
//7.1. lastIndexOf()
console.log(string1.lastIndexOf("with")); //the first char of "with" at the 14th place
console.log(string1.lastIndexOf("fumo")); //No "fumo" in the string1 -> Output: -1

//8. concat()
console.log(string1.concat(" Fumo", " is", " my", " life."));

//9. startWith() and endWith()
console.log(orgstring.startsWith("DatIT")); //true
console.log(orgstring.startsWith("LongGM")); //false
console.log(orgstring.endsWith("great")); //true
console.log(orgstring.endsWith("dumb")); //false

//10. search()
console.log(orgstring.search("great")); //9

/* 'match' takes a substring or regular expression pattern as an argument and returns an array
if there is match, or return null if not. */
let string2 = "love"
let patternOne = /love/ //with out any flag
let patternTwo = /love/gi // 'g' means to search in the whole text, 'i' case insensitive
// match syntax
let string3 = "I love JavaScript. If you do not love JavaScript what else can you love.";
console.log(string3.match(patternOne));
console.log(string3.match(patternTwo));

//extract number
let txt = "In 2019, I ran 30 Days of Cpp. Now, in 2020 I am super exited to start this challenge";
// 'd' with escape char means d not a normal d instead acts a digit
// '+' means one or more digit numbers, if there is a 'g' after that it mean global, search everywhere
console.log(txt.match(/\d/));
console.log(txt.match(/\d/g));
console.log(txt.match(/\d+/));
console.log(txt.match(/\d+/g));

/* 
    Bonus: `/\s+/` is a regular expression used to match one or more consecutive 
    whitespace characters, including spaces, tabs, and line breaks.
*/
txt5 = "I  love         you!";
console.log(txt5.match(/\s+/g)); //Output is every whitespace characters

/* 
    Bonus2: `/[^a-zA-Z\s]/ is a regular expression that matches any character that
    is not a letter (a-z or A-Z) or a whitespace.
        `[^...]`: Matches anything not inside the brackets.
        `a-zA-Z`: Matches uppercase (A-Z) and lowercase (a-z) letters.
        `\s`: Matches any whitespace character (spaces, tabs, line breaks).
    => Combined: Matches characters that are not letters and not whitespace.
*/
txt6 = "Hello@, Wo#&rld!123!";
const cleanedtxt6 = txt6.replace(/[^a-zA-Z\s]/g, '');
console.log(cleanedtxt6);

//repeat()
console.log(string2.repeat(5));