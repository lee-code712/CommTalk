<template>
    <div class="side-section">
        <!-- 실시간 인기 글 -->
        <div class="popular-posts-wrap">
          <div class="side-section-box-title">실시간 인기 글</div>
          
          <div class="side-section-box">
            <div class="writer-date-wrap">
              <div>작성자</div>
              <div>2023-08-10</div>
            </div>
            <div class="title">게시판 제목</div>
            <div class="content">게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용</div>
            <div class="board-action-wrap">
              <div class="board-name">자유게시판</div>
              <div class="action-wrap">
                <div class="action">
                  <img src="@/assets/images/fi-rr-thumbs-up.png"/> 5
                </div>
                <div class="action">
                  <img src="@/assets/images/fi-rr-comment.png"/> 5
                </div>
              </div>
            </div>
          </div>

          <div class="side-section-box">
            <div class="writer-date-wrap">
              <div>작성자</div>
              <div>2023-08-10</div>
            </div>
            <div class="title">게시판 제목</div>
            <div class="content">게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용게시판 내용</div>
            <div class="board-action-wrap">
              <div class="board-name">자유게시판</div>
              <div class="action-wrap">
                <div class="action">
                  <img src="@/assets/images/fi-rr-thumbs-up.png"/> 5
                </div>
                <div class="action">
                  <img src="@/assets/images/fi-rr-comment.png"/> 5
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Hot 게시물 -->
        <div class="hot-posts-wrap">
          <div class="side-section-box-title">Hot 게시물</div>
          
          <div class="side-section-box">
              <div class="title">게시물 제목</div>
              <div class="date">2023-08-15</div>
          </div>
          <div class="side-section-box">
              <div class="title">게시물 제목</div>
              <div class="date">2023-08-15</div>
          </div>
          <div class="side-section-box">
              <div class="title">게시물 제목</div>
              <div class="date">2023-08-15</div>
          </div>
          <div class="side-section-box">
              <div class="title">게시물 제목</div>
              <div class="date">2023-08-15</div>
          </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'SideSection',
    }
</script>

<style scoped lang="scss">
    @import "@/assets/scss/sidesection.scss";
</style>