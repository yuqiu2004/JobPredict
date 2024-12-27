import axios from "axios"
import { ElMessage } from "element-plus";

const BASE_PREFIX = import.meta.env.VITE_BASE_URL

// const PORT = import.meta.env.DEV ? '8080' : '11780';
const HOST = import.meta.env.DEV ? 'http://localhost:8080' : 'http://47.121.126.119:11780';

const http = axios.create({
    // baseURL: 'http://localhost:' + PORT + BASE_PREFIX,
    baseURL: HOST + BASE_PREFIX,
    timeout: 10000,
})

http.interceptors.request.use(function (config) {
    const token = localStorage.getItem('token')
    const whiteUrl = ['/user/login', '/soldier/list']
    if (token && !whiteUrl.includes(config.url)) {
        config.headers['token'] = token;
    }
    return config;
    }, function(error) {
        return Promise.reject(error);
});

http.interceptors.response.use(function (response) {
    if (response.data.code === 0) {
        ElMessage.warning(response.data.message);
    }
    return response;
    }, function (error) {
        return Promise.reject(error)
});

export default http