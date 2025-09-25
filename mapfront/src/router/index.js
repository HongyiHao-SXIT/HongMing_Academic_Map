import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import LoginPage from '../views/LoginPage.vue'
import RegisterPage from '../views/RegisterPage.vue'
import UserProfile from '../views/UserProfile.vue'
import UploadPage from '../views/UploadPage.vue'
import PaperDetail from '../views/PaperDetail.vue'
import AdminPanel from '../views/AdminPanel.vue'
import { useAuthStore } from '../store/auth'

const routes = [
  { path: '/', name: 'Home', component: HomePage },
  { path: '/login', name: 'Login', component: LoginPage, meta: { guest: true } },
  { path: '/register', name: 'Register', component: RegisterPage, meta: { guest: true } },
  { path: '/profile', name: 'Profile', component: UserProfile, meta: { requiresAuth: true } },
  { path: '/upload', name: 'Upload', component: UploadPage, meta: { requiresAuth: true } },
  { path: '/paper/:id', name: 'PaperDetail', component: PaperDetail },
  { 
    path: '/admin', 
    name: 'AdminPanel', 
    component: AdminPanel, 
    meta: { requiresAuth: true, requiresAdmin: true } 
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()
  
  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next('/login')
  } else if (to.meta.requiresAdmin && authStore.user.role !== 'ADMIN') {
    next('/')
  } else if (to.meta.guest && authStore.isAuthenticated) {
    next('/')
  } else {
    next()
  }
})

export default router