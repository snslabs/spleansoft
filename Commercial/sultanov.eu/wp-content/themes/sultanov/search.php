<?php	get_header(); ?>    
<div id="lcolum">
<h1>Search results: <a href="?s=<?php the_search_query(); ?>"><?php the_search_query(); ?></a></h1>
<?php include (TEMPLATEPATH . '/loop.php'); ?>
</div>    
<?php	get_sidebar(); ?>
<?php	get_footer(); ?>