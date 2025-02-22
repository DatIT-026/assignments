try {
    let lastName = 'DatIT';
    let fullName = firstName + ' ' + lastName;
} catch (err) {
    /* 
        ReferenceError: firstName is not defined at <anonymous>:3:20
    */
    console.log(err); // we can use console.log
    console.error(err); // or console.error()
} finally {
    console.log('In any case I will be executed');
}

// The catch block take a parameter. It is common to pass e, err, or error as a parameter to the catch block
// This parameter is an object and it has name and message keys.

try{
    let lastName2 = "DatIT2";
    let fullName2 = firstName2 + " " + lastName2;
} catch (err) {
    console.log("Name of the error", err.name); // Name of the error ReferenceError
    console.log("Error message", err.message); // Error message firstName2 is not defined
} finally {
    console.log("In any case I will be executed");
}

// throw: allows us to create a custom error which can through a string, number, boolean or an object.
// Use the throw statement to throw an exception and when you do that, expression specifies the value of the exception.
const throwErrorExampleFun = () => {
    let message;
    let x = prompt('Enter a number: ');
    try {
        if (x == '') throw 'empty';
        if (isNaN(x)) throw 'not a number';

        x = Number(x);
        
        if(x < 5) throw 'too low'
        if(x > 10) throw 'too high'
    } catch (err) {
        console.log(err);
    }
}
throwErrorExampleFun();

// Error Types
// ReferenceError: An illegal reference has occured. A ReferenceError is thrown if we use a var that has not been declared.

let firstname = 'datit';
let fullname = firstname + ' ' + lastname;
console.log(fullname);

// SyntaxError: a syntax error has occurred
let square = 2 x 2; // Uncaught SyntaxError: Unexpected identifier
console.log(square);

// TypeError: A type error has occurred
let num = 10;
console.log(num.toLowerCase()); // Uncaught TypeError: num.toLowerCase is not a function