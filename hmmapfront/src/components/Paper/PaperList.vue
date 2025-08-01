<template>
  <div>
    <search-bar @search="handleSearch" />
    <el-divider />
    <el-row :gutter="20">
      <el-col :span="8" v-for="paper in papers" :key="paper.id">
        <paper-card :paper="paper" />
      </el-col>
    </el-row>
    <pagination
      :total="total"
      :pageSize="pageSize"
      :currentPage="page"
      @update:currentPage="onPageChange"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import SearchBar from '@/components/Common/SearchBar.vue'
import PaperCard from './PaperCard.vue'
import Pagination from '@/components/Common/Pagination.vue'
import { fetchPapers } from '@/api/paper'

const papers = ref([])
const page = ref(1)
const pageSize = 10
const total = ref(0)

const loadPapers = async () => {
  try {
    const res = await fetchPapers(page.value - 1, pageSize)
    papers.value = res.content
    total.value = res.totalElements
  } catch (err) {
    console.error(err)
  }
}

const onPageChange = (newPage) => {
  page.value = newPage
  loadPapers()
}

const handleSearch = (keyword) => {
  page.value = 1
  loadPapers()
}

onMounted(loadPapers)
</script>
