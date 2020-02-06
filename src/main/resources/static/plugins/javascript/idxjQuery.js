/**
 * ============================================================
 * 初始化水印
 * ============================================================
 * @param {type} ele
 * @param {type} tColor
 * @returns {undefined}
 */
function initWatermark(ele, tColor) {
    var dateTime = new Date();
    var dateTimeStr = (dateTime.getFullYear()) + "-" + (dateTime.getMonth()) + "-" + (dateTime.getDay()) + "T" + (dateTime.getHours()) + ":" + one2two(dateTime.getMinutes()) + ":" + one2two(dateTime.getSeconds());
    //获取本人信息 增加水印
    $.getJSON(getURLroot() + "/rest/user/self/simple", {
        "tokens": getTokens()
    }, function (json) {
        if (!json.success) return;
        var part = ele === undefined ? "body" : ele;
        var textColor = tColor === undefined ? "#d2d2d2" : tColor;
        $(part).watermark({
            texts: [json.data.name, json.dataMap.usergroup.parentgroupname, json.data.id, json.dataMap.usergroup.groupname, dateTimeStr], //水印文字
            textColor: textColor, //文字颜色
            textFont: '14px 微软雅黑', //字体
            width: 100, //水印文字的水平间距
            height: 100, //水印文字的高度间距（低于文字高度会被替代）
            textRotate: -30 //-90到0， 负数值，不包含-90
        });
    });
}

/**
 * ============================================================
 * 显示 modalDiv
 * modalSize: "" | modal-lg | modal-sm
 * modalDivTit 标题
 * modalDivFooterTit 底部注解
 * havFooter true | false
 * havActionBut true | false
 * modalDivActionTit 按钮名称
 * isBodyByHtml true | false
 * modalDivBody 显示的内容
 * url 调用地址
 * ============================================================
 * @param {type} args
 * @returns {undefined}
 */
function showModalDiv(args) {
    //modalDivTit, url, modalDivBody, modalSize, havActionBut, modalDivActionTit, modalDivFooterTit
    webCache.pushData("modalArgs", args);
    $("#modalDivBody").children().remove();
    $("#modalDialog").addClass(args["modalSize"] === undefined ? "" : args["modalSize"]);
    $("#modalDivTit").text(args["modalDivTit"] === undefined ? "" : args["modalDivTit"]);
    $("#modalDivFooterTit").text(args["modalDivFooterTit"] === undefined ? "" : args["modalDivFooterTit"]);
    $("#modalDivAction").unbind();
    if (args["havFooter"] === false) {
        $("#modalDivFooter").addClass("d-none");
    } else {
        $("#modalDivFooter").removeClass("d-none");
    }
    if (args["havActionBut"] === false) {
        $("#modalDivAction").addClass("d-none");
        $("#modalDivAction").text("--");
    } else {
        $("#modalDivAction").removeClass("d-none");
        $("#modalDivAction").text(args["modalDivActionTit"]);
    }
    if (args["havModalHeader"] === false) {
        $("#modalHeader").addClass("d-none");
    } else {
        $("#modalHeader").removeClass("d-none");
    }
    if (args["isBodyByHtml"] === true) {
        $("#modalDivBody").html(args["modalDivBody"]);
    } else {
        var url = getURLroot() + args["url"];
        $.ajax({
            type: "get",
            url: url,
            contentType: "text/html",
            dataType: "html",
            success: function (html) {
                $("#modalDivBody").html(html);
            }
        });
    }
    $('#modalDiv').modal('show');
}