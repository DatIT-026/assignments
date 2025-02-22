// Static method
// the static keyword defines a static method for a class.
// Static methods are not called on instances of the class. Instead, they're called on the class itself.
// These are often utility functions, such as functions to create or clone obj.
// An example of static method is Date.now(). The now method is called directly from the class.

class Person {
    constructor(firstName, lastName, age, country, city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
        this.score = 0;
        this.skills = [];
    }
    get getScore() { return this.score; }
    get getSkills() { return this.skills; }
    set setScore(score) { this.score += score; }
    set setSkills(skill) { this.skills.push(skill); }

    getFullName() {
        const fullName = this.firstName + " " + this.lastName;
        return fullName;
    }

    getPersonInfo() {
        let fullName = this.getFullName();
        let skills = this.skills.length > 0 && this.skills.slice(0, this.skills.length - 1).join(', ') +
        ` and ${this.skills[this.skills.length - 1]}`

        let formattedSkills = skills ? `He knows ${skills}` : ''

        let info = `${fullName} is ${this.age}. He lives ${this.city}, ${this.country}. ${formattedSkills}`
        return info;
    }

    static favoriteSkill() {
        const skills = ['HTML', 'CSS', 'JS', 'React', 'Java', 'Node'];
        const index = Math.floor(Math.random() * skills.length)
        return skills[index];
    }

    static showDateTime() {
        let now = new Date();
        let year = now.getFullYear();
        let month = now.getMonth() + 1;
        let date = now.getDate();
        let hours = now.getHours();
        let minutes = now.getMinutes();

        if(hours < 10) hours = '0' + hours;
        if(minutes < 10) minutes = '0' + minutes;

        let DDMMYYYY = date + '.' + month + '.' + year;
        let time = hours + ':' + minutes;
        let fullTime = DDMMYYYY + ' ' + time;

        return fullTime;
    }
}

console.log(Person.favoriteSkill()); // HTML
console.log(Person.showDateTime()); // 18.2.2025 22:20

// Inheritance
class Student extends Person {
    saySomething() {
        console.log('I am a child of the person class');
    }
}

const s1 = new Student('Khoa', 'Ly', 'Vietnam', 12, 'HCM');
console.log(s1); // Student {firstName: 'Khoa', lastName: 'Ly', age: 'Vietnam', country: 12, city: 'HCM', …}
console.log(s1.saySomething()); // I am a child of the person class
console.log(s1.getFullName()); // Khoa Ly
console.log(s1.getPersonInfo()); // Khoa Ly is Vietnam. He lives HCM, 12. 

// Overriding methods

class Student2 extends Person {
    constructor(firstName, lastName, age, country, city, gender) {
        super(firstName, lastName, age, country, city);
        this.gender = gender;
    }
    saySomething() {
        console.log('I am a child of the person class.');
    }
    getPersonInfo() {
        let fullName = this.getFullName();
        let skills =
            this.skills.length > 0 && this.skills.slice(0, this.skills.length-1).join(', ') +
            ` and ${this.skills[this.skills.length-1]}`
        
        let pronoun = this.gender == "Male" ? 'He' : 'She'
        let formattedSkills = skills ? `${pronoun} knows ${skills}` : '';

        let info = `${fullName} is ${this.age}. ${pronoun} lives in ${this.city}, ${this.country}. ${formattedSkills}`
        return info;
    }
}

const s2 = new Student2('Khoa', 'Ly', 'Vietnam', 12, 'HCM', 'Male');
const s2_1 = new Student2('Lidiya', 'Tekle', 28, 'Finland', 'Helsinki', 'Female');

s2.setScore = 1;
s2.setSkills = 'HTML';
s2.setSkills = 'CSS';
s2.setSkills = "JS";

s2_1.setScore = 1;
s2_1.setSkills = 'Planning';
s2_1.setSkills = "Managing";
s2_1.setSkills = "Organizing";

console.log(s2); // Student2 {firstName: 'Khoa', lastName: 'Ly', age: 'Vietnam', country: 12, city: 'HCM', …}

console.log(s2.saySomething()); // I am a child of the person class.
console.log(s2.getFullName()); // Khoa Ly
console.log(s2.getPersonInfo()); // Khoa Ly is Vietnam. He lives in HCM, 12. He knows HTML, CSS and JS

console.log(s2_1.saySomething()); // I am a child of the person class.
console.log(s2_1.getFullName()); // Lidiya Tekle
console.log(s2_1.getPersonInfo()); // Lidiya Tekle is 28. She lives in Helsinki, Finland. She knows Planning, Managing and Organizing