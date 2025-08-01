<template>
  <el-card>
    <h2>Paper Review Management</h2>
    <el-table :data="papers" style="width: 100%">
      <el-table-column prop="id" label="ID" width="60" />
      <el-table-column prop="title" label="Title" />
      <el-table-column prop="authors" label="Authors" />
      <el-table-column prop="isPublic" label="Public" width="80">
        <template #default="{ row }">
          <el-switch v-model="row.isPublic" @change="togglePublic(row)" />
        </template>
      </el-table-column>
      <el-table-column prop="isDeleted" label="Deleted" width="80">
        <template #default="{ row }">
          <el-switch v-model="row.isDeleted" @change="toggleDeleted(row)" />
        </template>
      </el-table-column>
      <el-table-column label="Actions" width="160">
        <template #default="{ row }">
          <el-button size="small" type="primary" @click="editPaper(row)">Edit</el-button>
          <el-button size="small" type="danger" @click="deletePaper(row.id)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      :total="total"
      :pageSize="pageSize"
      :currentPage="page"
      @update:currentPage="onPageChange"
    />
  </el-card>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import Pagination from '@/components/Common/Pagination.vue'
import { fetchPapersAdmin, updatePaper, deletePaperById } from '@/api/admin'
import { ElMessage, ElMessageBox } from 'element-plus'

const papers = ref([])
const page = ref(1)
const pageSize = 10
const total = ref(0)

const loadPapers = async () => {
  try {
    const res = await fetchPapersAdmin(page.value - 1, pageSize)
    papers.value = res.content
    total.value = res.totalElements
  } catch {
    ElMessage.error('Failed to load papers')
  }
}

const onPageChange = (newPage) => {
  page.value = newPage
  loadPapers()
}

const togglePublic = async (paper) => {
  try {
    paper.isPublic = !paper.isPublic
    await updatePaper(paper.id, { isPublic: paper.isPublic })
    ElMessage.success('Paper visibility updated')
  } catch {
    ElMessage.error('Update failed')
  }
}

const toggleDeleted = async (paper) => {
  try {
    paper.isDeleted = !paper.isDeleted
    await updatePaper(paper.id, { isDeleted: paper.isDeleted })
    ElMessage.success('Paper deleted status updated')
  } catch {
    ElMessage.error('Update failed')
  }
}

const editPaper = (paper) => {
  // Navigate to edit page or show modal for editing
  // Not implemented here
}

const deletePaper = async (id) => {
  try {
    await ElMessageBox.confirm('Are you sure to delete this paper?', 'Warning', {
      confirmButtonText: 'Yes',
      cancelButtonText: 'No',
      type: 'warning'
    })
    await deletePaperById(id)
    ElMessage.success('Paper deleted')
    loadPapers()
  } catch {}
}

onMounted(loadPapers)
</script>
