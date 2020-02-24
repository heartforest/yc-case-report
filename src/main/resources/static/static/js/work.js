/**
 * 进出登记 JS
 **/
var cookidKey = 'yinchuan-cmbchina-work-user-key';
var workidMap = {
    "951000": "新材大厦",
    "02": "中海支行",
    "03": "丽景街支行",
    "04": "兴庆府支行",
    "05": "德胜支行",
    "06": "文化东街支行",
    "07": "新世纪支行",
    "08": "新华街支行",
    "09": "民族北街支行",
    "10": "西夏支行",
    "11": "金融街支行",
    "12": "阅海万家支行",
    "13": "黄河路支行"
};
/**
 * 项目初始化
 **/
$(function() {
    // 初始化加载
    $.ajax({
        url: getURLroot() + "/static/html/work-body.html",
        type: "GET",
        contentType: "text/html; charset=UTF-8",
        dataTpye: "text",
        success: function (html) {
            $("#work-body").children().remove();
            $("#work-body").html(html);
            $("#work-div").removeClass("d-none");
            // 初始化日期
            initDate();
            // 选择初始化
            initSelect();
            // 初始化保存
            initSave();
            //
            $("#fuzzy-key").blur(function() {
                initWorkInfo($(this).val());
            });
            // 尝试找出既往记录
            let userPhone = $.cookie(cookidKey);
            initWorkInfo(userPhone);
        }
    });
});

/**
 * 选择初始化
 */
function initSelect() {
    // 性别
    $("#sex").select({
        title: "性别",
        items: ["男", "女"]
    });

    // 单位
    $("#pgroupname").select({
        title: "单位",
        items: ["招商银行银川分行", "新材物业", "招行/新材保安", "新材地产", "兴业证券", "电梯公司", "汇合物业", "其他单位"]
    });

    // 当前位置
    $("#workBuilding").val(workidMap[$("#work-body").attr("workid")]);
}
/**
 * 日期初始化
 */
function initDate() {
    // 填报日期
    $("#workDate").val($.format.date(new Date(), 'yyyy-MM-dd'));
    $("#workDate").calendar({
        "dateFormat": 'yyyy-mm-dd'
    });
}

/**
 * 初始化initWorkInfo
 */
function initWorkInfo(fuzzyKey) {
    if(fuzzyKey === "" || fuzzyKey === null || fuzzyKey === undefined) return;
    $.getJSON(getURLroot() + "/rest/work/user/fuzzy/" + fuzzyKey, {}, function(json) {
        if(!json.success || json.dataMap["tabUser"] === null || json.dataMap["tabUser"] === undefined) return;
        let tabUser = json.dataMap["tabUser"];
        let tabCaseUser = json.dataMap["tabCaseUser"];
        // 赋值 tabUser
        $("[id][name=user]").each(function(idx, itm) {
            let val = tabUser[$(itm).attr("id")];
            $(itm).val(val);
        });
        $("#bodyTemperature").focus();
        try {
            $("#userCommunity").val($("#userCommunity").val() === "" && tabCaseUser.userCommunity !== null ? tabCaseUser.userCommunity : $("#userCommunity").val());
            $("#userBuilding").val($("#userBuilding").val() === "" && tabCaseUser.userBuilding !== null ? tabCaseUser.userBuilding : $("#userBuilding").val());
        } catch (e) {}
    });
}

// ------------------------------------------------------------------------------------------- save work
/**
 * save work 提交
 */
function initSave() {
    $("#save-work").click(function() {
        // 获取数据
        let check = true;
        let obj;
        let user = {};
        let work = {
            "workType": $("#work-body").attr("workType"),
            "workId": $("#work-body").attr("workid")
        };
        let data = {
            "user": user,
            "work": work
        };
        $("input").each(function(idx, itm) {
            let val = $(itm).val();
            if($(itm).attr("name") === "user") {
                user[$(itm).attr("id")] = val;
            } else if($(itm).attr("name") === "work") {
                work[$(itm).attr("id")] = val;
            }
            // 约束
            if($(itm).hasClass("must") && val === "") {
                check = false;
                obj = itm;
            }
        });
        // 身份证校验
        let patternUserCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        let patternUserPhone = /^1[3|4|5|6|7|8|9][0-9]{9}$/;
        let patternBodyTemperature = /^(((3[0-9]|4[0-3])(\.\d)?)|44)$/;
        //
        if(!patternBodyTemperature.test($('#bodyTemperature').val())) {
            $.alert("体温填写有误,请保持在[30 ~ 44]之间", "警告");
            return;
        } else if(!patternUserPhone.test($('#userPhone').val())) {
            $.alert("手机号填写有误", "警告");
            return;
        } else if(!patternUserCard.test($('#userCard').val())) {
            $.alert("身份证号填写有误", "警告");
            return;
        } else if(!check) {
            $.alert("请填写:[" + $(obj).attr("aria-label") + "]", "警告");
            return;
        }
        $("#save-work-loading").removeClass("d-none");
        $("#save-work").addClass("d-none");
        $.ajax({
            url: getURLroot() + "/rest/work/save",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            dataTpye: "json",
            data: JSON.stringify(data),
            success: function (json) {
                $("#save-work-loading").addClass("d-none");
                $("#save-work").removeClass("d-none");
                if(json.success) {
                    $.cookie(cookidKey, $('#userPhone').val(), {expires: 200, path: '/yc-case-report/web/work'});
                    $.alert("提交完成!", "感谢支持<br>祝您生活愉快,身体健康!");
                } else {
                    $.alert("保存异常:[" + json.msg + "]", "警告");
                }
            }
        });
    });
}