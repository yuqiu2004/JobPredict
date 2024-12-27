import { createStore } from 'vuex'
import login from './login'
import detail from './detail'

export default createStore({
    modules: {
        login,
        detail,
    }
});