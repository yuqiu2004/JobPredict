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

export const salaryGender = (data) => {
    return request.get('/salary/gender-statistics', data)
}

export const salaryEdu = (data) => {
    return request.get('/salary/education-statistics', data)
}

export const distributionEdu = (data) => {
    return request.get('/education/distribution', data)
}

export const distributionGender = (data) => {
    return request.get('salary/distribution', data)
}