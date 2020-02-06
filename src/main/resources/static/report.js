/**
 *Description JavaScript
 **/

$(function () {
    $("#startDate").val($.format.date(new Date(), 'yyyy-MM-dd'));
    $("#startDate").calendar({
        "dateFormat": 'yyyy-mm-dd'
    });
    $("#viewname").select({
        title: "选择报表",
        items: ["总行报表-总数据表", "同事", "其他"]
    });
    $("#select-report").click(function() {
        let conf = report_json[$(this).val()];
        let columns = conf["columns"];
        let url = conf["url"] + "/" + $("#startDate").val();
        initData(url, columns);
    });
});


var report_json = {
    "总行报表-总数据表" : {
        url: getURLroot() + "/rest/report/autoview/VIEW_ISOLATION_01",
        columns: [{"data": "caseDate", "title": "填报日期"},
            {"data": "pgroupname", "title": "机构名称"},
            {"data": "groupname", "title": "部门名称"},
            {"data": "name", "title": "姓名"},
            {"data": "userCity", "title": "城市"},
            {"data": "userCommunity", "title": "社区"},
            {"data": "strokeDest", "title": "行程-目的地"},
            {"data": "strokeVia", "title": "途径武汉"},
            {"data": "touchingSign", "title": "接触史"},
            {"data": "bodySign", "title": "本人或家属身体状态"},
            {"data": "bodycase", "title": "症状描述"},
            {"data": "doctorSign", "title": "是否就医"},
            {"data": "hospital", "title": "就医医院"},
            {"data": "doctorTime", "title": "就医时间"},
            {"data": "dectorInfo", "title": "医生诊断"},
            {"data": "caseExplain", "title": "其他"}]
    }
}

/**
 * 初始化数据
 * @returns {undefined}
 */
function initData(url, dataList) {
    var table = $('#datatable-id').DataTable({
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
            "dataSrc": function (json) {
                return json.datas === undefined ? [] : json.datas;
            }
        },
        "columns": dataList,
        "initComplete": function () {
            //
        }
    });
}