<template>
    <div class="like-mypage">
        <MypageSidebar/>

        <div class="admin-content-wrap">
            <MypageHeader/>

            <div class="admin-content">
                <div class="admin-content-inner">
                    <strong class="page-title">공감</strong>
  <div class="tab-content">
          <div>
            <div class="top-btns-wrap">
              <div class="btn-wrap">
                <label>
                  <input type="checkbox" class="whole-chk" v-model="isAllSelected" @change="selectAll" />
                </label>
                <button type="button" @click="deselectAll">선택해제</button>
                <button type="button" @click="deleteSelected">선택삭제</button>
              </div>

              <div class="search-wrap">
                <input type="text" placeholder="내용을 입력하세요." />
                <img src="@/assets/images/fi-rr-search.png" />
              </div>
            </div>
            <ul class="list-wrap">
              <li v-for="(commentPost, index) in commentPosts" :key="index">
                <label>
                  <input type="checkbox" v-model="commentPost.selected" />
                </label>
                <div class="list-content">
                  <div class="title">{{ commentPost.title }}</div>
                  <div class="content">{{ commentPost.content }}</div>
                  <div class="detail-info-wrap">
                    <div class="active-wrap">
                      <div>댓글 {{ commentPost.commentCnt }}</div>
                      <div>공감 {{ commentPost.likeCnt }}</div>
                      <div>조회수 {{ commentPost.viewCnt }}</div>
                    </div>

                    <div class="writer-date-wrap">
                      {{ commentPost.writer }}
                      {{ commentPost.date }}
                    </div>
                  </div>
                </div>
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
import MypageSidebar from '@/components/layout/MypageSidebar.vue';
import MypageHeader from '@/components/layout/MypageHeader.vue';
    export default {
        name: 'LikeMypage',
        components: {
            MypageSidebar,
            MypageHeader,
        },
         data() {
    return {
      commentPosts: [
        {
          title: '댓글 단 글 제목',
          content: '댓글 단 글 내용',
          writer: '작성자',
          date: '2023-09-01',
          commentCnt: '10',
          likeCnt: '14',
          viewCnt: '24',
          selected: false,
        },
        {
          title: '댓글 단 글 제목2',
          content: '댓글 단 글 내용',
          writer: '작성자',
          date: '2023-09-01',
          commentCnt: '10',
          likeCnt: '14',
          viewCnt: '24',
          selected: false,
        },
      ],
      isAllSelected: false,
    };
  },
  methods: {
    selectAll() {
      // 전체 체크박스 상태에 따라 각 체크박스 선택 여부를 업데이트
      const isChecked = this.isAllSelected;
      this.commentPosts.forEach((post) => (post.selected = isChecked));
    },
    deselectAll() {
      // 모든 체크박스 선택 해제
      this.commentPosts.forEach((post) => (post.selected = false));
      // 전체 체크박스도 선택 해제
      this.isAllSelected = false;
    },
    deleteSelected() {
      // 선택된 항목 삭제
      const selectedPosts = this.commentPosts.filter((post) => post.selected);
      selectedPosts.forEach((post) => {
        // 삭제 로직 추가 (예: API 호출 또는 배열에서 제거)
        // 이 예제에서는 배열에서 제거만 수행
        const index = this.commentPosts.indexOf(post);
        if (index !== -1) {
          this.commentPosts.splice(index, 1);
        }
      });
      // 선택 해제 및 전체 체크박스 초기화
      this.deselectAll();
    },
  },
    }
</script>

<style lang="scss">
@import "@/assets/scss/like-mypage.scss";
</style>