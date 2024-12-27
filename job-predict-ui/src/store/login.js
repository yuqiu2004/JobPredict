const state = {
    token : null,
    userId : null,
    username : null,
    name : null,
}

const mutations = {
    setItem(state, form) {
        state.token = form.token
        state.userId = form.id
        state.username = form.username
        state.name = form.name
    },
    logout(state) {
        state.token = null
        state.userId = null
        state.usernaem = null
        state.name = null
    }
}

export default {
    state, 
    mutations
}