//Destructuring is a way to unpack arrays, and object and assigning to a distinct variable
const numbers = [1, 2, 3];
let [numOne, numTwo, numThree] = numbers;
console.log(numOne, numTwo, numThree); //1 2 3

const numbers2 = [1, 2, 3];
let [numOne1, , numThree3] = numbers2; //2 is omitted
console.log(numOne1, numThree3); //1 3

const fullStack = [
    ['HTML', 'CSS', 'JS', 'React'],
    ['Node', 'Express', 'MongoDB']
]
const [frontEnd, backEnd] = fullStack;

console.log(frontEnd);
console.log(backEnd);

//We can use default value in case the value of array for that index is undefined
const names = [undefined, 'Brook', 'David']
let [
    firstPerson = 'Dat',
    secondPerson,
    thirdPerson,
    fourthPerson = 'IT'
] = names
console.log(firstPerson, fourthPerson, secondPerson, thirdPerson);

//Cannot assign var to all elements in arr. We can destucture few of the first & we can
//get the remaining as arr using spread operator(...).
const nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
let [num1, num2, num3, ...rest] = nums;
console.log(num1, num2, num3);
console.log(rest);

//Destructuring during iteration
const countries = [['Finland', 'Helsinki'], ['Sweden', 'Stockholm'], ['Norway', 'Oslo']]
for (const [country, city] of countries) {
    console.log(country, city);
}

for (const [first, second, third] of fullStack) {
    console.log(first, second, third);
}

//Destructuring Object
const rectangle = {
    width: 20,
    height: 10,
    area: 200
}
let { width, height, area, perimeter } = rectangle

console.log(width, height, area, perimeter);

let { width: w, height: h, area: a, perimeter: p } = rectangle;

console.log(w, h, a, p);

//If the key is not found in the obj, the var will be assigned to undefined
//Sometimes the key might not in the object, in taht case we can give a default value during declaration
const rectangle3 = {
    width3: 20,
    height3: 10,
    area3: 200
}
let { width3, height3, area3, perimeter3 = 60 } = rectangle3;
console.log(width3, height3, area3, perimeter3); //20 10 200 60

//or
const rectangle4 = {
    width4: 20, height4: 10, area4: 200, perimeter4: 80
}
let { width4, height4, area4, perimeter4 = 40 } = rectangle4;
console.log(width4, height4, area4, perimeter4);

//Object parameter without desturcturing
//Without destructuring
const rect = { width: 20, height: 10 };

const calPerimeter = rectan2gle => {
    return 2 * (rectan2gle.width + rectan2gle.height)
}
console.log(calPerimeter(rect)); //60

const person = {
    firstName: 'Kiyoshi',
    lastName: 'Datto',
    age: 18,
    country: 'Vietnam',
    job: 'Junior of Developer',
    skills: [
        'HTML', 'CSS', 'JS',
        'C/C++', 'Golang', 'Rust'
    ],
    languages: ['Vietnamese', 'English', 'Japanese']
}

const getPersonInfo = obj => {
    const skills = obj.skills;
    const formattedSkills = skills.slice(0, -1).join(', ');
    const languages = obj.languages;
    const formattedLanguages = languages.slice(0, -1).join(', ');

    personInfo = `${obj.firstName} ${obj.lastName} lives in ${obj.country}.
He is ${obj.age} years old. 
He is a/an ${obj.job}.
He has various skills such as ${formattedSkills} and ${skills[skills.length - 1]}. 
He speaks ${formattedLanguages} and a little bit of ${languages[2]}.`

    return personInfo;
}

console.log(getPersonInfo(person));

//Object parameter with destructuring
const calPerimeter2 = ({ width, height }) => {
    return 2 * (width + height);
}
console.log(calPerimeter2(rect)); //60

const getPersonInfo2 = ({firstName, lastName, age, country, job, skills, languages }) => {
    const formattedSkills = skills.slice(0, -1).join(', ');
    const formattedLanguages = languages.slice(0, -1).join(', ');

    personInfo2 = `${firstName} ${lastName} lives in ${country}.
He is ${age} years old. 
He is a/an ${job}.
He has various skills such as ${formattedSkills} and ${skills[skills.length - 1]}. 
He speaks ${formattedLanguages} and a little bit of ${languages[2]}.`
    return personInfo2;
}
console.log(getPersonInfo2(person));

//Destructuring object during iteration
const todoList = [
    {
        task: 'Prepare JS Lesson',
        time: '02/07/2025 21:00',
        completed: true
    },
    {
        task: 'Learning JS',
        time: '02/07/2025 22:00',
        completed: true 
    },
    {
        task: 'Revision the JS lesson',
        time: '02/08/2025 20:00',
        completed: false
    }
]
for (const {task, time, completed} of todoList) {
    console.log(task, time, completed);
}