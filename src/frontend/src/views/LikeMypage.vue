<template>
  <div class="like-mypage">
    <MypageSidebar />

    <div class="admin-content-wrap">
      <MypageHeader />

      <div class="admin-content">
        <div class="admin-content-inner">
          <strong class="page-title">공감</strong>
          <div class="tab-content">
            <div>
              <ul class="list-wrap">
                <li v-for="(likePost, index) in likePosts" :key="index">
                  <a :href="'/detail?postId=' + likePost.postId">
                    <div class="list-content">
                      <div class="title">{{ likePost.title }}</div>
                      <div class="content">{{ likePost.content }}</div>
                      <div class="detail-info-wrap">
                        <div class="active-wrap">
                          <div>댓글 {{ likePost.commentCnt }}</div>
                          <div>공감 {{ likePost.likeCnt }}</div>
                          <div>조회수 {{ likePost.viewCnt }}</div>
                        </div>

                        <div class="writer-date-wrap">
                          {{ likePost.createdAt }}
                        </div>
                      </div>
                    </div>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

import MypageSidebar from '@/components/layout/MypageSidebar.vue';
import MypageHeader from '@/components/layout/MypageHeader.vue';

export default {
  name: 'LikeMypage',
  components: {
    MypageSidebar,
    MypageHeader,
  },
  data() {
    return {
      headers: [],
      link: '',
      likePosts: [],
    };
  },
  created() {
    this.setupHeaders();
    this.getLike();
  },
  methods: {
    setupHeaders() { /* http 요청 헤더를 설정하고 엔드포인트에 대한 인증 토큰을 포함 */
      const token = localStorage.getItem('token');
      console.log(token);
      this.link = 'http://' + window.location.host;
      console.log(token);
      this.headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      };
    },
    getLike() { /* 내 공감 가져오기 */
    console.log(this.link);
    console.log(this.headers);
      axios
        .get(this.link + '/api/myPage/getPosts/liked', { headers: this.headers })
        .then((response) => {
          this.likePosts = response.data;
          console.log(this.likePosts);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style lang="scss">
@import "@/assets/scss/like-mypage.scss";
</style>