console.log("Hello, World!"); // Hello, World!
console.log("%d %s of JavaScript", 30, "Days");
console.log("%c30 Day%c %cof%c %cJavaScript",
    "color: green", "", "color: red", "", "color: yellow")

console.log("-------------------");

console.warn("This is a warning");
console.warn("Warning is defferent from error")

console.error("This is an error message");
console.error("Learning JavaScript is your mistakes");

console.log("-------------------");

const names = ["DatIT", "Fumo", "Khoa", "Long", "Huy"];
console.table(names);
const user = { name: "DatIT", title: "Programmer", country: "VietNam",
    city: "HCM", age: 21 }
console.table(user);
const countries = [
    ["Vietnam", "Vietnamese"],
    ['Thailand', 'Thai'],
    ['Japan', 'Japanese'],
]
console.table(countries);
const users = [
    {
      name: 'DatIT',
      title: 'Programmer',
      country: 'Vietnam',
      city: 'HCM',
      age: 21
    },
    {
      name: 'Eyob',
      title: 'Teacher',
      country: 'Sweden',
      city: 'London',
      age: 25
    },
    {
      name: 'Asab',
      title: 'Instructor',
      country: 'Norway',
      city: 'Oslo',
      age: 22
    },
    {
      name: 'Matias',
      title: 'Developer',
      country: 'Denmark',
      city: 'Copenhagen',
      age: 28
    }
  ]
  console.table(users)

console.log("-------------------");

console.time("Regular for loop");
for(let i = 0; i < countries.length; i++) {
    console.log(countries[i][0], countries[i][1]);
} console.timeEnd("Regular for loop");

console.time('for of loop')
for (const [name, city] of countries) {
  console.log(name, city);
} console.timeEnd('for of loop');

console.time('forEach loop')
countries.forEach(([name, city]) => {
  console.log(name, city)
})
console.timeEnd('forEach loop');

console.log("-------------------");

console.info("UrGift is available now. Get it on Google Play!");

console.log("-------------------");

console.assert(4 > 3, '4 is greater than 3'); // No result
console.assert(3 > 4, '3 is not greater than 4') // Assertion failed: 3 is not greater than 4
for(let i = 1; i <= 5; i++) {
    let errorMessage = `${i} is not even`;
    console.log(`the ${i} is an even number.`);
    console.assert(i % 2 === 0, { number: i, errorMessage: errorMessage });
}

console.log("-------------------");

console.group("Names");
console.log(names);
console.groupEnd();

console.group('Countries');
console.log(countries);
console.groupEnd();

console.group('Users');
console.log(user);
console.log(users);
console.groupEnd();

console.log("-------------------");

const func = () => {
    console.count("Function has been called");
} 
func(); func(); func();

console.log("-------------------");

console.log("console.%cclear()%c; %c// Cleans the browser console", 
    "color: #76a3e9", "", "color: green; font-style: italic");