import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/detail',
    name: 'detail',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/DetailView.vue')
  },
  {
    path: '/list',
    name: 'list',
    component: () => import('../views/ListView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/LoginView.vue')
  },
  {
    path: '/join',
    name: 'join',
    component: () => import('../views/JoinView.vue')
  },
  {
    path: '/edit',
    name: 'edit',
    component: () => import('../views/EditView.vue')
  },
  {
    path: '/mypage',
    name: 'mypage',
    children: [
      {
        path: 'info',
        component: () => import('../views/InfoMypage.vue')
      }, 
      {
        path: 'comment',
        component: () => import('../views/CommentMypage.vue')
      },
      {
        path: 'like',
        component: () => import('../views/LikeMypage.vue')
      },
      {
        path: 'board',
        component: () => import('../views/BoardMypage.vue')
      },
      {
        path: 'report',
        component: () => import('../views/ReportMypage.vue')
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
