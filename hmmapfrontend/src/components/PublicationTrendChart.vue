<template>
  <div ref="chart" style="width: 100%; height: 400px;"></div>
</template>

<script setup>
import * as echarts from 'echarts'
import { ref, onMounted } from 'vue'
import axios from 'axios'

const chart = ref(null)
let myChart = null

async function fetchPublicationTrend() {
  try {
    const res = await axios.get('/papers/stats/publicationTrend')
    const data = res.data.data
    const years = Object.keys(data)
    const counts = Object.values(data)

    const option = {
      title: { text: 'Publication Trend Over Years' },
      tooltip: { trigger: 'axis' },
      xAxis: { type: 'category', data: years },
      yAxis: { type: 'value' },
      series: [{ data: counts, type: 'line', smooth: true }]
    }

    myChart.setOption(option)
  } catch (error) {
    console.error('Failed to fetch publication trend:', error)
  }
}

onMounted(() => {
  myChart = echarts.init(chart.value)
  fetchPublicationTrend()
})
</script>
