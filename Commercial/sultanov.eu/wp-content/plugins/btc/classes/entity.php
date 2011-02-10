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