<template>
  <el-card>
    <h2>User Management</h2>
    <el-table :data="users" style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="id" label="ID" width="60" />
      <el-table-column prop="name" label="Name" />
      <el-table-column prop="account" label="Account" />
      <el-table-column prop="organization" label="Organization" />
      <el-table-column prop="role" label="Role" />
      <el-table-column label="Actions" width="180">
        <template #default="{ row }">
          <el-button size="small" @click="editUser(row)">Edit</el-button>
          <el-button size="small" type="danger" @click="deleteUser(row.id)">Delete</el-button>
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
import { fetchUsers, deleteUserById } from '@/api/admin'
import { ElMessage, ElMessageBox } from 'element-plus'

const users = ref([])
const page = ref(1)
const pageSize = 10
const total = ref(0)
const selectedUsers = ref([])

const loadUsers = async () => {
  try {
    const res = await fetchUsers(page.value - 1, pageSize)
    users.value = res.content
    total.value = res.totalElements
  } catch {
    ElMessage.error('Failed to load users')
  }
}

const onPageChange = (newPage) => {
  page.value = newPage
  loadUsers()
}

const handleSelectionChange = (selection) => {
  selectedUsers.value = selection
}

const deleteUser = async (id) => {
  try {
    await ElMessageBox.confirm('Are you sure to delete this user?', 'Warning', {
      confirmButtonText: 'Yes',
      cancelButtonText: 'No',
      type: 'warning'
    })
    await deleteUserById(id)
    ElMessage.success('User deleted')
    loadUsers()
  } catch {}
}

onMounted(loadUsers)
</script>
