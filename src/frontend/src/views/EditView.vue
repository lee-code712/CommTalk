<template>
  <div class="edit-view">
    <HeaderLayout />
    <SubHeader />

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
            <div class="checkbox-container">
              <input
                type="checkbox"
                :id="'checkbox01'"
                v-model="isAnonymous"
              />
              <label :for="'checkbox01'">
                <span class="checkbox-icon"></span>
                익명
              </label>
            </div>
            
            <div class="checkbox-container">
              <input
                type="checkbox"
                :id="'checkbox02'"
                v-model="isCommentable"
              />
              <label :for="'checkbox02'">
                <span class="checkbox-icon"></span>
                댓글 허용
              </label>
            </div>

            <label for="file">
              <div class="btn-upload"><img src="@/assets/images/fi-rr-cloud-upload.png" style="width: 14px; height: 14px;"/>파일 업로드하기</div>
            </label>
            <input type="file" id="file" ref="fileInput" multiple @change="handleFileChange" />
          </div>

          <div class="btn-wrap">
            <router-link :to="'/list?boardId=' + boardId" class="router-link-class">
              <button type="button" class="cancel-btn">취소</button>
            </router-link>
            <button type="submit" class="register-btn" @click="submitPost">등록</button>
          </div>
        </div>
      </div>
    </div>
    <FooterLayout />
  </div>
</template>

<script>
import axios from 'axios';

import HeaderLayout from "@/components/layout/common/HeaderLayout.vue";
import SubHeader from "@/components/layout/common/SubHeader.vue";
import FooterLayout from "@/components/layout/common/FooterLayout.vue";

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
      isAnonymous: true,
      isCommentable: true,
      boardId: this.$route.query.boardId
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
    setupHeaders() {
      /* http 요청 헤더를 설정하고 엔드포인트에 대한 인증 토큰을 포함 */
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
    submitPost() {
      /* 작성한 게시글 서버로 전송 */
      const boardId = this.$route.query.boardId;

      const data = {
        "boardId": boardId,
        "title": this.title,
        "content": this.content,
        "isAnonymous": this.isAnonymous ? 1 : 0,
        "isCommentable": this.isCommentable ? 1 : 0,
        "hashtags": this.hashtags
      };

      axios
        .post(`/api/post/createPost`, data, { headers: this.headers })
        .then(response => {
          console.log(response.data);
          
          this.uploadImageUrl(response.data.postId);
          
          this.$router.push(`/list?boardId=${boardId}`);
        })
        .catch(err => {
          console.error(err);
        });
    },
    uploadImageUrl(postId) {
      const formData = new FormData();

for (const image of this.imagePreviews) {
  formData.append('images', image.file);
}

if (formData.has('images')) { // 이미지가 있는 경우에만 요청을 보냅니다.
  const config = {
    headers: {
      'Content-Type': 'multipart/form-data',
      'Authorization': `Bearer ${localStorage.getItem('token')}`,
    },
  };

  /* 파일 업로드 요청 보내기 */
  axios
    .post(`/api/file/upload/${postId}`, formData, config)
    .then((response) => {
      console.log('파일 업로드 성공:', response.data);
    })
    .catch((err) => {
      console.error('파일 업로드 실패:', err);
    });
} else {
  console.log('이미지가 없어서 파일 업로드 요청을 보내지 않습니다.');
}

    }
  }
};
</script>

<style lang="scss">
  @import "@/assets/scss/pattern/pattern.scss";
  @import "@/assets/scss/pattern/layout.scss";
  @import "@/assets/scss/edit.scss";
  @import "@/assets/scss/common.scss";
</style>