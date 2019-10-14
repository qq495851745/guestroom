/**
 * Created by huihuazhang on 2016/4/27.
 * 基于HTML5 文件上传的核心脚本
 * http://www.w3.org/TR/html-markup/input.file.html
 */
(function ($) {
    function readAsDataURL(img, file, maxW, maxH) {
        // Using FileReader to display the image content
        var reader = new FileReader();
        reader.onload = (function (aImg) {
            return function (e) {
                aImg.src = e.target.result;

                var width = aImg.naturalWidth,
                    height = aImg.naturalHeight;
                aImg.setAttribute('data-width', width);
                aImg.setAttribute('data-height', height);

                if (maxW && maxH) {

                    /*if (width / maxW > height / maxH) {

                    } else {
                        aImg.setAttribute('width', maxW);
                    }*/
                    aImg.setAttribute('height', maxH);
                    aImg.setAttribute('width', maxW);
                }

            };
        })(img);

        reader.readAsDataURL(file);
    }

    function previewUploadImg($uploadWrap, files, maxW, maxH) {

        var $previewElem = $('<div class="thumbnail"></div>').appendTo($uploadWrap);

        var file = files[0];

        if (!file) {
            return false;
        }

        if (!file.type.match(/image.*/)) {
            throw "File Type must be an image";
        }

        var img = document.createElement("img");
        img.file = file;
        $previewElem.empty().append(img);

        // if ($previewElem.find('.edit-icon').size() == 0) {
        // 	$previewElem.append('<span class="edit-icon"></span>');
        // }

        if ($previewElem.find('.del-icon').size() == 0) {
            $('<a class="del-icon"></a>').appendTo($previewElem).click(function (event) {
                $previewElem.remove();
                $uploadWrap.find('input[type=file]').val('');
            });
        }

        readAsDataURL(img, file, maxW, maxH);

    }

    //multifile
    function previewUploadImg3($uploadWrap, files, maxW, maxH) {
        /* 克隆 start*/
        var $newWrap = $uploadWrap.clone();
        $newWrap.find(":file").val('');
        $uploadWrap.after($newWrap);
        /*end*/
        var $previewElem = $('<div class="thumbnail"></div>').appendTo($uploadWrap);

        var file = files[0];

        if (!file) {
            return false;
        }

        if (!file.type.match(/image.*/)) {
            throw "File Type must be an image";
        }

        var img = document.createElement("img");
        img.file = file;
        $previewElem.empty().append(img);

        // if ($previewElem.find('.edit-icon').size() == 0) {
        // 	$previewElem.append('<span class="edit-icon"></span>');
        // }
        /*添加删除标 start*/
        if ($previewElem.find('.del-icon').size() == 0) {
            $('<a class="del-icon"></a>').appendTo($previewElem);
        }
        /*end*/

        readAsDataURL(img, file, maxW, maxH);

    }

    // multiple
    function previewUploadImg2($uploadWrap, files, maxW, maxH) {

        var rel = $uploadWrap.attr('rel');
        var $previewElem = $(rel);

        $previewElem.empty();
        for (var index = 0; index < files.length; index++) {
            var file = files[index];

            var $thumb = $('<li class="thumbnail"></li>');

            var img = document.createElement("img");
            img.file = file;
            $thumb.append(img);
            $previewElem.append($thumb);

            readAsDataURL(img, file, maxW, maxH);
        }

    }

    $.fn.extend({
        /**
         * 选择上传图片缩略图列表预览
         * @param options
         */
        previewUploadImg: function (options) {
            var op = $.extend({maxW: 80, maxH: 80}, options);

            /* multifile文件上传  取消默认事件 start*/
            this.parent().on({
                "click": function () {
                    return false;
                }, "mouseover": function () {
                   /* $(this).parent().parent().find(">img").remove();
                    var $img = $(this).next().find("img").clone();
                    //$img=$("<div style='border: 10px solid red; width: 200px;height: 200px'></div>")
                    $img.removeAttr("width");
                    $img.removeAttr("height");
                    $img.css({
                        position: "absolute",
                        "top": "" + $(this).parent().parent().innerHeight() + "px",
                        "left": "0px"
                    });
                    $(this).parent().after($img);*/
                },"mouseout":function () {
                    /*$(this).parent().parent().find(">img").remove();*/
                }
            }, ":file[data-multifile='multifile']:not(:last)");
            /* end */
            /* multifile start*/
            this.parent().on({
                "change": function () {
                    var files = this.files;
                    previewUploadImg3($(this).parent(), files, op.maxW, op.maxH);
                }
            }, ":file[data-multifile='multifile']:last");
            /*end*/

            /*multifile文件上传 删除事件 start*/
            var $dd = this.find(":file[data-multifile='multifile']").parents("dd");

            $dd.on("click", "a", function () {
                if (!!$(this).attr("href")) {
                    if ($(this).attr("method") == "delete") {
                        var _this = this;
                        $.ajax({
                            type: 'POST',
                            url: $(_this).attr('href'),
                            dataType: "json",
                            data: {"_method": "delete"},
                            cache: false,
                            success: function (json) {
                                DWZ.ajaxDone(json);

                                if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok) {
                                    if(json[DWZ.keys.message] && alertMsg) alertMsg.warn(json[DWZ.keys.message]);
                                    var $uploadWrap = $(_this).parent().parent();
                                    var src = $uploadWrap.find("img").attr("src");
                                    var $dd = $uploadWrap.parent();
                                    $uploadWrap.remove();
                                    $dd.find(">img[src='" + src + "']").remove();
                                }
                            },
                            error: DWZ.ajaxError
                        });
                    }
                } else {
                    var $uploadWrap = $(this).parent().parent();
                    var src = $uploadWrap.find("img").attr("src");
                    var $dd = $uploadWrap.parent();
                    $uploadWrap.remove();
                    $dd.find(">img[src='" + src + "']").remove();

                }
                return false;
            });

            /*end*/


            return this.each(function () {
                var $uploadWrap = $(this);

                var $inputFiles = $uploadWrap.find('input[type=file]');
                $inputFiles.each(function (index) {
                    var $inputFile = $(this).css({left: (op.maxW * index) + 'px'});
                    /*不处理 multifile*/
                    $inputFile.filter(":not([data-multifile='multifile'])").on('change', function () {
                        var files = this.files;
                        if (this.multiple) {
                            previewUploadImg2($uploadWrap, files, op.maxW, op.maxH);
                        } else {
                            if ($(this).data("multifile") == 'multifile')
                                previewUploadImg3($uploadWrap, files, op.maxW, op.maxH);
                            else
                                previewUploadImg($uploadWrap, files, op.maxW, op.maxH);
                        }
                    });
                });

                var $delIcon = $uploadWrap.filter(">:not(:file[data-multifile='multifile'])").find('.del-icon');
                if ($delIcon) { // 删除服务器上的图片
                    $delIcon.click(function (event) {
                        $.ajax({
                            type: 'GET',
                            url: $delIcon.attr('href'),
                            dataType: "json",
                            cache: false,
                            success: function (json) {
                                DWZ.ajaxDone(json);

                                if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok) {
                                    $uploadWrap.find('div.thumbnail').remove();
                                    $uploadWrap.find('input[type=file]').val('');
                                }
                            },
                            error: DWZ.ajaxError
                        });

                        return false;
                    });
                }

            });
        }
    });


    DWZ.regPlugins.push(function ($p) {
        $("div.upload-wrap", $p).previewUploadImg();
    });

})(jQuery);