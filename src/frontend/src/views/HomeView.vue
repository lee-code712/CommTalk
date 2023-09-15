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
              <div class="more">+ 더 보기</div>
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
                <div class="board-box" v-for="(board, boardIndex) in boards" :key="boardIndex">
                  <div class="board-box-header">
                    <div class="board-name">{{ board.boardName }}</div>
                    <div class="more">+ 더 보기</div>
                  </div>

                  <div class="board-box-body">
                    <ul>
                      <li v-for="(item, itemIndex) in board.items" :key="itemIndex">
                        <div class="board-list-box">
                          <div class="title">{{ item.title }}</div>
                          <div class="comment-wrap">
                            <img src="@/assets/images/fi-rr-comment.png"/>
                            <div>{{ item.comment }}</div>
                          </div>
                        </div>
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
                  <strong class="main-title">반려동물</strong>
                  <div class="more">+ 더 보기</div>
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
                  <div class="more">+ 더 보기</div>
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
        galleries: [
          {imgsrc: require("@/assets/images/sampleimg.png"), txt: '토끼ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㄴㅇㅇㅇㅇㅇㅇㅇㅇ'},
          {imgsrc: require("@/assets/images/sampleimg2.png"), txt: '고양이'},
          {imgsrc: require("@/assets/images/sampleimg3.png"), txt: '강아지'},
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
    }
  }
</script>

<style lang="scss">
@import "@/assets/scss/pattern.scss";
@import "@/assets/scss/layout1.scss";
@import "@/assets/scss/home.scss";
</style>