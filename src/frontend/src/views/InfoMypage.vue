<template>
  <div class="info-mypage">
    <MypageSidebar/>

    <div class="mypage-content-wrap">
      <MypageHeader/>

      <div class="mypage-content">
          <h2>개인정보 변경</h2>
          <div class="info-box-wrap">
            <div class="info-box-content-wrap">
              <div class="sub-title">개인정보를 변경해주세요.</div>

              <div class="info-box">
                <div class="info-box-inner">
                  <label>아이디</label>
                  <div class="form-control">
                    <input type="text" placeholder="아이디" />
                  </div>
                </div>
                <div class="info-box-inner">
                  <label>비밀번호</label>
                  <div class="form-control">
                    <input type="password" placeholder="비밀번호" />
                  </div>
                </div>
                <div class="info-box-inner">
                  <label>비밀번호 확인</label>
                  <div class="form-control">
                    <input type="password" placeholder="비밀번호 확인" />
                  </div>
                </div>
                <div class="info-box-inner">
                  <label>이름</label>
                  <div class="form-control">
                    <input type="text" placeholder="이름" />
                  </div>
                </div>
                <div class="info-box-inner">
                  <label>닉네임</label>
                  <div class="form-control">
                    <input type="text" placeholder="닉네임" />
                  </div>
                </div>
              </div>

              <button>변경하기</button>
            </div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
  import MypageHeader from '@/components/layout/MypageHeader.vue'
  import MypageSidebar from '@/components/layout/MypageSidebar.vue'
  export default {
    name: 'InfoMypage',
    components: {
      MypageSidebar,
      MypageHeader,
    }
  }
</script>

<style lang="scss">
  @import "@/assets/scss/pattern.scss";
  @import "@/assets/scss/info.scss";
</style>