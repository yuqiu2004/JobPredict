import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'
import store from './store/index'

const app = createApp(App)
app.use(router) // 路由挂载
app.use(store)
app.mount('#app')
