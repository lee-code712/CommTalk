<template>
  <div class="side-bar">
    <div class="login-wrap" v-if="isExistToken">
        <div class="login-top">
            <div class="login-desc">
              <img src="@/assets/images/loginProfile.png" style="width: 64px; height: 64px;" />  
              <div>{{ user.nickname }}님</div>
            </div>
            
            
        <button type="button" class="logout-btn" @click="logout">로그아웃</button>
      </div>
      <div class="mypage-btn-wrap">
          <ul>
              <li><a href="mypage/comment">내 댓글</a></li>
              <li><a href="mypage/like">내 공감</a></li>
              <li><a href="mypage/scrap">내 스크랩</a></li>
          </ul>
      </div>
    </div>
    
    <div class="login-wrap" v-else>
        <div class="login-top">
      <div class="login-desc">로그인 후 이용해주세요.</div>
      </div>
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
			isExistToken: false,
			user: []
		}
	},
    created() {
    this.getPopularPosts();
    this.getMember();
  },
  mounted() {
    // 컴포넌트가 마운트될 때 로그인 상태 확인
    this.checkLoginStatus();
  },
  methods: {
      logout() {
      var link = "http://" + window.location.host;
      axios.get(link + "/api/auth/logout", null)
      .then(response => {
        console.log("로그아웃");
        localStorage.clear();
        this.isExistToken = false;

        this.$router.go();

        console.log(response.data);
      })
      .catch(err => {
        console.log(err);
      })
    },
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
    getMember() {
        const token = localStorage.getItem('token');
      var link = 'http://' + window.location.host;
      var headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      };
      
        axios
        .get(link + '/api/main/getMember', { headers: headers })
        .then(response => {
          this.user = response.data;
          console.log(this.user);
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  }
</script>

<style scoped lang="scss">
@import "@/assets/scss/pattern.scss";
@import "@/assets/scss/right-content.scss";
</style>