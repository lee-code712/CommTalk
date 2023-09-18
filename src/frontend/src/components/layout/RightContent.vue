<template>
  <div class="side-bar">
    <div class="login-wrap">
      <div class="login-desc">로그인 후 이용해주세요.</div>
        <button type="button" class="login-button">커톡커톡 로그인</button>
    </div>

    <div class="category-wrap" style="display: none;">
      <div class="title-angle-wrap">
        <h3>카테고리</h3>
        <span class="angle-icon" @click="toggleAccordion">
        <img v-bind:src="angleIconSrc" />
        </span>
      </div>
      <AccordionMenu v-if="category.open" :categories="categories" :updateCategory="updateCategory" />
    </div>

    <div class="popular-posts">
      <h3>실시간 인기 글</h3>
      <ul>
        <li v-for="(item, index) in items" :key="index">
        <div :class="['num', { 'on': index === 0 }]">
          {{ index + 1 }}
        </div>

          <div class="popular-posts-info">
            <div class="title-comment-wrap">
              <div class="title">{{ item.title }}</div> 
              <div class="comment">[{{ item.comment }}]</div>
            </div>
            <div class="view-like-wrap">
              자유게시판 | <img src="@/assets/images/fi-rr-eye.png"/> {{ item.view }} | <img src="@/assets/images/fi-rr-thumbs-up.png"/> {{ item.like }}
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import AccordionMenu from '@/components/AccordionMenu.vue';
  export default {
    name: 'RightContent',
    components: {
      AccordionMenu,
    },
    data() {
      return {
        category: {
          open: true
        },
        items: [
          {title: '실시간 인기 글 1', comment: '10', view: '3,200', like: '1,213'},
          {title: '실시간 인기 글 2', comment: '10', view: '3,200', like: '1,213'},
          {title: '실시간 인기 글 3', comment: '10', view: '3,200', like: '1,213'},
          {title: '실시간 인기 글 4', comment: '10', view: '3,200', like: '1,213'},
        ],
        categories: [
        {
          name: '메인 카테고리',
          expanded: true,
          items: ['자유게시판', '비밀게시판', '졸업생게시판', '컴과게시판'],
        },
        {
          name: '서브 카테고리',
          expanded: false,
          items: ['졸업생게시판', '취준생게시판', '연애게시판'],
        },
      ],
      }
    },
    methods: {
      toggleAccordion() {
        this.category.open = !this.category.open;
      },
      updateCategory(index) {
        this.categories[index].expanded = !this.categories[index].expanded;
        this.categories = [...this.categories];
      },
    },
    computed: {
      angleIconSrc() {
        return this.category.open
          ? require('@/assets/images/fi-rr-angle-small-up.png')
          : require('@/assets/images/fi-rr-angle-small-down.png');
      },
    },
  }
</script>

<style scoped lang="scss">
@import "@/assets/scss/pattern.scss";
@import "@/assets/scss/right-content.scss";
</style>