<template>
    <div class="mypage-sidebar">
        <div class="logo-wrap">
          <img src="@/assets/images/logo.png"/>
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
        </ul>
    </div>
</template>

<script>
    export default {
        name: 'MypageSidebar',
    }
</script>

<style lang="scss">
.mypage-sidebar {
    width: 270px;
    height: 100vh;
    border-right: 1px solid #f1f1f1;

    .logo-wrap {
      display: flex;
      align-items: center;
      margin: 0 auto;
      padding: 0px 25px;
      height: 80px;
      border-bottom: 1px solid #f1f1f1;
      img {
        width: 70px;
      }
    }
    ul {
      margin: 0 auto;
      width: 240px;
      list-style: none;

      li {
        display: flex;
        align-items: center;
        margin-top: 8px;
        padding: 0px 10px;
        height: 44px;
        border-radius: 4px;
        font-size: 14px;
        color: #5C667B;
        &:hover {
          background-color: #F4F6FA;
          cursor: pointer;
        }
        &.active {
          background-color: #F4F6FA;
        }
        .router-link-active {
          color: var(--blue90);
          font-weight: bold;
        }

        a {
            width: 100%;
            text-decoration: none;
            color: #5C667B;
        }
      }
    }
  }
</style>