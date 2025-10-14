//Spread or Rest Operator
//When we destructure an arr, we use the spread operator(...) to get the rest elements as arr
//In addition, spread operator is used to spread arr elements to another arr.

//Spread Operator to get the rest of array elements
const nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
let [num1, num2, num3, ...rest] = nums;
console.log(num1, num2, num3); //1 2 3
console.log(rest); //[4, 5, 6, 7, 8, 9, 10]

const fullStack = [
    'HTML', 'CSS', 'JS', 'React',
    'Node', 'Express', 'MongoDB'
]
let [html, css, , react, ...backend] = fullStack;
console.log(html, css, react);
console.log(backend);

//Spread operator to copy arr
const evens = [2, 4, 6, 8, 10];
const evenNumbers = [...evens];
const odds = [1, 3, 5, 7, 9];
const oddNumbers = [...odds];

const wholeNumbers = [...evens, ...odds];

console.log(evenNumbers);
console.log(oddNumbers);
console.log(wholeNumbers);

//Spread operator to copy object
const user = {
    name: 'DatIT',
    title: 'Programmer',
    country: 'VietNam',
    city: 'HCM'
}
const copiedUser = {...user};
console.log(copiedUser);

//Modifying or changing the object while copying
const copiedUser2 = {...user, title:'Junior of Developer'}
console.log(copiedUser2);

//Spread operator with arrow function
const sumAllNums = (...args) => {
    let sum = 0;
    for(const num of args) {
        sum += num;
    }
    return sum;
}
console.log(sumAllNums(1, 2, 3, 4, 5)); //15

