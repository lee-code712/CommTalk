<template>
    <div class="comment-mypage">
        <MypageSidebar/>

        <div class="admin-content-wrap">
            <MypageHeader/>

            <div class="admin-content">
                <div class="admin-content-inner">
                    <strong class="page-title">내가 쓴 글 / 댓글 단 글</strong>

                    <div class="tabs">
          <div
            v-for="(tab, index) in tabs"
            :key="index"
            @click="activeTab = tab"
            :class="{ 'active': activeTab === tab }"
            class="tab-container"
          >
            {{ tab }}
          </div>
        </div>

        <div class="tab-content">
          <div v-show="activeTab === '내가 쓴 글'">
            <div class="top-btns-wrap">
              <div class="btn-wrap">
                <label>
                  <input type="checkbox" class="mycomment-whole-chk" v-model="isAllSelected" @change="toggleSelectAll(mycomments)" />
                </label>
                <button type="button" @click="deselectAll(mycomments)">선택해제</button>
                <button type="button" @click="deleteSelected(mycomments)">선택삭제</button>
              </div>

              <div class="search-wrap">
                <input type="text" placeholder="내용을 입력하세요." />
                <img src="@/assets/images/fi-rr-search.png" />
              </div>
            </div>
            <ul class="list-wrap">
              <li v-for="(mycomment, index) in mycomments" :key="index">
                <label>
                  <input type="checkbox" v-model="mycomment.selected" />
                </label>
                <div class="list-content">
                  <div class="title">{{ mycomment.title }}</div>
                  <div class="content">{{ mycomment.content }}</div>
                  <div class="detail-info-wrap">
                    <div class="active-wrap">
                      <div>댓글 {{ mycomment.commentCnt }}</div>
                      <div>공감 {{ mycomment.likeCnt }}</div>
                      <div>조회수 {{ mycomment.viewCnt }}</div>
                    </div>
                    <div class="writer-date-wrap">
                      <div class="date">{{ mycomment.date }}</div>
                    </div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
          <div v-show="activeTab === '댓글 단 글'">
            <div class="top-btns-wrap">
              <div class="btn-wrap">
                <label>
                  <input type="checkbox" class="whole-chk" v-model="isAllSelected" @change="toggleSelectAll(commentPosts)" />
                </label>
                <button type="button" @click="deselectAll(commentPosts)">선택해제</button>
                <button type="button" @click="deleteSelected(commentPosts)">선택삭제</button>
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
                      {{ commentPost.writer }} {{ commentPost.date }}
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
        name: 'ReportAdmin',
        components: {
            MypageSidebar,
            MypageHeader,
        },
  data() {
    return {
      tabs: ['내가 쓴 글', '댓글 단 글'],
      activeTab: '내가 쓴 글',
      mycomments: [
        { 
          title: '내가 쓴 글 제목', 
          content: '내가 쓴 글 내용', 
          date: '2023-09-01', 
          commentCnt: '10',
          likeCnt: '20',
          viewCnt: '35',
          selected: false 
        },
         { 
          title: '내가 쓴 글 제목', 
          content: '내가 쓴 글 내용', 
          date: '2023-09-01', 
          commentCnt: '10',
          likeCnt: '20',
          viewCnt: '35',
          selected: false 
        },
      ],
      commentPosts: [
        {
          title: '댓글 단 글 제목',
          content: '댓글 단 글 내용',
          writer: '작성자',
          date: '2023-09-01',
          commentCnt: '10',
          likeCnt: '14',
          viewCnt: '24',
          selected: false 
        },
      ],
      isAllSelected: false,
    };
  },
    methods: {
    toggleSelectAll(dataArray) {
      const isChecked = this.isAllSelected;
      dataArray.forEach((item) => (item.selected = isChecked));
    },
    deselectAll(dataArray) {
      dataArray.forEach((item) => (item.selected = false));
      this.isAllSelected = false;
    },
    deleteSelected(dataArray) {
      const selectedItems = dataArray.filter((item) => item.selected);
      selectedItems.forEach((item) => {
        const index = dataArray.indexOf(item);
        if (index !== -1) {
          dataArray.splice(index, 1);
        }
      });
      this.deselectAll(dataArray);
    },
  },
       
    }
</script>

<style lang="scss">
@import "@/assets/scss/commentmypage.scss";
</style>