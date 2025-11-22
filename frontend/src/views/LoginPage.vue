<template>
  <div class="login-page">
    <div class="login-card">
      <h2 class="title">登录 Academic Map</h2>

      <form @submit.prevent="login">
        <div class="form-group">
          <label>邮箱</label>
          <input 
            type="email" 
            v-model="email" 
            placeholder="example@gmail.com" 
            required
          />
        </div>

        <div class="form-group">
          <label>密码</label>
          <input 
            type="password" 
            v-model="password" 
            placeholder="••••••••"
            required
          />
        </div>

        <button class="login-btn" type="submit">登录</button>
      </form>

      <div class="bottom-links">
        <a href="#">忘记密码？</a>
        <a href="#">注册新账户</a>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const email = ref('')
const password = ref('')

const login = () => {
  console.log("登录账号：", email.value, password.value)
  // TODO: 调用 Spring Boot 后端 API
}
</script>

<style scoped>
/* 背景居中布局 */
.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: #f5f5f5;
}

/* 登录卡片 */
.login-card {
  width: 380px;
  padding: 40px 30px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
  border: 1px solid #eaeaea;
}

/* 标题 */
.title {
  text-align: center;
  margin-bottom: 25px;
  font-weight: 500;
  color: #333;
}

/* 表单 */
.form-group {
  margin-bottom: 20px;
}

label {
  font-size: 14px;
  color: #555;
}

input {
  width: 100%;
  padding: 10px;
  margin-top: 6px;
  border: 1px solid #ddd;
  border-radius: 6px;
  outline: none;
  font-size: 14px;
}

input:focus {
  border-color: #4285f4;   /* 谷歌的蓝色 */
  box-shadow: 0 0 3px rgba(66,133,244,0.3);
}

/* 登录按钮 */
.login-btn {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 6px;
  background: #4285f4;
  color: #fff;
  font-size: 15px;
  cursor: pointer;
  margin-top: 10px;
}

.login-btn:hover {
  background: #3367d6;
}

/* 底部链接 */
.bottom-links {
  margin-top: 20px;
  text-align: center;
  font-size: 14px;
}

.bottom-links a {
  color: #4285f4;
  margin: 0 8px;
}
</style>
