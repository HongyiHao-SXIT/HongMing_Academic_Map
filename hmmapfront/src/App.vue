<template>
  <el-container>
    <el-header>
      <el-menu :default-active="active" mode="horizontal" @select="onSelect">
        <el-menu-item index="/">Home</el-menu-item>
        <el-menu-item index="/upload">Upload Paper</el-menu-item>
        <el-menu-item index="/login" v-if="!userStore.isLoggedIn">Login</el-menu-item>
        <el-menu-item index="/logout" v-else @click="logout">Logout</el-menu-item>
      </el-menu>
    </el-header>
    <el-main>
      <router-view />
    </el-main>
    <el-footer>Academic Paper Map © 2025</el-footer>
  </el-container>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useUserStore } from './stores/user'

const router = useRouter()
const route = useRoute()
const active = ref(route.path)
const userStore = useUserStore()

watch(route, (newRoute) => {
  active.value = newRoute.path
})

function onSelect(path) {
  if (path === '/logout') {
    logout()
  } else {
    router.push(path)
  }
}

function logout() {
  userStore.logout()
  router.push('/login')
}
</script>

<style>
body {
  margin: 0;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
</style>
