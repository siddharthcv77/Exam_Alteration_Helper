<?php 
   $con=mysqli_connect("localhost","id12160635_examalthelper","examalthelper","id12160635_examalthelper");
   $date = $_POST["date"];
   $room = $_POST["room"];
   $time = $_POST["time"];
   $block = $_POST["block"];
   $sql = "select * from EXAMSCHEDULE where date = '$date' and room = '$room' and time = '$time' and block = '$block'";
   $result = mysqli_query($con ,$sql);
   if(mysqli_num_rows($result) > 0) {
        while($row = $result->fetch_assoc())
        { echo $row["room"]."_".$row["time"]."_".$row["name"]."_".$row["block"];
        }		
   }
   else {
        echo "Invalid";
   }
?>