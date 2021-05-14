export function getDate(date = null, year = 0, month = 0, day = 0, format = "yyyy/MM/dd hh:mm:ss") {
    let tDate = null;
    if (date == null) {
        tDate = new Date();
    } else if (date instanceof Date) {
        tDate = date;
    } else {
        tDate = new Date(date);
    }
    tDate.setFullYear(tDate.getFullYear() + year);
    tDate.setMonth(tDate.getMonth() + month);
    tDate.setDate(tDate.getDate() + day);
    return tDate.format(format);
}
export function getDateZHCN(date = null, resultType = "yyyy年MM月dd日 hh:mm:ss") {
    return getDate(date, 0, 0, 0, resultType)
}
export function getDateByDay(day = 0, date = null, resultType = "yyyy/MM/dd hh:mm:ss") {
    return getDate(date, 0, 0, day, resultType)
}
export function getDateByMonth(month = 0, date = null, resultType = "yyyy/MM/dd hh:mm:ss") {
    return getDate(date, 0, month, 0, resultType)
}

export function getDateByYear(year = 0, date = null, resultType = "yyyy/MM/dd hh:mm:ss") {
    return getDate(date, year, 0, 0, resultType)
}

export function getDateByFormat(date = null, resultType = "yyyy-MM-dd hh:mm:ss") {
    return getDate(date, 0, 0, 0, resultType)
}

export function getDaysBetween(dateString1,dateString2){
    var  startDate = Date.parse(dateString1);
    var  endDate = Date.parse(dateString2);
    var days=(endDate - startDate)/(1*24*60*60*1000);
    return days < 0 ? -days : days;
 }

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份 
        "d+": this.getDate(),                    //日 
        "h+": this.getHours(),                   //小时 
        "m+": this.getMinutes(),                 //分 
        "s+": this.getSeconds(),                 //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds()             //毫秒 
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

export default {
    getDate,
    getDateByDay,
    getDateByMonth,
    getDateByYear,
    getDateByFormat,
    getDaysBetween,
    getDateZHCN
}
