function fnCallReportReplyModal(target, boardCode, articleId, replyId) {
    $("#reportModalTitle").text("Report " + target);
    $("#boardCode").val(boardCode.toUpperCase());
    $("#reportCategoryCode option:eq(0)").prop("selected", true);
    $("#reportDetail").val("");
    $("#articleId").val(articleId);
    $("#replyId").val(replyId);
    $("#target").val(target);
    $("#reportModal").modal({backdrop: "static"});
};

function fnCallReportArticleModal(target, boardCode, articleId) {
    $("#reportModalTitle").text("Report " + target);
    $("#boardCode").val(boardCode.toUpperCase());
    $("#reportCategoryCode option:eq(0)").prop("selected", true);
    $("#reportDetail").val("");
    $("#articleId").val(articleId);
    $("#replyId").val("");
    $("#target").val(target);
    $("#reportModal").modal({backdrop: "static"});
};

function fnReport() {
    let url = "/v1/api/b/report/article";
    var params = $("#reportForm").serializeObject();
    console.log(params);
    PromiseUtil.post(url, params)
    .then(JSON.parse)
    .then(function(d){
        alert("Thank you for your reporting.");
        window.location.reload();
    });
};