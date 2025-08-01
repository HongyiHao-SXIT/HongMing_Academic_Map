<template>
  <div class="admin-panel-view">
    <el-tabs v-model="activeTab">
      <el-tab-pane label="User Management" name="users">
        <user-table />
      </el-tab-pane>
      <el-tab-pane label="Paper Review" name="papers">
        <paper-review-table />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import UserTable from '@/components/Admin/UserTable.vue'
import PaperReviewTable from '@/components/Admin/PaperReviewTable.vue'

const activeTab = ref('users')
</script>

<style scoped>
.admin-panel-view {
  max-width: 1000px;
  margin: 20px auto;
  padding: 20px;
}
</style>
