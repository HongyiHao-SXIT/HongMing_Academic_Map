<template>
<section class="home-hero">
<div class="logo" aria-hidden="true">
<!-- Large logo similar in spirit to a search brand -- all English -->
<svg viewBox="0 0 300 80" width="300" height="80" role="img" aria-label="SearchDemo logo">
<rect x="0" y="0" width="72" height="72" rx="10" fill="#4285F4" />
<text x="92" y="52" font-family="Arial, Helvetica, sans-serif" font-size="40" font-weight="700">SearchDemo</text>
</svg>
</div>


<div class="search-box">
<input type="text" v-model="query" class="search-input" placeholder="Search the web" @keyup.enter="doSearch" />
<button class="search-button" @click="doSearch">Search</button>
</div>


<div class="options" role="radiogroup" aria-label="Search options">
<label class="radio-label">
<input type="radio" name="mode" value="all" v-model="mode" />
<span>All</span>
</label>
<label class="radio-label">
<input type="radio" name="mode" value="books" v-model="mode" />
<span>Books</span>
</label>
</div>


<div class="footer">
<div>Query: <strong>{{ queryDisplay }}</strong></div>
<div>Mode: <strong>{{ mode }}</strong></div>
</div>
</section>
</template>


<script setup>
import { ref, computed } from 'vue'
const query = ref('')
const mode = ref('all')
const doSearch = () => {
// In a real app you'd navigate or call an API. Here we just log and show the values.
console.log('Searching', { query: query.value, mode: mode.value })
alert(`Searching for "${query.value}" in mode "${mode.value}"`)
}
const queryDisplay = computed(() => query.value || '(empty)')
</script>


<style scoped>
/* small scoped adjustments for the Home view */
.home-hero { text-align:center }
.footer { margin-top:8px; color:#666 }
</style>