<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="<?php bloginfo('html_type'); ?>; charset=<?php bloginfo('charset'); ?>" />
<title><?php	/*	 * Print the <title> tag based on what is being viewed.	 */	global $page, $paged;	wp_title( '|', true, 'right' );	// Add the blog name.	bloginfo( 'name' );	// Add the blog description for the home/front page.	$site_description = get_bloginfo( 'description', 'display' );	if ( $site_description && ( is_home() || is_front_page() ) )		echo " | $site_description";	// Add a page number if necessary:	if ( $paged >= 2 || $page >= 2 )		echo ' | ' . sprintf( __( 'Page %s', 'twentyten' ), max( $paged, $page ) );	?></title>
<meta name="keywords" content="mobile, application, development, iphone, ipad, ipod, touch, ios, android, windows, start, app, project, amaya, software" />
<link rel="icon" href="<?php bloginfo( 'template_url' ); ?>/favicon.ico" type="image/x-icon" />
<link type="text/css" rel="stylesheet" href="<?php bloginfo( 'template_url' ); ?>/styles/style.css" />
<link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="<?php bloginfo('rss2_url'); ?>" />
<!--[if lt IE 7]><style type="text/css">@import url('<?php bloginfo( 'template_url' ); ?>/styles/ie_style.css');</style><![endif]-->
<!--[if IE 7]><style type="text/css">@import url('<?php bloginfo( 'template_url' ); ?>/styles/ie7_style.css');</style><![endif]-->
<?php wp_get_archives('type=monthly&format=link'); ?> 
<?php wp_head(); // API Hook ?>
<script language="javascript" type="text/javascript" src="<?php bloginfo( 'template_url' ); ?>/js/functions.js"></script>
</head>
<body>
<div class="warp">
  <div id="context">  


<div id="top"><a href="<?php bloginfo('url'); ?>" title="Home Page"><img src="<?php bloginfo( 'template_url' ); ?>/img/logo.jpg" align="left" alt="Sultanov ltd. logo" class="logo" /></a>
      <div class="topmenu"><?php parse_menu(wp_list_pages('title_li=&depth=1&echo=0'),get_bloginfo("template_url"));?></div>
      <form id="search" name="search" method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">
        <a href="#search" onclick="document.forms[0].submit()">Search</a>
        <input class="search" type="text" value="Search" name="s" id="s" onfocus="if
(this.value == 'Search') {this.value = '';}" onblur="if (this.value == '') {this.value =
'Search';}"  />
        <input class="hsubmit" type="submit" value="Search" />
      </form>
    </div>
    <div id="menu"><?php parse_submenu(wp_list_pages('title_li=&echo=0&child_of=0'),get_bloginfo("template_url"));?></div>