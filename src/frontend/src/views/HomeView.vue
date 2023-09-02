<template>
  <div class="home">
    <HeaderLayout />

    <div class="content-wrap">
      <div class="left-content">
        <div class="banner-wrap"></div>

        <div class="pinned-wrap">
          <h3>내가 설정한 게시판</h3>
          <button @click="showModal = true" class="setting-pin">
            <img src="@/assets/images/fi-rr-thumbtack.png" />
            나만의 핀 설정하기
          </button>
        </div>

        <div class="board-wrap">
          <div class="board-box" v-for="(board, boardIndex) in boards" :key="boardIndex">
            <div class="board-name-wrap">
              <h3>{{ board.boardName }}</h3>
              <div class="more">+ 더 보기</div>
            </div>
            <ul>
              <li v-for="(item, itemIndex) in board.items" :key="itemIndex">
                <div class="board-info">
                  <div class="title-comment-wrap">
                    <div class="title">{{ item.title }}</div>
                    <div class="comment">[{{ item.comment }}]</div>
                  </div>
                  <div class="activity-wrap">
                    <div class="view">조회수 {{ item.view }}</div> |
                    <div class="like">공감수 {{ item.like }}</div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="right-content">
        <RightContent />
      </div>
    </div>

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

export default {
  name: "HomeView",
  props: {
    msg: String,
  },
  components: {
    ModalComponent,
    HeaderLayout,
    RightContent,
  },
  data() {
    return {
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
            { title: "게시판 제목", content: "게시판 내용", date: "2023-08-25", comment: "10", view: "2,000", like: "34" },
            { title: "게시판 제목", content: "게시판 내용2", date: "2023-08-25", comment: "20", view: "3,400", like: "22" },
            { title: "게시판 제목", content: "게시판 내용", date: "2023-08-25", comment: "10", view: "2,000", like: "34" },
            { title: "게시판 제목", content: "게시판 내용2", date: "2023-08-25", comment: "20", view: "3,400", like: "22" },
          ],
        }, 
        {
          boardName: "자유게시판",
          items: [
            { title: "게시판 제목", content: "게시판 내용", date: "2023-08-25", comment: "10", view: "2,000", like: "34" },
            { title: "게시판 제목", content: "게시판 내용2", date: "2023-08-25", comment: "20", view: "3,400", like: "22" },
            { title: "게시판 제목", content: "게시판 내용", date: "2023-08-25", comment: "10", view: "2,000", like: "34" },
            { title: "게시판 제목", content: "게시판 내용2", date: "2023-08-25", comment: "20", view: "3,400", like: "22" },
          ],
        },
      ],
    };
  },
};
</script>

<style lang="scss">
@import "@/assets/scss/home.scss";
@import "@/assets/scss/layout1.scss";
</style>
