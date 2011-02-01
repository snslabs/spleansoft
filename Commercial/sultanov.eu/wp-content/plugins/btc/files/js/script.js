
var PhotosResult = "";
var Count = 0;
var UploadedFiles = 0;
function photos_fileDialogComplete(numFilesSelected, numFilesQueued) {
    try {
        if (numFilesQueued > 0) {
            PhotosResult = numFilesQueued == '1' ? ' image' : ' images';
            PhotosResult = numFilesQueued + PhotosResult + " attached";
            Count = parseInt(numFilesQueued);
            jQuery('#AddPhotos').val('Uploading...');
            jQuery('#submitStatus')
                .attr('disabled', 'disabled')
                .addClass('disabled');
            this.startUpload();
        }
    } catch (ex) {
    }
}

function photos_uploadProgress(file, bytesLoaded) {
    try {
        var pw = 115;
        var w = Math.ceil(pw * (UploadedFiles / Count + (bytesLoaded / (file.size * Count))));
        jQuery('#Progress').stop().animate({ width: w });
    } catch (ex) {
    }
}
function photos_uploadSuccess(file, serverData) {
    var res = null;
    eval("res = " + serverData);
    try {
        if(res != null && res.url != null){
            jQuery("#TOUR_PDF_URL_HREF").attr("href", res.url);
            jQuery("#TOUR_PDF_URL").val(res.url);
            jQuery("#BRAND_LOGO_URL_IMG").attr("src",res.url);
            jQuery("#BRAND_LOGO_URL").val(res.url);
            alert("Файл " + res.filename + " : " + res.url + " успешно загружен!");
        }
        UploadedFiles++;
    } catch (ex) {

    }
}

function photos_uploadComplete(file) {
    try {
        if (this.getStats().files_queued > 0) {
            this.startUpload();
        } else {
            jQuery('#UploadPhotos').hide();
            jQuery('#Buttons').prepend('<span id="UploadResult" class="images">' + PhotosResult + '</span>');
        }
    } catch (ex) {
    }
}
function photos_fileQueueError(file, errorCode, message) {
    try {
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                alert('Слишком много. Максимум - пять фоток.');
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                break;
        }
    } catch (ex) {
    }

}

function swfuploadLoaded() {
    jQuery('#Buttons object').hover(
        function() {
            jQuery(this).next().addClass('hover');
        },
        function() {
            jQuery(this).next().removeClass('hover');
        });

}
var ASPSESSID = "";
var swfuPhotos;
function BindSWFUpload(extensions, descriptions, paramControl, resultField) {
    var swfuPhotosSettings = {
        file_dialog_complete_handler: photos_fileDialogComplete,
        upload_progress_handler: photos_uploadProgress,
        upload_success_handler: photos_uploadSuccess,
        upload_complete_handler: photos_uploadComplete,
        swfupload_loaded_handler: swfuploadLoaded,
        file_queue_error_handler: photos_fileQueueError,

        file_size_limit: "100 MB",
        file_types: extensions==null?"*.pdf":extensions,
        file_types_description: descriptions==null?"PDF file":descriptions,
        file_upload_limit: "0",
        button_placeholder_id: "fAddPhotos"
    }

    var defaultSettings = {
        flash_url: "/wp-content/plugins/btc/files/swf/swfupload.swf",
        upload_url: "/wp-content/plugins/btc/upload.php",
        post_params: {
            "PHPSID": "1231231231313131313123",
            "ID" : jQuery(paramControl).val(),
            "RESULT_FIELD" : resultField
        },

        button_width: 115,
        button_height: 32,
        button_image_url: "/wp-content/plugins/btc/files/images/white50.png",

        button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
        button_cursor: SWFUpload.CURSOR.HAND
    }

    swfuPhotos = new SWFUpload(jQuery.extend(swfuPhotosSettings, defaultSettings));
}


