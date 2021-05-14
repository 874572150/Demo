/**
 * 获取echart的option
 * @param {echart标题} chartTitle 
 * @param {echartData数据：数据类型为Map<legendData,Map<xData,yData>} chartData 
 * @param {echart类型 line、bar、pie...} type 
 * @param {额外添加的option属性} optionAttr 
 */
export function getChartOption(chartTitle, chartData, type, optionAttr) {
    if (!optionAttr) {
        optionAttr = {};
    }
    let seriesAttr = optionAttr.seriesAttr || {};
    let xAxis = optionAttr.xAxis || {};
    let datas = parseLegendData2XData2YData(chartData);
    let chartOptionData = {
        xData: datas.xData,
        seriesData: [],
    }
    for (let i = 0; i < datas.name.length; i++) {
        let seriesData = {};
        seriesData['name'] = datas.name[i];
        seriesData['type'] = type;
        seriesData['data'] = [];
        let parseMode = '';
        if (type == 'pie') {
            parseMode = 'name2value'
        }
        if (xAxis.type == 'time') {
            parseMode = 'name2valueArr'
        }
        if (parseMode == 'name2value') {
            let temp = 0;
            for (let key in datas.xData) {
                seriesData['data'].push({ name: datas.xData[key], value: datas.yData[i][temp++] })
            }
        } else if (parseMode == 'name2valueArr') {
            for (let key in datas.xData) {
                seriesData['data'].push({ name: datas.xData[key], value: [datas.xData[key], datas.yData[i][key]] })
            }
        } else {
            seriesData['data'] = datas.yData[i];
        }
        for (let seriesKey in seriesAttr) {
            seriesData[seriesKey] = seriesAttr[seriesKey];
        }
        chartOptionData.seriesData.push(seriesData);
    }
    let option = {
        series: chartOptionData.seriesData
    };
    if (type == 'pie') {

    } else {
        option['xAxis'] = {
            data: chartOptionData.xData,
            axisLabel: {
                interval: 0,
            },
        };
        option['yAxis'] = {
            minInterval: 1,
            type: "value",
            axisLine: {
                show: true
            },
            axisTick: {
                show: true
            },
            axisLabel: {
                textStyle: {
                    color: "#999"
                }
            }
        }
    }
    option['title'] = setTitle(chartTitle);
    option['tooltip'] = setToolTip(optionAttr['tooltip'], type);
    option['legend'] = setLegend(optionAttr['legend']);

    if (xAxis.type) {
        option['xAxis']['type'] = xAxis.type
    }
    return option;
}

// 设置title
function setTitle(title) {
    let optionTitle = {
        text: title,
        left: 20,
        top: 10
    }
    return optionTitle;
}

// 设置tooltip
function setToolTip(tooltip, type) {
    var optionTooltip;
    if (tooltip) {
        optionTooltip = tooltip;
    } else {
        optionTooltip = defaultToolTip(type);
    }
    // 防抖动
    optionTooltip.transitionDuration = 0;
    return optionTooltip;
}

// 设置legend
function setLegend(legend) {
    var optionLegend;
    if (legend) {
        optionLegend = legend;
    } else {
        optionLegend = defalutLegend();
    }
    return optionLegend;
}

// 默认tooltip
function defaultToolTip(type) {
    let tooltip = {};
    switch (type) {
        case 'line': tooltip = {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        }; break;
        case 'bar': tooltip = {
            trigger: "axis",
            axisPointer: {
                type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
            },
        }; break;
        case 'pie': tooltip = {
            trigger: "item",
            formatter: "{a} <br/>{b} : {c} ({d}%)",
        }; break;
    }
    return tooltip;
}

// 默认legend
function defalutLegend() {
    let legend = {};
    legend = {
        x: 'center',
        y: "bottom",
    }
    return legend
}

/**
 * 解析Map<legendData,Map<xData,yData>型数据
 */
function parseLegendData2XData2YData(chartData) {
    let xData = false;
    let datas = {
        name: [],
        xData: [],
        yData: []
    }
    for (let k in chartData) {
        let tempSeriesData = []
        datas.name.push(k);
        for (let key in chartData[k]) {
            if (!xData) {
                datas.xData.push(key);
            }
            tempSeriesData.push(chartData[k][key]);
        }
        xData = true;
        datas.yData.push(tempSeriesData);
    }
    return datas;
}

export default {
    getChartOption
}
