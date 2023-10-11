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
    component: () => import('../views/DetailView.vue')
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
        path: 'scrap',
        component: () => import('../views/ScrapMypage.vue')
      },
      {
        path: 'report',
        component: () => import('../views/ReportMypage.vue')
      }, 
    ]
  },
  {
    path: '/admin',
    name: 'admin',
    children: [
      {
        path: 'board',
        component: () => import('../views/BoardAdmin.vue')
      }, 
      {
        path: 'report',
        component: () => import('../views/ReportAdmin.vue')
      }, 
      
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router