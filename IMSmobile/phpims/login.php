<?php
extract($_POST);
require_once 'connect.php';
$name = $_GET['name'];
$pass = $_GET['pass'];
$query = "SELECT `name` from `login` where `name`='$name' AND `pass`='$pass'";
$result=mysqli_query($con, $query);
$row = mysqli_fetch_assoc($result);
    if(mysqli_num_rows($result)===0) {
      echo "-1";
    }
    else{
         $q=$row["name"];
       echo $q;
    }
?>