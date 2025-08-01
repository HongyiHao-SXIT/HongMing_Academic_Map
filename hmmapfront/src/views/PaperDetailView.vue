<template>
  <div class="paper-detail-view">
    <paper-detail :paperId="paperId" />
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import PaperDetail from '@/components/Paper/PaperDetail.vue'

const route = useRoute()
const paperId = route.params.id
</script>

<style scoped>
.paper-detail-view {
  max-width: 900px;
  margin: 20px auto;
  padding: 20px;
}
</style>
