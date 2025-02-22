console.log("Level 1 ----------------------------");
const itCompanies = ['FaceBook', 'Google', 'Microsoft', 'Apple',
        'IBM', 'Oracle', 'Amazon'
];
console.log(itCompanies);
console.log(itCompanies.length);
console.log(itCompanies[0], 
    itCompanies[(itCompanies.length-1)/2],
    itCompanies[itCompanies.length-1]
);
console.log(itCompanies.toString().toUpperCase());

let isExist = 'Amazon';
if(itCompanies.includes(isExist) === true){
    console.log(isExist);
} else {
    console.log('Not found!');
}

const result = [];
for(let i = 0; i < itCompanies.length; i++){
    //`match(/o/g)` find all occurrences of `o`
    //`|| []` ensures it doesn't break if no `o` is found.
    if((itCompanies[i].match(/o/g) || []).length > 1) {
        result.push(itCompanies[i]); // Adds the element to result if condition is met
    }
}
console.log(result);

const sortCompaList = itCompanies.sort();
console.log(sortCompaList);
console.log(sortCompaList.reverse());

const itCompanies1 = ['FaceBook', 'Google', 'Microsoft', 'Apple',
    'IBM', 'Oracle', 'Amazon'
];
console.log(itCompanies1.slice(3));
const itCompanies2 = ['FaceBook', 'Google', 'Microsoft', 'Apple',
    'IBM', 'Oracle', 'Amazon'
];
console.log(itCompanies2.slice(0, itCompanies2.length-3));

console.log(itCompanies1.slice(3));
const itCompanies3 = ['FaceBook', 'Google', 'Microsoft', 'Apple',
    'IBM', 'Oracle', 'Amazon'
];
console.log(itCompanies3.slice(1, itCompanies2.length-1));