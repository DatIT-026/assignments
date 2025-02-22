// Callback
const doSomething = (callback) => {
    setTimeout(() => {
        const skills = ['HTML', 'CSS', 'JS'];
        callback('It did not go well', skills);
    }, 2000);
}

const callback = (err, result) => {
    if (err) return console.log(err); // It did not go well
    return console.log(result);
}
doSomething(callback);

console.log("---------------------------");

const doSomething2 = (callback) => {
    setTimeout(() => {
        const skills = ['HTML', 'CSS', 'JS'];
        callback(false, skills);
    }, 2000);
}

doSomething2((err, result) => {
    if (err) return console.log(err);
    return console.log(result); // ['HTML', 'CSS', 'JS']
});

console.log("---------------------------");
// Promise
const doPromise = new Promise((resolve, reject) => {
    setTimeout(() => {
        const skills = ['HTML', 'CSS', 'JS'];
        if(skills.length > 0) resolve(skills);
        else reject('Something wrong has happened');
    }, 2000);
})

// ["HTML", "CSS", "JS"]
doPromise.then(result => console.log(result)).catch(error => console.log(error));

console.log("---------------------------");

const doPromise2 = new Promise((resolve, reject) => {
    setTimeout(() => {
        const skills = ['HTML', 'CSS', 'JS'];
        if(skills.includes('Node')) resolve('fullstack developer');
        else reject('Something wrong has happened');
    }, 2000);
})

doPromise2.then(result => {console.log(result)}).catch(error => console.error(error)); // Something wrong has happened