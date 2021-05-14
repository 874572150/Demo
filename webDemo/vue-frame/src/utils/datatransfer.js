
// 深拷贝
export const deepCopy = (obj) => {
    var object;
    // 深度复制数组
    if (Object.prototype.toString.call(obj) == "[object Array]") {
        object = [];
        for (var i = 0; i < obj.length; i++) {
            object.push(deepCopy(obj[i]))
        }
        return object
    }
    // 深度复制对象
    if (Object.prototype.toString.call(obj) == "[object Object]") {
        object = {};
        for (var p in obj) {
            if (Object.prototype.toString.call(obj[p]) == "[object Object]") {
                object[p] = deepCopy(obj[p]);
            } else {
                object[p] = obj[p]
            }
        }
        return object
    }
}

export const jsonToArray = (nodes) => {
    var r = [];
    if (Array.isArray(nodes)) {
        for (var i = 0, l = nodes.length; i < l; i++) {
            r.push(nodes[i]); // 取每项数据放入一个新数组
            if (Array.isArray(nodes[i]["children"]) && nodes[i]["children"].length > 0)
                // 若存在children则递归调用，把数据拼接到新数组中，并且删除该children
                r = r.concat(jsonToArray(nodes[i]["children"]));
            delete nodes[i]["children"]
        }
    }
    return r;
}

// 初始化表单数据
export const initFormData = (formData) => {
    for (let key in formData) {
        if (formData[key] instanceof Array) {
            formData[key] = [];
        } else if (formData[key] instanceof Object) {
            initFormData(formData[key]);
        } else if (typeof formData[key] == "boolean") {
            formData[key] = false;
        } else {
            formData[key] = "";
        }
    }
}

// 从oldJson中将oldJson和newJson中均存在的属性进行深拷贝
export const commPropCopy = (newJson = {}, oldJson = {}) => {
    if (Object.prototype.toString.call(oldJson) == "[object Object]") {
        for (var key in oldJson) {
            if (newJson[key] != undefined) {
                if (Object.prototype.toString.call(oldJson[key]) == "[object Object]") {
                    commPropCopy(newJson[key], oldJson[key]);
                } else {
                    newJson[key] = oldJson[key]
                }
            }
        }

    }
}