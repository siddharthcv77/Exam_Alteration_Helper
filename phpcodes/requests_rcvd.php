<?php 
$con=mysqli_connect("localhost","id12160635_examalthelper","examalthelper","id12160635_examalthelper");
$date=$_POST["date"];
$time=$_POST["time"];
$username=$_POST["username"];
$mysql_qry = "select * from requests";
$result = mysqli_query($con,$mysql_qry);
if(mysqli_num_rows($result) > 0) {
        while($row = mysqli_fetch_array($result))
        { 
        	echo $row["DATE"]."_".$row["TIME"]."_".$row["NAME"]."_";         					
        }
		
}
else {
echo "Error: " . $mysql_qry . "<br>" . $con->error ;
}
$con->close();
?>
 