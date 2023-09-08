<template>
  <div class="list-view">
    <HeaderLayout/>
    <SubHeader/>

    <div class="full-container">
            <div class="sub-top-wrap">
                <strong class="board-name">자유 게시판</strong>
                <div class="search-wrap">
                    <input type="search" placeholder="내용을 입력해주세요." />
                    <img src="@/assets/images/fi-rr-search.png"/>
                </div>
            </div>

            <div class="list-wrap">
                <ul>
                    <li class="list" v-for="(board , index) in boards" :key="index">
                        <div class="list-title-wrap">
                            <strong class="title">{{ board.title }}</strong>

                         <img :src="require(`@/assets/images/${board.imgName}.png`)" @click="changeImg(index)" />
                        </div>

                        <div class="list-content">{{ board.content }}</div>

                        <div class="list-detail-info">
                            <div class="comment-like-wrap">
                                <div class="comment-box">
                                    <img src="@/assets/images/fi-rr-comment.png" style="width: 12px; height: 12px;"/>
                                    댓글 {{ board.commentCount }}
                                </div>
                                <div class="like-box">
                                    <img src="@/assets/images/fi-rr-thumbs-up.png" style="width: 14px; height: 14px;"/>
                                    공감 {{ board.likeCount}}
                                </div>
                                <div class="view-box">
                                    <img src="@/assets/images/fi-rr-eye.png" style="width: 14px; height: 14px;"/>
                                    조회수 {{ board.viewCount}}
                                </div>
                            </div>

                            <div class="writer-date-wrap">
                                <div class="writer">{{ board.writer }}</div>
                                <div class="date">{{ board.date }}</div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>

            <div class="paging-wrap">
                <div class="paging-inner">
                    <div class="prev-btn">이전</div>
                    <div class="paging-num-wrap">
                        <div class="on">1</div>
                        <div>2</div>
                        <div>3</div>
                    </div>
                    <div class="next-btn">다음</div>
                </div>
            </div>

            <div class="btn-wrap">
            <button class="write-btn">글쓰기</button>
            </div>
        </div>
        <FooterLayout/>
      </div>
</template>

<script>
  import HeaderLayout from '@/components/layout/HeaderLayout.vue'
  import SubHeader from '@/components/layout/SubHeader.vue'
  import FooterLayout from '@/components/layout/FooterLayout.vue'

  export default {
    name: 'ListView',
    components: {
      HeaderLayout,
      SubHeader,
      FooterLayout
    },
    data() {
      return {
          boards: [
              { writer: '김혜란', date: '2023-08-26', title: '이제 벌써 9월이 다가온다', content: 'ui 변경 중', commentCount: '10', likeCount: '20', viewCount: '150', imgName: 'fi-rr-bookmark' },
              { writer: '이유리', date: '2023-08-28', title: '8월의 마지막', content: '유리언니 백엔드 코드 작성 중', commentCount: '10', likeCount: '20', viewCount: '150', imgName: 'fi-rr-bookmark' }
          ],
      };
    },
    methods: {
        changeImg(index) {
        if (this.boards[index].imgName === 'fi-rr-bookmark') {
            this.boards[index].imgName = 'fi-sr-bookmark';
        } else {
            this.boards[index].imgName = 'fi-rr-bookmark';
        }
        }
    }
  }
</script>

<style scoped lang="scss">
  @import "@/assets/scss/list.scss";
  @import "@/assets/scss/pattern.scss";
  @import "@/assets/scss/layout.scss";
</style>