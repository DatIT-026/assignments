console.log("Level 1 ----------------------------");
let age = 18;
if (age >= 18 && age <= 100) {
    console.log("You are old enough to drive");
} else if (age >= 1 && age <= 17) {
    console.log("You are left with", 18 - age, "year");
} else {
    console.log("Invalid Input!");
}

console.log("Level 2 ----------------------------");
let score = 90;
switch (true) {
    case score <= 100 && score >= 80:
        console.log("A");
        break;
    case score <= 89 && score >= 70:
        console.log("B");
        break;
    case score <= 69 && score >= 60:
        console.log("C");
        break;
    case score <= 59 && score >= 50:
        console.log("D");
        break;
    case score <= 49 && score >= 0:
        console.log("F");
        break;
    default:
        console.log("Invalid Input!");
}
// -----------------------------------------
let day = "sUNdAY";
day = day.toLowerCase();

function dayModi(str) {
    if (!str) return ""; //Chắc chắn str không phải là null, hay undefined!
    return str[0].toUpperCase() + str.slice(1);
    //str.slice(1): lấy từ phần tử str[1] đến str[n-1]
}
switch (true) {
    case day === 'monday' || day === 'tuesday' || day === 'wednesday' || day === 'thursday' || day === 'friday':
        console.log(dayModi(day), "is a working day!");
        break;
    case day === 'saturday' || day === 'sunday':
        console.log(dayModi(day), "is a weekend.");
        break;
    default:
        console.log("Invalid Input!");
}
console.log("Level 3 ----------------------------");

let time = prompt("Enter Month and Year with format MM/YYYY (Example: 01/2025): ");
let month = String(time.substr(0, 2));
let year = Number(time.slice(3));
function monthModi(str) {
    const months = {
        '01': "January",
        '02': "February",
        '03': "March",
        '04': "April",
        '05': "May",
        '06': "June",
        '07': "July",
        '08': "August",
        '09': "September",
        '10': "October",
        '11': "November",
        '12': "December"
    };
    return months[str] || "Invalid Input!";
}

// Check if year is a valid number
if(!isNaN(year)) {
    switch (true) {
        case ['01', '03', '05', '07', '08', '10', '12'].includes(month):
            console.log(`${monthModi(month)}, ${year} "has 31 days.`);
            break;
        case ['04', '06', '09', '11'].includes(month):
            console.log(`${monthModi(month)}, ${year} "has 30 days.`);
            break;
        case month === '02' && ((year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0)):
            console.log(`${monthModi(month)}, ${year} "has 29 days.`);
            break;
        case month === '02':
            console.log(`${monthModi(month)}, ${year} "has 28 days.`);
            break;
        default:
            console.log("Invalid Input!");
    }
} else {
    console.log("Invalid Input!");
}