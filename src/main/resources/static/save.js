/**
 *Description JavaScript
 **/
var userData = {};
var caseUserData = {};
var caseTouchingData = {};

$(function() {
    $("#name").blur(function() {
        if(userData["userid"] === undefined || userData["userid"] === null) {
            getCaseDateByUserid("mul");
        }
    });
    $("#userCard").blur(function() {
        if(userData["userid"] === undefined || userData["userid"] === null) {
            getCaseDateByUserid("single");
        }
    });
    // ------------------------------------------------------------------------------------------- save user
    /**
     * save user 提交
     */
    $("#save-user").click(function() {
        // 获取数据
        let check = true;
        let obj;
        $("input[name=user]").each(function(idx, itm) {
            let val = $(itm).val();
            userData[$(itm).attr("id")] = val;
            // 约束
            if($(itm).hasClass("must") && val === "") {
                check = false;
                obj = itm;
            }
        });
        //
        if(!check) {
            $.alert("请填写:[" + $(obj).attr("aria-label") + "]", "警告");
            return;
        }
        $("#save-user-loading").removeClass("d-none");
        $("#save-user").addClass("d-none");
        $.ajax({
            url: getURLroot() + "/rest/info/save/user",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            dataTpye: "json",
            data: JSON.stringify(userData),
            success: function (json) {
                $("#save-user-loading").addClass("d-none");
                $("#save-user").removeClass("d-none");
                if(json.success) {
                    // 下一步
                    $("#main-progress").attr("style", "width: 25%;");
                    $("#bar1").removeClass("bg-primary");
                    $("#bar1").addClass("text-white bg-success");
                    $("#bar2").addClass("text-white bg-primary");
                    $("#bar2").click();
                    userData = json.data;
                    $("#bar2").focus();
                    $.alert("基本信息保存完成，请继续填报信息", "提示");
                } else {
                    $.alert("保存异常:[" + json.msg + "]", "警告");
                }
            }
        });
    });
    // ------------------------------------------------------------------------------------------- save caseUser
    $("#save-case-user").click(function() {
        // 下一步
        $("#main-progress").attr("style", "width: 50%;");
        $("#bar2").removeClass("bg-primary");
        $("#bar2").addClass("text-white bg-success");
        $("#bar3").addClass("text-white bg-primary");
        $("#bar3").click();
        $("#bar3").focus();
    });
    // ------------------------------------------------------------------------------------------- save isolation
    /**
     * save caseUser 提交
     */
    $("#save-case-isolation").click(function() {
        //userData["userid"] = "1580288736018-001-0-61";
        if(userData["userid"] === undefined || userData["userid"] === "" || userData["userid"] === null) {
            $.alert("请先填写基本信息并点[提交基本信息]", "警告");
            return;
        }
        caseUserData = caseUserData === null ? {} : caseUserData;
        caseUserData["userid"] = userData["userid"];
        // 获取数据
        let check = true;
        let obj;
        $("input[name=case-user]").each(function(idx, itm) {
            let val = $(itm).val();
            caseUserData[$(itm).attr("id")] = val;
            // 约束
            if($(itm).hasClass("must") && val === "") {
                check = false;
                obj = itm;
            }
        });
        $("textarea[name=case-user]").each(function(idx, itm) {
            let val = $(itm).val();
            caseUserData[$(itm).attr("id")] = val;
            // 约束
            if($(itm).hasClass("must") && val === "") {
                check = false;
                obj = itm;
            }
        });
        //
        if(!check) {
            $.alert("请填写:[" + $(obj).attr("aria-label") + "]", "警告");
            $(obj).focus();
            return;
        }
        $("#save-case-isolation-loading").removeClass("d-none");
        $("#save-case-isolation").addClass("d-none");
        $.ajax({
            url: getURLroot() + "/rest/info/save/case-user",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            dataTpye: "json",
            data: JSON.stringify(caseUserData),
            success: function (json) {
                $("#save-case-isolation-loading").addClass("d-none");
                $("#save-case-isolation").removeClass("d-none");
                if(json.success) {
                    // 下一步
                    $("#main-progress").attr("style", "width: 75%;");
                    $("#bar3").removeClass("bg-primary");
                    $("#bar3").addClass("text-white bg-success");
                    $("#bar4").addClass("text-white bg-primary");
                    $("#bar4").click();
                    caseUserData = json.data;
                    $("#bar4").focus();
                    $.alert("基本信息保存完成，请继续填报信息", "提示");
                    getUserReportInfo();
                } else {
                    $.alert("保存异常:[" + json.msg + "]", "警告");
                }
            }
        });
    });
    // ------------------------------------------------------------------------------------------- save caseTouching
    /**
     * save caseTouching 提交
     */
    $("#save-case-touching").click(function() {
        //caseUserData["fkCaseUserId"] = "1580589135779-001-0-1";
        //caseUserData["userid"] = "1580288736018-001-0-61";
        if(caseUserData["id"] === undefined || caseUserData["id"] === "" || caseUserData["id"] === null) {
            $.alert("请先填写本人填报信息并点[提交本人填报]", "警告");
            return;
        }
        caseTouchingData = caseTouchingData === null ? {} : caseTouchingData;
        caseTouchingData["fkCaseUserId"] = caseUserData["id"];
        caseTouchingData["userid"] = caseUserData["userid"];
        // 获取数据
        let check = true;
        let obj;
        $("input[name=case-touching]").each(function(idx, itm) {
            let val = $(itm).val();
            caseTouchingData[$(itm).attr("id")] = val;
            // 约束
            if($(itm).hasClass("must") && val === "") {
                check = false;
                obj = itm;
            }
        });
        $("textarea[name=case-touching]").each(function(idx, itm) {
            let val = $(itm).val();
            caseTouchingData[$(itm).attr("id")] = val;
            // 约束
            if($(itm).hasClass("must") && val === "") {
                check = false;
                obj = itm;
            }
        });
        //
        if(!check) {
            $.alert("请填写:[" + $(obj).attr("aria-label") + "]", "警告");
            return;
        }
        $("#save-case-touching-loading").removeClass("d-none");
        $("#save-case-touching").addClass("d-none");
        $.ajax({
            url: getURLroot() + "/rest/info/save/case-touching",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            dataTpye: "json",
            data: JSON.stringify(caseTouchingData),
            success: function (json) {
                $("#save-case-touching-loading").addClass("d-none");
                $("#save-case-touching").removeClass("d-none");
                if(json.success) {
                    // 下一步
                    $("#main-progress").attr("style", "width: 100%;");
                    $("#bar4").removeClass("bg-primary");
                    $("#bar4").addClass("text-white bg-success");
                    $.alert("提交完成，您可查询既往填报", "感谢支持");
                    caseTouchingData = json.data;
                    getGroupCount();
                } else {
                    $.alert("保存异常:[" + json.msg + "]", "警告");
                }
            }
        });
    });
});

/**
 * 获取既往数据
 **/
function getUserReportInfo() {
    $.getJSON(getURLroot() + "/rest/info/get/user/" + userData["userid"], {}, function(json) {
        let tabCaseUsers = json.datas;
        // 既往
        if(tabCaseUsers !== null) {
            let html = "";
            for(let idx in tabCaseUsers) {
                let tcu = tabCaseUsers[idx];
                html += "<div class=\"weui-cell\">"
                    + "    <div class=\"weui-cell__hd\">"
                    + "        <label class=\"weui-label list-tit\">" + tcu.caseDate + "</label>"
                    + "    </div>"
                    + "    <div class=\"weui-cell__bd\">"
                    + "        <label class=\"weui-label list-tit\">" + tcu.bodySign + "</label>"
                    + "    </div>"
                    + "</div>";
            }
            $("#case-his").children().remove();
            $("#case-his").html(html);
        }
    });
}

/**
 * 获取填报数据
 **/
function getCaseDateByUserid(tag) {
    let url = getURLroot() + "/rest/info/get/case-user/" + $("#userCard").val();
    if(tag === "mul") {
        url = getURLroot() + "/rest/info/get/case-user/" + $("#name").val() + "/" + $("#pgroupname").val() + "/" + $("#groupname").val();
    }
    //
    $.getJSON(url, {}, function(json) {
        if(!json.success) return;
        userData = json.dataMap["tabUser"];
        caseUserData = json.dataMap["tabCaseUser"];
        caseTouchingData = json.dataMap["tabCaseTouching"];

        // 赋值 tabUser
        if(userData !== null) {
            $("[id][name=user]").each(function(idx, itm) {
                let val = userData[$(itm).attr("id")];
                $(itm).val(val);
            });
        }
        // 赋值 tabCaseUser
        if(caseUserData !== null) {
            $("[id][name=case-user]").each(function(idx, itm) {
                let id = $(itm).attr("id");
                let val = caseUserData[id];
                if(id === "caseDate") {
                    //
                } else if($("input[type=checkbox][name=" + id + "]").attr("name") !== undefined) {
                    if(val === $(itm).attr("valt")) {
                        $("input[type=checkbox][name=" + id + "]").click();
                    }
                } else {
                    $(itm).val(val);
                }
            });
        }
        // 赋值 tabCaseTouching
        if(caseTouchingData !== null) {
            $("[id][name=case-touching]").each(function(idx, itm) {
                let id = $(itm).attr("id");
                let val = caseTouchingData[id];
                if($("input[type=checkbox][name=" + id + "]").attr("name") !== undefined) {
                    if(val === $(itm).attr("valt")) {
                        $("input[type=checkbox][name=" + id + "]").click();
                    }
                } else {
                    $(itm).val(val);
                }
            });
        }
        // 是否离开武汉
        if($("#strokeDest").val() === undefined || $("#strokeDest").val() === "") {
            //
        } else {
            $("input[type=checkbox][name=strokeDest-checkbox]").click();
        }
        // 获取既往填报信息
        getUserReportInfo();
    });
}