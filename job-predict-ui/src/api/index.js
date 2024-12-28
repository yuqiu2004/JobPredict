import request from '../utils/request'

export const login = (data) => {
    return request.post('/user/login', data)
}

export const register = (data) => {
    return request.post('/user/register', data)
}

export const predict = (data) => {
    return request.post('/predict', data)
}
