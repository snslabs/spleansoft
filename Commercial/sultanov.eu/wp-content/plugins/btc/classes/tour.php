<?php
class Tour{
    private $TABLE = "wp_tour";
    public $ID;
    public $TOUR_CAT_ID;
    public $TOUR_ID;
    public $LANG_ID;
    public $TOUR_NAME;
    public $TOUR_DESC;
    public $TOUR_ARTICLE_URL;
    public $TOUR_PDF_URL;
    public $IS_ACTIVE;

    function __construct($arr = null){
        if($arr != null){
            $this->ID = $arr['ID'];
            $this->TOUR_CAT_ID = $arr['TOUR_CAT_ID'];
            $this->TOUR_ID = $arr['TOUR_ID'];
            $this->LANG_ID = $arr['LANG_ID'];
            $this->TOUR_NAME = $arr['TOUR_NAME'];
            $this->TOUR_DESC = unescapeImages($arr['TOUR_DESC']);
            $this->TOUR_ARTICLE_URL = $arr['TOUR_ARTICLE_URL'];
            $this->TOUR_PDF_URL = $arr['TOUR_PDF_URL'];
            $this->IS_ACTIVE = $arr['IS_ACTIVE'];
        }
    }

    function unescapeImages($str){
        return str_replace($str, "\\\"", "\"");
    }


    function init($row){
        $this->ID = $row->ID;
        $this->TOUR_CAT_ID = $row->TOUR_CAT_ID;
        $this->TOUR_ID = $row->TOUR_ID;
        $this->LANG_ID = $row->LANG_ID;
        $this->TOUR_NAME = $row->TOUR_NAME;
        $this->TOUR_DESC = $row->TOUR_DESC;
        $this->TOUR_ARTICLE_URL = $row->TOUR_ARTICLE_URL;
        $this->TOUR_PDF_URL = $row->TOUR_PDF_URL;
        $this->IS_ACTIVE = $row->IS_ACTIVE;
    }
    
    public function toString(){
        return "Tour{ id= ".$this->ID."; tourCatId=".$this->TOUR_CAT_ID."; tourId=".$this->TOUR_ID."; " 
        ."langId=".$this->LANG_ID."; tourName=".$this->TOUR_NAME."; isActive=".$this->IS_ACTIVE."; }";
    }

    public function save() {
        global $wpdb;
        // echo $this->toString();

        if($this->ID != ""){
            // можно изменить только наименование тура, описание, ссылки на статью и PDF, но не номер и не язык
            $wpdb->update($this->TABLE,
                    array(  'TOUR_NAME'=>$this->TOUR_NAME,
                            'TOUR_DESC'=>$this->TOUR_DESC,
                            'TOUR_ARTICLE_URL'=>$this->TOUR_ARTICLE_URL,
                            'TOUR_PDF_URL'=>$this->TOUR_PDF_URL,
                            'IS_ACTIVE'=>$this->IS_ACTIVE,),
                    array('ID' => $this->ID), array("%s","%s","%s","%s","%d"), array("%d"));
        }
        else if($this->TOUR_ID != ""){
            // создание нового языка для существующего тура
            $wpdb->insert($this->TABLE,
                    array(  'LANG_ID'=>$this->LANG_ID,
                            'TOUR_CAT_ID'=>$this->TOUR_CAT_ID,
                            'TOUR_ID'=>$this->TOUR_ID,
                            'TOUR_NAME'=>$this->TOUR_NAME,
                            'TOUR_DESC'=>$this->TOUR_DESC,
                            'TOUR_ARTICLE_URL'=>$this->TOUR_ARTICLE_URL,
                            'TOUR_PDF_URL'=>$this->TOUR_PDF_URL,
                            'IS_ACTIVE'=>$this->IS_ACTIVE,),
                array("%s", "%d", "%d", "%s", "%s", "%s", "%s", "%d"));
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

}

?>