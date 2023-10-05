<template>
  <div class="side-bar">
    <div class="logo-wrap">
      <h3>커톡커톡</h3>
      <button v-if="isExistToken" @click="logout">로그아웃</button>
      <button v-else @click="login">로그인</button>
    </div>
    <AccordionMenu :categories="categories" :expanded="expanded" :updateCategory="updateCategory" />
  </div>
</template>

<script>
import AccordionMenu from '@/components/AccordionMenu.vue';
import axios from 'axios';

export default {
  name: 'SideBar',
  components: {
    AccordionMenu,
  },
  data() {
    return {
      categories: [
        // {
        //   name: '메인 카테고리',
        //   expanded: true,
        //   items: ['자유게시판', '비밀게시판', '졸업생게시판', '컴과게시판'],
        // },
        // {
        //   name: '서브 카테고리',
        //   expanded: false,
        //   items: ['졸업생게시판', '취준생게시판', '연애게시판'],
        // },
      ],
      expanded: [],
      isExistToken: false
    };
  },
  methods: {
    updateCategory(index) {
      this.expanded[index] = !this.expanded[index];
      this.categories = [...this.categories];
    },
    getCategories() {
      const token = localStorage.getItem('token');

      var link = 'http://' + window.location.host;
      var headers = {
        'Authorization': `Bearer ${token}`, // "Authorization" 헤더에 토큰 추가
        'Content-Type': 'application/json',
      };
      axios.get(link + '/api/common/getCategories', { headers: headers })
        .then(response => {
          this.categories = response.data;
          this.categories.forEach((category, index) => {
            if (index === 0) {
              this.expanded.push(true);
            } else {
              this.expanded.push(false);
            }
          })
        })
        .catch(err => {
          console.log(err);
        })
    },
    login () {
      this.$router.push('/login')
    },
    logout () {
      var link = 'http://' + window.location.host;
      axios.get(link + '/api/auth/logout', null)
        .then(response => {
          localStorage.clear();
          this.isExistToken = false
        })
        .catch(err => {
          console.log(err);
        })
    }
  },
  created () {
    this.getCategories();
    const token = localStorage.getItem('token');
    if (token != undefined) {
      this.isExistToken = true
    }
  },
};
</script>

<style scoped lang="scss">
 @import "@/assets/scss/sidebar.scss";
</style>