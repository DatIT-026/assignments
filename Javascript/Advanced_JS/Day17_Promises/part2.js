const url = 'https://restcountries.com/v2/all' // countries api
fetch(url).then(response => response.json()) //accessing the API data as JSON
          .then(data => {
            console.log(data); //getting the data
          })
          .catch(error => console.error(error)); //handling error if something wrong happens

const fetchData = async() => {
    try {
        const response = await fetch(url);
        const countries = await response.json();
        console.log(countries);
    } catch (err) {
        console.error(err);
    }
}
console.log('===== asynce and await');
fetchData();