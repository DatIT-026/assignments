function outerFunction() {
    let count = 0;
    function innerFunction() {
        count++;
        return count;
    }
    return innerFunction;
}

const innerFunc = outerFunction();

console.log(innerFunc()); // 1
console.log(innerFunc()); // 2
console.log(innerFunc()); // 3

console.log("-----------------------");

// More example of inner functions which returns an object
const outerFunction2 = () => {
    let count = 0;
    const plusOne = () => {
        count++;
        return count;
    }
    const minusOne = () => {
        count--;
        return count;
    }
    return { 
        plusOne: plusOne(), // Calls plusOne() immediately
        minusOne: minusOne() // Calls minusOne() immediately
    }
}
const innerFuncs = outerFunction2();

console.log(innerFuncs.plusOne); // 1
console.log(innerFuncs.minusOne); // 0

console.log("-----------------------");

// More example of inner functions without returns an object
const outerFunction2_1 = () => {
    let count = 0; // Bắt đầu với số 0
    const plusOne = () => {
        count++; // Tăng số lên 1
        return count; // Trả về số hiện tại
    }
    const minusOne = () => {
        count--; // Giảm số xuống 1
        return count;
    }
    return { 
        plusOne: plusOne, // returns the function itself
        minusOne: minusOne // returns the function itself
    }  
}
const innerFuncss = outerFunction2_1(); // Mở hộp và lấy nút

// Bây giờ bạn có thể nhấn nút nhiều lần
console.log(innerFuncss.plusOne()); // 1
console.log(innerFuncss.plusOne()); // 2
console.log(innerFuncss.plusOne()); // 3
console.log(innerFuncss.minusOne()); // 2
console.log(innerFuncss.minusOne()); // 1