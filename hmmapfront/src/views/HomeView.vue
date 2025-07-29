<template>
  <div class="home">
    <h1>HongMing Academic Map</h1>
    <div class="search-box">
      <el-input
        v-model="keyword"
        placeholder="Enter keyword"
        style="width: 400px"
        @keyup.enter="search"
      />
      <el-button type="primary" @click="search" style="margin-left: 10px">
        Search
      </el-button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const keyword = ref('')
const router = useRouter()

const search = () => {
  if (!keyword.value.trim()) {
    ElMessage.warning('Please enter a keyword')
    return
  }
  router.push({ path: '/search', query: { keyword: keyword.value.trim() } })
}
</script>

<style scoped>
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 100px;
}
.search-box {
  display: flex;
  align-items: center;
  margin-top: 20px;
}
</style>
