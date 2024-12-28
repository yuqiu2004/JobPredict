<template>
    <div class="register-container">
      <div class="register-box">
        <h2 class="form-title">用户注册</h2>
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
  
          <el-form-item label="确认密码" prop="confirmPassword" required>
            <el-input
              v-model="form.confirmPassword"
              type="password"
              placeholder="请再次输入密码"
            />
          </el-form-item>
  
          <el-form-item>
            <el-button
              type="primary"
              @click="submitForm"
              class="register-btn"
            >
              注册
            </el-button>
            <el-button
              type="text"
              class="login-link"
              @click="navigateToLogin"
            >
              去登录
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { ElMessage } from 'element-plus';
  import { register } from '../../api/index.js';
  import { useRouter } from 'vue-router';
  
  const router = useRouter();
  
  const form = ref({
    username: '',
    password: '',
    confirmPassword: '',
  });
  
  const submitForm = async () => {
    if (!form.value.username || !form.value.password || !form.value.confirmPassword) {
      ElMessage.error('请完整填写表单');
      return;
    }
  
    if (form.value.password !== form.value.confirmPassword) {
      ElMessage.error('两次输入的密码不一致');
      return;
    }
  
    const { username, password } = form.value;
    try {
      const response = await register({ username, password });
      if (response.data.success) {
        ElMessage.success('注册成功，前往登录');
        router.push('/login');
      }
    } catch (error) {
      ElMessage.error(error.response?.data?.message || '注册失败');
    }
  };
  
  const navigateToLogin = () => {
    router.push('/login');
  };
  </script>
  
  <style scoped>
  .register-container {
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
  
  .register-box {
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
  
  .register-btn {
    background: linear-gradient(135deg, #42a5f5, #2196f3);
    color: #fff;
    margin-bottom: 10px;
  }
  
  .login-link {
    color: #42a5f5;
    text-decoration: underline;
    padding: 0;
    font-size: 14px;
  }
  
  /* 响应式调整 */
  @media (max-width: 768px) {
    .register-box {
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
  