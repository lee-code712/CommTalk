<template>
    <div class="info-mypage">
        <MypageSidebar/>

        <div class="admin-content-wrap">
            <MypageHeader/>

            <div class="admin-content">
                <div class="admin-content-inner">
                    <strong class="page-title">개인정보 변경</strong>

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
    </div>
</template>

<script>
import MypageSidebar from '@/components/layout/MypageSidebar.vue';
import MypageHeader from '@/components/layout/MypageHeader.vue';
    export default {
        name: 'ReportAdmin',
        components: {
            MypageSidebar,
            MypageHeader,
        },
    }
</script>

<style lang="scss">
.info-mypage {
    display: flex;
    .admin-content-wrap {
        width: calc(100% - 270px);
        .admin-content {
            padding: 38px 0px;
            height: 100vh;
            background-color: #FCFCFE;
            .admin-content-inner {
            margin: 0 auto;
            width: 1480px;

            .page-title {    
                color: #373f57;
                font-size: 36px;
                font-weight: bold;
                letter-spacing: -3px;
                line-height: 56px;
            }
            .info-box-wrap {
          width: 100%;
          .info-box-content-wrap {
              margin: 40px auto 0px auto;
              display: flex;
              flex-direction: column;
              padding: 20px;
              width: 500px;
              background-color: #FFF;
              border: 1px solid #F5F5F5;
              border-radius: 10px;
  
              .sub-title {
                padding-bottom: 20px;
                font-size: 14px;
                color: var(--gray80);
              }
            .info-box {
              display: flex;
              flex-direction: column;
              padding: 20px 0px;
              width: 500px;
                border-top: 1px solid var(--gray30);
                border-bottom: 1px solid var(--gray30);
              .info-box-inner {
                display: flex;
                align-items: center;
                height: 44px;
                font-size: 14px;
                color: #5C667B;
  
                label {
                  width: 124px;
                }
  
                .form-control {
                  display: flex;
                  justify-content: space-between;
                  align-items: center;
                  width: 100%;
                  height: calc(100% - 10px);
                  border-radius: 4px;
                  border: 1px solid var(--gray30);
                  background-color: #FFF;
  
                  input {
                    padding: 0px 10px;
                    width: 100%;
                    height: 100%;
                    border: none;
                    border-radius: 4px;
                  }
                }
              }
            }
            button {
              margin-top: 20px;
              padding: 8px 10px;
              border: 1px solid #F5F5F5;
              background-color: var(--gray10);
              border-radius: 4px;
              font-size: 14px;
  
              &:hover {
                background-color: var(--gray20);
                cursor: pointer;
              }
            }
          }
        }
            }
        }
    }
}
</style>