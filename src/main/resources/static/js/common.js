$.fn.serializeObject2 = function() {
    var o = null;
    try {
        if (this != null && this.prop("tagName") === "FORM") {
            var arr = this.serializeArray();
            if (!!arr) {
                o = {};
                $.each(arr, function(idx, item) {
                    o[item.name] = item.value;
                });
            }
        }
    } catch (e) {
        alert(e.message);
    } finally {
    }

    return o;
};

$.fn.serializeObject = function() {
    "use strict";
    var result = {};
    var extend = function(i, element) {
        var node = result[element.name];
        if ('undefined' !== typeof node && node !== null) {
            if ($.isArray(node)) {
                node.push(element.value);
            }else {
                result[element.name] = [ node, element.value ];
            }
        } else if (element.name.includes(".")) {
        	var els = element.name.split(".");
        	var node = result[els[0]];
        	var obj = els[0], key = els[1], tmp = {};
        	if ('undefined' !== typeof node && node !== null) {
        		tmp[key] = element.value;
             	result[obj].push(tmp);
            } else {
            	result[obj] = [];
             	tmp[key] = element.value;
             	result[obj].push(tmp);
            }

        } else {
        	result[element.name] = element.value;
        }
    };

    $.each(this.serializeArray(), extend);
    return result;
};

$.fn.serializeFiles = function() {
    "use strict";
    var $form = $(this);
    var formData = new FormData();
    var formParams = $form.serializeArray();
    // data append
    $.each(formParams, function(i, val) {
        formData.append(val.name, val.value);
    });
    // file append
    $.each($form.find('input[type="file"]'), function(i, tag) {
        $.each(tag.files, function(i, file) {
            formData.append(tag.name, file);
        });
    });
    return formData;
};


$.fn.serializeParams = function() {
    "use strict";
    var $form = $(this);
    var formParams = $form.serializeArray();
    var data = [];
    $.each(formParams, function(i, val) {
        if (val.value === null || val.value === "") {
            return true;
        }
        data.push(val.name + "=" + val.value);
    });

    return data.join("&");
};

$.fn.initSummernote = function(articleType) {
	"use strict";
	var sNote = $(this);
	sNote.summernote({
        height : 500,
        tabsize : 2,
        toolbar : [
            [ 'style', [ 'style' ] ],
            [ 'font', [ 'bold', 'italic', 'underline', 'clear' ] ],
            [ 'fontname', [ 'fontname' ] ],
            [ 'fontsize', [ 'fontsize' ] ],
            [ 'color', [ 'color' ] ],
            [ 'para', [ 'ul', 'ol', 'paragraph' ] ],
            [ 'height', [ 'height' ] ],
            [ 'table', [ 'table' ] ],
            [ 'insert', [ 'link', 'picture', 'video', 'hr' ] ],
            [ 'view', [ 'fullscreen', 'codeview' ] ],
            [ 'help', [ 'help' ] ]
        ],
        callbacks: {
        	onImageUpload : function(files) {
                uploadSummernoteFile(files, sNote, articleType);
            },
            onImageLinkInsert : function(e) {
            	console.log(e);
            },
            onPaste: function (e) {
                var bufferText = ((e.originalEvent || e).clipboardData || window.clipboardData).getData('Text');
                e.preventDefault();
                document.execCommand('insertText', false, bufferText);
            }

          }
	});
};

function uploadSummernoteFile(files, sNote, articleType) {
	$.each(files, function(i, file) {
		var formData = new FormData();
        formData.append("contentFile", file);

    	let url = "/v1/api/file/r/" + (articleType !== '' ? articleType : "unknown");
        PromiseUtil.postWithFile(url, formData)
        .then(JSON.parse)
        .then(function(d){
        	sNote.summernote("insertImage", "/v1/api/file/img/" + d[0].fileId, function($image){
        		$image.attr("data-filename", d[0].fileOrginalName);
        		$image.attr("width", $image.width());
        		$image.css("maxWidth", "100%");
        	});
        });
    });
}

Number.prototype.format = function(){
    if(this==0) return 0;

    var reg = /(^[+-]?\d+)(\d{3})/;
    var n = (this + '');

    while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');

    return n;
};

String.prototype.format = function(){
    var num = parseFloat(this);
    if( isNaN(num) ) return "0";

    return num.format();
};

function ISOdateToYYYYMMDD(givenDateTime){
    if (givenDateTime == null || givenDateTime == '') {
        return '';
    }
    const givenDateTimeFormat = givenDateTime.split("T");
    const givenDate = givenDateTimeFormat[0];
    const gDate = givenDate.split("-");
    const givenTime= givenDateTimeFormat[1];
    if (givenTime == null){
        var pDate = new Date(gDate[0], gDate[1], gDate[2]);
        var yyyy = pDate.getFullYear();
        var mm = pDate.getMonth() < 10 ? "0" + pDate.getMonth() : pDate.getMonth(); // getMonth() is zero-based
        var dd  = pDate.getDate() < 10 ? "0" + pDate.getDate() : pDate.getDate();
        return "".concat(yyyy).concat("-").concat(mm).concat("-").concat(dd);
    } else {
        const gTime = givenTime.split(".")[0].split(":");
        var pDate = new Date(gDate[0], gDate[1], gDate[2], gTime[0], gTime[1], gTime[2]);
        var yyyy = pDate.getFullYear();
        var mm = pDate.getMonth() < 10 ? "0" + pDate.getMonth() : pDate.getMonth(); // getMonth() is zero-based
        var dd  = pDate.getDate() < 10 ? "0" + pDate.getDate() : pDate.getDate();
        var hh = pDate.getHours() < 10 ? "0" + pDate.getHours() : pDate.getHours();
        var min = pDate.getMinutes() < 10 ? "0" + pDate.getMinutes() : pDate.getMinutes();
        return "".concat(yyyy).concat("-").concat(mm).concat("-").concat(dd);
    }

}

function ISOdateToYYYYMMDDHH24MI(givenDateTime) {
    if (givenDateTime == null || givenDateTime == '') {
        return '';
    }
    const givenDateTimeFormat = givenDateTime.split("T");
    const givenDate = givenDateTimeFormat[0];
    const gDate = givenDate.split("-");
    const givenTime= givenDateTimeFormat[1];
    if (givenTime == null){
        var pDate = new Date(gDate[0], gDate[1], gDate[2]);
        var yyyy = pDate.getFullYear();
        var mm = pDate.getMonth() < 10 ? "0" + pDate.getMonth() : pDate.getMonth(); // getMonth() is zero-based
        var dd  = pDate.getDate() < 10 ? "0" + pDate.getDate() : pDate.getDate();
        return "".concat(yyyy).concat("-").concat(mm).concat("-").concat(dd).concat(" ").concat("00").concat(":").concat("00");
    } else {
        const gTime = givenTime.split(".")[0].split(":");
        var pDate = new Date(gDate[0], gDate[1], gDate[2], gTime[0], gTime[1], gTime[2]);
        var yyyy = pDate.getFullYear();
        var mm = pDate.getMonth() < 10 ? "0" + pDate.getMonth() : pDate.getMonth(); // getMonth() is zero-based
        var dd  = pDate.getDate() < 10 ? "0" + pDate.getDate() : pDate.getDate();
        var hh = pDate.getHours() < 10 ? "0" + pDate.getHours() : pDate.getHours();
        var min = pDate.getMinutes() < 10 ? "0" + pDate.getMinutes() : pDate.getMinutes();
        var sec = pDate.getSeconds() < 10 ? "0" + pDate.getSeconds() : pDate.getSeconds();
        return "".concat(yyyy).concat("-").concat(mm).concat("-").concat(dd).concat(" ").concat(hh).concat(":").concat(min).concat(":").concat(sec);
    }
};

function dateConverterToDateOrDateTime(givenDateTime) {
    if (givenDateTime == null || givenDateTime == '') {
        return '';
    }
    var dateTime = new Date(givenDateTime);
    var today = new Date();

    var yyyy = dateTime.getFullYear();
    var mm = dateTime.getMonth() +1;
    mm = mm < 10 ? '0' + dateTime.getMonth() : dateTime.getMonth();
    var dd = dateTime.getDate() < 10 ? '0' + dateTime.getDate() : dateTime.getDate();
    var hh = dateTime.getHours() < 10 ? "0" + dateTime.getHours() : dateTime.getHours();
    var min = dateTime.getMinutes() < 10 ? "0" + dateTime.getMinutes() : dateTime.getMinutes();
    var sec = dateTime.getSeconds() < 10 ? "0" + dateTime.getSeconds() : dateTime.getSeconds();

    if (dateTime.getDate() === today.getDate() && dateTime.getMonth() === today.getMonth() && dateTime.getFullYear() === today.getFullYear()) {
        return "".concat(hh).concat(":").concat(min).concat(":").concat(sec);
    } else {
        return "".concat(yyyy).concat("-").concat(mm).concat("-").concat(dd);
    }
}
