<?php
$servername = "localhost";
$username = "id12160635_examalthelper";
$password = "examalthelper";
$dbname = "id12160635_examalthelper";

// Create connection
$conn = new mysqli($servername, $username, $password,$dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
//echo "Connected successfully";
// $date=$_POST["date"];
$response=array();
$msg=0;
$room_ar=array();
$time_ar=array();
$name_ar=array();
$block_ar=array();
$mysql_qry = "SELECT * FROM EXAMSCHEDULE";
$result = mysqli_query($conn,$mysql_qry);
if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while ($row = $result->fetch_assoc()){
    	array_push($room_ar,$row["ROOM"]);
    	array_push($time_ar,$row["TIME"]);
    	array_push($name_ar,$row["NAME"]);
    	array_push($block_ar,$row["BLOCK"]);
    }
    $msg=1;
}
else {
    $msg=0;
}
if($msg==1){
	$room=array("room"=>$room_ar);
	$time=array("time"=>$time_ar);
	$name=array("name"=>$name_ar);
	$block=array("block"=>$block_ar);
	array_push($response,$room);
	array_push($response,$time);
	array_push($response,$name);
	array_push($response,$block);
	$response2=array("data"=>$response);
	echo json_encode($response2);
}
else{
    echo "error2";
}
$conn->close();
?>
 