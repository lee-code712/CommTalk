<template>
    <div class="mypage-sidebar">
        <div class="logo-wrap">
          <img src="@/assets/images/logo.png"/>
        </div>
        <ul>
            <li><router-link to="/mypage/info">개인정보 변경</router-link></li>
            <li><router-link to="/mypage/comment">내가 쓴 글 / 댓글 단 글</router-link></li>
            <li><router-link to="/mypage/like">내 공감</router-link></li>
            <li><router-link to="/mypage/board">게시판 관리</router-link></li>
            <li>신고 관리</li>
            <li>회원 탈퇴</li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: 'MypageSidebar',
    }
</script>

<style lang="scss">
  @import "@/assets/scss/mypage-sidebar.scss";
</style>