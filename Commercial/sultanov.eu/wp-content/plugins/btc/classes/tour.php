<?php
include_once('entity.php');

class Tour extends Entity{
    // table name
    public $_TABLE = "wp_brand";
    // promary key property name
    public $_PK = "TOUR_ID";
    
    public $TOUR_CAT_ID;
    public $TOUR_ID;
    public $LANG_ID;
    public $TOUR_NAME;
    public $TOUR_DATE;
    public $TOUR_DESC;
    public $TOUR_ARTICLE_URL;
    public $TOUR_PDF_URL;
    public $IS_ACTIVE;

    function initArray($arr = null){
        $this->TOUR_DESC = unescapeImages($this->TOUR_DESC);        
    }

    function unescapeImages($str){
        return str_replace($str, "\\\"", "\"");
    }
/*
    public function save() {
        global $wpdb;
        // echo $this->toString();

        if($this->ID != ""){
            // можно изменить только наименование тура, описание, ссылки на статью и PDF, но не номер и не язык
            $wpdb->update($this->TABLE,
                    array(  'TOUR_NAME'=>$this->TOUR_NAME,
                            'TOUR_DATE'=>$this->TOUR_DATE,
                            'TOUR_DESC'=>$this->TOUR_DESC,
                            'TOUR_ARTICLE_URL'=>$this->TOUR_ARTICLE_URL,
                            'TOUR_PDF_URL'=>$this->TOUR_PDF_URL,
                            'IS_ACTIVE'=>$this->IS_ACTIVE,),
                    array('ID' => $this->ID), array("%s", "%s" ,"%s","%s","%s","%d"), array("%d"));
        }
        else if($this->TOUR_ID != ""){
            // создание нового языка для существующего тура
            $wpdb->insert($this->TABLE,
                    array(  'LANG_ID'=>$this->LANG_ID,
                            'TOUR_CAT_ID'=>$this->TOUR_CAT_ID,
                            'TOUR_ID'=>$this->TOUR_ID,
                            'TOUR_NAME'=>$this->TOUR_NAME,
                            'TOUR_DATE'=>$this->TOUR_DATE,
                            'TOUR_DESC'=>$this->TOUR_DESC,
                            'TOUR_ARTICLE_URL'=>$this->TOUR_ARTICLE_URL,
                            'TOUR_PDF_URL'=>$this->TOUR_PDF_URL,
                            'IS_ACTIVE'=>$this->IS_ACTIVE,),
                array("%s", "%d", "%d", "%s", "%s", "%s", "%s", "%s", "%d"));
        }
        else{
            // создание нового тура
            // присваиваем новый тура
            $this->TOUR_ID = $wpdb->get_var("select IFNULL(max(TOUR_ID) + 1, 0) from " . $this->TABLE);
            echo "Obtained new Tour_id " . $this->TOUR_ID;
            $this->save();
        }
        echo  "<span style='background-color: #DDDDDD'>" . $wpdb->last_error . "</span>";
    }
    */

}

?>