<template>
  <div class="list-view">
    <HeaderLayout />
    <SubHeader />

    <div class="full-container">
      <div class="sub-top-wrap">
        <strong class="board-name">{{ boardName }}</strong>
        <div class="search-wrap">
          <input type="search" v-model="keyword" placeholder="내용을 입력해주세요." />
          <img src="@/assets/images/fi-rr-search.png" @click="fetchPosts" />
        </div>
      </div>

      <div class="list-wrap">
        <ul>
          <li class="list" v-for="board in boards" :key="board.postId">
            <a :href="'/detail?postId=' + board.postId">
            <div class="list-title-wrap">
                <div class="boardname-title-wrap">
                    <div v-if="isAllList" class="board-name">{{ board.board.boardName }}</div>
                <div class="title">{{ board.title }}</div>
              
              </div>
              <img :src="require(`@/assets/images/${board.imgName}.png`)" @click="changeImg(board)" />
            </div>

            <div class="list-content">{{ board.content }}</div>

            <div class="list-detail-info">
              <div class="comment-like-wrap">
                <div class="comment-box">
                  <img src="@/assets/images/fi-rr-comment.png" style="width: 12px; height: 12px;" />
                  댓글 {{ board.commentCnt }}
                </div>
                <div class="like-box">
                  <img src="@/assets/images/fi-rr-thumbs-up.png" style="width: 14px; height: 14px;" />
                  공감 {{ board.likes }}
                </div>
                <div class="view-box">
                  <img src="@/assets/images/fi-rr-eye.png" style="width: 14px; height: 14px;" />
                  조회수 {{ board.views }}
                </div>
              </div>

              <div class="writer-date-wrap">
                <div class="writer">{{ board.author.nickname }}</div>
                <div class="date">{{ board.createdAt }}</div>
              </div>
            </div>
            </a>
          </li>
        </ul>
      </div>

      <div class="paging-wrap">
        <div class="paging-inner">
          <div class="prev-btn" @click="loadPage('previous')" :disabled="pageNumber === 1">이전</div>
          <div class="paging-num-wrap">
            <div v-for="page in totalPages" :key="page" :class="{ 'on': page === pageNumber }" @click="gotoPage(page)">{{ page }}</div>
          </div>
          <div class="next-btn" @click="loadPage('next')" :disabled="pageNumber === totalPages">다음</div>
        </div>
      </div>

      <div class="btn-wrap">
        <router-link :to="'/edit?boardId=' + boardId" class="router-link-class">
          <button class="write-btn">글쓰기</button>
        </router-link>
      </div>
    </div>
    <FooterLayout />
  </div>
</template>

<script>
import axios from 'axios';
import HeaderLayout from '@/components/layout/HeaderLayout.vue';
import SubHeader from '@/components/layout/SubHeader.vue';
import FooterLayout from '@/components/layout/FooterLayout.vue';

export default {
  name: 'ListView',
  components: {
    HeaderLayout,
    SubHeader,
    FooterLayout,
  },
  data() {
    return {
      boards: [],
      boardName: '',
      pageNumber: 1,
      pageSize: 10, // 페이지당 게시글 수
      totalPages: '', // 전체 페이지 수
      isAllList: false
    };
  },
  created() {
    this.setupHeaders();
    this.fetchData();
    this.getBoardName();
  },
  methods: {
    setupHeaders() {
      const token = localStorage.getItem('token');
      this.link = 'http://' + window.location.host;
      this.headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      };
    },
    changeImg(board) {
        event.preventDefault();
      if (board.scraped === false) {
        board.imgName = 'fi-sr-bookmark';
        board.scraped = true;
      } else {
        board.imgName = 'fi-rr-bookmark';
        board.scraped = false;
      }
    },
    loadPage(action) {
      if (action === 'previous' && this.pageNumber > 1) {
        this.pageNumber--;
      } else if (action === 'next' && this.pageNumber < this.totalPages) {
        this.pageNumber++;
      }
      this.fetchData();
    },
    gotoPage(page) {
      if (page >= 1 && page <= this.totalPages) {
        this.pageNumber = page;
        this.fetchData();
      }
    },
    fetchData() {
      const boardId = this.$route.query.boardId;
      const commonKeyword = this.$route.query.commonKeyword;
      
      const data = {
        page: this.pageNumber,
        size: this.pageSize,
      };
      
      var url = "";
      if (commonKeyword) {
          console.log(commonKeyword);
          url = "/api/common/getPosts/" + commonKeyword;
          this.boardName = "전체";
          this.isAllList = true;
      } 
      
      if (boardId) {
          url = "/api/post/getPostsByBoard/" + boardId;
      }

      axios
        .get(url, data, { headers: this.headers })
        .then(response => {
            console.log("@@@@");
            console.log(response.data.posts);
          this.boards = response.data.posts.map(post => {
            if (post.scraped === false) {
              post.imgName = 'fi-rr-bookmark';
            } else {
              post.imgName = 'fi-sr-bookmark';
            }
            return post;
          });

          this.totalPages = response.data.totalPages;
        })
        .catch(err => {
          console.error(err);
        });
    },
    getBoardName() {
      const boardId = this.$route.query.boardId;
      axios
        .get(`/api/post/getBoard/${boardId}`, { headers: this.headers })
        .then(response => {
          this.boardName = response.data.boardName;
        })
        .catch(err => {
          console.error(err);
        });
    },
    fetchPosts() {
      if (!this.keyword) return;
//         const boardId = this.$route.query.boardId;
       const data = {
        page: this.pageNumber,
        size: this.pageSize,
      };

      axios
//        .get(`/api/common/getPosts/${boardId}/${this.keyword}`, data, { headers: this.headers })
        .get(`/api/common/getPosts/${this.keyword}`, data, { headers: this.headers })
        .then(response => {
          this.boards = response.data.posts.map(post => {
            if (post.scraped === false) {
              post.imgName = 'fi-rr-bookmark';
            } else {
              post.imgName = 'fi-sr-bookmark';
            }
            return post;
          });

          this.totalPages = response.data.totalPages;
        })
        .catch(err => {
          console.error(err);
        });
    },
  },
};
</script>

<style scoped lang="scss">
@import "@/assets/scss/list.scss";
@import "@/assets/scss/pattern.scss";
@import "@/assets/scss/layout.scss";
</style>
