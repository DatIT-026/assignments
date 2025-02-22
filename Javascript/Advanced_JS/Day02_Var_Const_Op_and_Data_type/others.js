//Window confirm() method
const agree = confirm('Are you sure you like to delete?');
console.log(agree);

//Date and Time
/*
    getFullYear(), getMonth(), getDate(), getDay(), getHours(),
    getMinutes, getSeconds(), getMilliseconds(), getTime()
*/
const now = new Date();
console.log(now);
console.log(now.getFullYear());
console.log(now.getMonth() + 1); //month is January (0) to December (11) so I have to (+1) 
console.log(now.getDate());
console.log(now.getDay()); //Sunday (0), Monday (1) to Saturday (6)