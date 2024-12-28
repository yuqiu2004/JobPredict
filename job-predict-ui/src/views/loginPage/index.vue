<template>
  <div class="login-container">
    <div class="login-box" v-if="!token">
      <h2 class="form-title">用户登录</h2>
      <el-form :model="form" ref="formRef" label-width="100px">
        <el-form-item label="用户名" prop="username" required>
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>

        <el-form-item label="密码" prop="password" required>
          <el-input
            v-model="form.password"
            type="password"
            placeholder="请输入密码"
          />
        </el-form-item>

        <el-form-item>
          <el-button
            type="primary"
            @click="submitForm"
            class="login-btn"
          >
            登录
          </el-button>
          <el-button
            type="text"
            class="register-link"
            @click="navigateToRegister"
          >
            去注册
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <div v-else class="logout-box">
      <strong>{{ '你好，' + var_name }}</strong>
      <br />
      <el-button
        @click="logout"
        type="danger"
        class="logout-btn"
      >
        退出登录
      </el-button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { ElMessage } from 'element-plus';
import { login } from '../../api/index.js';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const store = useStore();
const router = useRouter();

const token = computed(() => store.state.login.token);
const name = computed(() => store.state.login.name);
const var_name = ref('');

const logout = () => {
  store.commit('logout');
  ElMessage.success('退出成功！');
};

const form = ref({
  username: '',
  password: '',
});

const submitForm = async () => {
  if (!form.value.username || !form.value.password) {
    ElMessage.error('请输入用户名和密码');
    return;
  }
  const username = form.value.username;
  const password = form.value.password;
  login({ username, password }).then((response) => {
    const data = response.data.data;
    store.commit('setItem', data);
    var_name.value = name.value;
    ElMessage.success('登录成功！');
  });
};

const navigateToRegister = () => {
  router.push('/register');
};
</script>

<style scoped>
.login-container {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  width: 100vw;
  background: linear-gradient(135deg, #74ebd5, #acb6e5);
  background-size: cover;
  padding: 20px;
  box-sizing: border-box;
  overflow: hidden;
}

.login-box,
.logout-box {
  width: 100%;
  max-width: 400px;
  background: #fff;
  padding: 30px 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.form-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #333;
}

.el-input {
  width: 80%;
  border-radius: 5px;
}

.el-button {
  width: 80%;
  border-radius: 5px;
}

.login-btn {
  background: linear-gradient(135deg, #42a5f5, #2196f3);
  color: #fff;
  margin-bottom: 10px;
}

.register-link {
  color: #42a5f5;
  text-decoration: underline;
  padding: 0;
  font-size: 14px;
}

.logout-box {
  text-align: center;
  color: #333;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .login-box,
  .logout-box {
    max-width: 90%;
    padding: 20px 15px;
  }

  .form-title {
    font-size: 20px;
  }

  .el-button {
    font-size: 14px;
  }
}
</style>
