/**
 * 项目初始化
 **/
$(function() {
    // 时间处理
    initDate();
    //选择初始化
    initSelect();
    // 机构初始化
    initGroup();
    //初始化按钮事件
    $("input[type=checkbox]").click(function() {initBtn($(this))});
    $("#groupinfo").attr("href", getURLroot() + "/web/group-info");
    //
    $("#group-check").click(function() {
        if($(this).prop("checked")) {
            $("#group-div").removeClass("d-none");
        } else {
            $("#group-div").addClass("d-none");
        }
    });
});

// 选择初始化
function initSelect() {
    // 当前所在城市
    $("#userCity").cityPicker({
        title: "当前所在城市"
    });
    $("#userCity-oth").change(function() {
        $("#userCity").val($(this).val());
    });
    $("#userCity").change(function() {
        // 触发目的地址
        if($(this).val().indexOf("银川") < 0 && !$("input[type=checkbox][name=strokeDest-checkbox]").prop("checked")) {
            $("input[type=checkbox][name=strokeDest-checkbox]").click();
        } else if($(this).val().indexOf("银川") > 0 && $("#strokeDest-checkbox").prop("checked")) {
            //
        }
    });
    // 行程-目的地
    $("#strokeDest").cityPicker({
        title: "行程-目的地"
    });
    $("#strokeDest-oth").change(function() {
        $("#strokeDest").val($(this).val());
    });
    // 接触对象来源地
    $("#touchingUserFrom").cityPicker({
        title: "行程-目的地"
    });
    $("#touchingUserFrom-oth").change(function() {
        $("#touchingUserFrom").val($(this).val());
    });
    // 症状描述
    $("#bodycase").select({
        title: "症状描述",
        multi: true,
        items: ["发热（37.3度以上）", "咳嗽", "咽喉疼痛", "头疼", "流鼻涕", "其他"]
    });
    // 与本人关系
    $("#touchingUserRelat").select({
        title: "与本人关系",
        multi: true,
        items: ["亲友", "同事", "其他"]
    });

}

function initDate() {
    // 填报日期
    $("#caseDate").val($.format.date(new Date(), 'yyyy-MM-dd'));
    $("#caseDate").calendar({
        "dateFormat": 'yyyy-mm-dd'
    });
    // 离宁时间
    $("#strokeDateGo").calendar({
        "dateFormat": 'yyyy-mm-dd'
    });
    // 返宁时间
    $("#strokeDateBack").calendar({
        "dateFormat": 'yyyy-mm-dd'
    });
    // 隔离时间
    $("#isolationDate").calendar({
        "dateFormat": 'yyyy-mm-dd'
    });
    // 就医时间
    $("#doctorTime").calendar({
        "dateFormat": 'yyyy-mm-dd'
    });
    // 接触日期
    $("#touchingUserDate").calendar({
        "dateFormat": 'yyyy-mm-dd'
    });
    // 查询时间
    $("#group-date").calendar({
        "dateFormat": 'yyyy-mm-dd'
    });
    $("#group-date").val($.format.date(new Date(), 'yyyy-MM-dd'));
}

/**
 * 部门选择
 **/
function initGroup() {
    $.getJSON(getURLroot() + "/conf/group.json", {}, function(data) {
        let orgList = [];
        let html = "";
        let groupInfo = data["tab_group_info"];
        for(let idx in groupInfo) {
            let groupname = groupInfo[idx].groupname;
            if(groupname === "行长室") continue;
            orgList.push(groupname);
            html += "<div class=\"weui-cell\">" +
                "   <div class=\"weui-cell__hd\">" +
                "       <label class=\"weui-label text-right list-tit\">" + groupname + "</label>" +
                "   </div>" +
                "   <div class=\"weui-cell__bd\">" +
                "       <div class=''>" +
                "           <label class=\"font-weight-bold text-left w-25\" name='group-count' val=\"" + groupname + "-ctn\"></label>" +
                "           <label class=\"font-weight-bold text-left w-25 text-primary\" name='group-count' val=\"" + groupname + "-case\"></label>" +
                "       </div>" +
                "       <div class=''>" +
                "           <input class=\"weui-input px-2 text-center\" type=\"text\" placeholder=\"详情\" id=\"group-sel-" + idx + "\" name=\"group-sel\" readonly aria-label=\"" + groupname + "\">" +
                "       </div>" +
                "   </div>" +
                "</div>";
        }
        $("#group-count").html(html);
        $("#groupname").select({
            title: "选择部门",
            items: orgList
        });
        getGroupCount();
    });
}

/**
 * 机构信息调用时间
 */
$("#group-date").change(function() {
    getGroupCount();
});
/**
 * 获取机构填报信息
 */
function getGroupCount() {
    if($("#group-date").val() === undefined) return;
    $.getJSON(getURLroot() + "/rest/common/group/get/" + ($("#group-date").val()), {}, function(json) {
        let groupCount = json.dataMap["groupCount"];
        let groupCountCase = json.dataMap["groupCountCase"];
        let groupItems = json.dataMap["groupItems"];
        $("#bar4").removeClass("d-none");
        for(let idx in groupCount) {
            let gc = groupCount[idx];
            $("label[name=group-count][val=" + gc["GROUPNAME"] + "-ctn]").text("应报:" + gc["org_cnt"]);
        }
        for(let idx in groupCountCase) {
            let gcc = groupCountCase[idx];
            $("label[name=group-count][val=" + gcc["GROUPNAME"] + "-case]").text("实报:" + gcc["org_cnt"]);
        }
        // 填报明细
        let groupByNameList = {};
        for(let idx in groupItems) {
            // GROUPNAME, tu.name, tcu.case_date
            let item = groupItems[idx];
            let ls = groupByNameList[item["GROUPNAME"]];
            if(ls === undefined) {
                ls = [];
                groupByNameList[item["GROUPNAME"]] = ls;
            }
            ls.push(item["name"] + ":" + (item["case_date"] === undefined ? "未填报" : item["case_date"]));
        }
        $("input[name=group-sel]").click(function() {
            let groupname = $(this).attr("aria-label");
            let data = groupByNameList[groupname];
            //$.alert(data, groupname);
            let msg = "";
            for(let idx in data) {
                msg += data[idx] + " | ";
            }
            $.alert(msg, groupname);
        });
    });
}

/**
 * 按钮事件
 */
function initBtn(btn) {
    let actname = $(btn).attr("actname");
    let name = $(btn).attr("name");
    // 是否触发事件
    if(actname === undefined) {
        //
    } else if($(btn).prop("checked")) {
        $("[name=" + actname + "-cell]").removeClass("d-none");
    } else {
        $("[name=" + actname + "-cell]").addClass("d-none");
    }
    // 赋值
    $("#" + name).val($("#" + name).attr("val" + ($(btn).prop("checked") ? "t" : "f")));
}