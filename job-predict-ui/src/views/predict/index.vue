<template>
  <div class="predict-container">
    <el-card class="predict-card" shadow="hover">
      <h2 class="form-title">Prediction System</h2>
      <el-form :model="form" ref="formRef" label-width="150px" class="predict-form">
        <div class="form-row">
          <el-form-item label="Qualification" required>
            <el-input
              v-model="form.qualification"
              placeholder="Enter qualification"
            />
          </el-form-item>
          <el-form-item label="Salary" required>
            <el-input
              v-model="form.salary"
              placeholder="Enter salary"
            />
          </el-form-item>
        </div>
        <div class="form-row">
          <el-form-item label="Company Size" required>
            <el-input
              v-model="form.company_size"
              placeholder="Enter company size"
            />
          </el-form-item>
          <el-form-item label="Preference" required>
            <el-input
              v-model="form.preference"
              placeholder="Enter preference"
            />
          </el-form-item>
        </div>
        <el-form-item label="Skills" required>
          <el-input
            v-model="form.skills"
            type="textarea"
            placeholder="Enter required skills"
            rows="6"
          />
        </el-form-item>
        <el-form-item label="Responsibilities" required>
          <el-input
            v-model="form.responsibilities"
            type="textarea"
            placeholder="Enter responsibilities"
            rows="6"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">Predict</el-button>
        </el-form-item>
        <div v-if="predictionResult" class="result">
          <p><strong>Prediction Result:</strong></p>
          <p>{{ predictionResult }}</p>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import { predict } from '../../api/index.js';

const form = ref({
  qualification: '',
  salary: '',
  company_size: '',
  preference: '',
  skills: '',
  responsibilities: '',
});

const predictionResult = ref('');

const submitForm = async () => {
  const { qualification, salary, company_size, preference, skills, responsibilities } = form.value;
  if (!qualification || !salary || !company_size || !preference || !skills || !responsibilities) {
    ElMessage.error('Please fill in all required fields.');
    return;
  }
  try {
    const response = await predict(form.value);
    predictionResult.value = response.data.result || 'No result returned.';
    ElMessage.success('Prediction successful!');
  } catch (error) {
    ElMessage.error('Prediction failed. Please try again.');
  }
};
</script>

<style scoped>
.predict-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-image: url('../../assets/codebak.jpg');
  background-size: cover;
  padding: 20px;
  width: 100%;
}

.predict-card {
  width: 70%;
  max-width: 900px;
}

.form-title {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #333;
}

.predict-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-row {
  display: flex;
  gap: 20px;
}

.form-row > .el-form-item {
  flex: 1;
}

.el-input[type="textarea"] {
  resize: none;
}

.result {
  margin-top: 20px;
  padding: 10px;
  background: #e6f7ff;
  border: 1px solid #91d5ff;
  border-radius: 5px;
}
</style>
