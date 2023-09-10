<template>
  <div class="edit-view">
    <HeaderLayout />
    <SubHeader/>

    <div class="content-wrap">
      <div class="left-content">
        <div class="board-name">자유게시판</div>
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
                  <button class="remove-hashtag-btn" @click="removeHashtag(index)">X</button>
                </div>
                # <input type="text" placeholder="태그 입력 (최대 20개)" v-model="newHashtag" @keyup.enter="addHashtag" />
              </div>
            </div>
          </div>

          <div class="footer">
            <div>
              <div>
                <input type="checkbox"/>익명
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

      <div class="right-content">
        <RightContent />
      </div>
    </div>
  </div>
</template>

<script>
import HeaderLayout from "@/components/layout/HeaderLayout.vue";
import RightContent from "@/components/layout/RightContent.vue";
import SubHeader from "@/components/layout/SubHeader.vue";

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
    RightContent,
    SubHeader
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
</style>
