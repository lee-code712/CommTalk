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
        쪽지
        <div>|</div>
        마이페이지

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
$main_color: #4976EF;
$search-bkg: #f1f1f1;

  .header {
    margin-bottom: 30px;
    width: 100%;
    height: 90px;
    border-bottom: 1px solid #f1f1f1;

    .header-inner {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin: 0 auto;
      width: 1200px;
      height: 100%;

      .logo-search-wrap {
        display: flex;
        align-items: center;
        gap: 40px;

        .logo {
          color: $main_color;
          font-size: 24px;
        }

       .search-wrap {
          display: flex;
          justify-content: space-between;
          align-items:center;
          padding: 10px 16px;
          width: 540px;
          border: 2px solid transparent;
          background: linear-gradient(to right, white, white) padding-box,
          linear-gradient(to right, $main_color, #50A7C2) border-box;
          border-radius: 8px;
          input {
            width: 100%;
            border: transparent;
            font-size: 14px;
          }
        }
      }

      .gnb {
        font-size: 14px;
        color: #606060;
        display: grid;
        gap: 16px;
        grid-auto-flow: column dense;
        align-items: center;

        .login-btn {
          padding: 6px 20px;
          background-color: #FFF;
          border: 1px solid #ddd;
          border-radius: 50px;

          &:hover {
            background-color: #f1f1f1;
            cursor: pointer;
          }
        }
      }
    }
  }
</style>