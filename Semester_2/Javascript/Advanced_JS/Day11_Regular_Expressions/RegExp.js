// RegExp is a small programming languages that helps to find pattern in data
// Can be used to check if some pattern exists in a different data types.
/* To use RegExp, either we use RegExp constructor or we can declare a RegExp
pattern using 2 forward slashes followed by a flag. */
/* A string is declared using '', "", or ``, while a regular expression is 
declared with 2 forward slashes (//) and an optional flag (g, i, m, s, u, y) */

// RegExp parameters (/Pattern/Flags)

// Creating a pattern with RegExp Constructor
// Without flag
let pattern0 = 'love';
let regEx0 = new RegExp(pattern0);
// Declaring RegExp with global flag & case insensitive flag
let pattern1 = 'love';
let flag1 = 'gi';
let regEx1 = new RegExp(pattern1, flag1);
/* Declaring a regex pattern using RegExp obj. 
   Writing the pattern & flag inside the RegExp constructor */
let regEx2 = new RegExp('love', 'gi');

// Creating a pattern without RegExp Constructor
// Declaring RegExp with global flag & case insensitive flag
let regEx = /love/gi;
// The above regular expression is the same as the one which we created with RegExp constructor

// RegExp Obj Methods
// 1. test(): Tests for a match in a string. It returns true or false.
const str = 'I love JavaScript';
const str1 = 'I hate JavaScript';
const pattern = /love/;
const result = pattern.test(str);
const result1 = pattern.test(str1);
console.log(result, result1);

/* 2. match(): returns an array containing all of the matches, including capturing
groups, or null if no match is found if we do not use a flobal flag, match() returns
as an array containing the pattern, index, input, and group */
const result2 = str.match(pattern);
const pattern_3 = /love/g;
const result3 = str.match(pattern_3);
console.log(result2); // ["love", index: 2, input: "I love JavaScript", groups: undefined]
console.log(result3); // ["love"]

// 3. search(): tests for a match in a string. It returns the index of the match, or -1 if fails
const result4 = str.search(pattern_3);
console.log(result4); // 2

// 4. replace(): replaces a substring
const txt = 'Python is the most beautiful language that a human begin has ever created \
but I recommend that do not learn python for a first programming language.';

matchReplaced = txt.replace(/Python|python/, 'JavaScript');
matchReplaced2 = txt.replace(/Python|python/g, 'JavaScript');
matchReplaced3 = txt.replace(/Python|python/gi, 'JavaScript');
console.log(matchReplaced);
console.log(matchReplaced2);
console.log(matchReplaced3);

const txt2 = '%I a%m st%%u%%den%t% a%n%d %%I l%o%ve le%arn%ing. \
T%he%re i%s n%o%th%ing as m%ore r%ewarding a%s l%ear%ni%%ng a%n%d s%l%ee%p%%ing.';

matches = txt2.replace(/%/g, '');
console.log(matches);