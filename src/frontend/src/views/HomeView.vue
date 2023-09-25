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
                        <img :src="gallery.imgsrc" />
                      </div>
                      <div class="img-txt">{{ gallery.txt.length > 18 ? gallery.txt.slice(0, 18) + '...' : gallery.txt }}</div>
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
                      <li v-for="(pet, index) in pets" :key="index">
                        <div class="img-box-wrap">
                          <div class="img-box">
                            <img :src=pet.imgsrc />
                          </div>
                          <div class="img-txt">
                            {{ pet.txt.length > 12 ? pet.txt.slice(0, 12) + '...' : pet.txt }}
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
                            <img :src=fashion.imgsrc />
                          </div>
                          <div class="img-txt">
                            {{ fashion.txt.length > 12 ? fashion.txt.slice(0, 12) + '...' : fashion.txt }}
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

      <ModalComponent v-if="showModal" @close="showModal = false">
        <template #header>
          <div class="modal-title">나의 핀 설정하기</div>
          <div class="modal-close" @click="showModal = false">×</div>
        </template>
        <div>
          <div class="desc">아래에서 선택한 최대 8개의 게시판을 메인화면에 고정할 수 있습니다.</div>

          <div v-for="(checkbox, index) in checkboxes" :key="index">
            <input type="checkbox" :id="'checkbox-'+ index" v-model="checkbox.checked" />
            <label :for="'checkbox-' + index">{{ checkbox.label }}</label>
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
        galleries: [
          {imgsrc: require("@/assets/images/sampleimg.png"), txt: '장원영'},
          {imgsrc: require("@/assets/images/sampleimg2.png"), txt: 'nct'},
          {imgsrc: require("@/assets/images/sampleimg3.png"), txt: '엔믹스'},
        ],
        pets: [
          {imgsrc: require("@/assets/images/sampleimg.png"), txt: '토끼ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㄴㅇㅇㅇㅇㅇㅇㅇㅇ'},
          {imgsrc: require("@/assets/images/sampleimg2.png"), txt: '고양이'}
        ],
        fashions: [
          {imgsrc: require("@/assets/images/sampleimg3.png"), txt: '토끼ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㄴㅇㅇㅇㅇㅇㅇㅇㅇ'},
          {imgsrc: require("@/assets/images/sampleimg2.png"), txt: '고양이'}
        ],
        showModal: false,
        checkboxes: [
          { label: "게시판 1", checked: false },
          { label: "게시판 2", checked: false },
          { label: "게시판 3", checked: false },
          { label: "게시판 4", checked: false },
        ],
        boards: [
          {
            boardName: "자유게시판",
            items: [
              { title: "게시판 제목", comment: "10"},
              { title: "게시판 제목", comment: "20"},
              { title: "게시판 제목", comment: "10"},
              { title: "게시판 제목", comment: "20"},
            ],
          },
          {
            boardName: "자유게시판",
            items: [
              { title: "게시판 제목", comment: "10"},
              { title: "게시판 제목", comment: "20"},
              { title: "게시판 제목", comment: "10"},
              { title: "게시판 제목", comment: "20"},
            ],
          },
           {
            boardName: "자유게시판",
            items: [
              { title: "게시판 제목", comment: "10"},
              { title: "게시판 제목", comment: "20"},
              { title: "게시판 제목", comment: "10"},
              { title: "게시판 제목", comment: "20"},
            ],
          },
           {
            boardName: "자유게시판",
            items: [
              { title: "게시판 제목", comment: "10"},
              { title: "게시판 제목", comment: "20"},
              { title: "게시판 제목", comment: "10"},
              { title: "게시판 제목", comment: "20"},
            ],
          },
        ],
      };
    },
    created() {
        this.getPinnedBoards();
    },
    methods: {
        getPinnedBoards() {
            const token = localStorage.getItem('token');
      var link = 'http://' + window.location.host;
      var headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      };
      
        axios
        .get(link + '/api/main/getPinnedBoards', { headers: headers })
        .then(response => {
          this.pinnedBoards = response.data;
          console.log(this.pinnedBoards);
        })
        .catch(err => {
          console.log(err);
        });
        }
    }
  }
</script>

<style lang="scss">
@import "@/assets/scss/pattern.scss";
@import "@/assets/scss/layout1.scss";
@import "@/assets/scss/home.scss";
</style>