<template>
<div>
<header>
<div class="container nav">
<NavBar />
</div>
</header>


<main>
<div class="container">
<Home />
</div>
</main>


<footer>
<div class="container footer">Demo site — All in English</div>
</footer>
</div>
</template>


<script setup>
import NavBar from './components/NavBar.vue'
import Home from './views/Home.vue'
</script>