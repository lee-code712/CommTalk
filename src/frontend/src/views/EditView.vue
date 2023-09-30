<template>
  <div class="edit-view">
    <HeaderLayout />
    <SubHeader/>

    <div class="full-container">
        <strong class="board-name">{{ boardName }}</strong>
        <div class="title-content-wrap">
          <div class="title">
            <input type="text" placeholder="제목" />
          </div>
          <div class="content">
        <div id="editor"></div>
          </div>

          <div class="hashtag-wrap">
            <div class="hashtag-inner">
              <div class="hashtags">
                <div class="hashtag" v-for="(hashtag, index) in hashtags" :key="index">
                  # {{ hashtag }}
                  <button class="remove-hashtag-btn" @click="removeHashtag(index)">
                    <img src="@/assets/images/fi-rr-cross-small.png" style="width: 12px; height: 12px;" />
                  </button>
                </div> 
                # <input type="text" placeholder="태그 입력 (최대 20개)" v-model="newHashtag" @keyup.enter="addHashtag" />
              </div>
            </div>
          </div>

          <div class="detail-btn-wrap">
            <div class="anonymous-file-wrap">
              <div class="custom-checkbox">
                <input type="checkbox" id="customCheckbox">
                <label for="customCheckbox">익명</label>
            </div>
            </div>

            <div class="btn-wrap">
              <button type="button" class="cancel-btn">취소</button>
              <button type="submit" class="register-btn">등록</button>
            </div>
          </div>
        </div>
      </div>
      <FooterLayout/>
  </div>
</template>

<script>
import axios from 'axios';

import Editor from '@toast-ui/editor';
import '@toast-ui/editor/dist/toastui-editor.css';
import HeaderLayout from "@/components/layout/HeaderLayout.vue";
import SubHeader from "@/components/layout/SubHeader.vue";
import FooterLayout from "@/components/layout/FooterLayout.vue";

export default {
  name: 'EditView',
  data() {
    return {
      newHashtag: '',
      hashtags: [],
      editor: null,
      boardName: ''
    };
  },
  created() {
      this.getBoardName()
  },
  mounted() {
      this.editor = new Editor({
        el: document.querySelector("#editor"),
        height: '500px',
        initialEditType: 'markdown',
        previewStyle: 'vertical' 
      });
  },
  components: {
    HeaderLayout,
    SubHeader,
    FooterLayout
  },
  methods: {
    updateSelectedOption(option) {
      this.selectedOption = option;
    },
    addHashtag() {
      if (this.newHashtag.trim() !== '') {
        this.hashtags.push(this.newHashtag);
        this.newHashtag = '';
      }
    },
    removeHashtag(index) {
      this.hashtags.splice(index, 1);
    },
     getBoardName() {
      const boardId = this.$route.query.boardId;
      axios
        .get(`/api/post/getBoard/${boardId}`, { headers: this.headers })
        .then(response => {
          this.boardName = response.data.boardName;
        })
        .catch(err => {
          console.error(err);
        });
    },
  }
};
</script>

<style lang="scss">
  @import "@/assets/scss/layout1.scss";
  @import "@/assets/scss/pattern.scss";
  @import "@/assets/scss/edit.scss";
  @import "@/assets/scss/common.scss";
</style>
