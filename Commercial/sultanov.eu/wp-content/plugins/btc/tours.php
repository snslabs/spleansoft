<?php
define('TOUR_SERVICE_URL' ,get_option('home') . '/wp-content/plugins/btc/tours-service.php');
//define('CUR_PATH',dirname(__FILE__) . '/');
require_once(CUR_PATH . 'controls.php');
require_once(CUR_PATH . 'view.php');
require_once(CUR_PATH . 'velocity.php');

class TourManager{
    private $akmalPlugin;

    function TourManager(AkmalPlugin $_akmalPlugin){
        global $akmalPlugin;
        $this->akmalPlugin = $akmalPlugin;
    }

    function adminForm(){
		// dispatching
			if(isset($_POST['submit'])){
			
			}
			else{
      ?>
      <div class='wrap'>
	  <div id='icon-options-general' class='icon32'><br></div>
		<h2>Настройка туров</h2>
		<?php
				$this->showTourCategoryList();
		?>
      </div>
      <?php
			}
    }

    function getLanguages(){
        return $this->akmalPlugin->LANGUAGES;
    }

    function getDefaultLang(){
        return $this->akmalPlugin->DEFAULT_LANGUAGE;
    }

    function tourListShortCodeHandler($attrs, $content = null){
        $html = "
        <div id='tour_content' ><table width='100%'>";
        global $wpdb;
        $rsCategories = $wpdb->get_results($wpdb->prepare("select TOUR_CAT_ID, TOUR_CAT_NAME from wp_tour_cat where LANG_ID = %s", $this->getLangId()));
        foreach($rsCategories as $rCategory){
            $category = new TourCat();
            $category->init($rCategory);
            $html .= '<tr><td colspan="2">' . $category->TOUR_CAT_NAME . '</td></tr>';

            $rsTours = $wpdb->get_results($wpdb->prepare("select * from wp_tour where LANG_ID = %s and TOUR_CAT_ID = %d", $this->getLangId(), $category->TOUR_CAT_ID));
            foreach($rsTours as $rTour){
                $tour = new Tour();
                $tour->init($rTour);
                $html .= velocity_process_template("tour_list_element.tpl", $tour);
            }
        }
        $html .= "</table></div>";
        return $html;
    }

	function showTourCategoryList(){
		?>
        <script src="/wp-content/plugins/btc/files/js/swfupload.js" type="text/javascript"></script>
        <script src="/wp-content/plugins/btc/files/js/script.js" type="text/javascript"></script>
		<script language="JavaScript" type="text/javascript">
		function tourReload(){
            var langId = jQuery('#tour_lang_id').val() == undefined ? "<?= $this->DEFAULT_LANG ?>" : jQuery('#tour_lang_id').val();
			jQuery.post('<?php echo TOUR_SERVICE_URL ?>', {action : 'tour_cat_list', LANG_ID : langId } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
		}
		function listTours(tourCatId){
            var langId = jQuery('#tour_lang_id').val() == undefined ? "<?= $this->DEFAULT_LANG ?>" : jQuery('#tour_lang_id').val();
			jQuery.post('<?php echo TOUR_SERVICE_URL ?>', {action : 'tour_list', LANG_ID : langId, TOUR_CAT_ID: tourCatId } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
		}
		function editTourCat(id){
			jQuery.post('<?php echo TOUR_SERVICE_URL ?>', {action : 'tour_cat_edit', ID : id } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
		}
		function editTour(id){
			jQuery.post('<?php echo TOUR_SERVICE_URL ?>', {action : 'tour_edit', ID : id } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
		}
        function createTourCat(){
            jQuery.post('<?php echo TOUR_SERVICE_URL ?>', {action : 'tour_cat_create', LANG_ID : jQuery('#tour_lang_id').val() } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
        }
        function createTourCatTranslation(tourCatId, langId){
            jQuery.post('<?php echo TOUR_SERVICE_URL ?>', {action : 'tour_cat_create', LANG_ID : langId, TOUR_CAT_ID: tourCatId } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
        }
        function createTourTranslation(tourCatId, langId, tourId){
            jQuery.post('<?php echo TOUR_SERVICE_URL ?>', {action : 'tour_create', LANG_ID : langId, TOUR_CAT_ID: tourCatId, TOUR_ID: tourId } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
        }
        function createTour(tourCatId){
            jQuery.post('<?php echo TOUR_SERVICE_URL ?>', {action : 'tour_create', LANG_ID : jQuery('#tour_lang_id').val(), TOUR_CAT_ID: tourCatId } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
        }
        function saveTourCat(){
            var arr = jQuery("#tourCatForm").serializeArray();
            var data = {action: 'tour_cat_save'};
            for(var i = 0; i < arr.length; i++){
                data[arr[i].name] = arr[i].value;
            }
            jQuery.post('<?php echo TOUR_SERVICE_URL ?>', data, function(data) {  jQuery('#tablePlaceholder').html(data); });
        }

		function cancelEditCat(){
			jQuery('#formPlaceholder').html("");
		}
		</script>
		<style type="text/css">
		.tours_table th{
			background-color: #B0B0B0;
		}
		.tours_table td{
			background-color: white;
		}
        .buttonsPlaceholder input.button{
            width: 100px;
        }
		</style>
        <div style="width: 600px;">
            <div id="tablePlaceholder">
            </div>
            <br><br>
            <div id="formPlaceholder">
            </div>
        </div>
        <script>tourReload()</script>
<?php
	}

    function getLangId(){
        global $akmalPlugin;
        return $akmalPlugin->getLangId();
    }

}
?>