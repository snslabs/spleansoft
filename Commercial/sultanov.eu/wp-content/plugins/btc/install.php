<?php
	function _log($msg){
		$myFile = "tours.log";
		$fh = fopen($myFile, 'a') or die("can't open file");
		$stringData = $msg . "\n";
		fwrite($fh, $stringData);
		fclose($fh);
	}

	function activateToursPlugin(){
		// creating database structure
		global $wpdb;
		$tourCategoryTableName = $wpdb->prefix . "tour_cat";
		if($wpdb->get_var("SHOW TABLES LIKE '$tourCategoryTableName'") != $tourCategoryTableName){
			$sql =
			"CREATE TABLE " . $tourCategoryTableName . " (
				ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
				TOUR_CAT_ID INT NOT NULL ,
				LANG_ID CHAR( 2 ) NOT NULL ,
				TOUR_CAT_NAME VARCHAR( 250 ) NOT NULL ,
				UNIQUE (
				  TOUR_CAT_ID,
				  LANG_ID
				)
			) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1
			";
			$wpdb->show_errors(true);
			$wpdb->query($sql);
		}

        if($wpdb->get_var("SHOW TABLES LIKE 'wp_tour'") != 'wp_tour'){

            $sql =
            "CREATE TABLE wp_tour (
            `ID` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
            `TOUR_CAT_ID` INT NOT NULL ,
            `TOUR_ID` INT NOT NULL ,
            `LANG_ID` CHAR( 2 ) NOT NULL ,
            `TOUR_NAME` TEXT NOT NULL ,
            `TOUR_DESC` TEXT NULL ,
            `TOUR_ARTICLE_URL` TEXT NULL ,
            `TOUR_PDF_URL` TEXT NULL ,
            `IS_ACTIVE` TINYINT NOT NULL
            ) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1
            ";
            $wpdb->show_errors(true);
            $wpdb->query($sql);
            echo $wpdb->last_error;
        }
        if($wpdb->get_var("SHOW TABLES LIKE 'wp_brand_category'") != 'wp_brand_category'){

            $sql =
            "CREATE TABLE wp_brand_category (
            `ID` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
            `CAT_ID` INT NOT NULL ,
            `LANG_ID` CHAR( 2 ) NOT NULL ,
            `CAT_NAME` TEXT NOT NULL ,
            `PARENT_CAT_ID` TEXT NULL ,
            `IS_ACTIVE` TINYINT NOT NULL,
				UNIQUE (
				  CAT_ID,
				  LANG_ID
				)
            ) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1
            ";
            $wpdb->show_errors(true);
            $wpdb->query($sql);
        }

        if($wpdb->get_var("SHOW TABLES LIKE 'wp_brand'") != 'wp_brand'){

            $sql =
            "CREATE TABLE wp_brand (
            `ID` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
            `BRAND_ID` INT NOT NULL ,
            `LANG_ID` CHAR( 2 ) NOT NULL ,
            `BRAND_NAME` TEXT NOT NULL ,
            `BRAND_URL` TEXT NULL ,
            `BRAND_LOGO_URL` TEXT NULL ,
            `BRAND_ARTICLE_URL` TEXT NULL ,
            `IS_ACTIVE` TINYINT NOT NULL ,
				UNIQUE (
				  BRAND_ID,
				  LANG_ID
				)
            ) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1
            ";
            $wpdb->show_errors(true);
            $wpdb->query($sql);
        }

        if($wpdb->get_var("SHOW TABLES LIKE 'wp_brand_to_cat'") != 'wp_brand_to_cat'){

            $sql =
            "CREATE TABLE wp_brand_to_cat (
            `ID` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
            `BRAND_ID` INT NOT NULL ,
            `CAT_ID` INT NOT NULL 
            ) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1
            ";
            $wpdb->show_errors(true);
            $wpdb->query($sql);
        }
	}
	?>