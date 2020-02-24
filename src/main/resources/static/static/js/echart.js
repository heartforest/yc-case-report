/**
 * 项目初始化
 **/
$(function() {
    // 初始化图表
    let loadHtml = "    <div class=\"text-center\">\n" +
        "        <p class=\"font-weight-bold text-muted mt-4\">正在加载....</p>\n" +
        "        <span class=\"fa-3x cmb-red\">\n" +
        "             <i class=\"fas fa-spinner fa-spin\"></i>\n" +
        "        </span>\n" +
        "    </div>";
    for(let h in echarList) {
        let html = echarList[h];
        html = html.replace("loadHtml",loadHtml);
        html += "<hr>";
        $("#chart-body").append(html);
    }
    $("div[viewname]").each(function(idx, itm) {
        showEchart(itm);
    });
});

/**
 * 一行一个图
 * @type {string[]}
 */
var echarList = [
    "<div id='echarts-viewOrgWorkuser' viewname='VIEW_ORG_WORKUSER' option='bar-y-category' class='w-100' style='height: 2000px;'>loadHtml</div>",
    "<div id='echarts-viewWorkuserTemperature' viewname='VIEW_WORKUSER_TEMPERATURE' option='bar-y-category-stack' class='w-100' style='height: 600px;'>loadHtml</div>"
];

/**
 * 依照类别进行画图
 * @param url
 * @param type
 */
function showEchart(div) {
    let url = getURLroot() + "/rest/echart/autoview/" + $(div).attr("viewname");
    $.getJSON(url, {}, function(json) {
        if(!json.success || json.dataMap === null || json.dataMap === undefined) return;
        let datas = json.dataMap[$(div).attr("viewname")];
        let chart = echarts.init(document.getElementById($(div).attr("id")));
        let option = optionMap[$(div).attr("option")];
        let baseOption = everyBase[$(div).attr("id")];
        Object.assign(option, baseOption);
        option = getOption($(div).attr("option"), option, datas);
        chart.setOption(option);
    });
}

function getOption(optionType, option, datas) {
    let dMap = {};
    if("bar-y-category-stack" === optionType) {
        for(let key in datas) {
            let data = datas[key];
            if(dMap[data[1]] === undefined) {
                dMap[data[1]] = {
                    name: data[1],
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: [data[2]]
                };
            } else {
                let d = dMap[data[1]];
                d.data.push(data[2]);
            }
            // 补充yAxis
            if(option.yAxis.data.indexOf(data[0]) === -1) {
                option.yAxis.data.push(data[0]);
            }
            // 补充legend
            if(option.legend.data.indexOf(data[1]) === -1) {
                option.legend.data.push(data[1]);
            }
        }
        for(let key in dMap) {
            option.series.push(dMap[key]);
        }
    } else if("bar-y-category" === optionType) {
        let t;
        for(let key in datas) {
            let data = datas[key];
            if(dMap[data[0]] === undefined) {
                dMap['总'] = {
                    name: '总',
                    type: 'bar',
                    data: [data[3]]
                };
                t = data[0];
                dMap[data[0]] = {
                    name: data[0],
                    type: 'bar',
                    data: [data[2]]
                };
            } else {
                if(data[0] === t) {
                    let dt = dMap['总'];
                    dt.data.push(data[3]);
                }
                let d = dMap[data[0]];
                d.data.push(data[2]);
            }
            // 补充yAxis
            if(option.yAxis.data.indexOf(data[1]) === -1) {
                option.yAxis.data.push(data[1]);
            }
        }
        for(let key in dMap) {
            if(option.legend.data.indexOf(key) === -1) {
                option.legend.data.push(key);
            }
            option.series.push(dMap[key]);
        }
    }
    return option;
}

/**
 *
 * @type {{}}
 */
var everyBase = {
    "echarts-viewWorkuserTemperature": {
        title: {
            text: '签到体温走势(单位:摄氏度/人数)',
            subtext: ''
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        }
    },
    "echarts-viewOrgWorkuser": {
        title: {
            text: '签到统计(单位:人)',
            subtext: ''
        }
    }
};

/**
 * 每种类型一张图片
 * @type {{}}
 */
var optionMap = {
    "bar-y-category": {
        title: {
            text: '',
            subtext: ''
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            top: '1%',
            data: []
        },
        grid: {
            top: '3%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: []
        },
        series: []
    },
    "bar-y-category-stack": {
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            top: '4%',
            data: []
        },
        grid: {
            top: '8%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value'
        },
        yAxis: {
            type: 'category',
            data: []
        },
        series: []
    }
};