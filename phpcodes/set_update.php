<?php 
   $con=mysqli_connect("localhost","id12160635_examalthelper","examalthelper","id12160635_examalthelper");
   $username = $_POST["username"];
   $password = $_POST["password"];
   $sql = "select * from credentials where username = '$username' and password = '$password'";
   $result = mysqli_query($con ,$sql);
   if(mysqli_num_rows($result) > 0) {
        while($row = $result->fetch_assoc())
        { echo $row["username"]."_".$row["password"]."_".$row["name"]."_".$row["phone_number"]."_".$row["email"];
        }		
   }
   else {
        echo "Invalid";
   }
?>