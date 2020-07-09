function fnToggleLike(subpath, articleId, likeYn) {
	let url = "/v1/api/" + subpath + "/" +articleId + "/like/" + (likeYn ^ 1);
    PromiseUtil.post(url)
    .then(JSON.parse)
    .then(function(d){
    });
}

function fnReply(subpath, articleId) {
    let url = "/v1/api/" + subpath + "/" +articleId + "/reply";
    var params = $("#createReplyForm").serializeObject();
    PromiseUtil.post(url, params)
    .then(JSON.parse)
    .then(function(d){
    });
}

