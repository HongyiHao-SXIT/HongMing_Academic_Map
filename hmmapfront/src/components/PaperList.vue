<template>
  <div>
    <h2>Paper List</h2>
    <ul>
      <li v-for="paper in papers" :key="paper.id">
        <strong>{{ paper.title }}</strong> — {{ paper.authors }}
        <button @click="download(paper.id)">Download PDF</button>
      </li>
    </ul>
    <button @click="loadMore">Load More</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from '@/api/axios';

const papers = ref([]);
let page = 0;
const size = 10;

async function fetchPapers() {
  try {
    const data = await axios.get('/papers/list', {
      params: { page, size }
    });
    papers.value.push(...data);
  } catch (e) {
    alert('Failed to load papers');
  }
}

function loadMore() {
  page++;
  fetchPapers();
}

async function download(id) {
  try {
    const response = await axios.get(`/papers/download/${id}`, {
      responseType: 'blob'  // 重要，告诉 axios 这是文件流
    });

    // 创建临时链接下载文件
    const url = window.URL.createObjectURL(new Blob([response]));
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', `paper_${id}.pdf`);
    document.body.appendChild(link);
    link.click();
    link.remove();
    window.URL.revokeObjectURL(url);
  } catch (e) {
    alert('Download failed');
  }
}

onMounted(() => {
  fetchPapers();
});
</script>
