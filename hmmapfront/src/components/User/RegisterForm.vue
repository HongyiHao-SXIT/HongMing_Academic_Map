<template>
  <el-card>
    <h2>Register</h2>
    <el-form :model="form" ref="formRef" label-width="80px" @submit.prevent="submitForm">
      <el-form-item label="Name" prop="name" :rules="[{ required: true, message: 'Name is required', trigger: 'blur' }]">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="Account" prop="account" :rules="[{ required: true, message: 'Account is required', trigger: 'blur' }]">
        <el-input v-model="form.account" />
      </el-form-item>
      <el-form-item label="Password" prop="password" :rules="[{ required: true, message: 'Password is required', trigger: 'blur' }]">
        <el-input v-model="form.password" type="password" />
      </el-form-item>
      <el-form-item label="Confirm" prop="confirmPassword" :rules="[
          { required: true, message: 'Please confirm password', trigger: 'blur' },
          { validator: validateConfirmPassword, trigger: 'blur' }
        ]">
        <el-input v-model="form.confirmPassword" type="password" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" native-type="submit" :loading="loading">Register</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { register } from '@/api/user'
import { useRouter } from 'vue-router'

const formRef = ref(null)
const loading = ref(false)
const form = reactive({
  name: '',
  account: '',
  password: '',
  confirmPassword: ''
})

const router = useRouter()

const validateConfirmPassword = (rule, value, callback) => {
  if (value !== form.password) {
    callback(new Error('Passwords do not match'))
  } else {
    callback()
  }
}

const submitForm = async () => {
  loading.value = true
  try {
    await register(form)
    ElMessage.success('Registration successful')
    router.push('/login')
  } catch (err) {
    ElMessage.error(err.response?.data?.message || 'Registration failed')
  } finally {
    loading.value = false
  }
}
</script>
