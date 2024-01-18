<?php
	$con=mysqli_connect("localhost","id12160635_examalthelper","examalthelper","id12160635_examalthelper");

	$username = $_POST["username"];	

	$sql = "SELECT password,phone_number FROM credentials WHERE username= '$username'";
	$result = mysqli_query($con,$sql);

	if($result->num_rows>0){
	  while($row = $result->fetch_assoc())
        { echo $row["password"]."_".$row["phone_number"];
        }
	}else{
	  echo "invalid username"; 
	}
?>