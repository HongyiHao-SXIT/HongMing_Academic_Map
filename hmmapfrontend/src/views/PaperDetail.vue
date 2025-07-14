<template>
  <div v-if="paper">
    <h2>{{ paper.title }}</h2>
    <p>Authors: {{ paper.authors }}</p>
    <p>Publication: {{ paper.publication }}</p>
    <p>Abstract: {{ paper.abstractText }}</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import api from '@/api/axios';

const route = useRoute();
const paper = ref(null);

const fetchPaper = async () => {
  const response = await api.get(`/papers/${route.params.id}`);
  paper.value = response.data.data;
};

onMounted(fetchPaper);
</script>
