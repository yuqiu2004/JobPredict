<template>
  <div class="photo-wall">
    <el-row :gutter="20">
      <el-col v-for="(photo, index) in photos" :key="index" :span="6" class="photo-col">
        <div class="photo-container" @click="handlePhotoClick(index)">
          <el-image
            :src="photo.img"
            class="photo"
            fit="cover"
          />
          <p class="photo-name"><strong>{{ photo.name }}</strong></p> <!-- 照片下方的名字 -->
        </div>
      </el-col>
    </el-row>
    <div class="pagination">
      <el-pagination
      v-model:current-page="page"
      :page-size="pageSize"
      layout="total, prev, pager, next"
      :total="total"
      @current-change="handleCurrentChange"
      />
    </div>
    
  </div>
</template>

<script lang="js" setup>
import { ref, onMounted } from 'vue';
import { getSoldierList } from '../../api/index'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'; 

const router = useRouter()
const store = useStore()

const pageSize = ref(12)
const page = ref(1)
const total = ref(0)
// 照片列表（模拟数据，可以替换为实际的API请求）
const photos = ref([]);

onMounted(() => {
  updatePhotos()
})

const updatePhotos = () => {
  const params = {
    page: page.value,
    pageSize: pageSize.value
  }
  getSoldierList(params).then((res) => {
    const data = res.data.data
    photos.value = data.records
    total.value = data.total
  })
}

// 处理图片点击事件
const handlePhotoClick = (index) => {
  const clickedPhoto = photos.value[index];
  // 这里可以处理点击图片后的逻辑
  // console.log(clickedPhoto.id)
  store.commit('setSid', clickedPhoto.id)
  router.push('/detail')
};

const handleCurrentChange = (newPage) => {
  page.value = newPage
  updatePhotos()
};

</script>

<style scoped>
.photo-wall {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  gap: 20px;
  justify-content: center;
  /* background-image: url('https://picnew12.photophoto.cn/20180926/hongsechouduanpngsucai-30652653_1.jpg'); */
  background-image: linear-gradient(
    90deg,
    rgb(255, 255, 255),
    rgb(197, 230, 252)
  );
  background-size: 400%;
  animation: myanimation 10s infinite;
}

@keyframes myanimation {
  0%{
    background-position: 0% 50%;
  }
  50%{
    background-position: 100% 50%;
  }
  100%{
    background-position: 0% 50%;
  }
}

.photo-col {
  padding: 20px;
}

.photo-container {
  text-align: center;
  cursor: pointer;
  width: 200px;
}

.photo {
  width: 100%;
  height: 200px;
  object-fit: cover;
  border-radius: 8px;
  transition: transform 0.1s ease;
  margin: 1em;
}

.photo:hover {
  transform: scale(1.1); /* 鼠标悬停时轻微放大效果 */
}

.photo-name {
  margin-top: 8px;
  font-size: 24px;
  color: #000000;
}

.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  align-items: center;
}
</style>
