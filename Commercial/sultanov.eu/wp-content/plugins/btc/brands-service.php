<?php
/**
 This file contains AJAX service implementation for editing Tours plugin settings
 */
	define('CUR_PATH',dirname(__FILE__) . '/');
	require_once(CUR_PATH . '../../../wp-config.php');
	require_once(CUR_PATH . 'controls.php');
    require_once(CUR_PATH . 'brand-edit.php');
    require_once(CUR_PATH . 'classes/brand.php');

class BrandService{

    function brand_list(){
        $data = array();
        $langId = $_POST['LANG_ID'];
        $brandCatId = $_POST['BRAND_CAT_ID'];
        global $wpdb;
        global $akmalPlugin;

        $rs = $wpdb->get_results( $wpdb->prepare( "SELECT * FROM wp_brand ORDER BY BRAND_ID asc " ) );
        echo "<span style='background-color: #DDDDDD'>" . $wpdb->last_error . "</span>";

        $old = null;
        $langArray = array();
        $_PK = null;
        foreach($rs as $row){
            $t = new Brand();
            $t->init($row);
            if($old == null){
                $old = new Brand();
                // $old->init($row);
            }
            $_PK = $t->_PK;
            if(isset($t->$_PK) && $t->$_PK != $old->$_PK){
                $old->_LANGS = $langArray;
                array_push($data, $old);
                // initializing lang list
                $langArray = array();
                foreach($akmalPlugin->getLanguages() as $key => $value){
                    // removing existing language
                    if($key != $t->LANG_ID)
                        $langArray[$key] = $value;
                }
                $old = $t;
            }
            else{
                unset($langArray[$t->LANG_ID]);
                if($t->LANG_ID == $langId){
                    $old = $t;
                }
            }
        }

        if(isset($old->$_PK)){
            $old->_LANGS = $langArray;
            array_push($data, $old);
        }

        return $this->grid("Список брендов", $data, $langId);
    }

    function brand_cat_list(){

    }

    function brand_edit(){
        global $wpdb;
        $id = $_POST["ID"];
        $query = $wpdb->prepare("SELECT * FROM wp_brand WHERE id = %d", $id);
        $brand = new Brand();
        $brand->init($wpdb->get_row($query));
        renderEditBrand($brand);
    }

    function brand_create(){
        $brand = new Brand();
        $brand->LANG_ID = $_POST["LANG_ID"];
        $brand->BRAND_ID = $_POST["BRAND_ID"];
        $brand->IS_ACTIVE = true;
        renderEditBrand($brand);
    }

    function brand_save(){
        return $this->brandSave();
    }

    function grid($gridTitle, Array $data, $langId){
        ?>
        <h4><?= $gridTitle ?></h4>
        <table width="100%">
            <tr>
                <td>Язык:<?= langControl('brand_lang_id',$langId,'brand_lang_id') ?></td>
                <td align="right"><input type="button" value="К списку категорий" onClick='listBrandCategories()' class="button"/></td>
            </tr>
        </table>

        <table width="100%" class="tours_table">
            <thead><tr><th>Номер</th><th>Наименование</th><th>Добавить перевод</th></tr></thead>
        <?php
            foreach($data as $brand){
                echo $this->renderRow($brand, $brand->_LANGS);
            }
    ?>  </table>
        <div id="buttonsPlaceholder" align="right">
            <input type="button" value="Добавить бренд" class="button" onclick="createBrand()">
        </div>
        <script type="text/javascript">
        jQuery('#brand_lang_id').bind('change', function(){ listBrands(); });
        </script>
    <?php
    }

    function renderRow(Brand $brand, $langArray){
        if(!isset($brand->ID)) return "";
        $html = '<tr><td>' . $brand->BRAND_ID . '</td><td><a href="javascript:editBrand('. $brand->ID .')">' . $brand->BRAND_NAME . '</a></td>'
        .'<td>';
        foreach($langArray as $key => $value){
            $html .=  "<a href=\"javascript:createBrandTranslation(".$brand->BRAND_ID.",'". $key . "')\">" . $key . "</a> ";
        }
        $html .= '</td></tr>';
        return $html;
    }


	function brandCatList(){
		$langId = $_POST['LANG_ID'];
		global $wpdb;
        global $akmalPlugin;

		$rs = $wpdb->get_results( "SELECT ID, TOUR_CAT_ID, LANG_ID, TOUR_CAT_NAME FROM wp_tour_cat ORDER BY TOUR_CAT_ID asc" );
		?>
        <h4>Список категорий туров</h4>
        <table width="100%">
            <tr>
                <td>Язык:<?= langControl('tour_lang_id',$langId,'tour_lang_id') ?></td>
                <td align="right"><input type="button" value="Обновить список" onClick='tourReload()' class="button"/></td>
            </tr>
        </table>

        <table width="100%" class="tours_table">
			<thead><tr><th>Номер</th><th>Наименование</th><th>Добавить перевод</th><th colspan="2">Туры</th></tr></thead>
		<?php
		$html = '';
        $old = new TourCat();
        $langArray = array();
		foreach($rs as $row){
            $t = new TourCat();
            $t->init($row);
            // echo $t->toString();
            if($t->TOUR_CAT_ID != $old->TOUR_CAT_ID){

                $html .= renderRow($old, $langArray);
                // initializing lang list
                foreach($akmalPlugin->getLanguages() as $key => $value){
                    // removing existing language
                    if($key != $t->LANG_ID)
                        $langArray[$key] = $value;
                }
                $old = $t;
            }
            else{
                unset($langArray[$t->LANG_ID]);
                if($t->LANG_ID == $langId){
                    $old = $t;
                }
            }
		}

        if($old->TOUR_CAT_ID != null){
            unset($langArray[$t->LANG_ID]);
            $html .= renderRow($old, $langArray);
        }


		echo $html;
	?>  </table>
        <div id="buttonsPlaceholder" align="right">
            <input type="button" value="Добавить категорию" class="button" onclick="createTourCat()">
        </div>
        <script type="text/javascript">
        jQuery('#tour_lang_id').bind('change', tourReload);
        </script>
	<?php
	}
/*
    function renderRow($old, $langArray){
        if(!isset($old->ID)) return "";
        $html = '<tr><td>' . $old->TOUR_CAT_ID . '</td><td><a href="javascript:editTourCat('. $old->ID .')">' . $old->TOUR_CAT_NAME . '</a></td>'
        .'<td>';
        foreach($langArray as $key => $value){
            $html .=  "<a href=\"javascript:createTourCatTranslation(".$old->TOUR_CAT_ID.",'". $key . "')\">" . $key . "</a> ";
        }
        $html .= '</td><td><a href="javascript:listTours('.$old->TOUR_CAT_ID.')">Список</a></td>';
        $html .= '<td><a href="javascript:createTour('.$old->TOUR_CAT_ID.')">Добавить</a></td></tr>';
        return $html;
    }
*/
    function brandCatEditForm($t){
        global $akmalPlugin;
        ?>
        <h4>Редактирование категории туров</h4>
        <form action="#" id="tourCatForm">
            <table width="100%">
                <tr>
                    <td><b>Номер категории:</b> <?= $t->TOUR_CAT_ID ?> <?= hiddenControl('TOUR_CAT_ID', $t->TOUR_CAT_ID, 'TOUR_CAT_ID') ?></td>
                    <td nowrap><b>Язык:</b> <?= $akmalPlugin->LANGUAGES[$t->LANG_ID] ?>  <?= hiddenControl('LANG_ID', $t->LANG_ID, 'LANG_ID') ?> </td>
                    <td nowrap><span style="color:#a0a0a0" >ID: <?= $t->ID ?> <?= hiddenControl('ID', $t->ID, 'ID') ?></span></td>
                </tr>
                <tr>
                    <td><b>Наименование:</b></td><td><?= textControl('TOUR_CAT_NAME', $t->TOUR_CAT_NAME, 'TOUR_CAT_NAME' ) ?></td>
                </tr>
            </table>
            <table width="100%" class="buttonsPlaceholder"><tr>
                <td width="33%" align="left"><input type="button" value="Сохранить" class="button" onClick="saveTourCat();"></td>
                <td width="33%" align="center"><input type="reset" value="Сброс" class="button"></td>
                <td align="right"><input type="button" value="Выход" class="button" onClick="tourReload();"></td>
            </tr></table>
        </form>
        <script type="text/javascript">
            function tourCatForm(){

            }
        </script>
        <?php
    }

    function tourEditForm($tour){
        renderEditTour($tour);
    }

	function tourCatEdit($id){
		global $wpdb;
		$query = $wpdb->prepare("SELECT ID, TOUR_CAT_ID, LANG_ID, TOUR_CAT_NAME FROM wp_tour_cat WHERE id = %d", $id);
		$t = new TourCat();
        $t->init($wpdb->get_row($query));
        tourCatEditForm($t);
	}

	function brandEdit($id){
		global $wpdb;
		$query = $wpdb->prepare("SELECT * FROM wp_brand WHERE id = %d", $id);
		$t = new Brand();
        $t->init($wpdb->get_row($query));
        brandEditForm($t);
	}

    function tourCatCreate($langId, $tourCatId=null){
        $t = new TourCat();
        $t->LANG_ID = $langId;
        $t->TOUR_CAT_ID = $tourCatId;
        tourCatEditForm($t);
    }

    function tourCreate($langId, $tourCatId, $tourId = null){
        $t = new Tour();
        $t->TOUR_CAT_ID = $tourCatId;
        $t->LANG_ID = $langId;
        $t->TOUR_ID = $tourId;
        $t->IS_ACTIVE = 1;
        brandEditForm($t);
    }

    function tourCatSave(){
        global $wpdb;
        foreach($_POST as $key => $value){
             // debug output
            /*
            ?> <?= $key ?> : <?= $value ?><br><?php
            */
        }
        $tourCat = new TourCat($_POST);
        $tourCat->save();
        tourCatList();

    }

    function brandSave(){
        foreach($_POST as $key => $value){
             // debug output
            ?> <pre><?= $key ?> : <?= $value ?></pre><br><?php
        }
        $brand = new Brand();
        $brand->initArray($_POST);
        $brand->save();
        $this->brand_list();
    }

    function unescapeImages($str){
        return str_replace("\\\"", "\"", $str);
    }
}

$brandService = new BrandService();
// dispatching request
if(isset($_POST['action'])){
	$action =  $_POST['action'];

    if( method_exists($brandService, $action) ){
        $brandService->$action();
    }
    else{
        echo "Неверный вызов! Метод " . $action  . " не определен.";
    }
}
else{
	echo "Неверный вызов!";
}
?>