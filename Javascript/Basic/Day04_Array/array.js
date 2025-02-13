//Array syntax
const arr = Array();
let arr1 = new Array();
const arr2 = []; //Most recommended way to create an empty list 

console.log(arr);
console.log(arr1);
console.log(arr2);

//Array with value
const numbers = [1, 3.14, 9, 384];
const webTech = ['HTML', 'CSS', 'JS', 'React', 'MongDB'];
console.log('Numbers:', numbers);
console.log('Number of numbers:', numbers.length);
console.log('webTech:', webTech);

const user_webTechSkill = [
    1, 'DatIT',
    {city: 'HCM', country: 'VN'},
    {skills: ['HTML', 'CSS', 'JS', 'C/C++', 'Go', 'Rust']}
]
console.log(user_webTechSkill);

//split()
let js = 'JavaScript';
const charsInJS = js.split('');
console.log(charsInJS);

let txt = 'I love teaching and empowering people. I teach HTML, CSS, JS, React, Python.';
const words = txt.split(' ');
console.log(words);

let topCompanies = 'Microsoft, Google, Apple, Meta, Amazon, Oracle, IBM';
const companies = topCompanies.split(',');
console.log(companies);

//Methods to manipulate array
/*  Available methods to deal with arrays:
    Array, length, concat, indexOf, slice, splice, join, toString or String(), 
    includes, lastIndexOf, isArray, fill, push, pop, shift, unshift.
*/
//1. Array Constructor
const arr3 = Array() //An empty arr
const eightemptyArray = Array(8);
console.log(arr); //[]
console.log(eightemptyArray); //[empty x 8]

//2. static values with fill()
const eightXvalues = Array(8).fill('X');
const four4values = Array(4).fill(4);
console.log(eightXvalues);
console.log(four4values);

//3. Concatenating array using concat()
const firstList = [1, 2, 3];
const secondList = [4, 5, 6];
const thirdList = firstList.concat(secondList);
console.log(thirdList);

//4. Array's length:
console.log(thirdList.length);

//5. Getting index an element in arr
//indexOf() check if an item exist in arr, else returns -1.
console.log(thirdList.indexOf(5)); //The 4th element -> 4.
console.log(thirdList.indexOf(1)); //the 1st element -> 0.
console.log(thirdList.indexOf(9)); //No item called '9' exists in the arr
//lastIndexOf() gives the position of the last item in arr. If exists, returns the index, else -1.
console.log(thirdList.lastIndexOf(6));
console.log(thirdList.lastIndexOf(5));
console.log(thirdList.lastIndexOf(1));
console.log(thirdList.lastIndexOf(9));

//6. includes()
console.log(webTech.includes('HTML'));
console.log(webTech.includes('Python'));

//7. Checking array using Array.isArray()
const number = 100;
console.log(Array.isArray(thirdList));
console.log(Array.isArray(webTech));
console.log(Array.isArray(number)); //not an array

//8. Array -> String
console.log(thirdList.toString());
console.log(String(thirdList));
console.log((topCompanies.split(',')).toString());

//9. Joining arr elements using join()
/*
    join() is used to join the elements of the arr, the argument we passed in the join method
    will be joined in the arr and return as a string. By default, it joins with a comma, 
    but we can pass different string parameter which can be joined between the items.
*/
console.log(webTech.join());
console.log(webTech.join(''));
console.log(webTech.join(' '));
console.log(webTech.join(', '));
console.log(webTech.join(' # '));

//10. Slice arr elements
console.log(webTech.slice()); //it copies all item
console.log(webTech.slice(0)); //it copies all item
console.log(webTech.slice(0, webTech.length)); //it copies all item
console.log(webTech.slice(1, 3)); //from the 2rd to the (3-1) rd element.

//11. Splice() takes three parameters:
//Starting position, number of times to be removed and number of items to be added.
const numbers2 = [1, 2, 3, 4, 5, 6, 7]
console.log(numbers2.splice()); //remove all item

console.log(numbers2.splice(6, 7)); //remove the last item
console.log('After removed last item:', numbers2);

console.log(numbers2.splice(3, 3, 7, 8, 9)); //splice(start, deleteCount, item1, item2, ...)
console.log('After replaced item:', numbers2);

//12. Adding item to an arr using push, remove using pop
numbers2.push(8); //add 8
console.log(numbers2);
numbers2.pop(8); //remove 8
console.log(numbers2);

//Bonus
//shift() and unshift()
numbers2.shift(); //remove 1 item from the beginning (remove '1' in this example)
console.log('Remove the first ele:', numbers2);
numbers2.unshift(1); //add 1 item from the beginning (add '1' back)
console.log("Add '1' back to the ele:", numbers2);

//13. sort()
numbers2.sort();
console.log("Sort:", numbers2);
//14. reverse()
numbers2.reverse(); //add 1 item from the beginning (add '1' back)
console.log("Reverse:", numbers2);

//Array of Array
const frontEnd = ['HTML', 'CSS', 'JS', 'React', 'Redux'];
const backEnd = ['Node', 'Express', 'MongoDB'];
const fullStack = [frontEnd, backEnd];

console.log(fullStack);
console.log(fullStack.length);
console.log(fullStack[0]);
console.log(fullStack[1]);