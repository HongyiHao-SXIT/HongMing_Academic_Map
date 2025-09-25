<template>
  <el-form ref="uploadForm" :model="form" :rules="rules" label-width="120px">
    <el-form-item label="论文标题" prop="title">
      <el-input v-model="form.title" placeholder="请输入论文标题"></el-input>
    </el-form-item>
    
    <el-form-item label="摘要" prop="abstractText">
      <el-input type="textarea" v-model="form.abstractText" rows="4" placeholder="请输入论文摘要"></el-input>
    </el-form-item>
    
    <el-form-item label="作者" prop="authors">
      <el-input v-model="form.authors" placeholder="请输入作者，多个作者用逗号分隔"></el-input>
    </el-form-item>
    
    <el-form-item label="关键词" prop="keywords">
      <el-input v-model="form.keywords" placeholder="请输入关键词，多个关键词用逗号分隔"></el-input>
    </el-form-item>
    
    <el-form-item label="论文类型" prop="type">
      <el-select v-model="form.type" placeholder="请选择论文类型">
        <el-option label="期刊论文" value="期刊论文"></el-option>
        <el-option label="会议论文" value="会议论文"></el-option>
        <el-option label="学位论文" value="学位论文"></el-option>
        <el-option label="报告" value="报告"></el-option>
      </el-select>
    </el-form-item>
    
    <el-form-item label="分类" prop="categories">
      <el-input v-model="form.categories" placeholder="请输入分类"></el-input>
    </el-form-item>
    
    <el-form-item label="标签" prop="tags">
      <el-input v-model="form.tags" placeholder="请输入标签，多个标签用逗号分隔"></el-input>
    </el-form-item>
    
    <el-form-item label="论文文件" prop="file">
      <el-upload
        ref="upload"
        action=""
        :auto-upload="false"
        :on-change="handleFileChange"
        :file-list="fileList"
        accept=".pdf"
      >
        <el-button type="primary">选择文件</el-button>
        <template #tip>
          <div class="el-upload__tip">仅支持PDF格式文件</div>
        </template>
      </el-upload>
    </el-form-item>
    
    <el-form-item>
      <el-button type="primary" @click="handleUpload">提交</el-button>
      <el-button @click="resetForm">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import axios from '../../utils/axios'
import { ElMessage } from 'element-plus'

const router = useRouter()
const uploadForm = ref(null)
const upload = ref(null)
const fileList = ref([])
let selectedFile = null

const form = reactive({
  title: '',
  abstractText: '',
  authors: '',
  keywords: '',
  type: '',
  categories: '',
  tags: ''
})

const rules = {
  title: [
    { required: true, message: '请输入论文标题', trigger: 'blur' }
  ],
  authors: [
    { required: true, message: '请输入作者', trigger: 'blur' }
  ],
  file: [
    { required: true, message: '请选择论文文件', trigger: 'change' }
  ]
}

const handleFileChange = (file, fileList) => {
  selectedFile = file.raw
  fileList.value = fileList
}

const handleUpload = async () => {
  try {
    const valid = await uploadForm.value.validate()
    if (valid && selectedFile) {
      const formData = new FormData()
      formData.append('file', selectedFile)
      formData.append('title', form.title)
      formData.append('abstractText', form.abstractText)
      formData.append('authors', form.authors)
      formData.append('keywords', form.keywords)
      formData.append('type', form.type)
      formData.append('categories', form.categories)
      formData.append('tags', form.tags)
      
      await axios.post('/api/papers/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      
      ElMessage.success('论文上传成功')
      router.push('/')
    }
  } catch (error) {
    console.error('Upload failed:', error)
    ElMessage.error('上传失败，请重试')
  }
}

const resetForm = () => {
  uploadForm.value.resetFields()
  fileList.value = []
  selectedFile = null
}
</script>