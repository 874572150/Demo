// 重置表单，formRef为表单的ref值，excludeFields为要排除重新初始化值得属性
export function resetForm(formRef,formModel, ...excludeFields) {
    this.$refs[formRef].resetFields();
    let obj1 = this.$data;
    let obj2 = this.$options.data.call(this);
    // 清除新增时kind-editor内容清空问题
    if(obj1['content']) {
        obj1['content'] = "<br/>";
    }
    // 清除文件上传的相应数据
    if(obj1['uploadResData']) {
        obj1['uploadResData'] = obj2['uploadResData'];
    }
    // 清除upload中选择的文件
    if(this.$refs.upload) {
        this.$refs.upload.clearFiles()
    }

    obj1[formModel] = obj2[formModel];

    // if (!excludeFields || excludeFields.length === 0) {
    //     excludeFields = ["ruleValidate"];
    // }
    // for (let attrName in obj1) {
    //     if (excludeFields && excludeFields.includes(attrName)) {
    //         continue;
    //     }
    //     obj1[attrName] = obj2[attrName];
    // }
}

export default {
    resetForm
}