const isPrime = (num) => {
    if(num < 2) return false;
    for(let i = 2; i <= Math.sqrt(num); i++) {
        if(num % i === 0) return false;
    }
    return true;
}

const generateNumbers = (count) => {
    const wrapper = document.querySelector('.wrapper');

    wrapper.innerHTML = '';

    for(let i = 0; i <= count; i++) {
        const box = document.createElement('div');
        box.textContent = i;
        box.classList.add('box');

        if(isPrime(i)) {
                box.style.backgroundColor = 'red'
                box.style.color = '000'; // prime
            }
            else if(i % 2 === 0) {
                box.style.backgroundColor = 'green'
                box.style.color = '000'; // even
            }
            else {
                box.style.backgroundColor = '#ff9933';
                box.style.color = '000';
            }

    wrapper.appendChild(box);
    }
}

document.getElementById('btnGenerate').addEventListener('click', () => {
    const value = document.getElementById(`numInput`).value || 0;
    generateNumbers(value);
})