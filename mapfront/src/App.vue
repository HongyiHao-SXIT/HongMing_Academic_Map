<template>
  <el-container>
    <el-header>
      <nav class="navbar">
        <div class="logo">HongMing Academic Map</div>
        <el-menu mode="horizontal" :default-active="activeMenu">
          <el-menu-item index="/" @click="navigate('/')">首页</el-menu-item>
          <el-menu-item index="/upload" @click="navigate('/upload')" v-if="isAuthenticated">上传论文</el-menu-item>
          <el-menu-item index="/admin" @click="navigate('/admin')" v-if="isAdmin">管理员面板</el-menu-item>
        </el-menu>
        
        <div class="user-menu">
          <el-menu mode="horizontal" :default-active="userActiveMenu">
            <el-menu-item index="/profile" @click="navigate('/profile')" v-if="isAuthenticated">个人中心</el-menu-item>
            <el-menu-item index="/login" @click="navigate('/login')" v-if="!isAuthenticated">登录</el-menu-item>
            <el-menu-item index="/register" @click="navigate('/register')" v-if="!isAuthenticated">注册</el-menu-item>
            <el-menu-item index="logout" @click="handleLogout" v-if="isAuthenticated">退出登录</el-menu-item>
          </el-menu>
        </div>
      </nav>
    </el-header>
    
    <el-main>
      <router-view />
    </el-main>
    
    <el-footer>
      <p>© 2025 HongMing Academic Map. All rights reserved.</p>
    </el-footer>
  </el-container>
</template>

<script setup>
import { computed, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useAuthStore } from './store/auth'

const router = useRouter()
const route = useRoute()
const authStore = useAuthStore()

const activeMenu = computed(() => route.path)
const userActiveMenu = computed(() => {
  if (!authStore.isAuthenticated) return route.path
  return route.path.includes('/profile') ? '/profile' : ''
})

const isAuthenticated = computed(() => authStore.isAuthenticated)
const isAdmin = computed(() => authStore.user && authStore.user.role === 'ADMIN')

const navigate = (path) => {
  router.push(path)
}

const handleLogout = () => {
  authStore.logout()
  router.push('/')
}

onMounted(async () => {
  if (authStore.isAuthenticated) {
    await authStore.fetchUserProfile()
  }
})
</script>

<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
}

.logo {
  font-size: 20px;
  font-weight: bold;
  margin-right: 20px;
}

.user-menu {
  margin-left: auto;
}

.el-header {
  background-color: #f5f7fa;
  padding: 0 20px;
}

.el-footer {
  text-align: center;
  padding: 20px;
  background-color: #f5f7fa;
}
</style>