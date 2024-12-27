<template>
  <div class="home-container">
    <p>TEST INDEX PAGE</p>
    <button @click="handleClick">click here</button>
  </div>
</template>

<script lang="js" setup>
import { useStore } from 'vuex'
import { reactive, computed } from 'vue';

const store = useStore()
const name = computed(() => store.state.login.name)
// 定义点击按钮时触发的事件
const handleClick = () => {
  // alert('嗨喽!  ' + name.value);
  ElMessage.info('你好 '+ name.value);
  console.log(import.meta.env.VITE_BASE_URL)
  console.log(import.meta.env.DEV)
};
</script>

<style scoped>
/* 简单的样式设置 */
.home-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  width: 100%;
  height: 100%;
  background-color: #f5f5f5;
}

h1 {
  font-size: 2.5rem;
  margin-bottom: 20px;
}

p {
  font-size: 1.25rem;
  margin-bottom: 20px;
}

button {
  padding: 10px 20px;
  font-size: 1rem;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #358a67;
}
</style>
