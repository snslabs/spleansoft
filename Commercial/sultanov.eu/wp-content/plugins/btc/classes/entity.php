<?php
/**
 * Created by PhpStorm.
 * User: Serge
 * Date: 18.11.2010
 * Time: 23:10:26
 * To change this template use File | Settings | File Templates.
 */
 
class Entity {

    public $ID;
    public $_LANGS;

    function GetTableName(){
        
    }

    function initArray(Array $arr){
        if($arr != null){
            $vars = get_class_vars(get_class($this));
            foreach($vars as $name => $value){
                if(!(strpos($name, "_") === 0)){
                    $this->$name = $arr[$name];
                    echo "<div style='background-color: green;'>" . $name . " = " . $arr[$name]  . "++" .strpos($name, "_") . " </div>";
                }
            }
        }
    }

    function init($row){
        $vars = get_class_vars(get_class($this));
        foreach($vars as $name => $value){
            if(strpos($name, "_") === false || strpos($name, "_") != 0 ){
                $this->$name = $row->$name;
                // echo "<div>" . $name . " = " . $row->$name  . "++" .strpos($name, "_") . " </div>";
            }
        }
    }

    public function toString(){
        $str = "( " . get_class($this) . " ){";
        foreach(get_class_vars(get_class($this)) as $name => $value){
            $str .= "$name : " . $this->$name . " ; ";
        }
        return $str . "}";
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