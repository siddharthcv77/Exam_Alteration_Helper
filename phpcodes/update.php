<?php 
$con=mysqli_connect("localhost","id12160635_examalthelper","examalthelper","id12160635_examalthelper");
$username=$_POST["username"];
$password=$_POST["password"];
$email=$_POST["email"];
$phone_number=$_POST["phone_number"];
$name=$_POST["name"];
$mysql_qry = "update  credentials set name='$name',phone_number='$phone_number',email='$email' where USERNAME='$username' & PASSWORD='$password'";
if($con->query($mysql_qry)=== TRUE)  {
echo "Records Updated";
}
else {
echo "Error: " . $mysql_qry . "<br>" . $conn->error ;
}
$conn->close();
?>
 