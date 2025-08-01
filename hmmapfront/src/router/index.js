import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '../stores/user'
import Login from '../views/LoginView.vue'
import PaperList from '../views/PaperListView.vue'
import PaperDetail from '../views/PaperDetail.vue'
import UploadPaper from '../views/UploadPaper.vue'

const routes = [
  { path: '/login', component: Login },
  { path: '/', component: PaperList, meta: { requiresAuth: true } },
  { path: '/papers/:id', component: PaperDetail, meta: { requiresAuth: true } },
  { path: '/upload', component: UploadPaper, meta: { requiresAuth: true } },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

router.beforeEach((to, from, next) => {
  const userStore = useUserStore()
  if (to.meta.requiresAuth && !userStore.isLoggedIn) {
    next('/login')
  } else {
    next()
  }
})

export default router
