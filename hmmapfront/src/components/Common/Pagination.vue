<template>
  <div class="pagination-container">
    <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      :page-size="pageSize"
      :current-page="currentPage"
      @current-change="handlePageChange"
    />
  </div>
</template>

<script setup>
const props = defineProps({
  total: Number,
  pageSize: Number,
  currentPage: Number
})
const emits = defineEmits(['update:currentPage'])

const handlePageChange = (page) => {
  emits('update:currentPage', page)
}
</script>

<style scoped>
.pagination-container {
  margin-top: 20px;
  text-align: center;
}
</style>
