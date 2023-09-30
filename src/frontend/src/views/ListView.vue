<template>
  <div class="list-view">
    <HeaderLayout/>
    <SubHeader/>

    <div class="full-container">
            <div class="sub-top-wrap">
                <strong class="board-name">{{ boardName }}</strong>
                <div class="search-wrap">
                    <input type="search" placeholder="내용을 입력해주세요." />
                    <img src="@/assets/images/fi-rr-search.png"/>
                </div>
            </div>

            <div class="list-wrap">
                <ul>
                    <li class="list" v-for="(board , index) in boards" :key="index">
                        <div class="list-title-wrap">
                            <strong class="title">{{ board.title }}</strong>

                         <img v-if="board.imgName" :src="require(`@/assets/images/${board.imgName}.png`)" @click="changeImg(index)" />
                        </div>

                        <div class="list-content">{{ board.content }}</div>

                        <div class="list-detail-info">
                            <div class="comment-like-wrap">
                                <div class="comment-box">
                                    <img src="@/assets/images/fi-rr-comment.png" style="width: 12px; height: 12px;"/>
                                    댓글 {{ board.commentCnt }}
                                </div>
                                <div class="like-box">
                                    <img src="@/assets/images/fi-rr-thumbs-up.png" style="width: 14px; height: 14px;"/>
                                    공감 {{ board.likes}}
                                </div>
                                <div class="view-box">
                                    <img src="@/assets/images/fi-rr-eye.png" style="width: 14px; height: 14px;"/>
                                    조회수 {{ board.views }}
                                </div>
                            </div>

                            <div class="writer-date-wrap">
                                <div class="writer">{{ board.author.nickname }}</div>
                                <div class="date">{{ board.createdAt }}</div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>

            <div class="paging-wrap">
                <div class="paging-inner">
                    <div class="prev-btn">이전</div>
                    <div class="paging-num-wrap">
                        <div class="on">1</div>
                        <div>2</div>
                        <div>3</div>
                    </div>
                    <div class="next-btn">다음</div>
                </div>
            </div>

<div class="btn-wrap">
    <router-link :to="'/edit?boardId=' + boardId" class="router-link-class">
      <button class="write-btn">글쓰기</button>
    </router-link>
  </div>
        </div>
        <FooterLayout/>
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
    };
  },
  created() {
    this.setupHeaders();
    this.fetchData();
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
    changeImg(index) {
      if (this.boards[index].imgName === 'fi-rr-bookmark') {
        this.boards[index].imgName = 'fi-sr-bookmark';
      } else {
        this.boards[index].imgName = 'fi-rr-bookmark';
      }
    },
    fetchData() {
      const boardId = this.$route.query.boardId;
      
      const data = {
        page: 0
      };

      // axios를 사용하여 데이터를 가져오는 요청 보내기
      axios
        .get(`/api/post/getPostsByBoard/${boardId}`, data, { headers: this.headers })
        .then(response => {
          // 서버로부터 받은 데이터를 Vue 데이터에 할당
          this.boards = response.data.posts;
          console.log("posts@@@@");
          console.log(response.data.posts);

          // 데이터를 가져온 후에 게시판 이름을 가져오는 함수 호출
          this.getBoardName();
        })
        .catch(err => {
          console.log(err);
        });
    },
    getBoardName() {
      const boardId = this.$route.query.boardId;
      axios
        .get(`/api/post/getBoard/${boardId}`, { headers: this.headers })
        .then(response => {
          console.log("!@@@");
          console.log(response.data);
          this.boardName = response.data.boardName;
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>




<style scoped lang="scss">
  @import "@/assets/scss/list.scss";
  @import "@/assets/scss/pattern.scss";
  @import "@/assets/scss/layout.scss";
</style>