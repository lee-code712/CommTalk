<template>
  <div class="home-component">
    <div class="banner-wrap"></div>

    <div class="pinned-wrap">
      <div>게시판 고정</div>
      <button @click="showModal = true" class="setting-pin">
        <img src="@/assets/images/fi-rr-thumbtack.png" />나만의 핀 설정하기
      </button>
    </div>

    <div class="board-box-wrap">
      <div class="board-row" v-for="row in dividedBoards" :key="row[0].title">
        <div v-for="board in row" :key="board.title" class="board-box">
          <div class="side-section-box-title">{{ board.title }}</div>
          
          <div v-for="post in board.posts" :key="post.title" class="side-section-box">
            <div class="title">{{ post.title }}</div>
            <div class="date">{{ post.date }}</div>
          </div>
        </div>
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
          <input type="checkbox" :id="'checkbox-'+ index" v-model="checkbox.checked"/>
          <label :for="'checkbox-' + index">{{checkbox.label}}</label>
        </div>
      </div>
    </ModalComponent>
  </div>
</template>


<script>
import ModalComponent from "@/components/ModalComponent.vue";
export default {
  name: 'HomeComponent',
  props: {
    msg: String
  },
  components: {
    ModalComponent
  },
  data() {
    return {
      showModal: false,
      checkboxes: [
        { label: '게시판 1', checked: false },
        { label: '게시판 2', checked: false },
        { label: '게시판 3', checked: false },
        { label: '게시판 4', checked: false },
      ],
      boards: [
        {
          title: '게시판 1',
          posts: [
            { title: '게시물 제목', date: '2023-08-15' },
            { title: '게시물 제목', date: '2023-08-15' },
            { title: '게시물 제목', date: '2023-08-15' },
            { title: '게시물 제목', date: '2023-08-15' }
          ]
        },
        {
          title: '게시판 2',
          posts: [
            { title: '게시물 제목', date: '2023-08-15' },
            { title: '게시물 제목', date: '2023-08-15' },
            { title: '게시물 제목', date: '2023-08-15' },
            { title: '게시물 제목', date: '2023-08-15' }
          ]
        },
        {
          title: '게시판 3',
          posts: [
            { title: '게시물 제목', date: '2023-08-15' },
            { title: '게시물 제목', date: '2023-08-15' },
            { title: '게시물 제목', date: '2023-08-15' },
            { title: '게시물 제목', date: '2023-08-15' }
          ]
        },
        {
          title: '게시판 4',
          posts: [
            { title: '게시물 제목', date: '2023-08-15' },
            { title: '게시물 제목', date: '2023-08-15' },
            { title: '게시물 제목', date: '2023-08-15' },
            { title: '게시물 제목', date: '2023-08-15' }
          ]
        },
      ]
    };
  },
  computed: {
    dividedBoards() {
      const divided = [];
      for (let i = 0; i < this.boards.length; i += 2) {
        divided.push(this.boards.slice(i, i + 2));
      }
      return divided;
    }
  }
}
</script>

<style scoped lang="scss">
  @import "@/assets/scss/home.scss";

  .board-row {
    display: flex;
    justify-content: space-between;
  }
</style>
