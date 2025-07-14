<template>
  <div>
    <el-input v-model="keyword" placeholder="Search by keyword" @keyup.enter="fetchPapers" />
    <el-button @click="fetchPapers">Search</el-button>

    <el-table :data="papers">
      <el-table-column prop="title" label="Title" />
      <el-table-column prop="authors" label="Authors" />
      <el-table-column prop="publication" label="Publication" />
      <el-table-column label="Actions">
        <template #default="scope">
          <el-button size="small" @click="viewDetails(scope.row.id)">View</el-button>
          <el-button size="small" type="danger" @click="deletePaper(scope.row.id)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      :current-page="page"
      :page-size="size"
      :total="total"
      @current-change="handlePageChange"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '@/api/axios';
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';

const keyword = ref('');
const papers = ref([]);
const page = ref(1);
const size = ref(10);
const total = ref(0);
const router = useRouter();

const fetchPapers = async () => {
  const response = await api.get('/papers', {
    params: {
      keyword: keyword.value,
      page: page.value - 1,
      size: size.value
    }
  });
  papers.value = response.data.data.content;
  total.value = response.data.data.totalElements;
};

const handlePageChange = (newPage) => {
  page.value = newPage;
  fetchPapers();
};

const viewDetails = (id) => {
  router.push(`/papers/${id}`);
};

const deletePaper = async (id) => {
  await api.delete(`/papers/${id}`);
  ElMessage.success('Deleted');
  fetchPapers();
};

onMounted(fetchPapers);
</script>
