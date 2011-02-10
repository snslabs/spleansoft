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
    

}

?>