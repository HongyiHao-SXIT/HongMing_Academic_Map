<template>
  <div class="register-container">
    <div class="register-box">
      <h2 class="title">用户注册</h2>
      <el-form ref="registerForm" :model="registerForm" :rules="registerRules" label-width="100px">
        <el-form-item label="账号" prop="account">
          <el-input v-model="registerForm.account" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="registerForm.password" type="password" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="registerForm.name" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker
            v-model="registerForm.birthday"
            type="date"
            placeholder="选择生日"
            value-format="yyyy-MM-dd"
            style="width: 100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="个人简介" prop="intro">
          <el-input
            v-model="registerForm.intro"
            type="textarea"
            :rows="3"
            placeholder="请简要介绍自己"
          ></el-input>
        </el-form-item>
        <el-form-item label="组织" prop="organization">
          <el-input v-model="registerForm.organization" placeholder="请输入所属组织"></el-input>
        </el-form-item>
        <el-form-item label="部门" prop="department">
          <el-input v-model="registerForm.department" placeholder="请输入部门"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="registerForm.address" placeholder="请输入地址"></el-input>
        </el-form-item>
        <el-form-item label="邮编" prop="postCode">
          <el-input v-model="registerForm.postCode" placeholder="请输入邮编"></el-input>
        </el-form-item>
        <el-form-item label="专业领域" prop="field">
          <el-input v-model="registerForm.field" placeholder="请输入专业领域"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleRegister" :loading="loading">注册</el-button>
          <el-button @click="goToLogin">返回登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data() {
    return {
      registerForm: {
        account: '',
        password: '',
        name: '',
        birthday: '',
        intro: '',
        organization: '',
        department: '',
        address: '',
        postCode: '',
        field: ''
      },
      registerRules: {
        account: [
          { required: true, message: '请输入账号', trigger: 'blur' },
          { min: 4, max: 20, message: '长度在 4 到 20 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ]
      },
      loading: false
    }
  },
  methods: {
    handleRegister() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          this.loading = true
          // 调用注册API
          this.$axios.post('/api/register', this.registerForm)
            .then(response => {
              this.$message.success('注册成功')
              this.$router.push('/login')
            })
            .catch(error => {
              this.$message.error(error.response.data.message || '注册失败')
            })
            .finally(() => {
              this.loading = false
            })
        }
      })
    },
    goToLogin() {
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #6e8efb, #a777e3);
  padding: 20px;
}

.register-box {
  width: 700px;
  padding: 30px;
  background: rgba(255, 255, 255, 0.95);
  border-radius: 10px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
}

.title {
  margin-bottom: 30px;
  text-align: center;
  color: #303133;
}

.el-form-item {
  margin-bottom: 22px;
}

.el-button {
  width: 120px;
  margin-right: 20px;
}
</style>