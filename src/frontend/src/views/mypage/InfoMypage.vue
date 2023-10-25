<template>
  <div class="info-mypage">
    <MypageSidebar />

    <div class="admin-content-wrap">
      <MypageHeader />

      <div class="admin-content">
        <div class="admin-content-inner">
          <strong class="page-title">개인정보 변경</strong>

          <div class="info-box-wrap">
            <div class="info-box-content-wrap">
              <div class="sub-title">개인정보를 변경 시, 기존 비밀번호를 입력해 주세요.</div>

              <div class="info-box">
                <div class="info-box-inner">
                  <label>아이디</label>
                  <div class="form-control">
                    <input type="text" :placeholder="member.nickname" v-model="member.nickname" disabled />
                  </div>
                </div>
                <div class="info-box-inner">
                  <label>이름</label>
                  <div class="form-control">
                    <input type="text" :placeholder="member.username" v-model="member.username" />
                  </div>
                </div>
                <div class="info-box-inner">
                  <label>이메일</label>
                  <div class="form-control">
                    <input type="text" :placeholder="member.email" v-model="member.email" />
                  </div>
                </div>
                <div class="info-box-inner">
                  <label>연락처</label>
                  <div class="form-control">
                    <input type="number" :placeholder="member.phone" v-model="member.phone" />
                  </div>
                </div>
                <div class="info-box-inner">
                  <label>기존 비밀번호</label>
                  <div class="form-control">
                    <input type="password" placeholder="비밀번호" v-model="orgPassword" />
                  </div>
                </div>
                
                <div @click="togglePwd" class="change-pwd-wrap">
                  비밀번호 변경
                  <div class="angle-icon">
                    <img 
                      style="width: 16px; height: 16px;" 
                      :src="showChangePwd ? angleUpImg : angleDownImg"
                    />
                  </div>
                </div>
                
                <div class="info-box-inner" v-if="showChangePwd">
                  <label>새로운 비밀번호</label>
                  <div class="form-control">
                    <input type="password" placeholder="새로운 비밀번호" v-model="newPassword" />
                  </div>
                </div>
                <div class="info-box-inner" v-if="showChangePwd">
                  <label>비밀번호 확인</label>
                  <div class="form-control">
                    <input type="password" placeholder="비밀번호 확인" v-model="passwordCheck" />
                  </div>
                </div>
              </div>

              <button type="submit" @click="updateMember()">변경하기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

import MypageSidebar from '@/components/layout/mypage/MypageSidebar.vue';
import MypageHeader from '@/components/layout/mypage/MypageHeader.vue';

export default {
  name: 'InfoMypage',
  components: {
    MypageSidebar,
    MypageHeader,
  },
  data() {
    return {
      headers: [],
      link: '',
      member: {},
      orgPassword: '',
      newPassword: '',
      passwordCheck: '',
      nickname: '',
      username: '',
      email: '',
      phone: '',
      showChangePwd: false,
      angleUpImg: require('@/assets/images/fi-rr-angle-small-up.png'),
      angleDownImg: require('@/assets/images/fi-rr-angle-small-down.png'),
    };
  },
  created() {
    this.setupHeaders();
    this.getMember();
  },
  methods: {
    setupHeaders() { /* http 요청 헤더를 설정하고 엔드포인트에 대한 인증 토큰을 포함 */
      const token = localStorage.getItem('token');

      this.link = 'http://' + window.location.host;
      this.headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      };
    },
    getMember() { /* 회원 정보 가져오기 */
      axios
        .get(this.link + '/api/myPage/getMember', { headers: this.headers })
        .then((response) => {
          console.log(response.data);
          this.member = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateMember() { /* 회원 정보 변경 */
      const data  = {
        orgPassword: this.orgPassword,
        nickname: this.member.nickname,
        username: this.member.username,
        email: this.member.email,
        phone: this.member.phone,
      };

      if (this.newPassword) {
        data.newPassword = this.newPassword;
      }

      if (this.passwordCheck) {
        data.passwordCheck = this.passwordCheck;
      }

      console.log(data);

      axios
        .post(this.link + '/api/myPage/updateMember', data, { headers: this.headers })
        .then((response) => {
          console.log(response.data);

          const status = response.data.status;
          const message = response.data.message;

          if (!this.orgPassword) {
            alert("개인정보를 변경 시, 기존 비밀번호를 입력해 주세요.");
            return;
          }
          
          if (status == 'success') {
            alert("개인정보가 성공적으로 변경되었습니다.");
          } else if (status == 'error') {
            alert(message);
          }
          
          /* 현재 페이지를 리로드 */
          this.$router.go();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    togglePwd() { /* 비밀번호 변경 노출 */
      /* 비밀번호 변경 노출 상태를 전환 */
      this.showChangePwd = !this.showChangePwd;
    },
  }
}
</script>

<style lang="scss">
@import "@/assets/scss/mypage/info.scss";
</style>