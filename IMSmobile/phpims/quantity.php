<?php
require_once 'connect.php';
$name = $_GET['name'];
$location = $_GET['location'];
$query = "select quantity from product where location='$location' && name='$name'";
$result=mysqli_query($con, $query);

if(mysqli_query($con, $query)){
    if ($row = mysqli_fetch_assoc($result)) {
       $q=$row["quantity"];
       echo $q;
    }
    else{
        echo "-1";
    }
}
else {echo "fail";}
?>