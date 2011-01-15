<?php
/*
class ToursPluginView{
    public $CURRENT_LANG_ID = 'RU';

    function getCurrentLangId(){
        // взять язык из сессии текущего юзера, либо взять дефолтный
        if(isset($_GET['lang'])){
            return $_GET['lang'];
        }
        else{
            return $this->CURRENT_LANG_ID;
        }
    }

    function displayCategories(){
        $html = "<h2>Целевой туризм</h2>";
        global $wpdb;
        $q = $wpdb->prepare("select TOUR_CAT_ID, TOUR_CAT_NAME from wp_tour_cat where LANG_ID = %s", $this->getCurrentLangId());
        $rs = $wpdb->get_results($q);
        foreach($rs as $row){
            $t = new TourCat();
            $t->init($row);
            $html .= '<a href="#"><?= $t->TOUR_CAT_NAME ?></a><br>';
        }
        
        return $html;
    }


}
*/
?>