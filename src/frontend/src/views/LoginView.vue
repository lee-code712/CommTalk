<template>
  <div class="login">
    <form @submit.prevent="login">
      <router-link class="logo-wrap" to="/">
        <img class="logo" src="@/assets/images/logo.png"/>
      </router-link>
      <div class="user-input">
        <label for="nickname">닉네임</label>
        <input type="text" id="nickname" placeholder="닉네임을 입력해 주세요." v-model="nickname" required>
      </div>
      <div class="user-input">
        <label for="password">비밀번호</label>
        <input type="password" id="password" placeholder="비밀번호를 입력해 주세요." v-model="password" required>
      </div>
      <div>
        <button type="submit" id="loginBtn">로그인</button>
      </div>
      <div class="ask-join-wrap">
        <div>아직 회원이 아니신가요?</div>
        <router-link class="join-wrap" to="/join">
          <div>회원가입</div>
        </router-link>
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
        .post("/api/auth/login", JSON.stringify(data), { headers: headers })
        .then((response) => {
          const token = response.data.token;
          
          localStorage.setItem("token", token);
          this.$router.push('/');
        })
        .catch((error) => {
          this.error = "로그인 실패: " + error.response.data;
          this.password = "";
          
          alert("로그인 실패: " + error.response.data.message);
        });
    }
  }
};
</script>

<style lang="scss">
	@import "@/assets/scss/pattern/pattern.scss";
	@import "@/assets/scss/login.scss";
</style>