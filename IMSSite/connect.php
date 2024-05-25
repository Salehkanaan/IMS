<?php

  $server="localhost";
  $user="id22050951_imsite";
  $password="Imsite@2024";
  $db = "id22050951_ims";
  

$conn = mysqli_connect($server,$user,$password,$db);
if (mysqli_connect_errno())
{
 echo mysqli_connect_error();
}

?>