<?php 
$con=mysqli_connect("localhost","id12160635_examalthelper","examalthelper","id12160635_examalthelper");
$date=$_POST["date"];
$time=$_POST["time"];
$username=$_POST["username"];
$mysql_qry = "insert into requests(DATE,TIME,NAME) values('$date','$time','$username')";
if($con->query($mysql_qry)=== TRUE)  {
echo "Request Sent";
}
else {
echo "Error: " . $mysql_qry . "<br>" . $con->error ;
}
$con->close();
?>
 