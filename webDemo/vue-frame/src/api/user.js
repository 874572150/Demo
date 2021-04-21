import BaseApi from './baseApi.js'
export default class user extends BaseApi {
    /**
    * 用户列表
    */
    static async page(pageSize, pageNum, searchInfo) {
        const url = "/b_user/findAll";
        const param = {
            'pageSize' : pageSize,
            'pageNum' : pageNum,
            'searchInfo' : searchInfo,
        }
        const data = await this.$get(url,param)     
        return data;
    }

}