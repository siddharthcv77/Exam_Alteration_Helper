<?php 
$con=mysqli_connect("localhost","id12160635_examalthelper","examalthelper","id12160635_examalthelper");
$room=$_POST["room"];
$time=$_POST["time"];
$block=$_POST["block"];
$name=$_POST["name"];
$mysql_qry = "update  EXAMSCHEDULE set NAME='$name' where ROOM='$room' and TIME='$time' and BLOCK='$block'";
if($con->query($mysql_qry)=== TRUE)  {
echo "Records Updated";
}
else {
echo "Error: " . $mysql_qry . "<br>" . $conn->error ;
}
$conn->close();
?>