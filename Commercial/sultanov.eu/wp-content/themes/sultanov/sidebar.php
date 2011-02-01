<div id="rcolum">
  <h3>Footbal</h3>
  <img src="<?php bloginfo( 'template_url' ); ?>/img/arsenal.gif" alt="arsena club" /><img src="<?php bloginfo( 'template_url' ); ?>/img/vs.gif" alt="vs" /><img src="<?php bloginfo( 'template_url' ); ?>/img/chelsea.gif" alt="chelsea club" />
  <p align="center"><a class="biglink" href="#footbal" title="It's will be interesting!">Do you want to<br />
    see it life?</a></p>
  <br />
  <h3>Brands</h3>
  <p><a href="#brands" title="Brands page"> <img src="<?php bloginfo( 'template_url' ); ?>/img/brands/b1.gif" alt="next logo" /><img src="<?php bloginfo( 'template_url' ); ?>/img/brands/b2.gif" alt="acension logo" /> <img src="<?php bloginfo( 'template_url' ); ?>/img/brands/b3.gif" alt="ugg logo" /><img src="<?php bloginfo( 'template_url' ); ?>/img/brands/b4.gif" alt="acne logo" /> <img src="<?php bloginfo( 'template_url' ); ?>/img/brands/b5.gif" alt="asos logo" /><img src="<?php bloginfo( 'template_url' ); ?>/img/brands/b6.gif" alt="addidas logo" /></a> </p>
  <h3>Curency</h3>
  <p>&pound; GBP .......................... 2,521.83<br />
    &euro; EUR .......................... 2,063.00<br />
    $ USD .......................... 1,614.49<br />
    &yen; JPY ........................... 189.52</p>
  <!-- side menu -->
  <?php 
  get_rightmenu(); // output irht menu(Levon)
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
