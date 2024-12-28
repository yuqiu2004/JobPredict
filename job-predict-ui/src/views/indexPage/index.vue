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
import {salaryEdu, salaryGender, distributionEdu, distributionRange} from '../../api/index.js'
import { useStore } from 'vuex';
import { computed, onMounted } from 'vue';
import * as echarts from 'echarts/core';
import { BarChart, PieChart } from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  DatasetComponent,
  TransformComponent,
  LegendComponent,
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
  LegendComponent,
  PieChart,
]);

onMounted(() => {
  salaryGender().then((response) => {
    const data = response.data.data;
    const genders = data.map((item) => item.gender);
    const avgSalaries = data.map((item) => item.averageSalary);
    const medSalaries = data.map((item) => item.medianSalary);

    initGroupedBarChart('chart-box1', '性别倾向薪资统计', genders, avgSalaries, medSalaries);
  });

  salaryEdu().then((response) => {
    const data = response.data.data;
    const educations = data.map((item) => item.education);
    const avgSalaries = data.map((item) => item.averageSalary);
    const medSalaries = data.map((item) => item.medianSalary);

    initGroupedBarChart('chart-box2', '学历倾向薪资统计', educations, avgSalaries, medSalaries);
  });

  distributionEdu().then((response) => {
    const data = response.data.data;
    const educations = data.map((item) => item.education);
    const counts = data.map((item) => item.count);

    initPieChart('chart-box3', '不同学历数据量', educations, counts);
  });

  distributionRange().then((response) => {
    const data = response.data.data;
    const salaryRanges = data.map((item) => item.salaryRange);
    const counts = data.map((item) => item.count);

    initPieChart('chart-box4', '不同薪资数据量', salaryRanges, counts);
  });
});

const initGroupedBarChart = (id, title, categories, avgData, medianData) => {
  const container = document.getElementById(id);
  if (container) {
    const chart = echarts.init(container);
    chart.setOption({
      backgroundColor: 'transparent',
      title: { text: title },
      tooltip: {},
      legend: { data: ['平均薪资', '薪资众数'] },
      xAxis: { type: 'category', data: categories },
      yAxis: { type: 'value' },
      series: [
        { name: '平均薪资', type: 'bar', data: avgData },
        { name: '薪资众数', type: 'bar', data: medianData },
      ],
    });

    window.addEventListener('resize', () => chart.resize());
  }
};

const initPieChart = (id, title, categories, data) => {
  const container = document.getElementById(id);
  if (container) {
    const chart = echarts.init(container);
    chart.setOption({
      backgroundColor: 'transparent',
      title: { text: title },
      tooltip: { trigger: 'item' },
      series: [
        {
          name: '数据量',
          type: 'pie',
          radius: '50%',
          data: categories.map((category, index) => ({
            value: data[index],
            name: category,
          })),
        },
      ],
    });

    window.addEventListener('resize', () => chart.resize());
  }
};

const store = useStore();
const name = computed(() => store.state.login.name);

const handleClick = () => {
  console.log("hello")
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
