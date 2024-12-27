import { createRouter, createWebHashHistory } from "vue-router";
import Layout from '../views/Main.vue'
import Login from '../views/loginPage/index.vue'
import Index from "../views/indexPage/index.vue";
import Soldier from '../views/soldierPage/index.vue'
import Upload from '../views/soldierPage/upload.vue'
import Detail from '../views/soldierPage/detail.vue'

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
                path: '/soldier',
                component: Soldier,
            },
            {
                path: '/upload',
                component: Upload,
            },
            {
                path: '/detail',
                component: Detail,
            },
        ],
    },
];

const router = createRouter({
    routes,
    history: createWebHashHistory()
})

export default router;