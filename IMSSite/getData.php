<?php
include("connect.php");
session_start();
if (isset($_SESSION['loggedin'])) {
    $username = $_SESSION['username'];
    // Display the username

} else {
    header("Location:log_in.php");
    // If the session variable doesn't exist, handle accordingly
}

// Retrieve data from the database
$sql = "SELECT name, quantity, price, location FROM product";
$result = $conn->query($sql);

// Check if there are any rows
if ($result->num_rows > 0) {
    // Output data of each row
    while ($row = $result->fetch_assoc()) {
        $encodedName = urlencode($row["name"]);
        $encodedLocation = urlencode($row["location"]);
        echo "<tr><td>" . $row["name"] . "</td><td>" . $row["quantity"] . "</td><td>" . $row["price"] . "</td><td>" . $row["location"] . "</td><td>";

        // Determine the status based on quantity
        if ($row["quantity"] < 5) {
            echo '<button style="background-color: red;">Low Quantity</button>';
        } else {
            echo '<button style="background-color: green;">Sufficient Quantity</button>';
        }

        echo "</td></tr>";
    }
} else {
    echo "0 results";
}
$conn->close();
