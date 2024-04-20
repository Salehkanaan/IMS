<?php
$server="sql6.freesqldatabase.com";
$user="sql6692867";
$password="cu8mVDprys";
$db = "sql6692867";

$conn = mysqli_connect($server,$user,$password,$db);
if (mysqli_connect_errno())
{
 echo mysqli_connect_error();
}
?>