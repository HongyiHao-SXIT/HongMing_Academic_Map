<template>
  <div class="auth-page">
    <el-card class="auth-card" shadow="hover">
      <h2 style="text-align: center">{{ isLogin ? 'Login' : 'Register' }}</h2>

      <el-form :model="form" :rules="rules" ref="formRef" label-position="top">
        <el-form-item label="Email" prop="email">
          <el-input v-model="form.email" />
        </el-form-item>

        <el-form-item label="Password" prop="password">
          <el-input type="password" v-model="form.password" />
        </el-form-item>

        <el-form-item v-if="!isLogin" label="Confirm Password" prop="confirmPassword">
          <el-input type="password" v-model="form.confirmPassword" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="handleSubmit">
            {{ isLogin ? 'Login' : 'Register' }}
          </el-button>
          <el-button type="text" @click="toggleForm">
            {{ isLogin ? "Don't have an account? Register" : "Already have an account? Login" }}
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'

const isLogin = ref(true)
const formRef = ref(null)

const form = reactive({
  email: '',
  password: '',
  confirmPassword: ''
})

const rules = {
  email: [{ required: true, message: 'Email is required', trigger: 'blur' }],
  password: [{ required: true, message: 'Password is required', trigger: 'blur' }],
  confirmPassword: [
    {
      required: true,
      message: 'Confirm password is required',
      trigger: 'blur',
      validator: (_, value) => {
        return value === form.password
      }
    }
  ]
}

const toggleForm = () => {
  isLogin.value = !isLogin.value
  form.email = ''
  form.password = ''
  form.confirmPassword = ''
}

const handleSubmit = () => {
  formRef.value.validate(async (valid) => {
    if (!valid) return

    if (isLogin.value) {
      // TODO: Call login API
      console.log('Login with:', form)
      ElMessage.success('Login successful (mock)')
    } else {
      // TODO: Call register API
      console.log('Register with:', form)
      ElMessage.success('Register successful (mock)')
    }
  })
}
</script>

<style scoped>
.auth-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}
.auth-card {
  width: 400px;
  padding: 20px;
}
</style>
