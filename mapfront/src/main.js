import { createApp } from 'vue'
import App from './App.vue'

createApp(App).mount('#app')

import { reactive } from 'vue'

const counter = reactive({
  count: 0
})

console.log(counter.count)
counter.count++