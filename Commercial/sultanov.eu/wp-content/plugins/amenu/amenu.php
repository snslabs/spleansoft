<?php 

/*
Plugin Name: Advanced Menu
Plugin URI: http://dust999.blogspot.com/
Description: Advanced Menu make custum menu with params and icons
Version: 1.0.0
Author: Tumasov Levon
Author URI: http://dust999.blogspot.com/
*/

function install(){
	if(!is_admin()){exit;}
	global $wpdb;	
	$q='
		CREATE TABLE IF NOT EXISTS `'. $wpdb->prefix .'amenu` (
		`id` INT( 4 ) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
		`position` INT( 1 ) NOT NULL ,
		`page_id` VARCHAR( 255 ) NOT NULL ,
		`page_url` VARCHAR( 255 ) NOT NULL ,
		`title` VARCHAR( 255 ) NOT NULL ,
		`icon` VARCHAR( 255 ) NOT NULL ,
		`order` INT( 4 ) NOT NULL ,
		`lang` VARCHAR( 30 ) NOT NULL ,
		`aditional` VARCHAR( 50 ) NOT NULL 
		) ENGINE = MYISAM CHARACTER SET utf8 COLLATE utf8_general_ci
	';	
	$wpdb->query($q);
}

function del_picture($file){
	if(!is_admin()){exit;}
	@unlink($file);
}

function add_picture(){	
	if(!is_admin()){exit;}
	echo '<p>';
	echo '<form name="addpic" method="post" action="?page=menu.php&ins=pic" enctype="multipart/form-data">';
	echo '<h4>Размеры картинок 35/28 или 46/41</h4>';
	echo '<input type="file" name="image_file" value="Путь до картинки"/> ';
	echo '<input type="Submit" value="Загрузить" />';
	echo '</form>';
	echo '</p>';
	echo show_pictures();
	
}

function file_size($size){
	if(!is_admin()){exit;}
	$filesizename = array(" Bytes", " KB", " MB", " GB", " TB", " PB", " EB", " ZB", " YB");
	return $size ? round($size/pow(1024, ($i = floor(log($size, 1024)))), 2) . $filesizename[$i] : '0 Bytes';
}


function show_pictures($type='list'){
	if(!is_admin()){exit;}
	$dir=dirname(__FILE__);
	if( strpos($dir, '\\', 0) > 0 ){ $div='\\'; } // windows
	else{ $div='/'; } // unix
	$dir.=$div.'pics';
	if(!is_dir($dir)){		
		@mkdir($dir,0777) or die ('Не могу создать дирректорию');
	}	
	$dh=opendir($dir);
	$url=WP_PLUGIN_URL.'/'.str_replace(basename( __FILE__),"",plugin_basename(__FILE__)).'pics';
	$i=0;
	while($file=readdir($dh)){		
		if( ($file=='.') || ($file=='..')){continue;}
		$pics[$i][url]=$url.'/'.$file;
		$pics[$i][name]=$file;		
		$i++;
	}
	if ($type == 'list' ){
		$out= '<ol style="margin-top:5px">';
		for($i=0;$i<count($pics);$i++){
			$out.= '<li><a href="'.$pics[$i][url].'" title="'.$pics[$i][name].'"><img style="margin-right:5px" height="41px" width="46px" src="'.$pics[$i][url].'" alt="'.$pics[$i][name].'" />'.$pics[$i][name].'</a> ['.file_size(filesize($dir.$div.$pics[$i][name])).'] (<a href="?page=menu.php&del=pic&file='.$dir.$div.$pics[$i][name].'">удалить</a>)</li>';
		}
		$out.= '</ol>';
		return $out;
	}
	return $pics;	
}

function insert_picture(){
	if(!is_admin()){exit;}
	echo '<p>';	
	$file=$_FILES['image_file'];
	$dir=dirname(__FILE__);
	if( strpos($dir, '\\', 0) > 0 ){ $div='\\'; } // windows
	else{ $div='/'; } // unix
	$dir.=$div.'pics';	
	if(!is_dir($dir)){		
		@mkdir($dir,0777) or die ('Не могу создать дирректорию');
	}	
	move_uploaded_file( $file[tmp_name], $dir.$div.strtolower($file[name]) ) or die ('Не могу переписать фаил');	
	echo '<h3>Фаил успешно сохранён</h3>';
	echo '</p>';
}

function get_page_menu(){
	if(!is_admin()){exit;}
	$temp=wp_list_pages('title_li=&echo=0');	
	$temp=@explode('<li',$temp);
	$j=0;$depth=0;
	for($i=1;$i<count($temp);$i++){
		$temp[$i]=@explode('</a>',trim($temp[$i]));
		$tmp=@explode('">',$temp[$i][0]);		
		$links[$j][name]=$tmp[2];
		$links[$j][depth]=$depth;
		$tmp=@explode('"',$tmp[1]);
		$links[$j][url]=$tmp[1];		
		$j++;		
		if( strpos($temp[$i][1],'</ul>') > 0 ){$depth-=1;}
		elseif( trim($temp[$i][1] )!= '</li>' ){$depth++;}		
	}	
	return $links;
}

function add_record($id=-1){
	if(!is_admin()){exit;}
	if( $id>=0 ){
		global $wpdb;
		echo 'ok!';
		$q="SELECT * FROM ". $wpdb->prefix ."amenu	WHERE `id` =$id";		
		$results= $wpdb->get_results($q);	
		if( count($results)<=0 ){$id=-1;}	
		$result=$results[0];		
	}
	global $sitepress;
	if (isset($sitepress)){
		$langs=$sitepress->get_active_languages();
	}
	else{
		$langs='default';
	}
	?>   
    <h3>Добавить запись</h3>
    <script language="javascript">
		function setname(){			
			obj1=document.getElementById('pages');
			obj2=document.getElementById('title');
			obj3=document.getElementById('page_id');
			obj4=document.getElementById('page_url');
			tmp=obj1.options[obj1.selectedIndex].value.split('|');			
			obj2.value=tmp[0];	
			obj3.value=tmp[0];	
			obj4.value=tmp[1];	
		}
		function set_lang(lang){
			obj=document.getElementById('lang');						
			loc=document.location.toString();
			loc=loc.split('#');
			loc=loc[0];
			loc=loc.split('&lang');
			loc=loc[0];
			loc+='&lang='+obj.options[obj.selectedIndex].value;
			document.location=loc;
			
		}	
		function set_pic(id){						
			loc=document.location.toString();			
			loc=loc.split('#');	
			loc=loc[0];
			loc=loc.split('&cpic');
			loc=loc[0];
			loc+='&cpic='+id;			
			document.location=loc;			
		}	
		function show_pics(){
			obj2=document.getElementById('pictures');
			obj2.style.position='absolute';
			obj2.style.width='160px';
			obj2.style.height='200px';
			obj2.style.overflow='scroll';
		}
    </script> 
    <p>   
    	<form name="addrec" method="post" action="?page=menu.php&ins=rec">
        <table class="wp-list-table widefat" style="width:750px" cellspacing="0"><thead><tr>
        <th style="text-align:center">Язык</th>
        <th style="text-align:center">Иконка</th>
        <th style="text-align:center">Позиция</th>
        <th style="text-align:center">Cтраница</th>
        <th style="text-align:center">Название</th>
        <th style="text-align:center">Порядок</th>        
        </tr>
        </thead>
        <tbody><tr>
        <td><select id="lang" name="lang" onchange="set_lang()">
	<?php
	if (count($langs)<2){
		echo '<option value="default" selected="selected">Default</option>';
	}
	else{		
		foreach($langs as $lang){
			if (( $lang[code] == $_GET[lang] )&&( $id < 0 )){
				echo '<option value="'.$lang[code].'" selected="selected">'.$lang[native_name].'</option>';
				continue;
			}
			elseif(( $id >= 0)&&( $lang[code] == $result->lang )){
				echo '<option value="'.$lang[code].'" selected="selected">'.$lang[native_name].'</option>';
				continue;
			}
			echo '<option value="'.$lang[code].'">'.$lang[native_name].'</option>';
		}		
	}
	?>
		
        </select></td>
		<td style="width:200px">
   
    <?php	
	$pics=show_pictures(123);
	$icon = ( $id < 0 ? $pics[0][url] : $icon=$result->icon );
	if(isset($_GET['cpic'])){
		for($i=0;$i<count($pics);$i++){
			if($_GET['cpic']==$i){
				echo'<a id="selpic" href="#changepic" onclick="show_pics();"><img width="30px" height="30px" src="'.$pics[$i][url].'" alt="'.$pics[$i][name].'"/></a>';
				$icon=$pics[$i][url];
				break;
			}
		}
	}else{
		echo'<a id="selpic" href="#changepic" onclick="show_pics();"><img width="30px" height="30px" src="'.$icon.'" alt="'.$icon.'"/></a>';	
	}
	?>
    <div id="pictures" style="float:left;z-index:2;width:0;height:0;overflow:hidden;background:#fff;border:1px solid #ccc">
    <style type="text/css">
		.fpic li{width:40px;height:40px;float:left;margin:2px;padding:0}
		.fpic li a{border:1px #fff solid;padding:2px;width:35px;height:35px;display:block}
		.fpic li a:hover{border-color:#09f}
		.cfloat{clear:left}		
    </style>
    	<ul class="fpic">
		<?php 
		for($i=0;$i<count($pics);$i++){
		echo '<li><a href="#pic'.$i.'" title="'.$pics[$i][name].'" onclick="set_pic('.$i.')"><img width="35px" height="35px" src="'.$pics[$i][url].'" alt="'.$pics[$i][name].'" /></a></li>';		
	}
		?>	
        </ul>
        <div class="cfloat"></div>
    </div>
	
	<input type="hidden" name="icon_url" value="<?php echo $icon; ?>" /></td>
	<td><select name="position"><option value="0" <?php if($result->position==0){echo 'selected="selected"';} ?>>Верхнее меню</option><option value="1" <?php if($result->position==1){echo 'selected="selected"';} ?>>Под меню</option></select></td>	
	<td style="width:250px!important;"><select id="pages" name="pages" onchange="setname();">
    
	<?php
	$links=get_page_menu();	
	echo '<option value="default">Выбирите страницу</option>';
	for($i=0;$i<count($links);$i++){
		echo '<option value="'.$links[$i][name].'|'.$links[$i][url].'"';
		if (( $id >= 0 )&&( $links[$i][url] == $result->page_url )){echo 'selected="selected"';}
		echo '>';
		if($links[$i][depth]>0){
			for($j=0;$j<$links[$i][depth];$j++){
				echo '-';
			}
		}
		echo ' '.$links[$i][name].'</option>';
	}
	
	echo '</select>';
	?>
    <input type="hidden" id="page_id" name="page_id" value="<?php echo ( $id<0 ? 'page_id': $result->page_id);?>" />
	<input type="hidden" id="page_url" name="page_url" value="<?php echo ( $id<0 ? 'page_url': $result->page_url);?>" />
	</td>
	<td><input type="text" id="title" name="title" value="<?php echo (id<0 ? 'Название' : $result->title) ;?>" /></td>
	<td><input type="text" name="order" size="3" value="<?php echo ( $id<0 ? '0': $result->order); ?>" /></td>
	</tr>
	<tr><td style="white-space: nowrap">Дополнительные<br />сведения</td><td colspan="4"><textarea style="width:100%; height:40px" name="aditional"><?php echo ( $id<0 ? 'Доп. сведения': $result->aditional); ?></textarea></h5></td><td valign="middle"><br /><input type="Submit" value="<?php echo ($id<0?'Добавить':'Обновить');?>" /></td></tr>
	</tbody></table>
	</form>
	</p>
    <?php
}

function show_title(){
	if(!is_admin()){exit;}
	$home='Настройка меню';
	if( ($_GET['add']=='pic') || ($_GET['ins']=='pic') ){
		return '<a href="?page=menu.php" title="Advanced Menu">'.$home.'</a> > Картинки';
	}
	if( ($_GET['add']=='out') || ($_GET['ins']=='out') ){
		return '<a href="?page=menu.php" title="Advanced Menu">'.$home.'</a> > Настройки вывода';
	}
	elseif($_GET['add']=='rec'){
		return '<a href="?page=menu.php" title="Advanced Menu">'.$home.'</a> > Доавить запись';
	}
	return $home;	
}

function insert_record(){
	if(!is_admin()){exit;}
	if($_POST[page_id]=='page_id'){return true;}	
	global $wpdb;	
	$q="
	SELECT * 
	FROM `". $wpdb->prefix ."amenu` 
	WHERE `position` =$_POST[position]
	AND `page_id` LIKE '$_POST[page_id]'
	AND `page_url` LIKE '$_POST[page_url]'
	AND `title` LIKE '$_POST[title]'
	AND `icon` LIKE '$_POST[icon_url]'
	AND `order` =$_POST[order]
	AND `lang` LIKE '$_POST[lang]'
	AND `aditional` LIKE '$_POST[aditional]'
	";
	if ($wpdb->query($q)){
		return true;
	}
	$q="
	DELETE FROM `". $wpdb->prefix ."amenu` 
	WHERE `page_id` LIKE '$_POST[page_id]'
	AND `lang` LIKE '$_POST[lang]'
	";
	$wpdb->query($q);
	$q="INSERT INTO `". $wpdb->prefix ."amenu` (
		`id`,`position`,`page_id`,`page_url`,`title`,`icon`,`order`,`lang`,`aditional`)
	VALUES (
		NULL, '$_POST[position]', '$_POST[page_id]', '$_POST[page_url]', '$_POST[title]', '$_POST[icon_url]', '$_POST[order]', '$_POST[lang]', '$_POST[aditional]'
		);";
	//echo $q;	
	if ($wpdb->query($q)){
		echo '<h3>Запись успешно добавлена</h3>';
	}
}

function del_record($id){
	if(!is_admin()){exit;}
	global $wpdb;
	$q="
	DELETE FROM `". $wpdb->prefix ."amenu` 
	WHERE `id` = $id
	";
	$wpdb->query($q);	
}

function show_recs(){
	if(!is_admin()){exit;}
	global $wpdb;		
	$q='SELECT * FROM '. $wpdb->prefix .'amenu ORDER BY `order` ASC';
	$results= $wpdb->get_results($q);	
	if( count($results)<=0 ){echo 'Записей нет'; return false;}
	foreach($results as $res){
		if($res->position==0){
			$top[]=$res;
		}
		else{$bot[]=$res;}
	}
	if( (count($top)<1) && (count($bot)<1) ){return true;}
	echo '<h5>Верхнее меню</h3>';
	echo '<table class="wp-list-table widefat" style="width:600px"><thead>';
	echo '<th>#</th>';
	echo '<th>Страница</th>';
	echo '<th>Название</th>';
	echo '<th>Иконка</th>';
	echo '<th>Язык</th>';
	echo '<th>Порядок</th>';
	echo '<th>Изменить</th>';
	echo '</thead><tbody>';
	for($i=0;$i<count($top);$i++){		
		echo '<tr>';
		echo '<td>'.($i+1).'</td>';
		echo '<td>'.$top[$i]->page_id.'</td>';
		echo '<td>'.$top[$i]->title.'</td>';
		echo '<td><a href="'.$top[$i]->icon.'"><img width="20px" height="20px" src="'.$top[$i]->icon.'" /></a></td>';
		echo '<td>'.$top[$i]->order.'</td>';
		echo '<td>'.$top[$i]->lang.'</td>';
		echo '<td><a style="color:#930;font-size:9px" href="?page=menu.php&del=rec&id='.$top[$i]->id.'">Удалить</a><br /><a style="color:#930;font-size:9px" href="?page=menu.php&edt=rec&id='.$top[$i]->id.'">Редактировать</a></td>';
		echo '</tr>';
	}
	echo '</tbody></table>';
	if(count($bot)<1){return true;}
	echo '<h5>Нижнее меню</h3>';
	echo '<table class="wp-list-table widefat" style="width:600px"><thead>';
	echo '<th>#</th>';
	echo '<th>Страница</th>';
	echo '<th>Название</th>';
	echo '<th>Иконка</th>';
	echo '<th>Порядок</th>';
	echo '<th>Язык</th>';
	echo '<th>Изменить</th>';
	echo '</thead><tbody>';
	for($i=0;$i<count($bot);$i++){		
		echo '<tr>';
		echo '<td>'.($i+1).'</td>';
		echo '<td>'.$bot[$i]->page_id.'</td>';
		echo '<td>'.$bot[$i]->title.'</td>';
		echo '<td><a href="'.$bot[$i]->icon.'"><img width="20px" height="20px" src="'.$bot[$i]->icon.'" /></a></td>';
		echo '<td>'.$bot[$i]->order.'</td>';
		echo '<td>'.$bot[$i]->lang.'</td>';
		echo '<td><a style="color:#930;font-size:9px" href="?page=menu.php&del=rec&id='.$bot[$i]->id.'">Удалить</a><br /><a style="color:#930;font-size:9px" href="?page=menu.php&edt=rec&id='.$bot[$i]->id.'">Редактировать</a></td>';
		echo '</tr>';
	}
	echo '</tbody></table>';
}

function add_output(){	
	if(!is_admin()){exit;}	
	global $amenu;
	echo '<h3>Настройки для верхнего меню</h3>';
	?>
    <form action="?page=menu.php&ins=out" method="post">
    <h5>Начало меню</h5>
    <textarea name="m0_start" style="width:600px;height:50px" ><?php echo stripslashes($amenu[0][start]); ?></textarea>
    <h5>Элемент меню</h5>
    <textarea name="m0_cycle" style="width:600px;height:100px" ><?php echo stripslashes($amenu[0][cycle]); ?></textarea>
    <h5>Конец меню</h5>
    <textarea name="m0_end" style="width:600px;height:50px" ><?php echo stripslashes($amenu[0][end]); ?></textarea>
    <h5>Активный элемент</h5>
    <input type="text" name="m0_active" value="<?php echo stripslashes($amenu[0][active]); ?>" size="50" />    
    <p><input type="submit" value="Сохранить" /></p>
	<?php	
	echo '<h3>Настройки для нижнего меню</h3>';
	?>
    <h5>Начало меню</h5>
    <textarea name="m1_start" style="width:600px;height:50px" ><?php echo stripslashes($amenu[1][start]); ?></textarea>
    <h5>Элемент меню</h5>
    <textarea name="m1_cycle" style="width:600px;height:100px" ><?php echo stripslashes($amenu[1][cycle]); ?></textarea>
    <h5>Конец меню</h5>
    <textarea name="m1_end" style="width:600px;height:50px" ><?php echo stripslashes($amenu[1][end]); ?></textarea>
    <h5>Активный элемент</h5>
    <input type="text" name="m1_active" value="<?php echo stripslashes($amenu[1][active]); ?>" size="50" />    
    <p><input type="submit" value="Сохранить" /></p>
    </form>    
	<?php	
	echo '<h5>Результат</h5>';
	echo '<p><textarea style="height:200px;width:600px">';	
	echo "<!-- Top menu -->\n\r";
	render_amenu(0);	
	echo "\n\r<!-- Bottom menu -->\n\r";
	render_amenu(1);	
	echo '</textarea></p>';
}

function insert_output(){
	if(!is_admin()){exit;}	
	$dir=dirname(__FILE__);
	if( strpos($dir, '\\', 0) > 0 ){ $div='\\'; } // windows
	else{ $div='/'; } // unix
	$fname=$dir.$div.'settings.php';
	$fh=@fopen($fname,'w+');
	$s="<?php\n\r";
	$s.="/*top menu*/\n\r";
	$s.='$amenu[0][\'start\']="';
	$s.=trim($_POST[m0_start]);
	$s.="\";\n\r";
	$s.='$amenu[0][\'cycle\']="';
	$s.=trim($_POST[m0_cycle]);
	$s.="\";\n\r";
	$s.='$amenu[0][\'end\']="';
	$s.=trim($_POST[m0_end]);
	$s.="\";\n\r";
	$s.='$amenu[0][\'active\']="';
	$s.=trim($_POST[m0_active]);
	$s.="\";\n\r";
	$s.="/*bottom menu*/\n\r";
	$s.='$amenu[1][\'start\']="';
	$s.=trim($_POST[m1_start]);
	$s.="\";\n\r";
	$s.='$amenu[1][\'cycle\']="';
	$s.=trim($_POST[m1_cycle]);
	$s.="\";\n\r";
	$s.='$amenu[1][\'end\']="';
	$s.=trim($_POST[m1_end]);
	$s.="\";\n\r";
	$s.='$amenu[1][\'active\']="';
	$s.=trim($_POST[m1_active]);
	$s.="\";\n\r";
	$s.="?>";
	@fputs($fh,$s);
	@fclose($fh);
	?>
    <script language="javascript">
		loc=document.location;
		loc=loc.toString();
		loc=loc.split('&ins=out');
		document.location=loc[0]+'&add=out';
	</script>
	<?php	
}

function render_amenu($menu_id=0){	
	global $wpdb,$amenu;
	
	$langs=@icl_get_languages();
	$lang='default';
	foreach($langs as $lang1){
		if($lang1[active]){
			$lang=$lang1[language_code];
		}
	}	
	
	$links = wp_list_pages('title_li=&echo=0&post_status=publish');
	$links = explode('<li',trim($links));		
	for($i=0;$i<count($links);$i++){		
		if(( strpos($links[$i],'current_page_item',0) > 0 )||( strpos($links[$i],'current_page_ancestor',0) > 0 )){			
			$page_url = explode('</a>',$links[$i]);
			$page_url = explode('href="',$page_url[0]);
			$page_url = explode('"',$page_url[1]);				
			$pages_url[] = $page_url[0];
		}
	}
	
	$q="SELECT * FROM ". $wpdb->prefix ."amenu
		WHERE `position` =$menu_id
		AND `lang` LIKE '$lang'
		ORDER BY `order` ASC
	";		
	$results= $wpdb->get_results($q);	
	
	if( count($results)<=0 ){echo 'Записей нет'; return false;}	
	echo stripslashes($amenu[$menu_id]['start']);
	$i=0;
	foreach($results as $res){
		$s=$amenu[$menu_id]['cycle'];
		$s=str_replace('[id]',($i+1),$s);
		$s=str_replace('[position]',$res->position,$s);
		$s=str_replace('[page_id]',$res->page_id,$s);
		$s=str_replace('[page_url]',$res->page_url,$s);
		$s=str_replace('[title]',$res->title,$s);
		$s=str_replace('[icon]',$res->icon,$s);
		$s=str_replace('[order]',$res->order,$s);
		$s=str_replace('[lang]',$res->lang,$s);
		$s=str_replace('[aditional]',$res->aditional,$s);
		$active=false;
		for($i=0;$i<count($pages_url);$i++){
			if( $res->page_url == $pages_url[$i] ){ $active=true;	}
		}
		if($active){
			$s=str_replace('[active]',$amenu[$menu_id]['active'],$s);
		}else{
			$s=str_replace('[active]','',$s);
		}
		$i++;
		echo stripslashes($s);
	}
	echo stripslashes($amenu[$menu_id]['end']);
}

function menuConfig(){	
	if(!is_admin()){exit;}	
	global $wpdb;
	$wpdb->show_errors(true);
	
	install(); // crete table if not exists
	
	echo '<div><h2>'.show_title().'</h2>';
	
	?>
    <style>
	h5{margin:5px 0}
	table th, a{white-space:nowrap}
    </style>    
    <?php
	
	echo '<a class="button add-new-h2" href="?page=menu.php&add=rec">Доавить запись</a> ';
	echo '<a class="button add-new-h2" href="?page=menu.php&add=pic">Картинки</a>';
	echo '<a class="button add-new-h2" href="?page=menu.php&add=out">Настройка отображения</a>';
	
	if( isset($_GET['add']) ){		
		switch($_GET['add']){		
			case 'rec': add_record(); break;
			case 'pic': add_picture(); break;
			case 'out': add_output(); break;
		}
	}
	
	if( isset($_GET['del']) ){		
		switch($_GET['del']){		
			case 'pic': del_picture($_GET['file']); add_picture(); break;	
			case 'rec': del_record($_GET['id']); break;	
		}
	}
	
	if( isset($_GET['edt']) ){		
		switch($_GET['edt']){		
			case 'rec': add_record($_GET['id']); break;	
		}
	}
	
	if( isset($_GET['ins']) ){
		
		switch($_GET['ins']){		
			case 'pic': insert_picture(); add_picture(); break;
			case 'rec': insert_record(); break;
			case 'out': insert_output(); break;
		}
	}	
	
	if( ($_GET['add']=='out') || ($_GET['ins']=='out') || ($_GET['add']=='pic') || ($_GET['ins']=='pic')  || ($_GET['del']=='pic') ){echo'</div>';	return true;}

	echo get_option('page');
		
	echo '<h3>Текущее меню</h3>';
	echo '<p>';	
	
	show_recs();	
	
	echo '<p>';
	echo '<a class="button add-new-h2" href="?page=menu.php&add=rec">Доавить запись</a>';
	echo'</div>';	
}

function admin_menu(){
	if(!is_admin()){exit;}
	add_menu_page('Настройка меню заголовок','Advanced Menu',9,'menu.php','menuConfig');
//	add_submenu_page("menu",'Настройка туров','Настройка туров', 12, "tour",  array (&$this,  'toursAdminForm'));
       
}

add_action('admin_menu', 'admin_menu');

$dir=dirname(__FILE__);
if( strpos($dir, '\\', 0) > 0 ){ $div='\\'; } // windows
else{ $div='/'; } // unix
$fname=$dir.$div.'settings.php';
if(is_file($fname)){include($fname);}	

?>