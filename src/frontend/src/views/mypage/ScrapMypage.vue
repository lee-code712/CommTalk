<template>
  <div class="scrap-mypage">
    <MypageSidebar />
    <div class="admin-content-wrap">
      <MypageHeader />
      <div class="admin-content">
        <div class="admin-content-inner">
          <strong class="page-title">내 스크랩</strong>
          <div class="tab-content">
            <div>
              <ul class="list-wrap">
                <li v-for="(scrapPost, index) in scrapPosts" :key="index">
                  <a :href="'/detail?postId=' + scrapPost.postId">
                    <div class="list-content">
                      <div class="boardname-tit-wrap">
                        <div class="boardname">{{ scrapPost.board.boardName }}</div>
                        <div class="title">{{ scrapPost.title }}</div>
                      </div>
                      <div class="content">{{ scrapPost.content }}</div>
                      <div class="detail-info-wrap">
                        <div class="active-wrap">
                          <div class="comment-box">
                            <img src="@/assets/images/fi-rr-comment.png" style="width: 12px; height: 12px;" />
                              댓글 {{ scrapPost.commentCnt }}
                            </div>
                            <div class="like-box">
                              <img src="@/assets/images/fi-rr-thumbs-up.png" style="width: 14px; height: 14px;" />
                              공감 {{ scrapPost.likes }}
                            </div>
                            <div class="view-box">
                              <img src="@/assets/images/fi-rr-eye.png" style="width: 14px; height: 14px;" />
                              조회수 {{ scrapPost.views }}
                            </div>
                        </div>
                        <div class="writer-date-wrap">{{ scrapPost.createdAt }}</div>
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
import MypageSidebar from '@/components/layout/mypage/MypageSidebar.vue';
import MypageHeader from '@/components/layout/mypage/MypageHeader.vue';

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
      scrapPosts: [],
    };
  },
  created() {
    this.setupHeaders();
    this.getScrap();
  },
  methods: {
    setupHeaders() { /* http 요청 헤더를 설정하고 엔드포인트에 대한 인증 토큰을 포함 */
      const token = localStorage.getItem('token');
      console.log(token);
      this.link = 'http://' + window.location.host;
      this.headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      };
    },
    getScrap() { /* 내 스크랩 가져오기 */
     console.log(this.link);
      console.log(this.headers);
      axios
        .get(this.link + '/api/myPage/getPosts/scraped', { headers: this.headers })
        .then((response) => {
          this.scrapPosts = response.data;
          console.log(this.scrapPosts);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style lang="scss">
@import "@/assets/scss/mypage/scrap.scss";
</style>