<template>
  <div class="join">
    <form @submit.prevent="join">
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
      <div class="user-input">
        <label for="username">이름</label>
        <input type="text" id="username" placeholder="이름을 입력해 주세요." v-model="username" required>
      </div>
      <div class="user-input">
        <label for="email">이메일</label>
        <input type="email" id="email" placeholder="이메일을 입력해 주세요." v-model="email" required>
      </div>
      <div class="user-input">
        <label for="phone">전화번호</label>
        <input type="number" id="phone" placeholder="전화번호를 입력해 주세요." v-model="phone" required>
      </div>
      <div>
        <button type="submit" id="joinBtn">회원가입</button>
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
      username: "", // 추가: 이름 필드
      email: "",    // 추가: 이메일 필드
      phone: "",    // 추가: 전화번호 필드
      error: ""
    };
  },
  methods: {
    join() {
      const data = {
        nickname: this.nickname,
        password: this.password,
        username: this.username,
        email: this.email,
        phone: this.phone
      }

      const headers = {
        'Content-type': 'application/json; charset=UTF-8',
        'Accept': '*/*'
      }

      axios
        .post("/api/auth/join", JSON.stringify(data), { headers: headers })
        .then((response) => {
          console.log(response.data);
          
          this.$router.push('/login');
        })
        .catch((error) => {
          this.error = "회원가입 실패: " + error.response.data;
          this.password = "";
          
          alert("회원가입 실패: " + error.response.data.message);
        });
    }
  }
};
</script>

<style lang="scss">
    @import "@/assets/scss/pattern.scss";
    @import "@/assets/scss/join.scss";
</style>