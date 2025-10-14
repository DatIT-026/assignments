//Set is a collection of elements and can only contains UNIQUE elements

//Create an empty set
const companies = new Set();
console.log(companies); //Set(0) {}

//Create set from array
const numbers = [1, 2, 3, 4, 5];
const setOfNums = new Set(numbers);
console.log(setOfNums); //Set(5) {1, 2, 3, 4, 5}

//Adding & Deleting an element to a set
setOfNums.add(6);
console.log(setOfNums); //Set(6) {1, 2, 3, 4, 5, 6}

setOfNums.add(7);
setOfNums.add(8);
console.log(setOfNums); //Set(8) {1, 2, 3, 4, 5, 6, 7, 8}

setOfNums.add(9);
console.log(setOfNums.delete(8)); //Set(8) {1, 2, 3, 4, 5, 6, 7, 9}

//Checking an element in the set
console.log(setOfNums.has(6)); //true
console.log(setOfNums.has(8)); //false

//Clearing the set
setOfNums.clear();
console.log(setOfNums);

//Example
const languages = [
    'English', 'Vietnamese', 'French', 'English', 'Japanese', 'English'
]
const langSet = new Set(languages);
console.log(langSet); //Set(4) {'English', 'Vietnamese', 'French', 'Japan'}

const counts = []

// l nhận từng giá trị trong langSet
for(const l of langSet) {
    //(lng) => lng === l is a callback func được truyền vào filter()
    //filter() duyệt qua từng phần tử mảng languages
    //Với mỗi lng, callback func sẽ ktra có = l không?  
    //True? -> giữ lại phần tử đó, False? -> Loại bỏ
    const filteredLang = languages.filter((lng) => lng === l);
    console.log(filteredLang); //['English', 'English', 'English']
    counts[l] = filteredLang.length; //Gán vào Object counts
}
console.log(counts);

//Union of Sets (Hợp của 2 tập hợp hay A ∪ B)
let a = [1, 2, 3, 4, 5];
let b = [3, 4, 5, 6];
let c = [...a, ...b]; //một mảng gồm tất cả các phần tử của mảng a và b

let A = new Set(a)
let B = new Set(b)
let C = new Set(c)

console.log(C); //Set(6) {1, 2, 3, 4, 5, 6}

//Intersection of Sets (A ∩ B)
let a1 = [1, 2, 3, 4, 5];
let b1 = [3, 4, 5, 6];

let A1 = new Set(a1);
let B1 = new Set(b1);

let c1 = a1.filter((num) => B1.has(num));
let C1 = new Set(c1);

console.log(C1); //Set(3) {3, 4, 5}

//Difference of Sets (A \ B)
let a2 = [1, 2, 3, 4, 5];
let b2 = [3, 4, 5, 6];

let A2 = new Set(a2);
let B2 = new Set(b2);

let c2 = a2.filter((num) => !B2.has(num));
let C2 = new Set(c2);

console.log(C2); //Set(2) {1, 2}