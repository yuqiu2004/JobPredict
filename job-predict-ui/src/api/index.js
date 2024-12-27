import request from '../utils/request'

export const login = (data) => {
    return request.post('/user/login', data)
}

export const uploadSoldierMsg = (data) => {
    return request.post('/soldier', data)
}

export const getSoldierList = (params) => {
    return request.get('/soldier/list', {params})
}

export const getSoldierById = (id) => {
    return request.get('/soldier/detail', { params: { id } });
}
