<?php

define('DIR_ROOT',		$_SERVER['DOCUMENT_ROOT']);
//Директория с изображениями (относительно корневой)
define('DIR_IMAGES',	'/wp-content/plugins/btc/images');
//Директория с файлами (относительно корневой)
define('DIR_FILES',		'/wp-content/plugins/btc/files');

        $dir = DIR_ROOT . DIR_FILES;
        if(isset($_POST['Filename'])) {
            //Тип (изображение/файл)
            $pathtype = $_POST['pathtype'];

            if (strpos($_POST['Filename'], '.') !== false) {
                $extension = end(explode('.', $_POST['Filename']));
                $filename = substr($_POST['Filename'], 0, strlen($_POST['Filename']) - strlen($extension) - 1);
            } else {
                header('HTTP/1.1 403 Forbidden');
                exit();
            }
            if(isset($_POST["RESULT_FIELD"])){
                $storedFileName = $_POST["RESULT_FIELD"] . $_POST["ID"] . "." . $extension;  
            }
            /*
            if(isset($_POST["BRAND_ID"])){
                $storedFileName = "brand_".$_POST["BRAND_ID"]. "_".$_POST["LANG_ID"]."." .$extension;
            }
            else{
                $storedFileName = "tour_" . $_POST["TOUR_ID"] . ".pdf";
            }

             */

            echo "{filename: '" . $_POST['Filename'] . "', filepath:'" . $storedFileName . "', \n";

            $md5 = md5_file($_FILES['Filedata']['tmp_name']);
            $file = $md5.'.'.$extension;


            if(!copy($_FILES['Filedata']['tmp_name'],$dir.'/'.$storedFileName)) {
                header('HTTP/1.0 500 Internal Server Error');
                exit();
            }
            echo "stored_at:'" . $dir . "',\n";
            echo "url:'" . DIR_FILES . "/" . $storedFileName . "'";
            echo "}";
            exit();
        }
        else{
            echo "incorrect request!";
        }
?>