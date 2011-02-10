<?php

require_once("classes/tour_cat.php");
require_once("classes/tour.php");

function langControl($controlName, $controlValue, $controlId){ ?>
<select name="<?= $controlName ?>" id="<?= $controlId ?>" style="width: 80px;" >
<option <?= $controlValue == 'ru'?'selected':'' ?> value="ru">Русский  </option>
<option <?= $controlValue == 'en'?'selected':'' ?> value="en">English  </option>
<option <?= $controlValue == 'uz'?'selected':'' ?> value="uz">O'zbek   </option>
</select>
<?php
}

function textControl($controlName, $controlValue, $controlId){
?><input type="text" name="<?= $controlName ?>" id="<?= $controlId ?>" style='width: 300px;' value="<?= $controlValue ?>"><?php
}

function dateControl($controlName, $controlValue, $controlId){
?><input type="text" name="<?= $controlName ?>" id="<?= $controlId ?>" style='width: 300px;' value="<?= $controlValue ?>"><?php
}

function hiddenControl($controlName, $controlValue, $controlId){
?><input type="hidden" name="<?= $controlName ?>" id="<?= $controlId ?>" style='width: 300px;' value="<?= $controlValue ?>"><?php
}

function categoryDropDownControl($controlName, $controlValue, $controlId){
?><select name="<?= $controlName ?>" id="<?= $controlId ?>" style='width: 300px;'>
  <?php
  global $wpdb;
  $res = $wpdb->get_results( $wpdb->prepare("select TOUR_CAT_ID, TOUR_CAT_NAME from wp_tour_cat where lang_id = %s",'ru') );
  foreach($res as $row){
  ?>
        <option value="<?= $row->TOUR_CAT_ID ?>" <?= $row->TOUR_CAT_ID == $controlValue ?"selected":"" ?> ><?= $row->TOUR_CAT_NAME ?></option>
  <?php
  }
  ?>
  </select> <?php
}
function checkboxControl($controlName, $controlValue, $controlId){
?><input type="checkbox" name="<?= $controlName ?>" id="<?= $controlId ?>" value="<?= $controlValue ?>" <?= $controlValue==1?"checked":"" ?>><?php
}

function getLangIds(){
    return tourLangs;
}


?>