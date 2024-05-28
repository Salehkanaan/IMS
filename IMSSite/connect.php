<?php

  // $server="https://databases-auth.000webhost.com/index.php?route=/database/structure&db=id22050951_ims";
  // $user="id22050951_imsite";
  // $password="Imsite@2024";
  // $db = "id22050951_ims";
  
  $server="localhost";
  $user="root";
  $password="";
  $db = "test";
$conn = mysqli_connect($server,$user,$password,$db);
if (mysqli_connect_errno())
{
 echo mysqli_connect_error();
}

?>