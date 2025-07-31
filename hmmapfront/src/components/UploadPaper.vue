<template>
  <div class="upload-paper">
    <h2>Upload Paper</h2>
    <form @submit.prevent="submitPaper">
      <input v-model="title" placeholder="Title" required />
      <textarea v-model="abstractText" placeholder="Abstract"></textarea>
      <input v-model="authors" placeholder="Authors" />
      <input v-model="keywords" placeholder="Keywords (comma-separated)" />
      <input type="file" @change="handleFileChange" accept=".pdf" required />
      <button type="submit">Upload</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from '@/api/axios';

const title = ref('');
const abstractText = ref('');
const authors = ref('');
const keywords = ref('');
const file = ref(null);

function handleFileChange(event) {
  file.value = event.target.files[0];
}

async function submitPaper() {
  if (!file.value) {
    alert('Please select a PDF file');
    return;
  }

  const formData = new FormData();
  formData.append('file', file.value);
  formData.append('title', title.value);
  formData.append('abstractText', abstractText.value);
  formData.append('authors', authors.value);
  formData.append('keywords', keywords.value);

  try {
    const res = await axios.post('/papers/upload', formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    });
    alert('Upload successful: ' + res.title);
    // 清空表单
    title.value = '';
    abstractText.value = '';
    authors.value = '';
    keywords.value = '';
    file.value = null;
  } catch (err) {
    console.error(err);
    alert('Upload failed');
  }
}
</script>

<style scoped>
.upload-paper {
  max-width: 600px;
  margin: auto;
  padding: 20px;
}
.upload-paper input, .upload-paper textarea {
  display: block;
  margin-bottom: 10px;
  width: 100%;
}
</style>
