<template>
  <div class="login-container">
    <div v-if="!token">
      <h2 class="form-title">用户登录</h2>
      <el-form :model="form" ref="formRef" label-width="120px">
        <el-form-item label="用户名" prop="username" required>
          <el-input v-model="form.username" />
        </el-form-item>

        <el-form-item label="密码" prop="password" required>
          <el-input
            v-model="form.password"
            type="password"
          />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div v-else>
      <strong>{{ '你好 ' + var_name }}</strong>
      <br/>
      <el-button @click="logout" type="danger" style="margin-top: 40px;">退出登录</el-button>
    </div>
    
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { ElMessage } from 'element-plus';
import { login } from '../../api/index.js'
import {useStore} from 'vuex'

const store = useStore()

const token = computed(()=>store.state.login.token)
const name = computed(()=>store.state.login.name)
const var_name = ref('')

const logout = () => {
  store.commit('logout')
  ElMessage.success('退出成功！')
}

const form = ref({
  username: '',
  password: '',
});

const submitForm = async () => {
  if (!form.value.username || !form.value.password) {
    ElMessage.error('请输入用户名和密码');
    return;
  }
  const username = form.value.username
  const password = form.value.password
  login({username, password}).then( response => {
    const data = response.data.data;
    store.commit('setItem', data)
    var_name.value = name.value
    ElMessage.success('登录成功！')
  })
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: auto;
}
.form-title {
  text-align: center;
  margin-bottom: 20px;
}
</style>
