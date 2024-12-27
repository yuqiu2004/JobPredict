<template>
  <div class="page-container">
    <el-container>
      <!-- Header -->
      <el-header class="header">
        <el-menu
          :default-active='activeIndex'
          class="header-menu"
          mode="horizontal"
          :ellipsis="false"
          @select="handleSelect"
          active-text-color="red"
        >
          <el-menu-item index="0">
            <p><strong>首页</strong></p>
          </el-menu-item>
          <el-menu-item index="3"><strong>我的</strong></el-menu-item>
        </el-menu>
      </el-header>

      <!-- Main Content -->
      <el-main class="main">
        <div class="main-content">
          <router-view />
        </div>

      </el-main>
    </el-container>
  </div>
</template>

<script lang="js" setup>
import { useRouter } from 'vue-router';
import { onMounted, ref, computed } from 'vue';
import { useStore } from 'vuex';

const router = useRouter();
const activeIndex = ref('0');
const store = useStore()
const token = computed(() => store.state.login.token)

onMounted( () => {
  router.push('/index');
})

const handleSelect = (index) => {
  switch (index) {
    case '0': router.push('/index'); break;
    case '1': router.push('/soldier'); break;
    case '2': {
      router.push('/upload');//TODO:方便开发先不拦截
      // if (token.value) router.push('/upload');
      // else {
      //   ElMessage.warning('warning: 请先登录')
      //   router.push('/login');
      // } 
      break;
    }
    case '3': router.push('/login'); break;
  }
}
</script>

<style scoped>
/* 根容器全屏显示 */
.page-container {
  height: 100vh; /* 保证页面高度占满整个视窗 */
  display: flex;
  flex-direction: column; /* 让子元素按列排列 */
}

.header {
  padding: 0 20px;
  display: flex;
  align-items: center;
  height: 30px; /* 固定头部高度 */
}

.header-menu {
  flex: 1;
}

.el-menu-item p {
  margin: 0;
  color: rgb(0, 0, 0);
}

.el-menu--horizontal > .el-menu-item:nth-child(1) {
  margin-right: auto;
}

/* Main内容部分，使用flex-grow让它填充剩余空间 */
.main {
  flex-grow: 1; /* 填充剩余空间 */
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
}

.main-content {
  width: 100%;
  height: 100%;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
