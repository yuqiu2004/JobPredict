<template>
  <div class="container">
    <div class="nav-back">
      <el-button type="primary" @click="clickBack">返回</el-button>
    </div>
    <div class="soldier-detail">
      <el-card style="height: 100%; width: 100%">
        <img :src="soldier.img" alt="士兵图片" v-if="soldier.img" style="height: 160px;"/>
        <h3>姓名: {{ soldier.name }}</h3>
        <p><strong>性别:</strong> {{ soldier.gender === 1 ? '男' : '女' }}</p>
        <p><strong>出生日期:</strong> {{ soldier.birth || '未提供' }}</p>
        <p><strong>籍贯:</strong> {{ soldier.birthPlace || '未提供' }}</p>
        <p><strong>转业去向:</strong>: {{ soldier.resettlement || '未提供' }}</p>
        <p><strong>职务:</strong> {{ soldier.militaryPositions || '未提供' }}</p>
        <p v-html="content"></p>
      </el-card>
    </div>
  </div>
</template>

<script lang="js" setup>
import { useStore } from 'vuex'
import { reactive, computed, onMounted, ref } from 'vue';
import { getSoldierById } from '../../api/index'
import { useRouter } from 'vue-router';
import MarkdownIt from 'markdown-it';

const router = useRouter();
const store = useStore();
const sid = computed(() => store.state.detail.sid);
const soldier = reactive({}); // 创建响应式对象
const md = new MarkdownIt()
const content = ref('')

onMounted(() => {
  getSoldierById(sid.value).then((res) => {
    Object.assign(soldier, res.data.data); // 更新 soldier 对象
    content.value = md.render(soldier.experience)
  });
});

const clickBack = () => {
  router.push('/soldier');
}
</script>

<style>
.container {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  padding: 20px;
}

.nav-back {
  margin-bottom: 10px;
}

.soldier-detail {
  height: 100%;
  width: 100%;
  margin: 0 auto; /* 居中 */
}
</style>
