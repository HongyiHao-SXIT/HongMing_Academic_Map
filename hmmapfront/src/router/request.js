import axios from 'axios'

const instance = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 5000,
})

instance.interceptors.request.use(config => {
  return config
})

instance.interceptors.response.use(
  response => response.data,
  error => Promise.reject(error)
)

export default instance

router.beforeEach((to, from, next) => {
  const userStore = useUserStore()
  if (to.meta.requiresAuth && !userStore.isLoggedIn) {
    next('/login')
  } else if (to.meta.requiresAdmin && !userStore.isAdmin) {
    next('/') // 无权限回首页
  } else {
    next()
  }
})
