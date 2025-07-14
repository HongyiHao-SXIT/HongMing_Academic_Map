<template>
  <div ref="chart" style="width: 100%; height: 400px;"></div>
</template>

<script setup>
import * as echarts from 'echarts'
import { ref, onMounted } from 'vue'
import axios from 'axios'

const chart = ref(null)
let myChart = null

async function fetchKeywordStats() {
  try {
    const res = await axios.get('/papers/stats/keywords')
    const data = res.data.data
    const keywords = Object.keys(data)
    const counts = Object.values(data)

    const option = {
      title: { text: 'Keyword Frequency Distribution' },
      tooltip: {},
      xAxis: {
        type: 'category',
        data: keywords,
        axisLabel: { rotate: 45, interval: 0 }
      },
      yAxis: { type: 'value' },
      series: [{ data: counts, type: 'bar' }]
    }

    myChart.setOption(option)
  } catch (error) {
    console.error('Failed to fetch keyword stats:', error)
  }
}

onMounted(() => {
  myChart = echarts.init(chart.value)
  fetchKeywordStats()
})
</script>
