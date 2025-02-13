//Simple Array
let nums = [1, 2, 3]
nums[0] = 10

console.log(nums);

//These 2 var cannot strictly equal
let userOne = {
    name: "DatIT",
    age: "18",
    country: "VietNam"
}
let userTwo = {
    name: "DatIT",
    age: "18",
    country: "VietNam"
}
console.log(userOne == userTwo); //false

//This can be
let userOne1 = {
    name: "DatIT",
    age: "18",
    country: "VietNam"
}
let userTwo2 = userOne1;
console.log(userOne1 == userTwo2) //True