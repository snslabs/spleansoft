	<?php	get_header(); ?>    
    <div id="lcolum"> <img class="banner" src="<?php bloginfo( 'template_url' ); ?>/img/banner1.jpg" alt="Marketing banner" /><br />
    <!-- index -->
      <img src="<?php bloginfo( 'template_url' ); ?>/img/akmal.jpg" align="left" alt="Sultanov Akmal" />
      <h1>About the company</h1>
      <p class="lmargin">Hi, my name is Akmal Sultanov and i glad to see you on sultanov.com. We are small, specialist Management Consultancy in the UK. Clients range from start-ups, though to small family firms with a turnover in excess of £1 million and up to International companies turning over £100 million plus. </p>
      <p class="lmargin">The directors at Sultanov have trained of Business Strategy and have a wealth of experience. We have advised companies in manufacturing, services, retailing, wholesaling, importing and exporting. Our strategic consultants have assisted companies in the fields of agriculture, sporting goods, insurance, hotels, computer software, hardware, design and chemicals.</p>
      <p class="lmargin" align="right"><a href="#more">Read full version &raquo;</a></p><br />

	<!-- End of index -->
    <?php include (TEMPLATEPATH . '/loop.php'); ?>
    </div>    
    <?php	get_sidebar(); ?>
    <?php	get_footer(); ?>