document.addEventListener("DOMContentLoaded", function () {
    // Fetch data from PHP script and populate table
    fetch('getData.php')
        .then(response => response.text())
        .then(data => {
            document.getElementById('tableBody').innerHTML = data;

            // Get the username from the data attribute
            var usernameElement = document.getElementById('username');
            var username = usernameElement ? usernameElement.getAttribute('data-username') : '';

            // Attach click event listener to all "Retrieve" buttons
            document.querySelectorAll('.retrieve-button').forEach(button => {
                button.addEventListener('click', function (event) {
                    // event.preventDefault();  Prevent the default behavior of the link
                    var name = this.closest('tr').querySelector('td:first-child').innerText; // Get the product name
                    var quantity = this.closest('tr').querySelector('td:nth-child(2)').innerText; // Get the quantity
                    if (username) {
                        retrieveProduct(name, quantity, username); // Call the function to handle retrieving the product
                    } else {
                        alert('Username not found.'); // Show an error if the username is missing
                    }
                });
            });
        })
        .catch(error => console.error('Error:', error));
});

