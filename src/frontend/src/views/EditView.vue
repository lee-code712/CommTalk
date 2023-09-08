<template>
  <div class="edit-view">
    <HeaderLayout />
    <SubHeader/>

    <div class="full-container">
        <strong class="board-name">자유게시판</strong>
        <div class="title-content-wrap">
          <div class="title">
            <input type="text" placeholder="제목" />
          </div>
          <div class="content">
            <textarea placeholder="내용"></textarea>

            <div class="image-preview">
              <div class="image-preview-inner" v-for="(image, index) in imagePreviews" :key="index">
                <img :src="image.previewURL" alt="Preview" />
                <div class="img-name">{{ image.name }}</div>
                <button @click="removeImage(index)">삭제</button>
              </div>
            </div>
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
              <input type="file" ref="fileInput" multiple @change="handleFileChange" />
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
import HeaderLayout from "@/components/layout/HeaderLayout.vue";
import SubHeader from "@/components/layout/SubHeader.vue";
import FooterLayout from "@/components/layout/FooterLayout.vue";

export default {
  name: 'EditView',
  data() {
    return {
      imagePreviews: [],
      newHashtag: '',
      hashtags: []
    };
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
    handleFileChange(event) {
      const files = event.target.files;
      for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const reader = new FileReader();

        reader.onload = () => {
          this.imagePreviews.push({
            previewURL: reader.result,
            name: file.name,
            file: file // Store the actual File object
          });
        };

        reader.readAsDataURL(file);
      }
    },
    removeImage(index) {
      const removedImage = this.imagePreviews.splice(index, 1)[0];
      if (removedImage && removedImage.file) {
        URL.revokeObjectURL(removedImage.previewURL); // Release the object URL
      }
    }
  }
};
</script>

<style lang="scss">
  @import "@/assets/scss/layout1.scss";
  @import "@/assets/scss/pattern.scss";
  @import "@/assets/scss/edit.scss";
  @import "@/assets/scss/common.scss";
</style>
