<?php
require_once 'connect.php';
$name = $_GET['name'];
$date = $_GET['date'];
$query = "UPDATE login SET date='$date' where name='$name' ";

if(mysqli_query($con, $query)){
  
        echo "success";
     }
 // this returns the id that mysql used for the new tuple
else {echo "-1";}
?>