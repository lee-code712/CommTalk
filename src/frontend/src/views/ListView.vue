<template>
  <div class="list-view">
    <HeaderLayout />
    <SubHeader />

    <div class="full-container">
      <div class="sub-top-wrap">
        <strong class="board-name">{{ boardName }}</strong>
        <div class="search-wrap" v-if="!isAllList">
          <input type="search" v-model="keyword" placeholder="내용을 입력해주세요." />
          <img src="@/assets/images/list-search.png" @click="fetchPosts" />
        </div>
      </div>

      <div class="list-wrap">
        <ul>
          <li class="list" v-for="board in boards" :key="board.postId" @click="updateViews(board.postId)">
            <a :href="'/detail?postId=' + board.postId">
            <div class="list-title-wrap">
                <div class="boardname-title-wrap">
                    <div v-if="isAllList" class="board-name">{{ board.board.boardName }}</div>
                <div class="title">{{ board.title }}</div>
              </div>
              <img 
                :src="board.scraped ? scrapImgActive : scrapImg" 
                @click="changeImg(board, board.postId)" 
              />
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
          <div class="prev-btn" @click="loadPage('previous')" :disabled="pageNumber === 0">이전</div>
          <div class="paging-num-wrap">
            <div v-for="page in totalPages" 
              :key="page - 1" 
              :class="{ 'on': page - 1 === pageNumber }" 
              @click="gotoPage(page - 1)">
              {{ page }}
            </div>
          </div>
          <div class="next-btn" @click="loadPage('next')" :disabled="pageNumber === totalPages - 1">다음</div>
        </div>
      </div>

      <div class="btn-wrap">
        <router-link :to="'/edit?boardId=' + boardId" class="router-link-class">
          <button class="write-btn"><img src="@/assets/images/fi-rr-edit.png" style="width: 14px; height: 14px;" />글쓰기</button>
        </router-link>
      </div>
    </div>
    <FooterLayout />
  </div>
</template>

<script>
import axios from 'axios';

import HeaderLayout from '@/components/layout/common/HeaderLayout.vue';
import SubHeader from '@/components/layout/common/SubHeader.vue';
import FooterLayout from '@/components/layout/common/FooterLayout.vue';

export default {
  name: 'ListView',
  components: {
    HeaderLayout,
    SubHeader,
    FooterLayout,
  },
  data() {
    return {
      headers: [],
      link: '',
      boards: [],
      boardName: '',
      scrapImg: require('@/assets/images/fi-rr-bookmark.png'),
      scrapImgActive: require('@/assets/images/fi-sr-bookmark.png'),
      pageNumber: 0,
      totalPages: '',
      isAllList: false,
      boardId: this.$route.query.boardId,
    };
  },
  created() {
    this.setupHeaders();
    this.fetchData();
    this.getBoardName();
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
    changeImg(board, postId) { /* 스크랩 상태 전환 */
        /* 이벤트의 기본 동작을 막음 (ex. 스크랩 아이콘 클릭 시, /detail 화면 이동 막음) */
        event.preventDefault();
        
        console.log(board);
        /* 스크랩 상태를 전환 */
        board.scraped = !board.scraped;
        
        const data = {
                "refId": postId,
                "actionType": "scrap"
        }
        axios
        .post(`/api/post/changeEngagementAction`, data, { headers: this.headers })
        .then(response => {
          console.log(response.data.status);
          
          /* 강제로 렌더링 */
          this.$forceUpdate();
        })
        .catch(err => {
          console.error(err);
        });
    },
    getBoardName() { /* 게시판 이름 가져옴 */
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
    updateViews(postId) { /* 조회수 업데이트 */
        axios
        .post(`/api/post/updateViews`, { postId }, { headers: this.headers })
        .then(response => {
          console.log("views: " + response.data);
        })
        .catch(err => {
          console.error(err);
        });
    },
    fetchData() {
      const boardId = this.$route.query.boardId;
      const commonKeyword = this.$route.query.commonKeyword;
    
      let url = "";
      const params = {
        page: this.pageNumber,
        size: 10
      };
    
      if (commonKeyword) {
        url = `/api/common/getPosts/${commonKeyword}`;
        this.boardName = "전체";
        this.isAllList = true;
      }
    
      if (boardId) {
        url = `/api/post/getPostsByBoard/${boardId}`;
      }
    
      axios
        .get(url, { params, headers: this.headers })
        .then(response => {
          this.boards = response.data.posts;
          console.log(this.boards);
          this.totalPages = response.data.totalPages;
        })
        .catch(err => {
          console.error(err);
        });
    },
    fetchPosts() { /* 게시글 리스트 내에서 검색 시, 실행되는 함수 */
      if (!this.keyword) return;
        
      const boardId = this.$route.query.boardId;
      
      axios
        .get(`/api/post/getPosts/${boardId}/${this.keyword}`, { headers: this.headers })
        .then(response => {
          this.boards = response.data.posts;

          /* 전체 페이지 수 할당 */
          this.totalPages = response.data.totalPages;
        })
        .catch(err => {
          console.error(err);
        });
    },
    loadPage(action) { /* 페이지를 이전 또는 다음으로 이동 */
      if (action === 'previous' && this.pageNumber > 0) {
        this.pageNumber--;
      } else if (action === 'next' && this.pageNumber < this.totalPages - 1) {
        this.pageNumber++;
      }
      
      /* 게시글 리스트 재조회 */
      this.fetchData();
    },
    gotoPage(page) { /* 페이지 번호를 지정된 페이지로 이동  */
      if (page >= 0 && page <= this.totalPages - 1) {
        this.pageNumber = page;

        /* 게시글 리스트 재조회 */
        this.fetchData();
      }
    },
  },
};
</script>

<style scoped lang="scss">
@import "@/assets/scss/list.scss";
@import "@/assets/scss/pattern/pattern.scss";
</style>
