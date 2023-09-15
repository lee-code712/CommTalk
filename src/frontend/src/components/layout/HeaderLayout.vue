<template>
  <div class="header">
    <div class="header-inner">
      <div class="logo-search-wrap">
        <img style="width: 80px;" src="@/assets/images/logo.png" />

        <div className="search-wrap">
          <input type="search" placeholder="검색어를 입력하세요."/>
          <img style="width: 20px; height: 20px;" src="@/assets/images/fi-rr-search.png"/>
        </div>
      </div>

      <div class="gnb">
        <button type="button" class="login-btn">로그인</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: 'HeaderLayout',
}
</script>

<style lang="scss">
@import "@/assets/scss/header.scss";
</style>