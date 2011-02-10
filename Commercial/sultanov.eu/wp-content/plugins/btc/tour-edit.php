<?php
function renderEditTour($tour){
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

			// Replace values for the template plugin
            /*
			template_replace_values : {
				username : "Some User",
				staffid : "991234"
			}
			*/
		});
	});

    function saveTour(){
        var data = {
            action: 'tour_save',
            
            ID: jQuery('#ID').val(),
            TOUR_CAT_ID: jQuery('#TOUR_CAT_ID').val(),
            TOUR_ID: jQuery('#TOUR_ID').val(),
            LANG_ID: jQuery('#LANG_ID').val(),
            TOUR_NAME: jQuery('#TOUR_NAME').val(),
            TOUR_DATE: jQuery('#TOUR_DATE').val(),
            TOUR_DESC: jQuery('#TOUR_DESC').html(),
            TOUR_ARTICLE_URL: jQuery('#TOUR_ARTICLE_URL').val(),
            TOUR_PDF_URL: jQuery('#TOUR_PDF_URL').val(),
            IS_ACTIVE: jQuery('#IS_ACTIVE').is(':checked')?1:0            
        };
        // alert(toString(data));
        jQuery.post('<?php echo TOUR_SERVICE_URL ?>', data, function(data) {  jQuery('#tablePlaceholder').html(data); });

        
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
		<h2>Редактирование тура</h2>
        <table width="100%">
            <tr>
                <td><b>Номер тура:</b> <?= $tour->TOUR_ID ?> <?= hiddenControl('TOUR_ID', $tour->TOUR_ID, 'TOUR_ID') ?></td>
                <td nowrap><b>Язык:</b> <?= $akmalPlugin->LANGUAGES[$tour->LANG_ID] ?>  <?= hiddenControl('LANG_ID', $tour->LANG_ID, 'LANG_ID') ?> </td>
                <td nowrap><span style="color:#a0a0a0" >ID: <?= $tour->ID ?> <?= hiddenControl('ID', $tour->ID, 'ID') ?></span></td>
            </tr>
            <tr>
                <td colspan="2"><b>Категория:</b> <?= categoryDropDownControl('TOUR_CAT_ID', $tour->TOUR_CAT_ID, 'TOUR_CAT_ID') ?></td>
                <td colspan="2"><b>Активный:</b> <?= checkboxControl('IS_ACTIVE', $tour->IS_ACTIVE, 'IS_ACTIVE') ?></td>
            </tr>
            <tr>
                <td colspan="3"><b>Наименование:</b> <?= textControl('TOUR_NAME', $tour->TOUR_NAME, 'TOUR_NAME') ?></td>
            </tr>
            <tr>
                <td colspan="3"><b>Дата:</b> <?= textControl('TOUR_DATE', $tour->TOUR_DATE, 'TOUR_DATE') ?></td>
            </tr>
        </table>
		<!-- Gets replaced with TinyMCE, remember HTML in a textarea should be encoded -->
		<div style="width: 100%; margin-top: 20px; margin-bottom:20px;'">
			<textarea id="TOUR_DESC" name="TOUR_DESC" rows="15" cols="80" style="width: 80%" class="tinymce"><?= $tour->TOUR_DESC ?>
			</textarea>
		</div>
        <table cellspacing="2" cellpadding="5"><tr>
            <td>Брошюра:</td>
            <td valign="middle">
                <a id="TOUR_PDF_URL_HREF" target="_blank" href="<?= $tour->TOUR_PDF_URL==null?"#":$tour->TOUR_PDF_URL ?>" <?= $tour->TOUR_PDF_URL == null?"disabled='true'":"" ?> > Скачать </a>
            </td>
            <td>
                <div style="width: 100px; overflow: hidden; height: 26px;">
                    <div style="position: relative; width: 100px; float: left; height: 26px; padding:0; margin:0;z-index:100;"><span id="fAddPhotos"></span></div>
                    <div style="position: relative; width: 100px; float: left; height: 26px; padding:0; margin:0;z-index:10; top: -26px;"><input type="button" value="Загрузить"></div>
                </div><?= hiddenControl('TOUR_PDF_URL', $tour->TOUR_PDF_URL, 'TOUR_PDF_URL') ?>
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
                         and t.language_code=%s ",$tour->LANG_ID);
                    $results = $wpdb->get_results($query);
                    foreach($results as $row){
                        echo "<option value='" . $row->GUID . "' " . ($row->GUID == $tour->TOUR_ARTICLE_URL ? "selected" : "") . "   >" . $row->ID . " :: " . $row->POST_TITLE . "</option>";
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
            try{
                BindSWFUpload("*.pdf", "PDF files", "#TOUR_ID", "TOUR");
            }
            catch(e)
            {
                alert(e);
            }

        </script>
        <table width="100%" style="margin-top:20px;"><tr>
        <td align="center">
		    <input type="button" class="button" style="width: 150px;" name="save" value="Сохранить" onClick="saveTour()"/>
        </td><td align="center">
            <input type="reset" class="button"  style="width: 150px;" name="reset" value="Сброс" />
        </td><td align="center">
            <input type="button" class="button" style="width: 150px;" name="reset" value="Выход" onclick="tourReload()" />
        </td></tr>
        </table>
	</div>
</form>
<?php
}
?>
