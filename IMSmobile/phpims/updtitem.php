<?php
require_once 'connect.php';
$name = $_GET['name'];
$location = $_GET['location'];
$quantity = $_GET['quantity'];
$query = "UPDATE product SET quantity='$quantity' where name='$name' && location='$location'";

if(mysqli_query($con, $query)){
  
        echo "success";
     }
 // this returns the id that mysql used for the new tuple
else {echo "-1";}
?>