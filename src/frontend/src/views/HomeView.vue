<template>
  <div class="home">
    <HeaderLayout />
    <SubHeader />

    <div class="content-wrap">
      <div class="left-content">
        <div class="banner-wrap"></div>

        <section>
          <div class="section-header">
            <strong class="main-title">사진</strong>
            <router-link :to="'/list?boardId=' + '17'">
              <div class="more">더 보기<img src="@/assets/images/fi-rr-angle-small-right.png"/></div>
            </router-link>
          </div>
          <div class="section-body">
            <div class="gallery-container">
              <ul>
                <li v-for="(gallery, index) in galleries" :key="index">
                  <router-link :to="'/detail?postId=' + gallery.postId">
                    <div class="img-box-wrap">
                      <div class="img-box">
                        <img :src="getImageUrl(gallery.thumbnail.fileName)"/>
                      </div>
                      <div class="img-txt">{{ gallery.title.length > 18 ? gallery.title.slice(0, 18) + '...' : gallery.title }}</div>
                    </div>
                  </router-link>
                </li>
              </ul>
            </div>
          </div>
        </section>

        <section>
          <div class="section-header">
            <strong class="main-title">My 게시판</strong>
            <div>
              <button @click="showModal = true" class="setting-pin">
                <img src="@/assets/images/fi-rr-thumbtack.png" />
                나만의 핀 설정하기
              </button>
            </div>
          </div>
          <div class="section-body">
            <div class="board-box-wrap">
              <div class="board-box" v-for="(pinnedBoard, pinnedBoardIndex) in pinnedBoards" :key="pinnedBoardIndex">
                <div class="board-box-header">
                  <div class="board-name">{{ pinnedBoard.board.boardName }}</div>
                  <router-link :to="'/list?boardId=' + pinnedBoard.board.boardId">
                    <div class="more">더 보기<img src="@/assets/images/fi-rr-angle-small-right.png"/></div>
                  </router-link>
                </div>

                <div class="board-box-body">
                  <ul>
                    <li v-for="(post, postIndex) in pinnedBoard.posts" :key="postIndex">
                      <router-link :to="'/detail?postId=' + post.postId">
                        <div class="board-list-box">
                          <div class="title">{{ post.title }}</div>
                          <div class="comment-wrap">
                            <img src="@/assets/images/fi-rr-comment.png"/>
                            <div>{{ post.commentCnt }}</div>
                          </div>
                        </div>
                      </router-link>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </section>

        <section>
          <div class="multi-box-wrap">
            <div class="item-box">
              <div class="section-header">
                <strong class="main-title">여행</strong>
                <router-link :to="'/list?boardId=' + '6'">
                  <div class="more">더 보기<img src="@/assets/images/fi-rr-angle-small-right.png"/></div>
                </router-link>
              </div>
              <div class="section-body">
                <div class="pet-container">
                  <ul>
                    <li v-for="(trip, index) in trips" :key="index">
                      <router-link :to="'/detail?postId=' + trip.postId">
                        <div class="img-box-wrap">
                          <div class="img-box">
                            <img :src="getImageUrl(trip.thumbnail.fileName)"/>
                          </div>
                          <div class="img-txt">
                            {{ trip.title.length > 12 ? trip.title.slice(0, 12) + '...' : trip.title }}
                          </div>
                        </div>
                      </router-link>
                    </li>
                  </ul>
                </div>
              </div>
            </div>

            <div class="item-box">
              <div class="section-header">
                <strong class="main-title">패션</strong>
                <router-link :to="'/list?boardId=' + '11'">
                  <div class="more">더 보기<img src="@/assets/images/fi-rr-angle-small-right.png"/></div>
                </router-link>
              </div>
              <div class="section-body">
                <div class="fasion-container">
                  <ul>
                    <li v-for="(fashion, index) in fashions" :key="index">
                      <router-link :to="'/detail?postId=' + fashion.postId">
                        <div class="img-box-wrap">
                          <div class="img-box">
                            <img :src="getImageUrl(fashion.thumbnail.fileName)"/>
                          </div>
                          <div class="img-txt">
                            {{ fashion.title.length > 12 ? fashion.title.slice(0, 12) + '...' : fashion.title }}
                          </div>
                        </div>
                      </router-link>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
      <div class="right-content">
        <RightContent />
      </div>
    </div>

    <FooterLayout/>

    <ModalComponent v-if="showModal" @close="showModal = false" @save="savePin">
      <template #header>
        <div class="modal-title">나의 핀 설정하기</div>
        <div class="modal-close" @click="showModal = false">×</div>
      </template>
      <div>
        <div class="desc">아래에서 선택한 최대 8개의 게시판을 메인화면에 고정할 수 있습니다.</div>

        <div class="scrollable-container">
          <div v-for="(checkbox, index) in checkboxes" :key="index">
            <div class="checkbox-container">
              <input
                type="checkbox"
                :id="'checkbox-' + index"
                v-model="checkbox.checked"
                @change="updateCheckedBoards(checkbox.boardId, checkbox.checked)"
              />
              <label :for="'checkbox-' + index">
                <span class="checkbox-icon"></span>
                {{ checkbox.label }}
              </label>
            </div>
          </div>
        </div>
      </div>
    </ModalComponent>
  </div>
</template>

<script>
import axios from 'axios';

import HeaderLayout from "@/components/layout/common/HeaderLayout.vue";
import RightContent from "@/components/layout/common/RightContent.vue";
import SubHeader from "@/components/layout/common/SubHeader.vue";
import FooterLayout from "@/components/layout/common/FooterLayout.vue";
import ModalComponent from "@/components/ModalComponent.vue";

export default {
  name: 'HomeView',
  components: {
    HeaderLayout,
    RightContent,
    SubHeader,
    FooterLayout,
    ModalComponent,
  },
  data() {
    return {
      headers: [],
      link: '',
      boards: [],
      pinnedBoards: [],
      galleries: [],
      trips: [],
      fashions: [],
      showModal: false,
      checkboxes: [],
      checkedBoardIds : []
    };
  },
  created() {
    this.setupHeaders();
    this.getBoards();
    this.getPinnedBoards();
    this.getPosts('사진', 'galleries');
    this.getPosts('여행', 'trips');
    this.getPosts('패션', 'fashions');
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
    getBoards() { /* 핀 고정 게시판 모달에 사용할 전체 카테고리 조회 */
      axios
      .get(this.link + '/api/common/getCategories', { headers: this.headers })
      .then((response) => {
        const boardsArray = response.data.map((category) => category.boards).flat();

        boardsArray.forEach((board) => { /* boards 배열의 각 요소를 순회하면서 checkboxes에 항목을 추가 */
          this.checkboxes.push({
            boardId: board.boardId,
            label: board.boardName,
            checked: false,
          });
        });
      })
      .catch((err) => {
        console.log(err);
      });
    },
    getPinnedBoards() { /* 핀 고정 게시판 인기 게시글 조회 */
      axios
      .get(this.link + '/api/main/getPinnedBoards', { headers: this.headers })
      .then((response) => {
        this.pinnedBoards = response.data;
  
        this.pinnedBoards.forEach((pinnedBoard) => {
          /* pinnedBoard.board.boardId와 일치하는 댓글을 this.checkboxes 배열에서 찾음 */
          const checkbox = this.checkboxes.find((checkbox) => checkbox.boardId === pinnedBoard.board.boardId);

          if (checkbox) {
            /* 체크박스를 선택된 상태로 표시 */
            checkbox.checked = true;
            
            /* 선택된 게시판 Id를 checkedBoardIds 배열에 추가 */
            this.checkedBoardIds.push(checkbox.boardId); 
          }
        });
      })
      .catch((err) => {
        console.log(err);
      });
    },
    updateCheckedBoards(boardId, checked) { /* 모달 내 체크박스 클릭 시, 실행되는 함수 */
      if (checked) {
        /* 체크박스가 선택된 경우, boardId를 배열에 추가 */
        this.checkedBoardIds.push(boardId);
      } else {
        /* checkbox가 체크 해제되면 boardId를 배열에서 제거 */
        const index = this.checkedBoardIds.indexOf(boardId);
        
        if (index !== -1) {
          this.checkedBoardIds.splice(index, 1);
        }
      }
    },
    savePin() { /* 모달 저장 버튼 선택 시, 실행되는 함수 */
      const data = {
        boards: this.checkedBoardIds
      }
      axios
      .post(this.link + '/api/main/updatePinnedBoards', JSON.stringify(data), { headers: this.headers })
      .then(response => {
        console.log(response.data);
    
        this.$router.go();
    
        this.getBoards();
        this.getPinnedBoards();
      })
      .catch(err => {
        console.log(err);
        if (err.response && err.response.status === 403) {
            // logout 처리 함수 호출
            localStorage.clear();
        }
      });
    },
    getPosts(category, targetArray) { /* 사진, 여행, 패션 게시판 인기 게시글 조회 */
      axios.get(this.link + `/api/main/getPosts/${category}`, { headers: this.headers })
      .then(response => {
        this[targetArray] = response.data;
      })
      .catch(err => {
        console.error(`Error fetching posts for category ${category}:`, err);
      });
    },
    getImageUrl(fileName) { /* 사진, 여행, 패션 이미지 URL 가져옴 */
      const baseUrl = 'http://' + window.location.host;
      const apiUrl = '/api/file/load/' + fileName;
      const url = new URL(apiUrl, baseUrl);
      
      return url.href;
    },
  }
}
</script>

<style lang="scss">
@import "@/assets/scss/pattern/pattern.scss";
@import "@/assets/scss/pattern/layout.scss";
@import "@/assets/scss/home.scss";
@import "@/assets/scss/common.scss";
</style>