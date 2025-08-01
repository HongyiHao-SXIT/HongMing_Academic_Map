<template>
  <el-card>
    <h2>User Profile</h2>
    <el-form :model="form" ref="formRef" label-width="120px">
      <el-form-item label="Name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="Account" disabled>
        <el-input v-model="form.account" disabled />
      </el-form-item>
      <el-form-item label="Birthday">
        <el-date-picker
          v-model="form.birthday"
          type="date"
          placeholder="Select date"
          style="width: 100%"
        />
      </el-form-item>
      <el-form-item label="Introduction">
        <el-input type="textarea" v-model="form.intro" rows="4" />
      </el-form-item>
      <el-form-item label="Organization">
        <el-input v-model="form.organization" />
      </el-form-item>
      <el-form-item label="Department">
        <el-input v-model="form.department" />
      </el-form-item>
      <el-form-item label="Address">
        <el-input v-model="form.address" />
      </el-form-item>
      <el-form-item label="Post Code">
        <el-input v-model="form.postCode" />
      </el-form-item>
      <el-form-item label="Field">
        <el-input v-model="form.field" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateProfile" :loading="loading">Save</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { getUserProfile, updateUserProfile } from '@/api/user'

const formRef = ref(null)
const loading = ref(false)
const form = reactive({
  name: '',
  account: '',
  birthday: '',
  intro: '',
  organization: '',
  department: '',
  address: '',
  postCode: '',
  field: ''
})

const loadProfile = async () => {
  loading.value = true
  try {
    const data = await getUserProfile()
    Object.assign(form, data)
  } catch {
    ElMessage.error('Failed to load profile')
  } finally {
    loading.value = false
  }
}

const updateProfile = async () => {
  loading.value = true
  try {
    await updateUserProfile(form)
    ElMessage.success('Profile updated')
  } catch {
    ElMessage.error('Update failed')
  } finally {
    loading.value = false
  }
}

onMounted(loadProfile)
</script>
