<div id="rcolum"> 
  <?php 
  get_side_by_meta('right_colum'); // output irht menu(Levon)
  /* Widgetized sidebar, if you have the plugin installed.  if ( !function_exists('dynamic_sidebar') || !dynamic_sidebar() ) : ?>
  <?php wp_list_pages('title_li=<h3>Pages</h3>' ); ?>
  <?php wp_list_bookmarks(); ?>
  <h3>Archives</h3>
  <p>
    <?php wp_get_archives('type=monthly'); ?>
  </p>
  <h3>Meta</h3>
  <p>
    <?php wp_meta(); // API Hook ?>
    <?php wp_register(); ?>
  </p>
  <?php wp_loginout(); ?>
  <?php endif; */?>
   <ul>
   <?php wp_register(); ?>
   </ul>
  <!-- End side menu -->
</div>
