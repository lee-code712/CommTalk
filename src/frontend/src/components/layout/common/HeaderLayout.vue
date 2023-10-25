<template>
  <div class="header">
    <div class="header-inner">
      <div class="logo-search-wrap">
        <router-link to="/">
          <img style="width: 80px;" src="@/assets/images/logo.png" />
        </router-link>
        <div class="search-wrap">
          <input type="search" placeholder="검색어를 입력하세요." v-model="commonKeyword" />
            <a :href="'/list?commonKeyword=' + commonKeyword">
              <img style="width: 20px; height: 20px;" src="@/assets/images/fi-rr-search.png" />
            </a>
        </div>
      </div>

      <div class="gnb">
        <!-- 사용자가 로그인 상태인 경우 -->
        <button v-if="isExistToken" type="button" class="login-btn" @click="logout">로그아웃</button>
        
        <!-- 사용자가 로그아웃 상태인 경우 -->
        <router-link v-else :to="'/login'" class="router-link-class">
          <button type="button" class="login-btn">로그인</button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'HeaderLayout',
  data() {
    return {
      isExistToken: false,
      commonKeyword: ''
    }
  },
  methods: {
    logout() {
      var link = "http://" + window.location.host;
      axios.get(link + "/api/auth/logout", null)
      .then(response => {
        console.log("로그아웃");
        localStorage.clear();
        this.isExistToken = false;

        this.$router.go();

        console.log(response.data);
      })
      .catch(err => {
        console.log(err);
      })
    },
    checkLoginStatus() {
      if (localStorage.getItem('token')) {
        this.isExistToken = true;
      } else {
        this.isExistToken = false;
      }
    }
  },
  mounted() {
    // 컴포넌트가 마운트될 때 로그인 상태 확인
    this.checkLoginStatus();
  }
}
</script>

<style lang="scss">
@import "@/assets/scss/layout/header.scss";
</style>