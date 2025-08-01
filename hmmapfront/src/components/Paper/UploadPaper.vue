<template>
  <el-card>
    <h2>Upload Paper</h2>
    <el-form ref="formRef" :model="form" label-width="120px">
      <el-form-item label="Title" :rules="[{ required: true, message: 'Title is required', trigger: 'blur' }]">
        <el-input v-model="form.title" />
      </el-form-item>
      <el-form-item label="Abstract">
        <el-input type="textarea" v-model="form.abstractText" rows="4" />
      </el-form-item>
      <el-form-item label="Authors">
        <el-input v-model="form.authors" />
      </el-form-item>
      <el-form-item label="Keywords">
        <el-input v-model="form.keywords" />
      </el-form-item>
      <el-form-item label="PDF File" :rules="[{ required: true, message: 'Please upload a file', trigger: 'change' }]">
        <el-upload
          class="upload-demo"
          action=""
          :http-request="uploadFile"
          :show-file-list="false"
          :before-upload="beforeUpload"
        >
          <el-button type="primary">Select PDF</el-button>
        </el-upload>
        <div v-if="fileName" class="file-name">{{ fileName }}</div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm" :loading="loading">Upload</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { uploadPaper } from '@/api/paper'

const formRef = ref(null)
const loading = ref(false)
const fileName = ref('')
const fileData = ref(null)

const form = reactive({
  title: '',
  abstractText: '',
  authors: '',
  keywords: ''
})

const beforeUpload = (file) => {
  const isPdf = file.type === 'application/pdf'
  if (!isPdf) {
    ElMessage.error('Only PDF files are allowed.')
  }
  return isPdf
}

const uploadFile = ({ file }) => {
  fileData.value = file
  fileName.value = file.name
  return false
}

const submitForm = async () => {
  if (!form.title.trim()) {
    ElMessage.error('Please input the title.')
    return
  }
  if (!fileData.value) {
    ElMessage.error('Please select a PDF file.')
    return
  }
  loading.value = true
  const formData = new FormData()
  formData.append('file', fileData.value)
  formData.append('title', form.title)
  formData.append('abstractText', form.abstractText)
  formData.append('authors', form.authors)
  formData.append('keywords', form.keywords)
  try {
    await uploadPaper(formData)
    ElMessage.success('Upload successful')
    form.title = ''
    form.abstractText = ''
    form.authors = ''
    form.keywords = ''
    fileName.value = ''
    fileData.value = null
  } catch (err) {
    ElMessage.error('Upload failed')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.file-name {
  margin-top: 5px;
  font-style: italic;
}
</style>
