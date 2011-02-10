<?php
/*Levon`s functions*/
/*Prepare menu*/
function get_menu_by_meta($meta){ // return array with [name], [link], [active] or not of menus' items and full [url] to page
	$links = wp_list_pages('title_li=&echo=0&post_status=publish&meta_key='.$meta);	
	$links = explode('</li>',trim($links));	
	for($i=0;$i<(sizeof($links)-1);$i++){
		if(strpos($links[$i],'current_page_item')>0){ // if active menu
			$result[$i][active]=true;
			$active=true;
		}
		else{
			$result[$i][active]=false;
		}
		$links[$i]=trim($links[$i]);
		$temp=explode('<a href="',$links[$i]);
		$result[$i][url]='<a href="'.$temp[1];
		$temp=explode('"',$temp[1]);
		$result[$i][link]=$temp[0];
		$temp=explode('>', substr($result[$i][url],0,strlen($result[$i][url])-4));
		$result[$i][name]=$temp[1];															
	}
	return $result;	
}
function output_menu($items,$position='',$path=''){ // output top_menu & sub_menu
	if($position=='top_menu'){ // Hightlight Home page
		for ($i=0;$i<sizeof($items);$i++){			
			if($items[$i][active]==true){
				$active=true;
				break 1;
			}
		}
		if(!isset($active)){
			$items[0][active]=true;
		}
	}
	// prepare constants for menus
	if($position=='sub_menu'){
		$divider='<div class="div"></div>';
		$u1='';$u2='';
		$imgurl='micon_';
	}
	else{
		$imgurl='top_icon_';
		$u1='<u>';
		$u2='</u>';
	}
	// output items
	for($i=0;$i<sizeof($items);$i++){
		$active='';		
		if ($items[$i][active]==true){
			$active='class="active" ';
		}
		if($position=='sub_menu'){  // text correction
			$l = strlen($items[$i][name]);
			if($l<30){ // if small
				$u1='<b>';$u2='</b>';
			}
			elseif($l>35){ // if big
				$u1='<span>';$u2='</span>';
			}
			else{$u1='';$u2='';}
			
		}
		$s='<a '.$active.' href="'.$items[$i][link].'" tite="'.$items[$i][name].'" ><img src="'.$path.'/img/'.$imgurl.($i+1).'.gif" alt="Menu Icon '.($i+1).'"/> '.$u1.$items[$i][name].$u2.'</a>'.$divider;
		echo $s;		
	}
}
function get_side_by_meta($meta_key){ // side kontent by meta
	// Simple query to DB. Table wp_postmeta - metadata of posts
	/*$q="SELECT `post_id`,`meta_value` FROM `wp_postmeta` WHERE meta_key='right'";
	$r=@mysql_query($q) or die('error');
	$r=mysql_fetch_row($r);*/
	$pages=get_pages(array('meta_key' => $meta_key)); // get page by filter meta_key
	foreach($pages as $page){ 
		//print_r($page); output all content
		//echo "<h3>".$page->meta_value."</h3>";
		echo $page->post_content;
		break; // return 2 records why? =(
	}
}
?>