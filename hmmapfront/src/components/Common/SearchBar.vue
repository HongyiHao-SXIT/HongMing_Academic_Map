<template>
  <div class="search-bar">
    <el-input
      v-model="searchQuery"
      placeholder="Search papers..."
      clearable
      @keyup.enter="handleSearch"
    />
    <el-button type="primary" @click="handleSearch" icon="el-icon-search">Search</el-button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const searchQuery = ref('')
const router = useRouter()

const handleSearch = () => {
  if (searchQuery.value.trim()) {
    router.push({ name: 'SearchResults', query: { q: searchQuery.value } })
  }
}
</script>

<style scoped>
.search-bar {
  display: flex;
  gap: 10px;
  align-items: center;
}
</style>
