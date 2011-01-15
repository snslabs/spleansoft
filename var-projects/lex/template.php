<HTML>
<HEAD>
	<TITLE>Юридические услуги</TITLE>
	<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=Windows-1251">
	<link rel="stylesheet" type="text/css" href="styles.css">
</HEAD>
<BODY>
<TABLE  width="100%" BORDER=0 CELLPADDING=0 CELLSPACING=0>
	<TR>
		<TD>
			<IMG SRC="images/index_01.jpg" WIDTH=237 HEIGHT=86></TD>
		<TD COLSPAN=7 width="466">
			<IMG SRC="images/index_02.jpg" WIDTH=466 HEIGHT=86></TD>
		<TD>&nbsp;</TD>
	</TR>
	<TR>
		<TD background="images/index_04.jpg" WIDTH=237>&nbsp;</TD>
		<TD COLSPAN=7 width="466" background="images/index_12.jpg">
			<TABLE  BORDER=0 CELLPADDING=0 CELLSPACING=0>
				<tr>
					<TD WIDTH=68><a href="index.php"><IMG SRC="images/index_05.jpg" border="0" WIDTH=68 HEIGHT=23></a></TD>
					<TD WIDTH=60>
						<a href="services.php"><IMG SRC="images/index_06.jpg" border="0" WIDTH=60 HEIGHT=23></a></TD>
					<TD WIDTH=74>
						<a href="price.php"><IMG SRC="images/index_07.jpg" border="0" WIDTH=74 HEIGHT=23></a></TD>
					<TD WIDTH=82>
						<a href="info.php"><IMG SRC="images/index_08.jpg" border="0" WIDTH=82 HEIGHT=23></a></TD>
					<TD WIDTH=60>
						<a href="laws.php"><IMG SRC="images/index_09.jpg" border="0" WIDTH=60 HEIGHT=23></a></TD>
					<TD WIDTH=61>
						<a href="news.php"><IMG SRC="images/index_10.jpg" border="0" WIDTH=61 HEIGHT=23></a></TD>
					<TD WIDTH=61>
						<a href="contacts.php"><IMG SRC="images/index_11.jpg" border="0" WIDTH=61 HEIGHT=23></a></TD>
				</tr>
			</table>
		</td>
		<TD background="images/index_12.jpg">&nbsp;</td>
<!-- 			<IMG SRC="" WIDTH=77 HEIGHT=23></TD> -->
	</TR>
	<TR>
		<TD width="237" valign="top" style="background: url(images/index_13.jpg) no-repeat;">
		<img src="images/index_13.jpg" border="0">
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td valign="top" class="side_menu">
					<div class="side_menu">
						<span class="menutitle"><?= $currentMenuItem ?></span><br>
						<hr>
						<div class="side_menu" style="margin-left: 20px; margin-top: 5px;">
							<? sumbenuItems() ?>
						</div>
						<hr>
						<? include("content/briefs.php"); ?>
						<div align="center">
							<img src="images/index_20.jpg">
						</div>
					</div>
				</td>
				<td><img src="s.jpg" height="200" width="1"></td>
			</tr>
		</table>
		</TD>
		<TD COLSPAN=8 valign="top">
			<div style="margin-left: 5px; margin-top: 5px; margin-bottom: 20px;" class="content">
				<?
				include("content/".$pageId.".php");
				 ?>
			</div>
		</TD>
	</TR>
	<TR>
		<TD COLSPAN=9 background="images/index_12.jpg" height="21" valign="middle" >
			<div class="footer">&nbsp;&nbsp;<font point-size="8">(C) 2006-2007, ЗАО "Юридические услуги", Москва, 100000, Красная площадь, д.1, тел.:555-55-55</font></div>
		</TD>

	</TR>
</TABLE>
<!-- End ImageReady Slices -->
</BODY>
</HTML>