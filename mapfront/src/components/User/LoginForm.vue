<template>
  <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-form">
    <el-form-item label="账号" prop="account">
      <el-input v-model="form.account" placeholder="请输入账号"></el-input>
    </el-form-item>
    
    <el-form-item label="密码" prop="password">
      <el-input v-model="form.password" type="password" placeholder="请输入密码"></el-input>
    </el-form-item>
    
    <el-form-item>
      <el-button type="primary" @click="handleLogin" class="login-button">登录</el-button>
      <el-button @click="navigateToRegister">注册</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../../store/auth'
import { ElMessage } from 'element-plus'

const router = useRouter()
const authStore = useAuthStore()
const loginForm = ref(null)

const form = reactive({
  account: '',
  password: ''
})

const rules = {
  account: [
    { required: true, message: '请输入账号', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' }
  ]
}

const handleLogin = async () => {
  try {
    const valid = await loginForm.value.validate()
    if (valid) {
      const success = await authStore.login(form.account, form.password)
      if (success) {
        ElMessage.success('登录成功')
        router.push('/')
      } else {
        ElMessage.error('账号或密码错误')
      }
    }
  } catch (error) {
    console.error('Validation failed:', error)
  }
}

const navigateToRegister = () => {
  router.push('/register')
}
</script>

<style scoped>
.login-form {
  max-width: 350px;
  margin: 100px auto;
  padding: 20px;
  border: 1px solid #eaeaea;
  border-radius: 5px;
}

.login-button {
  width: 100%;
  margin-bottom: 10px;
}
</style>