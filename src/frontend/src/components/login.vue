<template>
  <div>
    <h1>Login</h1>
    <form @submit.prevent="login">
      <div>
        <label for="nickname">Username:</label>
        <input type="text" id="nickname" v-model="nickname" required>
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <div>
        <button type="submit">Login</button>
      </div>
    </form>
    <div v-if="error" class="error">{{ error }}</div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      nickname: "",
      password: "",
      error: ""
    };
  },
  methods: {
    login() {
      const data = {
        nickname: this.nickname,
        password: this.password
      }

      const headers = {
        'Content-type': 'application/json; charset=UTF-8',
        'Accept': '*/*'
      }

      axios
        .post("/api/auth/login", JSON.stringify(data), { headers : headers })
        .then((response) => {
          console.log(response.data)
          // 로그인 성공 시 토큰 또는 세션 저장
          const token = response.data.token; // 서버에서 전달된 토큰
          // 토큰을 저장하는 로직 (로컬 스토리지, 쿠키 등)
          localStorage.setItem("token", token);
          // 로그인 후 리다이렉트 또는 다음 단계 수행
          // 예: this.$router.push("/dashboard");
          this.$router.push('/');
        })
        .catch((error) => {
          // 로그인 실패 시 에러 메시지 표시
          this.error = "로그인 실패: " + error.response.data;
        });
    }
  }
};
</script>

<style>
/* 스타일링을 원하는 대로 추가하세요. */
</style>