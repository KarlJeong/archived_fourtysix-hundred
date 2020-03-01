$.fn.serializeObject = function() {
    var o = null;
    try {
        if (this != null && this.prop("tagName") === "FORM") {
            var arr = this.serializeArray();
            if (!!arr) {
                o = {};
                jQuery.each(arr, function(idx, item) {
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