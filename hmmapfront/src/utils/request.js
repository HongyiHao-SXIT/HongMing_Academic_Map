import axios from 'axios'
import { useUserStore } from '../stores/user'

const instance = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 5000,
})

instance.interceptors.request.use(config => {
  const userStore = useUserStore()
  if (userStore.token) {
    config.headers.Authorization = `Bearer ${userStore.token}`
  }
  return config
})

instance.interceptors.response.use(
  response => response.data,
  error => {
    if (error.response && error.response.status === 401) {
      const userStore = useUserStore()
      userStore.logout()
      window.location.href = '/login'
    }
    return Promise.reject(error)
  }
)

export default instance
