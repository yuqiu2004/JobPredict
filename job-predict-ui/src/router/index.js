import { createRouter, createWebHashHistory } from "vue-router";
import Layout from '../views/Main.vue'
import Login from '../views/loginPage/index.vue'
import Index from "../views/indexPage/index.vue"
import Register from '../views/loginPage/register.vue'
import Predict from '../views/predict/index.vue'

const routes = [
    {
        path: '/',
        component: Layout,
        children: [
            {
                path: '/login',
                component: Login,
            },
            {
                path: '/index',
                component: Index,
            },
            {
                path: '/register',
                component: Register,
            },
            {
                path: '/predict',
                component: Predict,
            }
        ],
    },
];

const router = createRouter({
    routes,
    history: createWebHashHistory()
})

export default router;