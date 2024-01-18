<?php 
   $con=mysqli_connect("localhost","id12160635_examalthelper","examalthelper","	id12160635_examalthelper");
   $username = $_POST["username"];
   $password = $_POST["password"];
   $email = $_POST["email"];
   $phone_number = $_POST["phone_number"];
   $name = $_POST["name"];	
$mysql_qry = "insert into credentials (username,password,name,phone_number,email) values ('$username','$password','$name','$phone_number','$email')";
if($conn->query($mysql_qry)=== TRUE)  {
echo "Records Inserted";
}
else {
echo "Error: " . $mysql_qry . "<br>" . $conn->error ;
}


$conn->close();

?>
 