<template>
  <div class="edit-view">
    <HeaderLayout />
    <SubHeader/>

    <div class="full-container">
      <strong class="board-name">{{ boardName }}</strong>
      <div class="title-content-wrap">
        <div class="title">
          <input type="text" placeholder="제목" v-model="title" />
        </div>
        <div class="content">
          <textarea placeholder="내용" v-model="content"></textarea>

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
            <button type="submit" class="register-btn" @click="submitPost">등록</button>
          </div>
        </div>
      </div>
    </div>
    <FooterLayout/>
  </div>
</template>

<script>
import axios from 'axios';

import HeaderLayout from "@/components/layout/HeaderLayout.vue";
import SubHeader from "@/components/layout/SubHeader.vue";
import FooterLayout from "@/components/layout/FooterLayout.vue";

export default {
  name: 'EditView',
  data() {
    return {
      headers: [],
      link: '',
      imagePreviews: [],
      newHashtag: '',
      hashtags: [],
      boardName: '',
      isAnonymous: false,
    };
  },
  created() {
    this.setupHeaders();
    this.getBoardName();
  },
  components: {
    HeaderLayout,
    SubHeader,
    FooterLayout
  },
  methods: {
    setupHeaders() { /* http 요청 헤더를 설정하고 엔드포인트에 대한 인증 토큰을 포함 */
      const token = localStorage.getItem('token');
      
      this.link = 'http://' + window.location.host;
      this.headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      };
    },
    getBoardName() { /* 게시판 이름 가져옴 */
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
    addHashtag() { /* 새 해시태그 추가 */
      if (this.newHashtag.trim() !== '') {
        /* 새 해시태그 배열에 추가 */
        this.hashtags.push(this.newHashtag);
        /* 입력 필드 초기화 */
        this.newHashtag = '';
      }
    },
    removeHashtag(index) { /* 선택한 해시태그 제거 */
    /* 배열에서 선택한 해시태그 제거 */
      this.hashtags.splice(index, 1);
    },
    handleFileChange(event) { /* 파일 선택(input[type="file"])이 변경되었을 때 호출 */
      /* 선택된 파일 목록을 가져옴 */
      const files = event.target.files;
      
      for (let i = 0; i < files.length; i++) {
        /* 현재 파일을 가져옴 */
        const file = files[i];
        const reader = new FileReader();

        /* 파일을 읽을 때 동작 정의 */
        reader.onload = () => {
          /* 이미지 미리보기 데이터를 배열에 추가 */
          this.imagePreviews.push({
            /* 이미지 미리보기 URL */
            previewURL: reader.result,
            /* 파일 이름 */
            name: file.name,
            /* 파일 자체 */
            file: file
          });
        };

        /* 파일을 데이터 URL로 변환 */
        reader.readAsDataURL(file);
      }
    },
    removeImage(index) { /* 이미지 미리보기에서 이미지 제거 */
    /* 선택한 이미지를 배열에서 제거하고 반환 */
      const removedImage = this.imagePreviews.splice(index, 1)[0];
      
      if (removedImage && removedImage.file) {
        /* 이미지 URL 해제 */
        URL.revokeObjectURL(removedImage.previewURL);
      }
    },
    submitPost() { /* 작성한 게시글 서버로 전송 */
      const data = {
        title: this.title,
        content: this.content,
        hashtags: this.hashtags,
        isAnonymous: this.isAnonymous,
        images: []
      };
    
      /* 이미지를 Base64로 변환하여 데이터에 추가 */
      for (const image of this.imagePreviews) {
        this.uploadImageUrl(image.file)
          .then(base64Data => {
            data.images.push(base64Data);
    
            if (data.images.length === this.imagePreviews.length) {
              console.log("Data to be submitted:", data);
              /* axios로 서버에 전달 */
            }
          })
          .catch(error => {
            console.error("Error converting image to Base64:", error);
          });
      }
    },
    uploadImageUrl(imageFile) { /* 이미지 파일을 Base64로 변환하여 Promise로 반환 */
      return new Promise((resolve, reject) => {
        const reader = new FileReader();
    
        reader.onload = () => {
          /* Base64 데이터 부분 추출 */
          const base64Data = reader.result.split(',')[1];
          
          /* Base64 데이터를 Promise로 반환 */
          resolve(base64Data);
        };
    
        reader.onerror = (error) => {
          /* 에러가 발생한 경우 Promise를 거부 */
          reject(error);
        };
    
        /* 이미지 파일을 Base64로 변환하여 읽음 */
        reader.readAsDataURL(imageFile);
      });
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