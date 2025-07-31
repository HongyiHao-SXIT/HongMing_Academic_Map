<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="handleLogin">
      <input v-model="account" placeholder="Account" required />
      <input v-model="password" type="password" placeholder="Password" required />
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { login } from '@/api/auth';
import { useRouter } from 'vue-router';

const router = useRouter();
const account = ref('');
const password = ref('');

const handleLogin = async () => {
  try {
    const user = await login(account.value, password.value);
    localStorage.setItem('token', user.token);
    router.push('/home');
  } catch (e) {
    console.error(e);
  }
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: auto;
  padding: 40px;
}
</style>
