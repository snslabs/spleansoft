<?
$currentMenuItem = "������";
$pageId="services";
function sumbenuItems(){ 
	$category = @$_REQUEST["c"];
?>
	<ul class="sidemenu">
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=1&s=0">������������</a>
	<? if($category == "1"){ ?>
	<div style="margin-left: 25px;" >
		<ul class="sidesubmenu">
		<li><a href="services.php?c=1&s=1">������������ ����������� ����������������</a></li>
		<li><a href="services.php?c=1&s=2">������������ �������� ����������� �������� ��� ���</a></li>
		<li><a href="services.php?c=1&s=3">��������� ������������ ����������������</a></li>
		<li><a href="services.php?c=1&s=4">����� ����� ����������������� ����������� �������� ��� ���</a></li>
		</ul>
	</div>
	<? } ?>
	</li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=2&s=1">������������� �������������</a></li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=3&s=1">�������� ���������</a></li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=4&s=1">���������������� �������������</a></li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=5&s=1">���������� �����������</a></li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=6&s=1">��������������</a></li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=7&s=1">����������� ����</a></li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=8&s=0">����������� �����</a>
	<? if($category == "8"){ ?>
	<div style="margin-left: 25px;" >
		<ul class="sidesubmenu">
		<li><a href="services.php?c=8&s=1">����������� ���. ������� ������ �����</a></li>
		<li><a href="services.php?c=8&s=2">����������� ������</a></li>
		<li><a href="services.php?c=8&s=3">����������� ������� ��� ������������� ��</a></li>
		<li><a href="services.php?c=8&s=4">����������� ������� ��� ���������� ��</a></li>
		</ul>
	</div>
	<? } ?>
	
	
	
	
	</li>
	<li><img border="0" src="images/closed.gif" />&nbsp;<a href="services.php?c=9&s=1">�������� �������</a></li>
	</ul>
<? } 

include "template.php";
?>
