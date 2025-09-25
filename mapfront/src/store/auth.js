import { defineStore } from 'pinia'
import axios from 'axios'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: null,
    token: localStorage.getItem('token') || null,
    isAuthenticated: !!localStorage.getItem('token')
  }),
  
  actions: {
    async login(account, password) {
      try {
        const response = await axios.post('/api/auth/login', { account, password })
        const { token } = response.data
        
        localStorage.setItem('token', token)
        this.token = token
        this.isAuthenticated = true
        
        // 设置全局axios请求头
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
        
        // 获取用户信息
        await this.fetchUserProfile()
        
        return true
      } catch (error) {
        console.error('Login failed:', error)
        return false
      }
    },
    
    async register(userData) {
      try {
        await axios.post('/api/auth/register', userData)
        return true
      } catch (error) {
        console.error('Registration failed:', error)
        return false
      }
    },
    
    async fetchUserProfile() {
      try {
        const response = await axios.get('/api/auth/me')
        this.user = response.data
        return this.user
      } catch (error) {
        console.error('Failed to fetch user profile:', error)
        this.logout()
        return null
      }
    },
    
    logout() {
      localStorage.removeItem('token')
      this.token = null
      this.user = null
      this.isAuthenticated = false
      delete axios.defaults.headers.common['Authorization']
    }
  }
})