localStorage.setItem('firstName', 'Datto'); // since the value is string we do not stringify it
console.log(localStorage); // Storage {firstName: 'Datto', length: 1}

localStorage.setItem('age', 21)
console.log(localStorage); // Storage {firstName: 'Datto', age: '21', length: 2}

const skills = ['HTML', 'CSS', 'JS', 'React'];
// Skills array jas to be stringified first to keep the format.
const skillsJSON = JSON.stringify(skills, undefined, 4);
localStorage.setItem('skills', skillsJSON);
console.log(" ", localStorage);

let skills2 = [
    { tech: 'HTML', level: 10 },
    { tech: 'CSS', level: 9 },
    { tech: 'JS', level: 8 },
    { tech: 'React', level: 9 },
    { tech: 'Redux', level: 10 },
    { tech: 'Node', level: 8 },
    { tech: 'MongoDB', level: 8 }
]
let skillJSON = JSON.stringify(skills2);
localStorage.setItem('skills', skillJSON);
console.log(localStorage);

const user = {
    firstName: 'Datto',
    age: 21,
    skills: ['HTML', 'CSS', 'JS', 'React']
}

const userText = JSON.stringify(user, undefined, 4);
localStorage.setItem('user', userText);
