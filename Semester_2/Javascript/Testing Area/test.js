// Create a list of fruits
const fruits = ['Apple', 'Banana', 'lemon'];
// Get the list element
const myList = document.getElementById('myList');
// Populate the list
fruits.forEach(fruit => {
    const listItem = document.createElement('p');
    listItem.textContent = fruit;
    myList.appendChild(listItem);
})

// Get the button element
const changeColorButton = document.getElementById('changeColor');
// Add an event listener to the button
changeColorButton.addEventListener('click', () => {
    // Generate random RGB values
    const randR = Math.floor(Math.random() * 256);
    const randG = Math.floor(Math.random() * 256);
    const randB = Math.floor(Math.random() * 256);

    // Create a random RGB color string
    const bgcolor = `rgb(${randR}, ${randG}, ${randB})`

    // Change the background color of the list
    myList.style.backgroundColor = bgcolor;
})

