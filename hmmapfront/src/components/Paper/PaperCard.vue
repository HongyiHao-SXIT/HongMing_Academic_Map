<template>
  <el-card class="paper-card" shadow="hover" @click="goToDetail">
    <h3>{{ paper.title }}</h3>
    <p><strong>Authors:</strong> {{ paper.authors }}</p>
    <p><strong>Published:</strong> {{ formatDate(paper.publishDate) }}</p>
    <p class="abstract">{{ paper.abstractText }}</p>
  </el-card>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { computed } from 'vue'

const props = defineProps({
  paper: Object
})

const router = useRouter()

const goToDetail = () => {
  router.push(`/paper/${props.paper.id}`)
}

const formatDate = (date) => {
  if (!date) return 'N/A'
  return new Date(date).toLocaleDateString()
}
</script>

<style scoped>
.paper-card {
  cursor: pointer;
  margin-bottom: 16px;
}
.abstract {
  color: #666;
  font-size: 0.9em;
  margin-top: 8px;
  max-height: 60px;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
