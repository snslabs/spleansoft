<?php
class TourCat{
    private $TABLE = "wp_tour_cat";
    public $ID;
    public $TOUR_CAT_ID;
    public $LANG_ID;
    public $TOUR_CAT_NAME;

    function __construct($arr = null){
        if($arr != null){
            $this->ID = $arr['ID'];
            $this->TOUR_CAT_ID = $arr['TOUR_CAT_ID'];
            $this->LANG_ID = $arr['LANG_ID'];
            $this->TOUR_CAT_NAME = $arr['TOUR_CAT_NAME'];
           //  echo "TourCat{ id= ".$this->ID."; tourCatId=".$this->TOUR_CAT_ID."; langId=".$this->LANG_ID."; tourCatName=".$this->TOUR_CAT_NAME." }<br>";
        }
    }

    function init($row){
        $this->ID = $row->ID;
        $this->TOUR_CAT_ID = $row->TOUR_CAT_ID;
        $this->LANG_ID = $row->LANG_ID;
        $this->TOUR_CAT_NAME = $row->TOUR_CAT_NAME;
        // echo "TourCat{ id= ".$this->ID."; tourCatId=".$this->TOUR_CAT_ID."; langId=".$this->LANG_ID."; tourCatName=".$this->TOUR_CAT_NAME." }<br>";
    }


    public function toString(){
        return "TourCat{ id= ".$this->ID."; tourCatId=".$this->TOUR_CAT_ID."; langId=".$this->LANG_ID."; tourCatName=".$this->TOUR_CAT_NAME." }";
    }

    public function save() {
        global $wpdb;
        echo "TourCat{ id= ".$this->ID."; tourCatId=".$this->TOUR_CAT_ID."; langId=".$this->LANG_ID."; tourCatName=".$this->TOUR_CAT_NAME." }<br>";

        if($this->ID != ""){
            // можно изменить только наименование категории, но не номер и не язык
            $wpdb->update($this->TABLE, array('TOUR_CAT_NAME'=>$this->TOUR_CAT_NAME), array('ID' => $this->ID), array("%s"), array("%d"));
        }
        else if($this->TOUR_CAT_ID != ""){
            // создание нового языка для существующей категории
            $wpdb->insert($this->TABLE, array('LANG_ID'=>$this->LANG_ID, 'TOUR_CAT_ID'=>$this->TOUR_CAT_ID, 'TOUR_CAT_NAME'=>$this->TOUR_CAT_NAME),
                array("%s", "%d", "%s"));
        }
        else{
            // создание новой категории
            // присваиваем новый номер категории тура
            $this->TOUR_CAT_ID = $wpdb->get_var("select IFNULL(max(TOUR_CAT_ID) + 1, 0) from " . $this->TABLE);
            echo "Obtained new Tour_cat_id " . $this->TOUR_CAT_ID;
            $wpdb->insert($this->TABLE, array('LANG_ID'=>$this->LANG_ID, 'TOUR_CAT_ID'=>$this->TOUR_CAT_ID, 'TOUR_CAT_NAME'=>$this->TOUR_CAT_NAME),
                array("%s", "%d", "%s"));
        }
        echo  "<span style='background-color: #DDDDDD'>" . $wpdb->last_error . "</span>";
    }
}
?>