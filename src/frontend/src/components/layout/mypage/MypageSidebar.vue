<template>
    <div class="mypage-sidebar">
        <div class="logo-wrap">
            <a href="/">
            <img src="@/assets/images/logo.png"/>
            </a>
        </div>
        <ul>
          <li :class="{ 'active': $route.path === '/mypage/info' }">
              <router-link to="/mypage/info">개인정보 변경</router-link>
          </li>
          <li :class="{ 'active': $route.path === '/mypage/comment' }">
              <router-link to="/mypage/comment">내가 쓴 글 / 댓글 단 글</router-link>
          </li>
          <li :class="{ 'active': $route.path === '/mypage/like' }">
              <router-link to="/mypage/like">내 공감</router-link>
          </li>
          <li :class="{ 'active': $route.path === '/mypage/scrap' }">
              <router-link to="/mypage/scrap">내 스크랩</router-link>
          </li>
          <li style="display: none;" :class="{ 'active': $route.path === '/mypage/report' }">
              <router-link to="/mypage/report">내 신고</router-link>
          </li>
          <li style="display: none;">회원 탈퇴</li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: 'MypageSidebar',
    }
</script>

<style lang="scss">
@import "@/assets/scss/mypage/mypage-sidebar.scss";
</style>