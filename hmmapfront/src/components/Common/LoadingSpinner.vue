<template>
  <div class="spinner-wrapper">
    <el-icon class="loading-icon"><loading /></el-icon>
    <p>Loading...</p>
  </div>
</template>

<script setup>
import { Loading } from '@element-plus/icons-vue'
</script>

<style scoped>
.spinner-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
}
.loading-icon {
  font-size: 36px;
  animation: spin 1s linear infinite;
  color: #409EFF;
}
@keyframes spin {
  0% { transform: rotate(0); }
  100% { transform: rotate(360deg); }
}
</style>
