<template>
  <div class="search-result">
    <h2>Search Results for: "{{ keyword }}"</h2>

    <el-empty v-if="loading === false && papers.length === 0" description="No results found" />

    <el-skeleton v-if="loading" rows="6" animated />

    <el-card
      v-for="paper in papers"
      :key="paper.id"
      class="paper-card"
      shadow="hover"
    >
      <h3>{{ paper.title }}</h3>
      <p><strong>Author:</strong> {{ paper.author }}</p>
      <p><strong>Abstract:</strong> {{ paper.abstract }}</p>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const keyword = route.query.keyword || ''
const papers = ref([])
const loading = ref(false)

const fetchPapers = async () => {
  loading.value = true
  try {
    const response = await axios.get('/api/search', {
      params: { keyword }
    })
    papers.value = response.data.data || []
  } catch (error) {
    console.error('Search failed:', error)
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchPapers()
})
</script>

<style scoped>
.search-result {
  padding: 30px 10%;
}

.paper-card {
  margin-bottom: 20px;
}
</style>
