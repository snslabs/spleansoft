<?
$currentMenuItem = "Услуги";
$pageId="services";
function sumbenuItems(){ 
	$category = @$_REQUEST["c"];
?>
	<ul class="sidemenu">
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=1&s=0">Аккредитация</a>
	<? if($category == "1"){ ?>
	<div style="margin-left: 25px;" >
		<ul class="sidesubmenu">
		<li><a href="services.php?c=1&s=1">Аккредитация сотрудников представительств</a></li>
		<li><a href="services.php?c=1&s=2">Аккредитация филиалов иностранных компаний при ГРП</a></li>
		<li><a href="services.php?c=1&s=3">Продление аккредитации представительств</a></li>
		<li><a href="services.php?c=1&s=4">Смена главы представительства иностранных компаний при ГРП</a></li>
		</ul>
	</div>
	<? } ?>
	</li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=2&s=1">Бухгалтерское сопровождение</a></li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=3&s=1">Внесение изменений</a></li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=4&s=1">Интеллектуальная собственность</a></li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=5&s=1">Ликвидация предприятий</a></li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=6&s=1">Лицензирование</a></li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=7&s=1">Нормативная база</a></li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=8&s=0">Регистрация акций</a>
	<? if($category == "8"){ ?>
	<div style="margin-left: 25px;" >
		<ul class="sidesubmenu">
		<li><a href="services.php?c=8&s=1">Регистрация доп. выпуска ценных бумаг</a></li>
		<li><a href="services.php?c=8&s=2">Регистрация отчета</a></li>
		<li><a href="services.php?c=8&s=3">Регистрация эмиссии при реорганизации ЮЛ</a></li>
		<li><a href="services.php?c=8&s=4">Регистрация эмиссий при учреждении АО</a></li>
		</ul>
	</div>
	<? } ?>
	
	
	
	
	</li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=9&s=1">Создание бизнеса</a></li>
	</ul>
<? } 

include "template.php";
?>
