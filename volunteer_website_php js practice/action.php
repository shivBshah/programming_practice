<?php
	if($_SERVER['REQUEST_METHOD']=="POST") {		
	   	
       		$fname = $_POST["firstname"];
         	$lname = $_POST["lastname"];
        	$email = $_POST["email"];
	   	$phone = $_POST["phone"];
		
		$volunteer = array('fname'=>$fname,
				   'lname'=>$lname,
                                   'email'=>$email,
                                   'phone'=>$phone);
		
		if(file_exists("volunteers.json"))
		{
			$data = file_get_contents("volunteers.json");
	       		$volunteers = json_decode($data,true);
		}
		else
			$volunteers = array();
		
		$volunteers[] =  $volunteer;	
		file_put_contents("volunteers.json",json_encode($volunteers));		
		echo "<h1>You are successfully added to the list of volunteers.</h1>";
	}
?>	