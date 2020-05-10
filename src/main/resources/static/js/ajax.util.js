var PromiseUtil = !window.PromiseUtil ? {} : window.PromiseUtil;

PromiseUtil.get = function(url) {
    // Return a new promise.
    return new Promise(function(resolve, reject) {
        // Do the usual XHR stuff
        var req = new XMLHttpRequest();
        req.open('GET', url);
        req.setRequestHeader($("meta[name='_csrf_header']").attr("content"),  $("meta[name='_csrf']").attr("content"));

        req.onload = function() {
            // This is called even on 404 etc
            // so check the status
            if (req.status == 200) {
                var res = JSON.parse(req.response);
            	if (["SUCCESS_REDIRECT", "FAIL_REDIRECT"].indexOf(res.resultCd) >= 0) {
            		alert(res.resultMsg);
            		window.location.href = res.linkUrl;
            		return;
            	}
                // Resolve the promise with the response text
                resolve(req.response);
            } else {
                // Otherwise reject with the status text
                // which will hopefully be a meaningful error
                reject(Error(req.statusText));
            }
        };

        // Handle network errors
        req.onerror = function() {
            reject(Error("Network Error"));
        };

        // Make the request
        req.send();
    });
}

PromiseUtil.post = function(url, params) {
    // Return a new promise.
    return new Promise(function(resolve, reject) {
        // Do the usual XHR stuff
        var req = new XMLHttpRequest();
        req.open('POST', url);
        req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        req.setRequestHeader($("meta[name='_csrf_header']").attr("content"),  $("meta[name='_csrf']").attr("content"));
        req.onload = function() {
            // This is called even on 404 etc
            // so check the status
            if (req.status == 200) {
                var res = JSON.parse(req.response);
            	if (["SUCCESS_REDIRECT", "FAIL_REDIRECT"].indexOf(res.resultCd) >= 0) {
            		alert(res.resultMsg);
            		window.location.href = res.linkUrl;
            		return;
            	}
                // Resolve the promise with the response text
                resolve(req.response);
            } else {
                // Otherwise reject with the status text
                // which will hopefully be a meaningful error
                reject(Error(req.statusText));
            }
        };

        // Handle network errors
        req.onerror = function() {
            reject(Error("Network Error"));
        };

        // Make the request
        req.send(JSON.stringify(params));
    });
}

PromiseUtil.delete = function(url, params) {
    // Return a new promise.
    return new Promise(function(resolve, reject) {
        // Do the usual XHR stuff
        var req = new XMLHttpRequest();
        req.open('DELETE', url);
        req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        req.setRequestHeader($("meta[name='_csrf_header']").attr("content"),  $("meta[name='_csrf']").attr("content"));
        req.onload = function() {
            // This is called even on 404 etc
            // so check the status
            if (req.status == 200) {
                var res = JSON.parse(req.response);
            	if (["SUCCESS_REDIRECT", "FAIL_REDIRECT"].indexOf(res.resultCd) >= 0) {
            		alert(res.resultMsg);
            		window.location.href = res.linkUrl;
            		return;
            	}
                // Resolve the promise with the response text
                resolve(req.response);
            } else {
                // Otherwise reject with the status text
                // which will hopefully be a meaningful error
                reject(Error(req.statusText));
            }
        };

        // Handle network errors
        req.onerror = function() {
            reject(Error("Network Error"));
        };

        // Make the request
        req.send(JSON.stringify(params));
    });
}

PromiseUtil.postWithFile = function(url, params) {
    // Return a new promise.
    return new Promise(function(resolve, reject) {
        // Do the usual XHR stuff
        var req = new XMLHttpRequest();
        req.open('POST', url);
        req.setRequestHeader($("meta[name='_csrf_header']").attr("content"),  $("meta[name='_csrf']").attr("content"));
        req.onload = function() {
            // This is called even on 404 etc
            // so check the status
            if (req.status == 200) {
                // Resolve the promise with the response text
                resolve(req.response);
            } else {
                // Otherwise reject with the status text
                // which will hopefully be a meaningful error
                reject(Error(req.statusText));
            }
        };

        // Handle network errors
        req.onerror = function() {
            reject(Error("Network Error"));
        };

        // Make the request
        req.send(params);
    });
}

PromiseUtil.getJson = function(url) {
    return this.get(url).then(JSON.parse);
}

window.PromiseUtil = PromiseUtil;

$(function() {
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
    });
});