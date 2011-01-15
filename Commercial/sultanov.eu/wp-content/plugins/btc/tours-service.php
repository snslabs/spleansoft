<?php
/**
 This file contains AJAX service implementation for editing Tours plugin settings
 */
	define('CUR_PATH',dirname(__FILE__) . '/');
	require_once(CUR_PATH . '../../../wp-config.php');
	require_once(CUR_PATH . 'controls.php');
    require_once(CUR_PATH . 'tour-edit.php');

    function tourList(){
        $langId = $_POST['LANG_ID'];
        $tourCatId = $_POST['TOUR_CAT_ID'];
        global $wpdb;
        global $akmalPlugin;
        
        $rs = $wpdb->get_results($wpdb->prepare( "SELECT * FROM wp_tour WHERE TOUR_CAT_ID = %d ORDER BY TOUR_ID asc ", $tourCatId));
        echo "<span style='background-color: #DDDDDD'>" . $wpdb->last_error . "</span>";
        ?>
        <h4>Список туров категории: <?= $tourCatId ?></h4>
        <table width="100%">
            <tr>
                <td>Язык:<?= langControl('tour_lang_id',$langId,'tour_lang_id') ?></td>
                <td align="right"><input type="button" value="К списку категорий" onClick='tourReload()' class="button"/></td>
            </tr>
        </table>

        <table width="100%" class="tours_table">
            <thead><tr><th>Номер</th><th>Наименование</th><th>Добавить перевод</th></tr></thead>
        <?php
        $html = '';
        $old = new Tour();
        $langArray = array();
        foreach($rs as $row){
            $t = new Tour();
            $t->init($row);
            // echo $t->toString();
            if($t->TOUR_ID != $old->TOUR_ID){

                $html .= renderRowTour($old, $langArray);
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

        if($old->TOUR_ID != null){
            unset($langArray[$t->LANG_ID]);
            $html .= renderRowTour($old, $langArray);
        }


        echo $html;
    ?>  </table>
        <div id="buttonsPlaceholder" align="right">
            <input type="button" value="Добавить тур" class="button" onclick="createTour(<?= $tourCatId ?>)">
        </div>
        <script type="text/javascript">
        jQuery('#tour_lang_id').bind('change', function(){ listTours(<?= $tourCatId ?>); });
        </script>
    <?php
    }

    function renderRowTour($old, $langArray){
        if(!isset($old->ID)) return "";
        $html = '<tr><td>' . $old->TOUR_CAT_ID . '</td><td><a href="javascript:editTour('. $old->ID .')">' . $old->TOUR_NAME . '</a></td>'
        .'<td>';
        foreach($langArray as $key => $value){
            $html .=  "<a href=\"javascript:createTourTranslation(".$old->TOUR_CAT_ID.",'". $key . "',".$old->TOUR_ID.")\">" . $key . "</a> ";
        }
        $html .= '</td></tr>';
        return $html;
    }


	function tourCatList(){
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

    function tourCatEditForm($t){
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

    function brandEditForm($tour){
        renderEditTour($tour);
    }

	function tourCatEdit($id){
		global $wpdb;
		$query = $wpdb->prepare("SELECT ID, TOUR_CAT_ID, LANG_ID, TOUR_CAT_NAME FROM wp_tour_cat WHERE id = %d", $id);
		$t = new TourCat();
        $t->init($wpdb->get_row($query));
        tourCatEditForm($t);
	}

	function tourEdit($id){
		global $wpdb;
		$query = $wpdb->prepare("SELECT * FROM wp_tour WHERE id = %d", $id);
		$t = new Tour();
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

    function tourSave(){
        global $wpdb;
        foreach($_POST as $key => $value){
             // debug output
            /*
            ?> <pre><?= $key ?> : <?= unescapeImages($value) ?></pre><br><?php
            */
        }
        $tour = new Tour($_POST);
        $tour->save();
        tourList();
    }

    function unescapeImages($str){
        return str_replace("\\\"", "\"", $str);
    }

if(isset($_POST['action'])){
	$action =  $_POST['action'];
	if($action == 'tour_cat_list'){
		tourCatList();
	}
	else if($action == 'tour_list'){
		tourList();
	}
	else if ($action == 'tour_cat_edit'){
		tourCatEdit($_POST['ID']);
	}
	else if ($action == 'tour_edit'){
		tourEdit($_POST['ID']);
	}
	else if ($action == 'tour_cat_create'){
        tourCatCreate($_POST['LANG_ID'], $_POST['TOUR_CAT_ID']);
	}
    else if($action == 'tour_cat_save'){
        tourCatSave();
    }
    else if($action == 'tour_create'){
        tourCreate($_POST['LANG_ID'], $_POST['TOUR_CAT_ID'], $_POST['TOUR_ID']);
    }
    else if($action == 'tour_save'){
        tourSave();
    }
	else{
		tourCatList();
	}
}
else{
	echo "Неверный вызов!";
}
?>