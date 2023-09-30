<template>
  <div class="side-bar">
    <div class="login-wrap" v-if="isExistToken">
        <div class="login-desc">####님 환영합니다!</div>
      <router-link :to="'/mypage/info'" class="router-link-class">
        <button type="button" class="login-button">마이페이지로 이동</button>
      </router-link>
    </div>
    
    <div class="login-wrap" v-else>
      <div class="login-desc">로그인 후 이용해주세요.</div>
      <router-link :to="'/login'" class="router-link-class">
        <button type="button" class="login-button">커톡커톡 로그인</button>
      </router-link>
    </div>

    <div class="popular-posts">
      <h3>실시간 인기 글</h3>
      <ul>
        <li v-for="(popularPost, index) in popularPosts" :key="index">
		<router-link :to="'/detail?postId=' + popularPost.postId" class="router-link-class">
        <div :class="['num', { 'on': index === 0 }]">
          {{ index + 1 }}
        </div>

          <div class="popular-posts-info">
            <div class="title-comment-wrap">
              <div class="title">{{ popularPost.title }}</div> 
              <div class="comment">[{{ popularPost.commentCnt }}]</div>
            </div>
            <div class="view-like-wrap">
              {{ popularPost.board.boardName }} 
              <div class="view-wrap"><img src="@/assets/images/fi-rr-eye.png"/> {{ popularPost.views }}</div>
              <div class="like-wrap"><img src="@/assets/images/fi-rr-thumbs-up.png"/> {{ popularPost.likes }}</div>
            </div>
          </div>
         </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
  export default {
    name: 'RightContent',
    data() {
		return {
			popularPosts: [],
			isExistToken: false
		}
	},
    created() {
    this.getPopularPosts();
  },
  mounted() {
    // 컴포넌트가 마운트될 때 로그인 상태 확인
    this.checkLoginStatus();
  },
  methods: {
    checkLoginStatus() {
      if (localStorage.getItem('token')) {
        this.isExistToken = true;
      } else {
        this.isExistToken = false;
      }
    },
    getPopularPosts() {
      const token = localStorage.getItem('token');
      var link = 'http://' + window.location.host;
      var headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      };

      axios
        .get(link + '/api/main/getPopularPosts', { headers: headers })
        .then(response => {
          this.popularPosts = response.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
  },
  }
</script>

<style scoped lang="scss">
@import "@/assets/scss/pattern.scss";
@import "@/assets/scss/right-content.scss";
</style>