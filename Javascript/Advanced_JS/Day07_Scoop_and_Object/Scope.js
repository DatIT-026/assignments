console.log("-------------------------");
let globalVar = "global"; //Global scope

function testFunc() {
    let localVar = "local"; //local scope
    if(true) {
        let blockVar = "blockVar"; //block scope
        console.log(blockVar); //block
    }
    console.log(localVar); //local
}

testFunc();
console.log(globalVar); //global
