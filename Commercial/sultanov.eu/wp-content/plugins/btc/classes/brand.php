<?php
include_once('entity.php');

class Brand extends Entity{
    // table name
    public $_TABLE = "wp_brand";
    // promary key property name
    public $_PK = "BRAND_ID";

    // properties
    public $BRAND_ID;
    public $LANG_ID;
    public $BRAND_NAME;
    public $BRAND_URL;
    public $BRAND_ARTICLE_URL;
    public $BRAND_LOGO_URL;
    public $IS_ACTIVE;

    function unescapeImages($str){
        return str_replace($str, "\\\"", "\"");
    }
    
    public function save() {
        global $wpdb;
        echo $this->toString();
        $dataArray = array();
        $typeArray = array();
        $vars = get_class_vars(get_class($this));
        $index = 0;
        $PKFieldName = $this->_PK;
        foreach($vars as $name => $value){
            $value = $this->$name;
            // echo $name . " = " . $value . " <br>";
            if($name != "ID" && strpos($name,"_") != 0){
                $dataArray[$name] = $value;
                if(is_string($value)){
                    $typeArray[$index] = "%s";
                }
                else if(is_numeric($index)){
                    $typeArray[$index] = "%d";
                }
                else{
                    // undefined data - let it be string for now
                    $typeArray[$index] = "%s";
                }
                $index++;
            }
        }

        if($this->ID != ""){
            $wpdb->update($this->_TABLE, $dataArray, array('ID' => $this->ID), $typeArray, array("%d"));
        }
        else if($this->$PKFieldName != ""){
            /*
            foreach ($dataArray as $val){
                echo $val . "<br>";
            }
             */
            $wpdb->insert($this->_TABLE, $dataArray, $typeArray);
        }
        else{
            // создание нового бренда
            $this->$PKFieldName = $wpdb->get_var("select IFNULL(max(  $this->_PK  ) + 1, 0) from $this->_TABLE");
            // echo "saved with PK = " . $this->$PKFieldName;
            $this->save();
        }
        echo  "<span style='background-color: red'>" . $wpdb->last_error . "<br>".$this->_TABLE."</span>";
    }

}

?>