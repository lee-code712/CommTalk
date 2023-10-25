<template>
  <div class="comment-mypage">
    <MypageSidebar />

    <div class="admin-content-wrap">
      <MypageHeader />

      <div class="admin-content">
        <div class="admin-content-inner">
          <strong class="page-title">내가 쓴 글 / 댓글 단 글</strong>

          <div class="tabs">
            <div
              v-for="(tab, index) in tabs"
              :key="index"
              @click="activeTab = tab"
              :class="{ 'active': activeTab === tab }"
              class="tab-container"
            >
              {{ tab }}
            </div>
          </div>

          <div class="tab-content">
            <div v-show="activeTab === '내가 쓴 글'">
              <ul class="list-wrap">
                <li v-for="(mycomment, index) in mycomments" :key="index">
                  <a :href="'/detail?postId=' + mycomment.postId">
                    <div class="list-content">
                      <div class="boardname-tit-wrap">
                        <div class="boardname">{{ mycomment.board.boardName }}</div>
                        <div class="title">{{ mycomment.title }}</div>
                      </div>
                      <div class="content">{{ mycomment.content }}</div>
                      <div class="detail-info-wrap">
                        <div class="active-wrap">
                          <div class="comment-box">
                            <img src="@/assets/images/fi-rr-comment.png" style="width: 12px; height: 12px;" />
                            댓글 {{ mycomment.commentCnt }}
                          </div>
                          <div class="like-box">
                            <img src="@/assets/images/fi-rr-thumbs-up.png" style="width: 14px; height: 14px;" />
                            공감 {{ mycomment.likes }}
                          </div>
                          <div class="view-box">
                            <img src="@/assets/images/fi-rr-eye.png" style="width: 14px; height: 14px;" />
                            조회수 {{ mycomment.views }}
                          </div>
                        </div>
                        <div class="writer-date-wrap">
                          <div class="date">{{ mycomment.createdAt }}</div>
                        </div>
                      </div>
                    </div>
                  </a>
                </li>
              </ul>
            </div>
            <div v-show="activeTab === '댓글 단 글'">
              <ul class="list-wrap">
                <li v-for="(commentPost, index) in commentPosts" :key="index">
                  <a :href="'/detail?postId=' + commentPost.postId">
                    <div class="list-content">
                      <div class="boardname-tit-wrap">
                        <div class="boardname">{{ commentPost.board.boardName }}</div>
                        <div class="title">{{ commentPost.title }}</div>
                      </div>
                      <div class="content">{{ commentPost.content }}</div>
                      <div class="detail-info-wrap">
                        <div class="active-wrap">
                          <div class="comment-box">
                            <img src="@/assets/images/fi-rr-comment.png" style="width: 12px; height: 12px;" />
                            댓글 {{ commentPost.commentCnt }}
                          </div>
                          <div class="like-box">
                            <img src="@/assets/images/fi-rr-thumbs-up.png" style="width: 14px; height: 14px;" />
                            공감 {{ commentPost.likes }}
                          </div>
                          <div class="view-box">
                            <img src="@/assets/images/fi-rr-eye.png" style="width: 14px; height: 14px;" />
                            조회수 {{ commentPost.views }}
                          </div>
                        </div>
                        <div class="writer-date-wrap">
                          {{ commentPost.createdAt }}
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

import MypageSidebar from '@/components/layout/mypage/MypageSidebar.vue';
import MypageHeader from '@/components/layout/mypage/MypageHeader.vue';

export default {
  name: 'CommentMypage',
  components: {
    MypageSidebar,
    MypageHeader,
  },
  data() {
    return {
      headers: [],
      link: '',
      tabs: ['내가 쓴 글', '댓글 단 글'],
      activeTab: '내가 쓴 글',
      mycomments: [],
      commentPosts: [],
    };
  },
  created() {
    this.setupHeaders();
    this.getPost();
    this.getComment();
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
    getPost() { /* 내가 쓴 글 */
      axios
        .get(this.link + '/api/myPage/getPosts/post', { headers: this.headers })
        .then((response) => {
          console.log('내가 쓴 글');
          this.mycomments = response.data;
          console.log(this.mycomments);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getComment() { /* 댓글 단 글 */
      axios
        .get(this.link + '/api/myPage/getPosts/comment', { headers: this.headers })
        .then((response) => {
          console.log('내가 쓴 댓글');
          this.commentPosts = response.data;
          console.log(this.commentPosts);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style lang="scss">
@import "@/assets/scss/mypage/comment.scss";
</style>