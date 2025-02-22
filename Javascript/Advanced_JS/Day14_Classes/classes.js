// JS is an OOP language.

// Class instantiation
/* Instantiation class means creating an obj from a class.
We need the keyword *new* and we call the name of the class after the word *new* */

class Person {
    // Code goes here
}
const person = new Person();
console.log(person); // Person {}

// Class Constructor
class Person2 {
    constructor(firstName, lastName) {
        console.log(this); // Check the output from here
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
const person2 = new Person2();
console.log(person2); // Person2 {firstName: undefined, lastName: undefined}
const person3 = new Person2('Datto', 'Kiyoshi'); 
console.log(person3); // Person2 {firstName: 'Datto', lastName: 'Kiyoshi'}

// Default values with constructor
class Person3 {
    constructor(
        firstName = "Datto",
        lastName = "Kiyoshi",
        age = 21,
        country = 'Vietnam',
        city = 'HCM'
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
    }
}
const person4 = new Person3(); // it will take the default values
const person5 = new Person3('Long', 'Nguyen', '19', 'Britain', 'London');
console.log(person4); // Person3 {firstName: 'Datto', lastName: 'Kiyoshi', age: 21, country: 'Vietnam', city: 'HCM'}
console.log(person5); // Person3 {firstName: 'Long', lastName: 'Nguyen', age: '19', country: 'Britain', city: 'British'}

// Class methods
class Person4 {
    constructor(firstName, lastName, age, country, city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
    }
    getFullName() {
        const fullName = this.firstName + ' ' + this.lastName;
        return fullName;
    }
}
const person6 = new Person4('Long', 'Nguyen', '19', 'Britain', 'London');
console.log(person6.getFullName()); // Long Nguyen

// Properties with initial value
// When creating a class for some properties, we may have an initial value.
// For instance, if u are playing a game, u staring score will be 0. So, we may have a starting score or score which is 0.
// In other way, we may have an initial skill and we will acquire some skill after some time.
class Person5 {
    constructor(firstName, lastName, age, country, city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
        this.score = 0;
        this.skills = [];
    }
    getFullName() {
        const fullName = this.firstName + ' ' + this.lastName;
        return fullName;
    }
}
const person7 = new Person5("Datto", "Kiyoshi", 21, "Vietnam", "HCM");
console.log(person7.score); // 0
console.log(person7.skills); // []

// getter & setter
// getter allow us to access value from the obj
class Person5_1 {
    constructor(firstName, lastName, age, country, city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
        this.score = 0;
        this.skills = [];
    }
    getFullName() {
        const fullName = this.firstName + ' ' + this.lastName;
        return fullName;
    }
    get getScore() {
        return this.score;
    }
    get getSkills() {
        return this.skills;
    }
}
const person7_1 = new Person5_1("Datto", "Kiyoshi", 21, "Vietnam", "HCM");
// We do not need parenthesis to call a getter method
console.log(person7_1.getScore); // 0
console.log(person7_1.getSkills); // []

// setter allow us to modify the value of certain properties.
class Person5_2 {
    constructor(firstName, lastName, age, country, city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
        this.score = 0;
        this.skills = [];
    }
    getFullName() {
        const fullName = this.firstName + ' ' + this.lastName;
        return fullName;
    }
    get getScore() {
        return this.score;
    }
    get getSkills() {
        return this.skills;
    }
    set setScore(score) {
        this.score += score;
    }
    set setSkill(skill) {
        this.skills.push(skill);
    }
}
const person7_2 = new Person5_2("Datto", "Kiyoshi", 21, "Vietnam", "HCM");
// We do not need parenthesis to call a getter method
person7_2.setScore = 1;
person7_2.setSkill = "HTML";
person7_2.setSkill = "CSS";
person7_2.setSkill = "JS";

console.log(person7_2.score); // 1
console.log(person7_2.skills); //  ['HTML', 'CSS', 'JS']

// Do not be puzzled by the difference between regular method and a getter.
// Now, let add regular method called getPersonInfo in the Person class
class Person6 {
    constructor(firstName, lastName, age, country, city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
        this.score = 0;
        this.skills = [];
    }

    get getScore() { 
        return this.score; 
    }
    get getSkills() { 
        return this.skills; 
    }
    set setScore(score) { 
        this.score += score;
    }
    set setSkill(skill) { 
        this.skills.push(skill);
    }

    getFullName() {
        const fullName = this.firstName + ' ' + this.lastName;
        return fullName;
    }

    getPersonInfo() {
        let fullName = this.getFullName();

        let skills = 
        this.skills.length > 0 && this.skills.slice(0, this.skills.length - 1).join(', ') +
        ` and ${this.skills[this.skills.length - 1]}`

        let formattedSkills = skills ? `He knows ${skills}` : ''
        
        let info = `${fullName} is ${this.age}. He lives ${this.city}, ${this.country}. ${formattedSkills}.`
        return info;
    }
}

const person8 = new Person6("Datto", "Kiyoshi", 21, "Vietnam", "HCM");
person8.setScore = 1;
person8.setSkill = "HTML";
person8.setSkill = "CSS";
person8.setSkill = "JS";

console.log(person8.getScore);
console.log(person8.getSKills);
console.log(person8.getPersonInfo());