<?php
// Start the session
session_start();

// Include the connection file
include 'connect.php';

// Check if the session variable containing the username exists
if (isset($_SESSION['loggedin'])) {
    $username = $_SESSION['username'];
    // Display the username
} else {
    header("Location:log_in.php");
    // If the session variable doesn't exist, handle accordingly
}

// Process form data
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Escape user inputs for security
    $product_name = $conn->real_escape_string($_POST['product_name']);
    $quantity = $conn->real_escape_string($_POST['quantity']);
    $timestamp = $_POST['date_time']; // No need to escape as it's generated on the server side

    // Check if the product exists in the products table
    $check_product_sql = "SELECT * FROM product WHERE name = '$product_name'";
    $result = $conn->query($check_product_sql);

    if ($result->num_rows > 0) {
        // Product exists, retrieve available quantity
        $row = $result->fetch_assoc();
        $available_quantity = $row['quantity'];
        $location = $row['location'];
        $price = $row['price'];
        // Check if entered quantity exceeds available quantity
        if ($quantity > $available_quantity) {
            // Quantity exceeds available quantity, display an error message
            echo "Error: Quantity entered exceeds available quantity ($available_quantity).";
        } else {
            // Quantity is valid, proceed with inserting the transaction
            // SQL query to insert data into transactions table
            $insert_transaction_sql = "INSERT INTO transaction (pname, quantity, date, ename,price) VALUES ('$product_name', '$quantity', '$timestamp', '$username','$price')";
            $q = $available_quantity - $quantity;
            $insert_update_sql = "UPDATE product SET quantity='$q' where name='$product_name' && location='$location'";

            if ($conn->query($insert_transaction_sql) === TRUE && $conn->query($insert_update_sql) === TRUE) {
                echo "New record created successfully";
            } else {
                echo "Error: " . $insert_transaction_sql . "<br>" . $conn->error;
            }
        }
    } else {
        // Product does not exist, display an error
        echo "Error: Product '$product_name' is not available.";
    }
}

// Close connection
$conn->close();
