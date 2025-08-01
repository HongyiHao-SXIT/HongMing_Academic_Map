<template>
  <div class="user-profile-view">
    <profile-form />
  </div>
</template>

<script setup>
import ProfileForm from '@/components/User/ProfileForm.vue'
</script>

<style scoped>
.user-profile-view {
  max-width: 600px;
  margin: 40px auto;
  padding: 20px;
}
</style>
