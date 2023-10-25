<template>
  <div class="sub-header">
    <div class="sub-header-inner">
      <div class="category-wrap">
        <div
          class="category-box"
          v-for="(category, categoryindex) in categories"
          :key="categoryindex"
        >
          <span class="depth1">
            <span class="txt">{{ category.categoryName }}</span>
          </span>
          <ul class="depth2">
            <li v-for="(board, boardIndex) in category.boards" :key="boardIndex">
              <a :href="'/list?boardId=' + board.boardId" @click="refreshPage(board.boardId)" class="router-link-class">
                {{ board.boardName }}
              </a>
            </li>
          </ul>
        </div>

        <div class="category-banner-wrap">
          <img src="@/assets/images/category-banner.png" class="category-banner" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'SubHeader',
  data() {
    return {
      isExistToken: false,
      categories: [],
    };
  },

  created() {
    this.getCategories();
  },
  methods: {
    refreshPage(boardId) {
      this.$router.push(`/list?boardId=${boardId}`);
    },
    getCategories() {
      const token = localStorage.getItem('token');
      var link = 'http://' + window.location.host;
      var headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      };

      axios
        .get(link + '/api/common/getCategories', { headers: headers })
        .then(response => {
          this.categories = response.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
  },
};
</script>

<style lang="scss">
@import "@/assets/scss/layout/subheader.scss";
</style>