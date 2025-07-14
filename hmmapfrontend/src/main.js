import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080/api'

createApp(App).mount('#app')

const app = createApp(App)
app.config.globalProperties.$axios = axios
app.mount('#app')
