<template>
  <div class="home-container">
    <div class="chart-container">
      <div class="chart-box" id="chart-box1"></div>
      <div class="chart-box" id="chart-box2"></div>
      <div class="chart-box" id="chart-box3"></div>
      <div class="chart-box" id="chart-box4"></div>
    </div>
    <button @click="handleClick">Click here</button>
  </div>
</template>

<script lang="js" setup>
import {salaryEdu, salaryGender, distributionEdu, distributionGender} from '../../api/index.js'
import { useStore } from 'vuex';
import { computed, onMounted } from 'vue';
import * as echarts from 'echarts/core';
import { BarChart } from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  DatasetComponent,
  TransformComponent,
} from 'echarts/components';
import { LabelLayout, UniversalTransition } from 'echarts/features';
import { CanvasRenderer } from 'echarts/renderers';

// 注册组件
echarts.use([
  TitleComponent,
  TooltipComponent,
  GridComponent,
  DatasetComponent,
  TransformComponent,
  BarChart,
  LabelLayout,
  UniversalTransition,
  CanvasRenderer,
]);

// 初始化每个图表
const initChart = (id, title, data) => {
  const container = document.getElementById(id);
  if (container) {
    const chart = echarts.init(container);
    chart.setOption({
      backgroundColor: 'transparent',
      title: { text: title },
      tooltip: {},
      xAxis: {
        data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子'],
      },
      yAxis: {},
      series: [{ name: '销量', type: 'bar', data }],
    });

    // 响应窗口大小变化
    window.addEventListener('resize', () => chart.resize());
  } else {
    console.error(`Chart container ${id} not found!`);
  }
};

onMounted(() => {
  initChart('chart-box1', '性别倾向薪资统计', [5, 20, 36, 10, 10, 20]);
  initChart('chart-box2', '学历倾向薪资统计', [15, 25, 10, 30, 20, 35]);
  initChart('chart-box3', '不同学历数据量', [12, 18, 33, 25, 15, 22]);
  initChart('chart-box4', '不同性别数据量', [8, 30, 25, 20, 30, 40]);
});

const store = useStore();
const name = computed(() => store.state.login.name);

const handleClick = () => {
  console.log('你好！');
};
</script>

<style scoped>
.home-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100vh;
  background-image: url('../../assets/jobbak.jpg');
  background-size: cover;
  background-repeat: no-repeat;
  position: relative;
}

.home-container::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.71);
}

.chart-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px; /* 图表之间的间距 */
  width: 80%;
  margin: 20px 0;
}

.chart-box {
  width: 100%;
  height: 300px; /* 单个图表高度 */
  background-color: #f5f5f59b; /* 可选：图表容器背景色 */
  border-radius: 8px; /* 可选：图表容器圆角 */
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); /* 可选：阴影 */
}

button {
  padding: 10px 20px;
  font-size: 1rem;
  background-color: #8db6ed;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  z-index: 1;
}

button:hover {
  background-color: #3c85f9;
}
</style>
