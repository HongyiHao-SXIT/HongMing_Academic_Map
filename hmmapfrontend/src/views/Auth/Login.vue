<template>
  <div class="login-container">
    <div class="login-box">
      <h2 class="title">欢迎登录</h2>
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules">
        <el-form-item prop="account">
          <el-input
            v-model="loginForm.account"
            placeholder="请输入账号"
            prefix-icon="el-icon-user"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            prefix-icon="el-icon-lock"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            class="login-btn"
            @click="handleLogin"
            :loading="loading"
          >登录</el-button>
        </el-form-item>
      </el-form>
      <div class="footer">
        <span @click="goToRegister">还没有账号？立即注册</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      loginForm: {
        account: '',
        password: ''
      },
      loginRules: {
        account: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      },
      loading: false
    }
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          // 调用登录API
          this.$axios.post('/api/login', this.loginForm)
            .then(response => {
              // 登录成功处理
              this.$message.success('登录成功')
              this.$router.push('/dashboard')
            })
            .catch(error => {
              this.$message.error(error.response.data.message || '登录失败')
            })
            .finally(() => {
              this.loading = false
            })
        }
      })
    },
    goToRegister() {
      this.$router.push('/register')
    }
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #6e8efb, #a777e3);
  background-size: cover;
}

.login-box {
  width: 400px;
  padding: 40px;
  background: rgba(255, 255, 255, 0.9);
  border-radius: 10px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
}

.title {
  margin-bottom: 30px;
  text-align: center;
  color: #303133;
}

.login-btn {
  width: 100%;
  margin-top: 10px;
}

.footer {
  margin-top: 20px;
  text-align: center;
  color: #909399;
  cursor: pointer;
}

.footer:hover {
  color: #409EFF;
}
</style>