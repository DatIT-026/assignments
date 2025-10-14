for(let i = 0; i <= 10; i++) {
    console.log(i);
}

console.log("-------------------------");

let output = "";
for(let i = 0; i <= 10; i++) {
    output += i + " ";
}
console.log(output);

console.log("-------------------------");

let sum = 0;
for(let i = 0; i <= 10; i++) {
    sum += i;
}
console.log(sum);

console.log("-------------------------");

let number = [1, 2, 3, 4, 5];
let newArray = [];
for(let i = 0; i < number.length; i++) {
    newArray.push(number[i] ** 2);
}
console.log(newArray);

console.log("-------------------------");

const countries = ['VietNam', 'ThaiLand', 'Japan', 'Cuba', 'USA'];
const newArr = [];
const newArrWithoutAmericas = [];

for(let i = 0; i < countries.length; i++) {
    newArr.push(countries[i]);
}
console.log(newArr);
console.log(newArr.slice(0, 3));

console.log("-------------------------");

let j = 0;
while (j <= 10) {
    console.log(j);
    j++;
    if(j == 6) {
        break;
    }
}

console.log("-------------------------");

let j2 = 0;
for(let i = 0; i <= 10; i++) {
    if(i % 2 == 0) {
        continue;
    }
    console.log(i);
}

console.log("-------------------------");

let k = 5;
do {
    console.log(k)
    k--;
} while (k >= 0);

console.log("for of Loop ------------------");

const Mainum = [1, 2, 3, 4, 5];
for(const num of Mainum) {
    console.log(num);
}
console.log("------------------");
for(const num of Mainum) {
    console.log(num ** 2);
}
const webTechs = [
    'HTML',
    'CSS',
    'JavaScript',
    'React',
    'Redux',
    'Node',
    'MongoDB',
]
for(const tech of webTechs) {
    console.log(tech.toLowerCase());
}

console.log("------------------");

for(const tech of webTechs) {
    console.log(tech[0] + tech[1] + tech[2]);
}