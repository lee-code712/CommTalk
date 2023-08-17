<template>
  <div class="side-bar">
    <div class="logo-wrap">
      <h3>커톡커톡</h3>
    </div>
    <AccordionMenu :categories="categories" :updateCategory="updateCategory" />
  </div>
</template>

<script>
import AccordionMenu from '@/components/AccordionMenu.vue';

export default {
  name: 'SideBar',
  components: {
    AccordionMenu,
  },
  data() {
    return {
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
    };
  },
  methods: {
    updateCategory(index) {
      this.categories[index].expanded = !this.categories[index].expanded;
      this.categories = [...this.categories];
    },
  },
};
</script>

<style scoped lang="scss">
 @import "@/assets/scss/sidebar.scss";
</style>