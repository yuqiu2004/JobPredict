<template>
  <div class="upload-container">
    <h2 class="form-title">人物信息上传</h2>
    <el-form :model="form" ref="formRef" label-width="200px">
      <el-form-item label="名称" prop="name" required>
        <el-input v-model="form.name" style="width: auto;"/>
      </el-form-item>

      <el-form-item label="性别" prop="gender" required>
        <el-select v-model="form.gender" style="width: 90px;">
          <el-option label="男" :value="1" />
          <el-option label="女" :value="0" />
        </el-select>
      </el-form-item>

      <el-form-item label="出生日期" prop="birth">
        <el-date-picker
          v-model="form.birth"
          type="date"
          placeholder="选择日期"
          style="width: auto"
          value-format="YYYY-MM-DD"
        />
      </el-form-item>
      <el-form-item label="籍贯" prop="birthPlace">
        <el-input v-model="form.birthPlace" style="width: auto;"/>
      </el-form-item>
      <el-form-item label="转业去向" prop="resettlement">
        <el-input v-model="form.resettlement" style="width: auto;"/>
      </el-form-item>

      <el-form-item label="上传图片" prop="img" required style="height: 200px;">
        <el-upload
          class="avatar-uploader"
          :action="API_URLS.UPLOAD"
          :headers="uploadHeaders"
          :show-file-list="false"
          :before-upload="beforeAvatarUpload"
          :on-change="handleImageChange"
          :on-success="handleUploadSuccess"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>

      <el-form-item label="职务" prop="militaryPositions">
        <el-input v-model="form.militaryPositions" style="width: auto;" />
      </el-form-item>

      <!-- <el-form-item label="经历" prop="experience" style="height: 200px;">
        <el-input type="textarea" v-model="form.experience" resize="none" style="width: 400px;" :rows="8" />
      </el-form-item> -->
      <!-- 使用富文本编辑器 -->
      <el-form-item label="经历" prop="experience" style="width: 90%;">
        <div style="border: 1px solid #ccc">
          <Toolbar
            style="border-bottom: 1px solid #ccc"
            :editor="editorRef"
            :defaultConfig="toolbarConfig"
            :mode="mode"
          />
          <Editor
            style="height: 500px; overflow-y: hidden;"
            v-model="form.experience"
            :defaultConfig="editorConfig"
            :mode="mode"
            @onCreated="handleCreated"
          />
        </div>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { Plus } from '@element-plus/icons-vue';
import { API_URLS } from '../../config/config'
import { useStore } from 'vuex'
import { computed, onBeforeUnmount, shallowRef } from 'vue';
import { uploadSoldierMsg } from '../../api/index'
import '@wangeditor/editor/dist/css/style.css' // 引入 css
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

const store = useStore();
const token = computed(() => store.state.login.token)
const editorRef = shallowRef()
// 内容 HTML
const toolbarConfig = {}
const editorConfig = { placeholder: '请输入内容...' }
const handleCreated = (editor) => {
    editorRef.value = editor // 记录 editor 实例，重要！
  }

const imageUrl = ref('');
const form = ref({
  name: '',
  gender: null,
  birth: null,
  birthPlace: null,
  resettlement: null,
  img: null,
  militaryPositions: '',
  experience: '',
});

// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
      const editor = editorRef.value
      if (editor == null) return
      editor.destroy()
})

const uploadHeaders = {
  'token': token.value,
}

const beforeAvatarUpload = (rawFile) => {
  console.log(API_URLS.UPLOAD)
  // if (rawFile.type !== 'image/jpeg') {
  //   // ElMessage.error('头像图片必须为 JPG 格式！');
  //   return false;
  // } else if (rawFile.size / 1024 / 1024 > 20) { // 修改为 20MB
  //   // ElMessage.error('头像图片大小不能超过 20MB！');
  //   return false;
  // }
  return true;
};

const handleUploadSuccess = (res) => {
  form.value.img = res.data;
  console.log(res.data)
}

const handleImageChange = (file) => {
  const reader = new FileReader();
  reader.onload = (e) => {
    imageUrl.value = e.target.result; // 读取文件内容
  };
  reader.readAsDataURL(file.raw);
};

const submitForm = async () => {
  if (!form.value.name || form.value.gender === null || !form.value.img) {
    ElMessage.error('请填写所有必填字段并上传图片');
    return;
  }
  uploadSoldierMsg(form.value).then( res => {
    imageUrl.value = ''
    form.value.img = ''
    ElMessage.success('上传成功！')
  })
};
</script>

<style scoped>
.upload-container {
  width: 100%;
  height: 100%;
  margin: auto;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.form-title {
  text-align: center;
  margin-left: 180px;
}
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.avatar-uploader .el-upload {
  border: 3px;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
