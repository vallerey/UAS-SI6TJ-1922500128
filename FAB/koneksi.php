<?php
$con = new mysqli("localhost","root","","campuss");

if ($con-> connect_errno) {
    echo "Failed to Connect to MySQL:". $con->connect_error;
    exit();
}
?>