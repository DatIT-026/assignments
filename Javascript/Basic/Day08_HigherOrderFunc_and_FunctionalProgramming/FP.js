//forEach
let sum = 0;
const numbers = [1, 2, 3, 4, 5];
numbers.forEach(num => console.log(num));
numbers.forEach(num => sum += num);
console.log("SumOfArray =", sum);

console.log("=========================");
//map
const numbers_Square = numbers.map((num) => num * num);
console.log(numbers_Square);

const names = ['KhoaLM', 'HoangHH', 'DatIT', 'BaoTQ23'];

const namesToLowerCase = names.map((name) => name.toLowerCase());
console.log(namesToLowerCase);

console.log("=========================");
//filter
const countries = ['Albania', 'Bolivia', 'Canada', 'Denmark', 
                   'Ethiopia','Finland', 'Germany','Hungary',
                   'Ireland', 'Japan', 'Kenya'];

const countriesEndsby_ia = countries.filter(
    (country) => country.endsWith('ia'));
console.log(countriesEndsby_ia);

const countriesEndsby_land = countries.filter(
    (country) => country.endsWith('land'));
console.log(countriesEndsby_land);

const countriesHave5Letter = countries.filter(
    (country) => country.length === 5);
console.log(countriesHave5Letter);

console.log("=========================");
//reduce
const SumOfArray = numbers.reduce((acc, cur) => acc + cur, 0);
console.log(SumOfArray); //15

console.log("=========================");
//every
const areAllStr = names.every((name) => typeof name === "string");
console.log(areAllStr); //true
const allEven = numbers.every(num => num % 2 === 0);
console.log(allEven); //false (vì 1, 3, 5 không phải số chẵn)

console.log("=========================");
//find & findIndex
const ages = [22, 24, 32, 25, 35, 18, 29, 65];
const age = ages.find((age) => age < 20);
console.log(age);

const shortName = names.findIndex((name) => name.length < 6);
console.log(shortName); //2

console.log("=========================");
//some
const areAllStr2 = names.some((name) => typeof name === 'number');
const hasEven = numbers.some(num => num % 2 === 0);
console.log(areAllStr2); //false
console.log(hasEven); //true (vì có một phần tử (2) thoả điều kiện)

console.log("=========================");
//sort
const normalSort = names.sort((a, b) => a.localeCompare(b, "en")); //`en` means English language
console.log(normalSort); //Sorting string values
const reverseSort = names.sort((a, b) => b.localeCompare(a));
console.log(reverseSort);
console.log(ages.sort()); //Sorting numeric values

//Sorting Object Array
const people = [
    { name: "Dat", age: 18 },
    { name: "Alice", age: 20 },
    { name: "Bob", age: 17 }
];

people.sort((a, b) => b.age - a.age); //Giảm dần theo độ tuổi
console.log(people);

//Tăng dần theo tên
//Shallow Copy [...people] giống việc tạo 1 hộp đồ chơi mới, hộp cũ 
//không bị ảnh hưởng trong trường hợp dữ liệu không lồng nhau.
const reverseName1 = [...people].sort((a, b) => a.name.localeCompare(b.name));
console.log(reverseName1);
const reverseName2 = [...people].sort((a, b) => b.name.localeCompare(a.name));
console.log(reverseName2);