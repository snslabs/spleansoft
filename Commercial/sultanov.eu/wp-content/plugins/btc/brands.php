<?php
//define('TOUR_SERVICE_URL' ,get_option('home') . '/wp-content/plugins/btc/tours-service.php');
//define('CUR_PATH',dirname(__FILE__) . '/');
require_once(CUR_PATH . 'controls.php');
require_once(CUR_PATH . 'view.php');
require_once(CUR_PATH . 'velocity.php');

class BrandManager{
    private $akmalPlugin;

    function __contructor(AkmalPlugin $_akmalPlugin){
        $this->akmalPlugin = $_akmalPlugin;
    }

    function adminForm(){
		// dispatching
			if(isset($_POST['submit'])){
			
			}
			else{
      ?>
      <div class='wrap'>
	  <div id='icon-options-general' class='icon32'><br></div>
		<h2>Настройка брендов</h2>
		<?php
				$this->showMainForm();
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

	function showMainForm(){
		?>
        <script src="/wp-content/plugins/btc/files/js/swfupload.js" type="text/javascript"></script>
        <script src="/wp-content/plugins/btc/files/js/script.js" type="text/javascript"></script>
		<script language="JavaScript" type="text/javascript">
		function listBrands(){
            var langId = jQuery('#tour_lang_id').val() == undefined ? "<?= $this->DEFAULT_LANG ?>" : jQuery('#tour_lang_id').val();
			jQuery.post('<?php echo BRAND_SERVICE_URL ?>', {action : 'brand_list', LANG_ID : langId } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
		}
		function listBrandCategories(){
            var langId = jQuery('#tour_lang_id').val() == undefined ? "<?= $this->DEFAULT_LANG ?>" : jQuery('#tour_lang_id').val();
			jQuery.post('<?php echo BRAND_SERVICE_URL ?>', {action : 'brand_cat_list', LANG_ID : langId } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
		}
		function editBrandCat(id){
			jQuery.post('<?php echo BRAND_SERVICE_URL ?>', {action : 'brand_cat_edit', ID : id } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
		}
		function editBrand(id){
			jQuery.post('<?php echo BRAND_SERVICE_URL ?>', {action : 'brand_edit', ID : id } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
		}
        function createBrandCat(){
            jQuery.post('<?php echo BRAND_SERVICE_URL ?>', {action : 'brand_cat_create', LANG_ID : jQuery('#brand_lang_id').val() } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
        }
        function createBrandCatTranslation(brandCatId, langId){
            jQuery.post('<?php echo BRAND_SERVICE_URL ?>', {action : 'brand_cat_create', LANG_ID : langId, brand_CAT_ID: brandCatId } ,function(data) {  jQuery('#tablePlaceholder').html(data); });
        }
        function createBrandTranslation(brandId, langId){
            jQuery.post('<?php echo BRAND_SERVICE_URL ?>', {action : 'brand_create', BRAND_ID: brandId, LANG_ID : langId} ,function(data) {  jQuery('#tablePlaceholder').html(data); });
        }
        function createBrand(){
            jQuery.post('<?php echo BRAND_SERVICE_URL ?>', {action : 'brand_create', LANG_ID : jQuery('#brand_lang_id').val()} ,function(data) {  jQuery('#tablePlaceholder').html(data); });
        }
        function saveTourCat(){
            var arr = jQuery("#brandCatForm").serializeArray();
            var data = {action: 'brand_cat_save'};
            for(var i = 0; i < arr.length; i++){
                data[arr[i].name] = arr[i].value;
            }
            jQuery.post('<?php echo BRAND_SERVICE_URL ?>', data, function(data) {  jQuery('#tablePlaceholder').html(data); });
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
        <script>listBrands()</script>
<?php
	}

}
?>