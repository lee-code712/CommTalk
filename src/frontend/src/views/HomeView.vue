<template>
  <div class="home">
    <HeaderLayout />
    <SubHeader />

    <div class="content-wrap">
      <div class="left-content">
        <div class="banner-wrap"></div>

        <section>
          <div class="section-header">
            <strong class="main-title">갤러리</strong>
            <router-link :to="'/list?boardId=' + '17'">
              <div class="more">더 보기<img src="@/assets/images/fi-rr-angle-small-right.png"/></div>
            </router-link>
          </div>
          <div class="section-body">
            <div class="gallery-container">
              <ul>
                <li v-for="(gallery, index) in galleries" :key="index">
                  <div class="img-box-wrap">
                    <div class="img-box">
                      <img src="getImageUrl(gallery.tumbnail.fileName)"/>
                    </div>
                    <div class="img-txt">{{ gallery.title.length > 18 ? gallery.title.slice(0, 18) + '...' : gallery.title }}</div>
                  </div>
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
                      <div class="img-box-wrap">
                        <div class="img-box">
                            <img src="getImageUrl(trip.tumbnail.fileName)"/>
                        </div>
                        <div class="img-txt">
                          {{ trip.title.length > 12 ? trip.title.slice(0, 12) + '...' : trip.title }}
                        </div>
                      </div>
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
                      <div class="img-box-wrap">
                        <div class="img-box">
                            <img src="getImageUrl(fashion.tumbnail.fileName)"/>
                        </div>
                        <div class="img-txt">
                          {{ fashion.title.length > 12 ? fashion.title.slice(0, 12) + '...' : fashion.title }}
                        </div>
                      </div>
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

        <div v-for="(checkbox, index) in checkboxes" :key="index">
          <input 
            type="checkbox"
            :id="'checkbox-'+ index" 
            v-model="checkbox.checked" 
            @change="updateCheckedBoards(checkbox.boardId, checkbox.checked)"
          />
          <label :for="'checkbox-' + index"> {{ checkbox.label }}</label>
        </div>
      </div>
    </ModalComponent>
  </div>
</template>

<script>
import axios from 'axios';
import ModalComponent from "@/components/ModalComponent.vue";
import HeaderLayout from "@/components/layout/HeaderLayout.vue";
import RightContent from "@/components/layout/RightContent.vue";
import SubHeader from "@/components/layout/SubHeader.vue";
import FooterLayout from "@/components/layout/FooterLayout.vue";

export default {
  name: 'TestView',
  components: {
    HeaderLayout,
    RightContent,
    SubHeader,
    FooterLayout,
    ModalComponent,
  },
  data() {
      return {
        pinnedBoards: [],
        galleries: [],   // Initialize as an empty array
        trips: [],       // Initialize as an empty array
        fashions: [],    // Initialize as an empty array
        showModal: false,
        checkboxes: [],
        link: '',
        boards: [],
        checkedBoardIds : []
      };
    },
  created() {
    this.setupHeaders();
    this.getPinnedBoards();
    this.getPosts('여행', 'trips');
    this.getPosts('사진', 'galleries');
    this.getPosts('패션', 'fashions');
    this.getBoards();
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
    getImageUrl(fileName) {
      const baseUrl = 'http://' + window.location.host;
      const apiUrl = '/api/file/load/' + fileName;
      const url = new URL(apiUrl, baseUrl);
      return url.href;
    },
    getPinnedBoards() {
      axios
        .get(this.link + '/api/main/getPinnedBoards', { headers: this.headers })
        .then(response => {
          this.pinnedBoards = response.data;
          console.log("pin");
          console.log(this.pinnedBoards);
        })
        .catch(err => {
          console.log(err);
        });
    },
    getPosts(category, targetArray) {
      axios.get(this.link + `/api/main/getPosts/${category}`, { headers: this.headers })
        .then(response => {
          this[targetArray] = response.data;
          console.log(`${category}###`);
          console.log(this[targetArray]);
        })
        .catch(err => {
          console.error(`Error fetching posts for category ${category}:`, err);
        });
    },
    getBoards() {
      axios
        .get(this.link + '/api/common/getCategories', { headers: this.headers })
        .then((response) => {
          // response.data에서 boards 배열 추출
          const boardsArray = response.data.map((category) => category.boards).flat();
          
          // boards 배열의 각 요소를 순회하면서 checkboxes에 항목을 추가
          boardsArray.forEach((board) => {
            this.checkboxes.push({ boardId: board.boardId, label: board.boardName, checked: false });
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    savePin() {
		const data = {
			boards: this.checkedBoardIds
		}
		axios
        .post(this.link + '/api/main/updatePinnedBoards', JSON.stringify(data), { headers: this.headers })
        .then(response => {
			console.log(response.data);
        })
        .catch(err => {
          console.log(err);
          if (err.response && err.response.status === 403) {
              // logout 처리 함수 호출
          }
        });
	},
	updateCheckedBoards(boardId, checked) {
      if (checked) {
        // checkbox가 체크되면 boardId를 배열에 추가
        console.log("push");
        console.log(boardId);
        this.checkedBoardIds.push(boardId);
      } else {
        // checkbox가 체크 해제되면 boardId를 배열에서 제거
        const index = this.checkedBoardIds.indexOf(boardId);
        if (index !== -1) {
          this.checkedBoardIds.splice(index, 1);
        }
      }
    },
  }
}
</script>

<style lang="scss">
@import "@/assets/scss/pattern.scss";
@import "@/assets/scss/layout1.scss";
@import "@/assets/scss/home.scss";
</style>