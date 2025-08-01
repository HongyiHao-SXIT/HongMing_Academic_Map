<template>
  <el-form ref="formRef" :model="form" label-width="100px" class="upload-form" @submit.prevent="onSubmit">
    <el-form-item label="Title" :rules="[{ required: true, message: 'Please input title', trigger: 'blur' }]">
      <el-input v-model="form.title" />
    </el-form-item>
    <el-form-item label="Abstract">
      <el-input type="textarea" v-model="form.abstractText" rows="4" />
    </el-form-item>
    <el-form-item label="Authors">
      <el-input v-model="form.authors" />
    </el-form-item>
    <el-form-item label="Keywords">
      <el-input v-model="form.keywords" placeholder="Comma separated" />
    </el-form-item>
    <el-form-item label="PDF File" :rules="[{ required: true, message: 'Please upload PDF', trigger: 'change' }]">
      <el-upload
        ref="uploadRef"
        drag
        action=""
        :auto-upload="false"
        :on-change="handleFileChange"
        :before-upload="beforeUpload"
        :file-list="fileList"
        accept=".pdf"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">Drop file here or <em>click to upload</em></div>
        <div class="el-upload__tip" slot="tip">Only PDF files allowed</div>
      </el-upload>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" :loading="loading" @click="onSubmit">Upload</el-button>
    </el-form-item>
    <el-alert
      v-if="error"
      :title="error"
      type="error"
      show-icon
      closable
      @close="error = null"
      style="margin-top: 15px"
    />
  </el-form>
</template>

<script setup>
import { ref } from 'vue'
import request from '../utils/request'

const formRef = ref(null)
const uploadRef = ref(null)
const fileList = ref([])

const form = ref({
  title: '',
  abstractText: '',
  authors: '',
  keywords: '',
  file: null,
})

const loading = ref(false)
const error = ref(null)

function handleFileChange(file) {
  form.value.file = file.raw
  fileList.value = [file]
}

function beforeUpload(file) {
  const isPDF = file.type === 'application/pdf'
  if (!isPDF) {
    error.value = 'Only PDF files are allowed!'
  }
  return isPDF
}

async function onSubmit() {
  if (!form.value.title || !form.value.file) {
    error.value = 'Please fill required fields and upload a PDF file'
    return
  }

  loading.value = true
  error.value = null

  try {
    const formData = new FormData()
    formData.append('file', form.value.file)
    formData.append('title', form.value.title)
    formData.append('abstractText', form.value.abstractText)
    formData.append('authors', form.value.authors)
    formData.append('keywords', form.value.keywords)

    await request.post('/papers/upload', formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    })

    alert('Upload successful!')
    // reset form
    form.value = {
      title: '',
      abstractText: '',
      authors: '',
      keywords: '',
      file: null,
    }
    fileList.value = []
    uploadRef.value.clearFiles()
  } catch (e) {
    error.value = e.response?.data?.message || e.message || 'Upload failed'
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.upload-form {
  max-width: 600px;
  margin: 40px auto;
}
</style>
