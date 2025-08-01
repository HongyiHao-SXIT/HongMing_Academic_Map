import { defineStore } from 'pinia'
import request from '../utils/request'

export const useUserStore = defineStore('user', {
  state: () => ({
    token: localStorage.getItem('token') || '',
    userInfo: null,
    loading: false,
    error: null,
  }),
  actions: {
    async login(account, password) {
      this.loading = true
      this.error = null
      try {
        const res = await request.post('/auth/login', { account, password })
        this.token = res.token
        localStorage.setItem('token', res.token)
        this.userInfo = res.user || null
      } catch (e) {
        this.error = e.response?.data?.message || e.message || 'Login failed'
      } finally {
        this.loading = false
      }
    },
    logout() {
      this.token = ''
      this.userInfo = null
      localStorage.removeItem('token')
    },
  },
  getters: {
    isLoggedIn: state => !!state.token,
  },
})

state: () => ({
  token: localStorage.getItem('token') || '',
  userInfo: null,
  role: localStorage.getItem('role') || '',
  loading: false,
  error: null,
}),
actions: {
  async login(account, password) {
    // ...
    this.role = res.user?.role || 'USER'
    localStorage.setItem('role', this.role)
    // ...
  },
  logout() {
    this.role = ''
    localStorage.removeItem('role')
    // ...
  },
},
getters: {
  isAdmin: state => state.role === 'ADMIN',
  // ...
}

