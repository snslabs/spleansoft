<?php
function renderEditBrand(Brand $brand){
// $scriptURL = "tinymce/jscripts/tiny_mce/tiny_mce.js";
    // this urls adopted to allow this page me loaded via AJAX call  
    $scriptURL = "../wp-content/plugins/btc/tinymce/jscripts/tiny_mce/tiny_mce.js";
    $jQueryScriptURL= "../wp-content/plugins/btc/tinymce/jscripts/tiny_mce/jquery.tinymce.js";

    // $tour = $tour;
    // $tour = new Tour();
    global $akmalPlugin;
?>

<script type="text/javascript" src="<?= $jQueryScriptURL ?>"></script>
<script type="text/javascript">
/*
	jQuery().ready(function() {
		jQuery('textarea.tinymce').tinymce({
			// Location of TinyMCE script
			script_url : '<?= $scriptURL ?>',

			// General options
			theme : "advanced",
			plugins : "images,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist",

			// Theme options
			theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
			theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,images,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
			theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
			theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak",
			theme_advanced_toolbar_location : "top",
			theme_advanced_toolbar_align : "left",
			theme_advanced_statusbar_location : "bottom",
			theme_advanced_resizing : true,

			// Example content CSS (should be your site CSS)
			content_css : "css/content.css",

			// Drop lists for link/image/media/template dialogs
			template_external_list_url : "lists/template_list.js",
			external_link_list_url : "lists/link_list.js",
			external_image_list_url : "lists/image_list.js",
			media_external_list_url : "lists/media_list.js"

		});
	});
*/
    function saveBrand(){
        var data = {
            action: 'brand_save',
            
            ID: jQuery('#ID').val(),
            BRAND_CAT_ID: jQuery('#BRAND_CAT_ID').val(),
            BRAND_ID: jQuery('#BRAND_ID').val(),
            LANG_ID: jQuery('#LANG_ID').val(),
            BRAND_NAME: jQuery('#BRAND_NAME').val(),
            BRAND_ARTICLE_URL: jQuery('#BRAND_ARTICLE_URL').val(),
            BRAND_LOGO_URL: jQuery('#BRAND_LOGO_URL').val(),
            IS_ACTIVE: jQuery('#IS_ACTIVE').is(':checked')?1:0,
            XDEBUG_SESSION_START: "PhpStorm1"
        };
        jQuery.post('<?php echo BRAND_SERVICE_URL ?>', data, function(data) {  jQuery('#tablePlaceholder').html(data); });
    }

    function toString(arr){
        var s = "{\n";
        for(keyVar in arr){
            s += "\t" + keyVar + " : " + arr[keyVar] + ";\n"
        }
        return s+="}";
    }
</script>
<!-- /TinyMCE -->
    <link href="/wp-content/plugins/btc/files/css/reset-min.css" rel="stylesheet" type="text/css" />
    <link href="/wp-content/plugins/btc/files/css/style.css" rel="stylesheet" type="text/css" />

<form method="post" action="">
	<div>
		<h2>Редактирование брэнда</h2>
        <table width="100%">
            <tr>
                <td><b>Номер бренда:</b></td><td><?= $brand->BRAND_ID ?> <?= hiddenControl('BRAND_ID', $brand->BRAND_ID, 'BRAND_ID') ?></td>
                <td nowrap><b>Язык:</b></td><td><?= $akmalPlugin->LANGUAGES[$brand->LANG_ID] ?>  <?= hiddenControl('LANG_ID', $brand->LANG_ID, 'LANG_ID') ?> </td>
                <td nowrap><span style="color:#a0a0a0" >ID: </td><td><?= $brand->ID ?> <?= hiddenControl('ID', $brand->ID, 'ID') ?></span></td>
            </tr>
            <tr>
                <td colspan="1"><b>Наименование:</b></td><td colspan="3"> <?= textControl('BRAND_NAME', $brand->BRAND_NAME, 'BRAND_NAME') ?></td>
                <td colspan="1"><b>Активный:</b></td><td><?= checkboxControl('IS_ACTIVE', $brand->IS_ACTIVE, 'IS_ACTIVE') ?></td>
            </tr>
<!--
        </table>
		<div style="width: 100%; margin-top: 20px; margin-bottom:20px;'">
			<textarea id="TOUR_DESC" name="TOUR_DESC" rows="15" cols="80" style="width: 80%" class="tinymce"><?= $brand->TOUR_DESC ?>
			</textarea>
		</div>
        <table cellspacing="2" cellpadding="5">
            -->
        <tr>
            <td><b>Логотип:</b></td>
            <td align="left">
                <div style="width: 100px; overflow: hidden; height: 26px;">
                    <div style="position: relative; width: 100px; float: left; height: 26px; padding:0; margin:0;z-index:100;"><span id="fAddPhotos"></span></div>
                    <div style="position: relative; width: 100px; float: left; height: 26px; padding:0; margin:0;z-index:10; top: -26px;"><input type="button" value="Загрузить"></div>
                </div><?= hiddenControl('BRAND_LOGO_URL', $brand->BRAND_LOGO_URL, 'BRAND_LOGO_URL') ?>
            </td>
            <td valign="middle">
        <?php if ($brand->BRAND_LOGO_URL != null) { ?>
            <img id="BRAND_LOGO_URL_IMG" src="<?= $brand->BRAND_LOGO_URL ?>" alt=""/>
        <?php }else{ ?>
            <img id="BRAND_LOGO_URL_IMG" src="#" alt=""/>
        <?php } ?>
            </td>
        </tr>
        <tr>
            <td>Статья:</td>
            <td valign="middle" colspan="2">
                <select name="TOUR_ARTICLE_URL" id="TOUR_ARTICLE_URL">
                    <option value=""> </option>
                <?php
                    global $wpdb;
                    $query = $wpdb->prepare(
                        "select p.id as ID, p.post_title as POST_TITLE, p.guid as GUID
                         from wp_posts p
                         left outer join wp_icl_translations t
                            on t.element_id = p.ID
                               and t.element_type = 'post_page'
                         where p.post_type='page'
                         and t.language_code=%s ",$brand->LANG_ID);
                    $results = $wpdb->get_results($query);
                    foreach($results as $row){
                        echo "<option value='" . $row->GUID . "' " . ($row->GUID == $brand->BRAND_ARTICLE_URL ? "selected" : "") . "   >" . $row->ID . " :: " . $row->POST_TITLE . "</option>";
                    }
                ?>
                </select>
            </td>
            <td>
            </td>
        </tr>

        </table>
        <script type="text/javascript">
            ASPSESSID = "ciftuemt43pxrh55jjdohm3o";
            BindSWFUpload("*.jpg; *.gif; *.png", "Images", "BRAND_ID");
        </script>
        <table width="100%" style="margin-top:20px;"><tr>
        <td align="center">
		    <input type="button" class="button" style="width: 150px;" name="save" value="Сохранить" onClick="saveBrand()"/>
        </td><td align="center">
            <input type="reset" class="button"  style="width: 150px;" name="reset" value="Сброс" />
        </td><td align="center">
            <input type="button" class="button" style="width: 150px;" name="reset" value="Выход" onclick="listBrands()" />
        </td></tr>
        </table>
	</div>
</form>
<?php
}
?>
