<?php
 // Connect to the database
 $host="sql6.freesqldatabase.com";
 $user="sql6692867";
 $password="cu8mVDprys"; // this field is equal to root for MAC users
 $db = "sql6692867";

// Create connection
$conn = mysqli_connect($host, $user, $password, $db);
  
  // Check connection
  if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
  }
?>