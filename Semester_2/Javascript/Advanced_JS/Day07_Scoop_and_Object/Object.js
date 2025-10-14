const rectangle = {
    length: 20,
    width: 20
}
console.log(rectangle);

console.log("=========================");

//Create an objecting with value
const person = {
    firstName: "Dat",
    lastName: "Tien",
    age: 18,
    country: 'VietNam',
    city: 'HCM',
    skills: [
        'HTML', 'CSS', 'JavaScript',
        'React', 'Node', 'Python'
    ],
    isMarried: false,
    getFullName: function() {
        return `${this.firstName} ${this.lastName}`;
    },
    location: function() {
        return `${this.country}, ${this.city}`;
    },
    'phone number': '+84948147172'
}
console.log(person);

//Getting values from an object
//Accessing value using .
console.log("Name:", person.getFullName()); //getFullName defined as a method
console.log("Age:", person.age);
console.log("Location:", person.location()); //Location defined as a method
//Accessing valye using []
console.log("Skills:", person["skills"]);
console.log("Phone Number:", person["phone number"]); //Phone number only uses []

console.log("=========================");

//Setting new key for an object
const person1 = {
    firstName: "Dat",
    lastName: "Ha Nguyen Tien",
    age: 30,
    country: 'USA',
    city: 'New York',
    skills: [
        'HTML', 'CSS', 'JavaScript',
        'React', 'Node', 'Python', '.NET', 'Java', 'Linux'
    ],
    isMarried: false,
    getFullName: function() {
        return `${this.lastName} ${this.firstName}`;
    },
    location: function() {
        return `${this.country}, ${this.city}`;
    },
    'phone number': 'None'
}

person1.nationality = "Vietnamese";
person1.country = "Vietnam";
person1.title = "Senior of Software Engineering";
person1.isMarried = "true";
person1.getPersonInfo = function() {
    let skillsWithoutLastSkill = this.skills
        .splice(0, this.skills.length - 1)
        .join(', ')
    let lastSkill = this.skills.splice(this.skills.length - 1)[0];
    let skills = `${skillsWithoutLastSkill}, and ${lastSkill}`;

    let fullName = this.getFullName();

    let statement = `${fullName} is a ${this.title}. \nHe lives in ${this.country}.
                    \nHis skills include: ${skills}.`;
    return statement;
}
console.log(person1);
console.log(person1.getPersonInfo());

console.log("=========================");
//Object Methods
//Object.assign
const person2 = {
    firstName: "Datto",
    lastName: "Kiyoshi",
    age: 21,
    country: 'Japan',
    city: 'HCM',
    Health: {
        height: 171, //centimeters
        weight: 55, //kilograms
        IQ: 'undefined'
    },
    skills: [
        'HTML', 'CSS', 'JS',
    ],
    isMarried: false,
    getFullName: function() {
        return `${this.lastName} ${this.firstName}`;
    },
    location: function() {
        return `${this.country}, ${this.city}`;
    },
    'phone number': 'None',

    getPersonInfo: function() {
        return `I am ${this.getFullName()} and I live in ${this.location()}
                I am ${this.age} years old.`
    }
}
console.log(person2);
//Object methods: Object.assign, Object.keys, Object.values, Object.entries, hasOwnProperty

//Object.assign({}, ...): To copy an object without modifying the original object
const copyPerson2 = Object.assign({}, person2);
console.log(copyPerson2);

//Object.keys(): To get the keys or properties of an object as an array
const keys = Object.keys(copyPerson2);
console.log(keys);
const Health = Object.keys(copyPerson2.Health);
console.log(Health);

//Object.values(): To get values of an object as an array
const values = Object.values(copyPerson2);
console.log(values);

//Object.entries(): To get the keys & values in an array
const entries = Object.entries(copyPerson2);
console.log(entries);

//hasOwnProperty: To check if a specific key yor preperty exist in an object
console.log(copyPerson2.hasOwnProperty('name')); //false
console.log(copyPerson2.hasOwnProperty('location')); //true