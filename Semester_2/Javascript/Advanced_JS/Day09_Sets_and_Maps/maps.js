//Creating a Map from array
langOfCountries = [
    ['England', 'English'],
    ['VietNam', 'Vietnamese'],
    ['Japan', 'Japanese']
]
const map1 = new Map(langOfCountries);
console.log(map1);

//Adding values to the Map
const Value = new Map();
console.log(Value.size); //0

Value.set('China', 'Chinese');
Value.set('Europe', 'European');
console.log(Value);
console.log(Value.size); //2

//Getting a value from Map
console.log(Value.get('China'));
console.log(map1.get("VietNam"));

//Checking key in Map
console.log(map1.has("VietNam")); //true
console.log(Value.has("England")); //false

//Getting All values from map using loop
for(const country of map1) {
    console.log(country);
}