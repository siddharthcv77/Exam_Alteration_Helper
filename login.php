	$con=mysqli_connect("localhost","id12160635_examalthelper","examalthelper","id12160635_examalthelper");

	$username = $_POST("username");
	$password = $_POST("password");

	$sql = "SELECT * FROM credentials WHERE username= '$username' AND password = '$password'";
	$result = mysqli_query($con,$sql);

	if($result->num_rows>0){
	  echo "logged in successfully";
	}else{
	  echo "user not found"; 
	}		