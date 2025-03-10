// btnDet clear the key
document.querySelector('.btnDet').addEventListener('click', () => {
    document.querySelector('.wrapper2').textContent = '';
    console.clear();
});

document.addEventListener('keydown', (event) => {
    const keyNumber = event.keyCode;
    console.log(keyNumber);
    document.querySelector('.wrapper2').textContent = keyNumber;
});