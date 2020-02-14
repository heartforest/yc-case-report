/**
 * 结果审核
 **/
function initUserResult() {
    let itemsList = [];
    for(let idx in groupItems) {
        let item = groupItems[idx];
        if(userData["groupname"] !== item["GROUPNAME"]) continue;
        if(userData["userPost"] === "普通员工" && userData["userid"] !== item["userid"]) continue;
        let ls = {};
        ls["title"] = item["name"];
        ls["value"] = item["userid"];
        itemsList.push(ls);
    }
    //选择列表
    $("#result-user").select({
        title: "选择员工",
        items: itemsList,
        onChange: function(data){
            getUserInfoAll(data.values, data.titles);
        }
    });
    $("#result-date").click(function() {
        $("#user-result").children().remove();
    });
}

/**
 * 触发获取结果
 */
function selectUserInfoAll(userid, name) {
    $("#bar12").click();
    window.location.hostname == getURLroot() + "/web/info#tab12";
    $("#result-user").val(name);
    getUserInfoAll(userid, name);
}
var USERID_RESULT;
/**
 * 获取人员信息 - 全量
 * @param userid
 * @param name
 */
function getUserInfoAll(userid, name) {
    if(userid === undefined) return;
    $("#result-user-loading").removeClass("d-none");
    $("#user-result").children().remove();
    let url = getURLroot() + "/rest/info/user/all/" + userid + "/" + $("#result-date").val();
    $.getJSON(url, {}, function(json) {
        $("#result-user-loading").addClass("d-none");
        if(!json.success) {
            $.alert("获取[" + name + "]的填报信息:" + json.msg, "警告");
            return;
        }
        let examineTabUser = json.dataMap["tabUser"];
        USERID_RESULT = examineTabUser["userid"];
        let examineTabCaseUser = json.dataMap["tabCaseUser"];
        let examineTabCaseTouching = json.dataMap["tabCaseTouching"] === null ? {} : json.dataMap["tabCaseTouching"];
        // 审核按钮
        let html = "";
        if(userData["userPost"] === "支行行长" || userData["userPost"] === "部门负责人") {
            let msg = "验证人:" + examineTabCaseUser["examineName"] + "|验证时间:" + examineTabCaseUser["examineTime"];
            html += "<div class=\"weui-cell\">"
                + "    <div class=\"weui-cell__hd\">"
                + "        <label class=\"weui-label list-tit\">审核</label>"
                + "    </div>"
                + "    <div class=\"weui-cell__bd\">"
                + "        <label id='examine-msg'>" + (examineTabCaseUser["examineName"] == null ? "" : msg) + "</label>"
                + "    </div>"
                + "    <div class=\"weui-cell__ft\">"
                + "        <input class=\"weui-switch\" type=\"checkbox\" id=\"examine-btn\" caseUserId=\"" + examineTabCaseUser["id"] + "\" examineName=\"" + examineTabCaseUser["examineName"] + "\">"
                + "    </div>"
                + "</div>";
        }
        // 赋值 tabUser
        $("[id][name=user]").each(function(idx, itm) {
            if($(itm).attr("id") === "updateKey") return;
            let val = examineTabUser[$(itm).attr("id")];
            html += "<div class=\"weui-cell\">"
                + "    <div class=\"weui-cell__hd\">"
                + "        <label class=\"weui-label list-tit\">" + $(itm).attr("aria-label") + "</label>"
                + "    </div>"
                + "    <div class=\"weui-cell__bd\">"
                + "        <label class=\"text-primary\">" + val + "</label>"
                + "    </div>"
                + "</div>";
        });
        // 赋值 tabCaseUser
        html += "<hr>";
        $("[id][name=case-user]").each(function(idx, itm) {
            let val = examineTabCaseUser[$(itm).attr("id")];
            if($(itm).attr("id") === "strokeDest-checkbox") {
                val = examineTabCaseUser["strokeDest"] === "" || examineTabCaseUser["strokeDest"] === null ? "否" : "是";
                val = val === undefined ? "否" : "是";
            }
            html += "<div class=\"weui-cell\">"
                + "    <div class=\"weui-cell__hd\">"
                + "        <label class=\"weui-label list-tit\">" + $(itm).attr("aria-label") + "</label>"
                + "    </div>"
                + "    <div class=\"weui-cell__bd\">"
                + "        <label class=\"text-primary\">" + ((val === null || val === "") ? "未选" : val) + "</label>"
                + "    </div>"
                + "</div>";
        });
        // 赋值 tabCaseTouching
        html += "<hr>";
        $("[id][name=case-touching]").each(function(idx, itm) {
            let val = examineTabCaseTouching[$(itm).attr("id")];
            if(json.dataMap["tabCaseTouching"] === null) {
                val = "家属信息未填报，视为家属无异常!";
            } else if($(itm).attr("id") === "btn-touching-focus") {
                val = examineTabCaseTouching["touchingUser"] === "" || examineTabCaseTouching["touchingUser"] === null ? "否" : "是";
                val = val === undefined ? "否" : "是";
            }
            html += "<div class=\"weui-cell\">"
                + "    <div class=\"weui-cell__hd\">"
                + "        <label class=\"weui-label list-must\">" + $(itm).attr("aria-label") + "</label>"
                + "    </div>"
                + "    <div class=\"weui-cell__bd\">"
                + "        <label class=\"text-primary\">" + ((val === null || val === "") ? "未选" : val) + "</label>"
                + "    </div>"
                + "</div>";
        });
        $("#user-result").html(html);
        examineAction();
    });
}

/**
 * 审核操作
 */
function examineAction() {
    if($("#examine-btn").attr("examineName") !== undefined && $("#examine-btn").attr("examineName") !== "" && $("#examine-btn").attr("examineName") !== "null") {
        $("#examine-btn").click();
    }
    //
    $("#examine-btn").click(function() {
        let examineCaseUserData = {};
        examineCaseUserData["id"] = $(this).attr("caseUserId");
        examineCaseUserData["examineName"] = $(this).prop("checked") ? userData["name"] : "";
        examineCaseUserData["examineUserid"] = userData["userid"];
        $.ajax({
            url: getURLroot() + "/rest/info/examine",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            dataTpye: "json",
            data: JSON.stringify(examineCaseUserData),
            success: function (json) {
                if(json.success) {
                    let html = "验证人:" + json.data["examineName"] + "|验证时间:" + json.data["examineTime"];
                    $("#examine-msg").html(html);
                } else {
                    $.alert("本页保存异常:[" + json.msg + "]", "警告");
                }
            }
        });
    });
}