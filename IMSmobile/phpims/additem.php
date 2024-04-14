<?php
require_once 'connect.php';
$name = $_GET['name'];
$location = $_GET['location'];
$price=$_GET['price'];
$quantity = $_GET['quantity'];
$query = "insert into product(name,price,location,quantity) values('$name','$price','$location','$quantity')";
if(mysqli_query($con, $query)){
    
 echo mysqli_insert_id($con);
 
 // this returns the id that mysql used for the new tuple
}
else {echo "-1";}
?>