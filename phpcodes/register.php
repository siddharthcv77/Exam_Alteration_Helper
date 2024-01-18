<?php
   $con=mysqli_connect("localhost","id12160635_examalthelper","examalthelper","	id12160635_examalthelper");
   $username = $_POST["username"];
   $password = $_POST["password"];
   $email = $_POST["email"];
   $phone_number = $_POST["phone_number"];
   $name = $_POST["name"];

   $sql = "INSERT INTO credentials(username,password,name,phone_number,email) values ('$username','$password','$name','$phone_number','$email')";
   $result = mysqli_query($con,$sql);

   if($result){
   	echo "registered"
   }
   else{
   	echo "failed1"
   }
   ?>