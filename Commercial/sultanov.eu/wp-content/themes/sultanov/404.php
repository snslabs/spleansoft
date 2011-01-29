<?php	get_header(); ?>    
<div id="center">     	 
        <div id="emsg"><img src="<?php bloginfo( 'template_url' ); ?>/img/404.jpg" alt="404 error"/><h1>Error <b>404</b></h1><p>Page was not found back to <a href="./">Home Page</a></p><div class="clear"></div></div>
       	<?php include (TEMPLATEPATH . '/loop.php'); ?>
    </div>

<?php /*	get_sidebar(); */?>
<?php	get_footer(); ?>