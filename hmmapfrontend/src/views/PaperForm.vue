<template>
  <el-form :model="form" label-width="120px">
    <el-form-item label="Title">
      <el-input v-model="form.title" />
    </el-form-item>
    <el-form-item label="Authors">
      <el-input v-model="form.authors" />
    </el-form-item>
    <el-form-item label="Abstract">
      <el-input type="textarea" v-model="form.abstractText" />
    </el-form-item>
    <el-form-item label="Publication">
      <el-input v-model="form.publication" />
    </el-form-item>

    <el-button type="primary" @click="submitPaper">Submit</el-button>
  </el-form>
</template>

<script setup>
import { ref } from 'vue';
import api from '@/api/axios';
import { ElMessage } from 'element-plus';

const form = ref({
  title: '',
  authors: '',
  abstractText: '',
  publication: ''
});

const submitPaper = async () => {
  await api.post('/papers', form.value);
  ElMessage.success('Paper submitted');
};
</script>
