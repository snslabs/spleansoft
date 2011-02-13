<?php
/*
Plugin Name: Close Site
Plugin URI: http://dust999.blogspot.com/
Description: Close Site for a time
Version: 1.0.0
Author: Tumasov Levon
Author URI: http://dust999.blogspot.com/
*/

function stop(){
	include (TEMPLATEPATH . '/closed.php');
	die();
}

add_action('get_header','stop');
?>