<template>
  <div class="register-container">
    <div class="register-box">
      <h2 class="title">User Registration</h2>
      <el-form ref="registerForm" :model="registerForm" :rules="registerRules" label-width="120px">
        <el-form-item label="Username" prop="account">
          <el-input v-model="registerForm.account" placeholder="Enter username"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input v-model="registerForm.password" type="password" placeholder="Enter password" show-password></el-input>
        </el-form-item>
        <el-form-item label="Full Name" prop="name">
          <el-input v-model="registerForm.name" placeholder="Enter your full name"></el-input>
        </el-form-item>
        <el-form-item label="Birthday" prop="birthday">
          <el-date-picker
            v-model="registerForm.birthday"
            type="date"
            placeholder="Select your birthday"
            value-format="yyyy-MM-dd"
            style="width: 100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="Bio" prop="intro">
          <el-input
            v-model="registerForm.intro"
            type="textarea"
            :rows="3"
            placeholder="Briefly introduce yourself"
          ></el-input>
        </el-form-item>
        <el-form-item label="Organization" prop="organization">
          <el-input v-model="registerForm.organization" placeholder="Enter your organization"></el-input>
        </el-form-item>
        <el-form-item label="Department" prop="department">
          <el-input v-model="registerForm.department" placeholder="Enter your department"></el-input>
        </el-form-item>
        <el-form-item label="Address" prop="address">
          <el-input v-model="registerForm.address" placeholder="Enter your address"></el-input>
        </el-form-item>
        <el-form-item label="Postcode" prop="postCode">
          <el-input v-model="registerForm.postCode" placeholder="Enter your postal code"></el-input>
        </el-form-item>
        <el-form-item label="Field" prop="field">
          <el-input v-model="registerForm.field" placeholder="Enter your area of expertise"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleRegister" :loading="loading">Register</el-button>
          <el-button @click="goToLogin">Back to Login</el-button>
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
          { required: true, message: 'Please enter a username', trigger: 'blur' },
          { min: 4, max: 20, message: 'Length must be between 4 and 20 characters', trigger: 'blur' }
        ],
        password: [
          { required: true, message: 'Please enter a password', trigger: 'blur' },
          { min: 6, max: 20, message: 'Length must be between 6 and 20 characters', trigger: 'blur' }
        ],
        name: [
          { required: true, message: 'Please enter your full name', trigger: 'blur' }
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
          this.$axios.post('/api/register', this.registerForm)
            .then(response => {
              this.$message.success('Registration successful')
              this.$router.push('/login')
            })
            .catch(error => {
              this.$message.error(error.response.data.message || 'Registration failed')
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