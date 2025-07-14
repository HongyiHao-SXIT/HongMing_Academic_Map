import { createRouter, createWebHistory } from 'vue-router';
import PaperList from '@/views/PaperList.vue';
import PaperDetail from '@/views/PaperDetail.vue';
import PaperForm from '@/views/PaperForm.vue';

const routes = [
  { path: '/', component: PaperList },
  { path: '/papers/:id', component: PaperDetail },
  { path: '/upload', component: PaperForm }
];

export default createRouter({
  history: createWebHistory(),
  routes
});
