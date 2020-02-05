/**
 * ============================================================
 * 资源地址
 * ============================================================
 * @returns {String}
 */
function getURLroot() {
    var arr = window.location.pathname.split("/");
    return window.location.protocol + "//" + window.location.host + "/" + (arr[0] === "" ? arr[1] : arr[0]);
}

/**
 * ============================================================
 * token to tokens
 * ============================================================
 * @returns {undefined}
 */
function getTokens() {
    return "none";
}

/**
 * ============================================================
 * 数据缓存
 * ============================================================
 * @returns {WebCache}
 */
function WebCache() {
    this.data = {};
    return this;
}

WebCache.prototype = {
    pushData: function (key, data) {
        this.data[key] = data;
    },
    getData: function (key) {
        return this.data[key];
    },
    version: function () {
        return "welcome to use WebCache!~";
    }
};
/**
 * ============================================================
 * 业务转码
 * ============================================================
 * @returns {code}
 */
var code = function () {
    this.version = "0.1";
};
/**
 * ============================================================
 * 阿拉伯 转 中文
 * ============================================================
 * @param {type} intNum
 * @returns {String}
 */
code.toCnNumber = function (intNum) {
    var arrCnNum = new Array("零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十",
        "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九",
        "二十", "二十一", "二十二", "二十三", "二十四", "二十五", "二十六", "二十七", "二十八", "二十九",
        "三十", "三十一", "三十四", "三十五", "三十六", "三十七", "三十八", "三十九");
    var n = parseInt(intNum);
    return arrCnNum[n];
};

/**
 * 初始化
 * @type WebCache
 */
var webCache = new WebCache();