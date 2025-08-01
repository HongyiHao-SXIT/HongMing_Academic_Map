<template>
  <el-card>
    <h2>Login</h2>
    <el-form :model="form" @submit.prevent="submitForm" ref="formRef" label-width="80px">
      <el-form-item label="Account" prop="account" :rules="[{ required: true, message: 'Please input account', trigger: 'blur' }]">
        <el-input v-model="form.account" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Password" prop="password" :rules="[{ required: true, message: 'Please input password', trigger: 'blur' }]">
        <el-input v-model="form.password" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" native-type="submit" :loading="loading">Login</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { login } from '@/api/user'
import { useUserStore } from '@/store/user'
import { useRouter } from 'vue-router'

const formRef = ref(null)
const loading = ref(false)
const form = reactive({
  account: '',
  password: ''
})

const userStore = useUserStore()
const router = useRouter()

const submitForm = async () => {
  loading.value = true
  try {
    const res = await login(form)
    userStore.setToken(res.token)
    userStore.setUser(res.user)
    ElMessage.success('Login successful')
    router.push('/')
  } catch (err) {
    ElMessage.error(err.response?.data?.message || 'Login failed')
  } finally {
    loading.value = false
  }
}
</script>
