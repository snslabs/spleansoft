<?php
/**
 * Velocity like template processing engine
 * User: slyubimov
 * Date: 09.11.2010
 * Time: 12:51:08
 */
define('CUR_PATH',dirname(__FILE__) . '/');

function velocity_process_template($templateFilename, $model){
    $template = file_get_contents(CUR_PATH . "templates/" .$templateFilename);
    // $template = "this is check of #property1# #property1# #property1# ###property1#velocity engine!";
    $properties = get_object_vars($model);
    foreach($properties as $name => $value ){
        // enumerating all members and replacing corresponding placeholders
        $template = preg_replace("/#" . $name . "#/i", ($value . '') , $template);
        
    }

    // listing missing members
    // todo: list missing members
    return $template;
}

?>