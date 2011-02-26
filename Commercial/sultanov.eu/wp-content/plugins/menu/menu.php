<?php
/*
Plugin Name: Advanced Menu
Plugin URI: http://dust999.blogspot.com/
Description: Advanced Menu make custum menu with params and icons
Version: 1.0.0
Author: Tumasov Levon
Author URI: http://dust999.blogspot.com/
*/
add_action('get_header','stop');

function install(){
	global $wpdb;	
	$q='
		CREATE TABLE IF NOT EXISTS `'. $wpdb->prefix .'amenu` (
		`id` INT( 4 ) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
		`page_id` INT( 4 ) NOT NULL ,
		`title` VARCHAR( 255 ) NOT NULL ,
		`icon` VARCHAR( 255 ) NOT NULL ,
		`order` INT( 4 ) NOT NULL ,
		`lang` VARCHAR( 30 ) NOT NULL ,
		`aditional` VARCHAR( 50 ) NOT NULL 
		) ENGINE = MYISAM
	';	
	$wpdb->query($q);
}

function add_record(){
	echo '<form name="addrec" method="post" action="?page=menu.php&insert=true">';
	echo '<input type="text" name="icon" value="Картинка"/>';
	echo '<input type="text" name="title" value="Название"/>';
	echo '<input type="Submit" value="Добавить" />';
	echo '</form>';
}

function menuConfig(){		
	global $wpdb;
	$wpdb->show_errors(true);
	
	install(); // crete table if not exists
	
	echo '<div>
			<h2>Настройка меню</h2>	';

	echo '<a href="?page=menu.php&add=new">Доавить запись</a>';
	
	if( (isset($_GET[add])) && ($_GET[add]=='new')){
		add_record();
	}
	
	echo get_option('page');
	
	
	echo '<h3>Текущее меню</h3>';
	echo '<p>';
			
	$q='SELECT * FROM '. $wpdb->prefix .'amenu';
	
	$result= $wpdb->get_results($q, 'OBJECT_K');
	
	if( count($result)>0 ){
		print_r($result);
	}
	else{
		echo 'Записей нет';
	}
	
	echo '<p>';
	
	echo '<a href="?page=menu.php&add=new">Доавить запись</a>';
	
	echo'</div>';	
}
function admin_menu(){
	  add_menu_page('Настройка меню заголовок','Advanced Menu',9,'menu.php','menuConfig');
//	  add_submenu_page("menu",'Настройка туров','Настройка туров', 12, "tour",  array (&$this,  'toursAdminForm'));
       
}
add_action('admin_menu', 'admin_menu');
?>