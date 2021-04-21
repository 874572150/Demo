//--------------------------------------------拦截器  axios-------------------------------------------------------------------
import axios from 'axios';
import globalVariable from '../constant/global-variable.js' //引用变量文件 此处无this 故单独引用
import router from '../router'
import store from '../store/store'

const CancelToken = axios.CancelToken;

const service = axios.create({
    // process.env.NODE_ENV === 'development' 来判断是否开发环境
    baseURL: globalVariable.baseURL, 
    timeout: 2000000
})
service.defaults.withCredentials = true;

service.interceptors.request.use(config => {
    config.retry = config.headers.retry;
    config.retryDelay = 1000;
    config.cancelToken = new axios.CancelToken(cancel => {
        // window._axiosPromiseArr.push(cancel);
        globalVariable._axiosPromiseArr.push({cancel})
    })
    return config;
}, error => {
    global.console.log('请求错误日志', error);
    return Promise.reject(error);
})

service.interceptors.response.use(response => {
    if (response.status === 200) {
        return response;
    } else {
        //文件流
        const headers = response.headers
        if (headers['content-type'] === 'application/octet-stream;charset=utf-8') {
            return response
        }
        const data = response.data
        const header = data.header

        if (header && header.code === '20001000') {
            // 未登录或者登陆验证失败
            store.dispatch("logoutClean",null);
            localStorage.removeItem("bch-user");

        } else if (header && header.code === '20001001') {
            store.dispatch("logoutClean",null);
            localStorage.removeItem("bch-user");
        } else if (header && header.code !== '200') {
            // 系统异常
        
        }
        else if (header && header.code === '401') {
            // 系统异常
            store.dispatch("logoutClean",null);
            localStorage.removeItem("bch-user");
        }
        return Promise.resolve(response.data);
        //Promise.reject();
    }
}, error => {
    if(!error.response) {
    }
    //登录过期
    if(error.response.status === 401){
        // 清空后续的请求
        if(globalVariable._axiosPromiseArr) {
            globalVariable._axiosPromiseArr.forEach((ele, index) => {
                ele.cancel("请求已取消");
                delete globalVariable._axiosPromiseArr[index];
            })
        }
     
 
        store.dispatch("logoutClean",null);
        localStorage.removeItem("bch-user");
        router.push("/")
        
    }else if(error.response.status === 403){

        router.push("/403")
        
    }else if(error.response.status === 911){
                // 清空后续的请求
                if(globalVariable._axiosPromiseArr) {
                    globalVariable._axiosPromiseArr.forEach((ele, index) => {
                        ele.cancel("请求已取消");
                        delete globalVariable._axiosPromiseArr[index];
                    })
                }
              
                store.dispatch("logoutClean",null);
                localStorage.removeItem("bch-user");
                router.push("/")
        
    }else{
       
    }
     
    return Promise.reject(error.response.data) // 返回接口返回的错误信息

})

export const post = (url, param) => {
    return service({
        url: url,
        method: 'post',
        data: param
    })
}

export const get = (url, param) => {
    return service({
        url: url,
        method: 'get',
        params: param
    })
}

export const del = (url, param) => {
    return service({
        url: url,
        method: 'delete',
        data: param
    })
}

export const put = (url, param) => {
    return service({
        url: url,
        method: 'put',
        data: param
    })
}