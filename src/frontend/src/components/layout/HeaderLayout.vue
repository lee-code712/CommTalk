<template>
  <div class="header">
    <div class="header-inner">
      <div class="logo-search-wrap">
<router-link to="/">
        <img style="width: 80px;" src="@/assets/images/logo.png" />
</router-link>
        <div className="search-wrap">
          <input type="search" placeholder="검색어를 입력하세요."/>
          <img style="width: 20px; height: 20px;" src="@/assets/images/fi-rr-search.png"/>
        </div>
      </div>

      <div class="gnb">
          <button type="button" class="login-btn" @click="logout">로그아웃</button>
        <router-link :to="'/login'" class="router-link-class">
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
            isExistToken: false
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
                console.log(response.data);
            })
            .catch(err => {
                console.log(err);
            })
        }
    }
}
</script>

<style lang="scss">
@import "@/assets/scss/header.scss";
</style>