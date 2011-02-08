<?
	$service_category = @$_REQUEST["c"];
	$service_code = @$_REQUEST["s"];
	$service_page = "content/services/".$service_category."-".$service_code.".html";
	if($service_code != "" && $service_category != ""){
		@include($service_page);
	}
	else{	?>
		Пожалуйста выберите пункт меню слева.
<?	}

?>