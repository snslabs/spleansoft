<?php
/*Levon`s functions*/
/*Prepare menu*/
function parse_menu($s,$path=''){
	$s = preg_replace('#<li\s(.+)>.+(href=".+" title=".+">.+</a>)</li>#siU','<a $1 $2',$s);
	$s = preg_replace('#page.*? \b#','"',$s);
	$s = str_replace('class="""current_"','class="active" ',$s);
	$s = str_replace('current_"','',$s);
	$s = str_replace('class="""','',$s);
	$list = explode("\n",$s);
	$list[0]=str_replace('">','"><img src="'.$path.'/img/top_icon_1.gif" alt="house" /> <u>',$list[0]);
	$list[1]=str_replace('">','"><img src="'.$path.'/img/top_icon_2.gif" alt="camel" /> <u>',$list[1]);
	$list[2]=str_replace('">','"><img src="'.$path.'/img/top_icon_3.gif" alt="paper" /> <u>',$list[2]);
	
	array_pop($list);
	for($i=0;$i<sizeof($list);$i++){
		echo substr($list[$i],0,strlen($list[$i])-4).'</u></a>';
	}
}
/*Prepare submenu*/
function parse_submenu($s,$path=''){
	$s=explode("<ul class='children'>",$s);
	$s=explode('</ul>',$s[1]);
	$s=$s[0];
	$s = preg_replace('#<li\s(.+)>.+(href=".+" title=".+">.+</a>)</li>#siU','<a $1 $2',$s);
	$s = preg_replace('#page.*? \b#','"',$s);
	$s = str_replace('class="""current_"','class="active" ',$s);
	$s = str_replace('class="""','',$s);
	$list = explode("\n",$s);
	$list[1]=str_replace('">','"><img src="'.$path.'/img/micon_1.gif" alt="Presents &amp; brands" /><b>',$list[1]);
	$list[1]=substr($list[1],0,strlen($list[1])-4).'</b></a><div class="div"></div>';
	$list[2]=str_replace('">','"><img src="'.$path.'/img/micon_2.gif" alt="Airplane &amp; travel" />',$list[2]);
	$list[2].='<div class="div"></div>';
	$list[3]=str_replace('">','"><img src="'.$path.'/img/micon_3.gif" alt="Preentation &amp; buiness" /><span>',$list[3]);
	$list[3]=substr($list[3],0,strlen($list[3])-4).'</span></a>';
	array_pop($list);	
	for($i=1;$i<sizeof($list);$i++){
		echo $list[$i];
	}
}
?>