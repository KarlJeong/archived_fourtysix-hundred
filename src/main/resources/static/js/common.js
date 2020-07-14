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

    	let url = "/v1/api/file/" + (articleType !== '' ? articleType : "unknown");
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

function dateConverterToDateOrDateTime(givenDateTime) {
    if (givenDateTime == null || givenDateTime == '') {
        return '';
    }

    var dateTime = moment(givenDateTime, moment.ISO_8601);
    var today = moment(new Date());
    if (dateTime.format('YYYY') === today.format('YYYY') && dateTime.format('MM') === today.format('MM') && dateTime.format('DD') === today.format('DD')) {
        return dateTime.format('HH:mm:ss');
    } else {
        return dateTime.format('YYYY-MM-DD');
    }
};

$('.mainblog').on('click', function(event){
    var $this = $(this);
    if ($this.attr("data-link")) {
        window.location.href = "/blog/viewdetail/"+$this.attr("data-link");
    }

  });
