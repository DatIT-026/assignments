// See the img, let's use example to clarify the above meta characters
// Square Bracket
const pattern0 = '[Aa]pple'; // This square bracket means either A or a
const pattern1 = /[Aa]pple/g;
const pattern2 = /[Aa]pple|[Bb]anana/g;
const txt0 = 'Apple and banana are fruits. \
An old cliche says an apple a day keeps the doctor way has been \
replaced by a banana a day keeps the doctor far away. \
Banana is easy to eat too.';
const matches0 = txt0.match(pattern0);
const matches1 = txt0.match(pattern1);
const matches2 = txt0.match(pattern2);
console.log(matches0); // ["Apple", index: 0, input: "Apple and banana are fruits...easy to eat too.", groups: undefined]
console.log(matches1); // ["Apple", "apple"]
console.log(matches2); // ["Apple", "banana", "apple", "banana", "Banana"]

// Escape character (\) in RegExp
const pattern = /\d/g // d is a special char which means a single digit
const pattern_1 = /\d+/g // d+ is a special char which means one or more consecutive digits
const txt = 'This regular expression example was made in February 13, 2025.';
const matches = txt.match(pattern);
const matches_1 = txt.match(pattern_1);
console.log(matches); // ["1", "3", "2", "0", "2", "5"]
console.log(matches_1); // ["13", "2025"]

//Period (.)
const pattern_2 = /[a]./g // This square bracket means a and . means any char except new line
const pattern_3 = /[a].+/g // . any char, + any char one or more times
const txt_2 = 'Apple and banana are fruits.';
const matches_2 = txt_2.match(pattern_2);
const matches_3 = txt_2.match(pattern_3);
console.log(matches_2); // ["an", "an", "an", "a ", "ar"]
console.log(matches_3); // ['and banana are fruits']

// Zero or more times (*). The pattern may not occur or it can occur many times.
const pattern_4 = /[a].*/g  //. any character, + any character one or more times 
const matches_4 = txt_2.match(pattern_4)
console.log(matches_4)  // ['and banana are fruits']

// Zero or more times (*). The pattern may not occur or it can occur many times.
const txt_3 = 'I am not sure if there is a convention how to write the word e-mail. \
Some people write it email others may write it as Email or E-mail.'
const pattern_5 = /[Ee]-?mail/g // ? means optional
matches_5 = txt_3.match(pattern_5);
console.log(matches_5); // ["e-mail", "email", "Email", "E-mail"]

//Quantifier in RegExp
/* We can specify the length of the substring we look for in a text, using a curly bracket.
Let us see, how to use RegExp quantifiers. Imagine, we are interested in substring that their
length are 4 chars */
const pattern_6 = /\b\w{4}\b/g // exactly 4 character words
const pattern_7 = /\b[a-zA-Z]{4}\b/g // exactly 4 character words without numbers
const pattern_8 = /\d{4}/g // a number and exactly 4 digits
const pattern_9 = /\d{1,4}/g // 1 to 4

const matches_6 = txt.match(pattern_6);
const matches_7 = txt.match(pattern_7);
const matches_8 = txt.match(pattern_8);
const matches_9 = txt.match(pattern_9);

console.log(matches_6); // ['This', 'made', '2025']
console.log(matches_7); // ['This', 'made']
console.log(matches_8); // ['2025']
console.log(matches_9); // ['13', '2025']

// Cart ^
// Starts with
const pattern_10 = /^This/ // ^ means starts with
const matches_10 = txt.match(pattern_10);
console.log(matches_10); // ['This']
// Negation
const pattern_10_1 = /[^A-Za-z,. ]+/g // ^ in set char means negation, not A to Z, not a to z, no space, no comma no period
const matches_10_1 = txt.match(pattern_10_1);
console.log(matches_10_1); // ['13', '2025']

// Exact match
// It should have ^ starting and $ which is an end.
let pattern3 = /^[A-Z][a-z]{3,7}$/;
let name = 'Kiyoshi'; // Nice!
let name2 = 'dAtto'; // Need a capital letter at the beginning.
let name3 = 'Bi'; // 3 <= lowercase chars <= 7
let result = pattern3.test(name);
let result2 = pattern3.test(name2);
let result3 = pattern3.test(name3);
console.log(result, result2, result3); // true false false

//Explain: The string must start with a capital letter followed by 3 to 7 lowercase letters.