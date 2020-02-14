/**
 *Description JavaScript
 **/
var timestamp = "";
var sha256 = "";
var cookidReprotType = 'yinchuan-cmbchina-report-type';
var cookidKey = 'yinchuan-cmbchina-report';
var cookidTimestamp = 'yinchuan-cmbchina-report-timestamp';
var viewItems = {
    "all": ["数据总览表",
        "总行报表-总数据表",
        "总行报表-隔离数据表",
        "个人离宁返宁情况排查表-金融局",
        "亲属及社会关系接触情况排查表-金融局",
        "个人离宁返宁情况排查表-2",
        "亲属及社会关系接触情况排查表-2",
        "总行报表-总行最新-0207"],
    "part2": ["数据总览表"]
}
$(function () {
    // 判断是否登录
    //console.log("--->>> " + sha256 + "|" + timestamp + "|" + $.cookie(cookidKey) + "|" + $.cookie(cookidTimestamp));
    if(sha256 === "" && ($.cookie(cookidKey) === null || $.cookie(cookidKey) === undefined)) {
        timestamp = (new Date()).valueOf();
        // 登录
        login();
    } else if($.cookie(cookidKey) !== null) {
        sha256 = $.cookie(cookidKey);
        timestamp = $.cookie(cookidTimestamp);
        $("#viewname").select({
            title: "选择报表",
            items: viewItems[$.cookie(cookidReprotType)]
        });
    }
    //
    $("#startDate").val($.format.date(new Date(), 'yyyy-MM-dd'));
    $("#startDate").calendar({
        "dateFormat": 'yyyy-mm-dd'
    });
    $("#select-report").click(function() {
        let conf = report_json[$("#viewname").val()];
        let columns = conf["columns"];
        let url = conf["url"] + "/" + $("#startDate").val();
        $("#datatable-id").remove();
        $("#tablePanal").html("<table id=\"datatable-id\" class=\"display table-striped table-hover table-bordered stripe datatables-font dataTables-example\"></table>");
        initData(url, columns);
    });
    $("#btn-login-alert").click(function() {
        login();
    });
});

function login() {
    $.prompt({
        title: '浏览填报数据',
        text: '请输入验证码',
        input: '预设验证码',
        empty: false, // 是否允许为空
        onOK: function (input) {
            loginAction(input);
        },
        onCancel: function () {
            //点击取消
        }
    });
}

function loginAction(passwordkey) {
    sha256 = sha256_digest(passwordkey + timestamp);
    $.getJSON(getURLroot() + "/rest/report/login/" + timestamp + "/" + sha256, {}, function(json) {
        if(json.success) {
            $.cookie(cookidKey, sha256, {expires: 1});
            $.cookie(cookidTimestamp, timestamp, {expires: 1});
            $.cookie(cookidReprotType, json.msg, {expires: 1});
            $("#viewname").select({
                title: "选择报表",
                items: viewItems[json.msg]
            });
            $.alert("验证码通过", "提示");
        } else {
            $.alert("验证码错误", "提示");
        }
    });
}

var report_json = {
    "数据总览表": {
        url: getURLroot() + "/rest/report/autoview/VIEW_CASE",
        columns: [{"data": "case_date", "title": "填报日期"},
            {"data": "groupname", "title": "部门名称"},
            {"data": "name", "title": "姓名"},
            {"data": "user_city", "title": "当前城市"},
            {"data": "user_community", "title": "当前社区"},
            {"data": "hvaleav", "title": "是否离开宁夏"},
            {"data": "stroke_dest", "title": "目的地"},
            {"data": "stroke_date_go", "title": "离宁时间"},
            {"data": "stroke_date_back", "title": "返宁时间"},
            {"data": "body_sign", "title": "本人或家属身体状态"},
            {"data": "user_call_community", "title": "有无向社区申报"},
            {"data": "body_temperature", "title": "今日体温"},
            {"data": "isolation_status", "title": "隔离状态"},
            {"data": "work_status", "title": "现场状态"},
            {"data": "epidemic_community_count", "title": "员工所在住宅小区发现确诊病例"},
            {"data": "stranded_sign", "title": "交通滞留"},
            {"data": "examine_name", "title": "验证人"},
            {"data": "examine_time", "title": "验证时间"}]
    },
    "个人离宁返宁情况排查表-金融局" : {
        url: getURLroot() + "/rest/report/autoview/VIEW_PUT1",
        columns: [{"data": "case_date", "title": "填报日期"},
            {"data": "pgroupname", "title": "机构名称"},
            {"data": "name", "title": "姓名"},
            {"data": "user_card", "title": "身份证号"},
            {"data": "user_community", "title": "住址"},
            {"data": "user_phone", "title": "联系电话"},
            {"data": "stroke_date_go", "title": "离宁时间"},
            {"data": "stroke_dest", "title": "去往地点"},
            {"data": "stroke_date_back", "title": "返宁时间"},
            {"data": "isolation_date", "title": "居家隔离时间"},
            {"data": "hospital", "title": "有无就诊史(医疗机构名称)"},
            {"data": "user_call_community", "title": "有无向社区申报"},
            {"data": "user_meet", "title": "返宁后有无参加三人以上聚会"},
            {"data": "user_pub_place", "title": "返宁后有无到人流密集场所"}]
    },
    "亲属及社会关系接触情况排查表-金融局" : {
        url: getURLroot() + "/rest/report/autoview/VIEW_PUT2",
        columns: [{"data": "case_date", "title": "填报日期"},
            {"data": "groupname", "title": "机构名称"},
            {"data": "name", "title": "姓名"},
            {"data": "user_card", "title": "身份证号"},
            {"data": "user_community", "title": "住址"},
            {"data": "user_phone", "title": "联系电话"},
            {"data": "hav_touching", "title": "有无亲属2020年1月8日后从外地来宁（反宁）人员"},
            {"data": "touching_user", "title": "接触对象姓名"},
            {"data": "touching_user_relat", "title": "与本人关系"},
            {"data": "touching_user_from", "title": "来源地"},
            {"data": "touching_user_date", "title": "接触日期"},
            {"data": "touching_user_hospitall", "title": "有无就诊史(医疗机构名称)"},
            {"data": "touching_user_call_community", "title": "有无向社区申报"},
            {"data": "touching_meet", "title": "返宁后有无参加三人以上聚会"},
            {"data": "touching_pub_place", "title": "返宁后有无到人流密集场所"}]
    },
    "个人离宁返宁情况排查表-2" : {
        url: getURLroot() + "/rest/report/autoview/VIEW_PUT4",
        columns: [{"data": "case_date", "title": "填报日期"},
            {"data": "pgroupname", "title": "机构名称"},
            {"data": "groupname", "title": "部门名称"},
            {"data": "name", "title": "姓名"},
            {"data": "user_phone", "title": "联系电话"},
            {"data": "user_card", "title": "身份证号"},
            {"data": "user_city", "title": "当前城市"},
            {"data": "user_community", "title": "社区/小区"},
            {"data": "user_building", "title": "楼栋单元"},
            {"data": "hvaleav", "title": "是否离开宁夏"},
            {"data": "stroke_dest", "title": "目的地"},
            {"data": "stroke_via", "title": "是否途经武汉"},
            {"data": "stroke_date_go", "title": "离宁时间"},
            {"data": "stroke_date_back", "title": "返宁时间"},
            {"data": "isolation_date", "title": "居家隔离时间"},
            {"data": "touching_sign", "title": "是否有病毒感染确诊病例解除史"},
            {"data": "body_sign", "title": "本人或家属身体状态"},
            {"data": "bodycase", "title": "症状"},
            {"data": "user_call_community", "title": "有无向社区申报"},
            {"data": "body_temperature", "title": "今日体温"},
            {"data": "doctor_sign", "title": "是否就医"},
            {"data": "hospital", "title": "就医医院"},
            {"data": "doctor_time", "title": "就医时间"},
            {"data": "dector_info", "title": "医生诊断"},
            {"data": "case_explain", "title": "其他"}]
    },
    "亲属及社会关系接触情况排查表-2" : {
        url: getURLroot() + "/rest/report/autoview/VIEW_PUT3",
        columns: [{"data": "case_date", "title": "填报日期"},
            {"data": "pgroupname", "title": "机构名称"},
            {"data": "groupname", "title": "部门名称"},
            {"data": "name", "title": "姓名"},
            {"data": "user_phone", "title": "联系电话"},
            {"data": "user_card", "title": "身份证号"},
            {"data": "hav_touching", "title": "有无亲属2020年1月8日后从外地来宁（反宁）人员"},
            {"data": "touching_user", "title": "接触对象姓名"},
            {"data": "touching_user_relat", "title": "与本人关系"},
            {"data": "touching_user_from", "title": "来源地"},
            {"data": "touching_user_date", "title": "接触日期"},
            {"data": "touching_user_hospitall", "title": "有无就诊史(医疗机构名称)"},
            {"data": "touching_user_call_community", "title": "有无向社区申报"},
            {"data": "touching_meet", "title": "返宁后有无参加三人以上聚会"},
            {"data": "touching_pub_place", "title": "返宁后有无到人流密集场所"},
            {"data": "touching_explain", "title": "其他"}]
    },
    "总行报表-总数据表" : {
        url: getURLroot() + "/rest/report/autoview/VIEW_ISOLATION_01",
        columns: [{"data": "case_date", "title": "填报日期"},
            {"data": "pgroupname", "title": "机构名称"},
            {"data": "groupname", "title": "部门名称"},
            {"data": "name", "title": "姓名"},
            {"data": "istatus1", "title": "政府强制"},
            {"data": "istatus2", "title": "行内要求"},
            {"data": "istatus3", "title": "主动隔离"},
            {"data": "istatus4", "title": "解除隔离"},
            {"data": "work_status1", "title": "现场办公"},
            {"data": "work_status2", "title": "居家远程办公"},
            {"data": "work_status3", "title": "居家休息"},
            {"data": "epidemic_community_count", "title": "员工所在住宅小区发现确诊病例"},
            {"data": "stranded_sign", "title": "交通滞留"}]
    },
    "总行报表-隔离数据表" : {
        url: getURLroot() + "/rest/report/autoview/VIEW_ISOLATION_02",
        columns: [{"data": "case_date", "title": "填报日期"},
            {"data": "pgroupname", "title": "一级分行"},
            {"data": "groupname", "title": "支行/部室/团队"},
            {"data": "name", "title": "姓名"},
            {"data": "sex", "title": "性别"},
            {"data": "user_age", "title": "年龄"},
            {"data": "user_job", "title": "岗位"},
            {"data": "user_post", "title": "职位"},
            {"data": "user_type", "title": "人员类别"},
            {"data": "user_city", "title": "所在地省市/驻地省市"},
            {"data": "user_community", "title": "当前住址"},
            {"data": "user_building", "title": "当前住址"},
            {"data": "user_phone", "title": "本人联系电话"},
            {"data": "user_urgent_phone", "title": "本人应急联系人及联系电话"},
            {"data": "stroke_via", "title": "14天内有无湖北旅行、生活史"},
            {"data": "touching_sign", "title": "14天内有无湖北/武汉亲友接触史"},
            {"data": "body_sign", "title": "目前身体情况"},
            {"data": "isolation_status", "title": "状态"},
            {"data": "isolation_description", "title": "基本情况简述"},
            {"data": "isolation_date", "title": "开始隔离日期"},
            {"data": "unisolation_date", "title": "预计解除隔离日期"},
            {"data": "work_status", "title": "工作及休息情况"},
            {"data": "stranded_sign", "title": "交通滞留"}]
    },
    "总行报表-总行最新-0207" : {
        url: getURLroot() + "/rest/report/autoview/VIEW_ISOLATION_03",
        columns: [{"data": "case_date", "title": "填报日期"},
            {"data": "pgroupname", "title": "一级分行"},
            {"data": "groupname", "title": "支行/部室/团队"},
            {"data": "name", "title": "姓名"},
            {"data": "sex", "title": "性别"},
            {"data": "user_age", "title": "年龄"},
            {"data": "user_job", "title": "岗位"},
            {"data": "user_post", "title": "职位"},
            {"data": "user_type", "title": "人员类别"},
            {"data": "user_city", "title": "所在地省市/驻地省市"},
            {"data": "user_community", "title": "当前住址"},
            {"data": "user_building", "title": "当前住址"},
            {"data": "user_phone", "title": "本人联系电话"},
            {"data": "user_urgent_phone", "title": "本人应急联系人及联系电话"},
            {"data": "stroke_via", "title": "14天内有无湖北旅行、生活史"},
            {"data": "touching_sign", "title": "14天内有无湖北/武汉亲友接触史"},
            {"data": "body_sign", "title": "目前身体情况"},
            {"data": "isolation_tag", "title": "是否隔离"},
            {"data": "isolation_status", "title": "隔离原因"},
            {"data": "isolation_description", "title": "隔离描述"},
            {"data": "bodycase", "title": "症状描述"},
            {"data": "isolation_date", "title": "开始隔离日期"},
            {"data": "unisolation_date", "title": "预计解除隔离日期"},
            {"data": "work_status", "title": "工作及休息情况"},
            {"data": "stranded_sign", "title": "交通滞留"}]
    }
}
/**
 * 初始化数据
 * @returns {undefined}
 */
function initData(url, dataList) {
    let table = $('#datatable-id').DataTable({
        "processing": true,
        "paging": false,
        "scrollY": "600px",
        "bDestroy": true,
        "dom": 'Bfrtip',
        "buttons": [{
            "text": "Excle",
            "extend": 'excelHtml5'
        }, {
            "text": "Copy",
            "extend": 'copyHtml5'
        }, {
            "text": "CSV",
            "extend": 'csvHtml5'
        }, {
            "text": "打印",
            "extend": 'print'
        }],
        "ajax": {
            "url": url,
            "data": {
                "timestamp": timestamp,
                "tokens": sha256
            },
            "dataSrc": function (json) {
                if(!json.success) {
                    $.alert("验证码错误", "提示");
                    return [];
                }
                return json.datas === undefined ? [] : json.datas;
            }
        },
        "columns": dataList,
        "initComplete": function () {
            //
        }
    });
}