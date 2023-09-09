<template>
    <div class="scrap-mypage">
        <MypageSidebar/>

        <div class="admin-content-wrap">
            <MypageHeader/>

            <div class="admin-content">
                <div class="admin-content-inner">
                    <strong class="page-title">내 스크랩</strong>
  <div class="tab-content">
          <div>
            <div class="top-btns-wrap">
              <div class="btn-wrap">
                <label>
                  <input type="checkbox" class="whole-chk" v-model="isAllSelected" @change="selectAll" />
                </label>
                <button type="button" @click="deselectAll">선택해제</button>
                <button type="button" @click="deleteSelected">선택삭제</button>
              </div>

              <div class="search-wrap">
                <input type="text" placeholder="내용을 입력하세요." />
                <img src="@/assets/images/fi-rr-search.png" />
              </div>
            </div>
            <ul class="list-wrap">
              <li v-for="(commentPost, index) in commentPosts" :key="index">
                <label>
                  <input type="checkbox" v-model="commentPost.selected" />
                </label>
                <div class="list-content">
                  <div class="title">{{ commentPost.title }}</div>
                  <div class="content">{{ commentPost.content }}</div>
                  <div class="detail-info-wrap">
                    <div class="active-wrap">
                      <div>댓글 {{ commentPost.commentCnt }}</div>
                      <div>공감 {{ commentPost.likeCnt }}</div>
                      <div>조회수 {{ commentPost.viewCnt }}</div>
                    </div>

                    <div class="writer-date-wrap">
                      {{ commentPost.writer }}
                      {{ commentPost.date }}
                    </div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import MypageSidebar from '@/components/layout/MypageSidebar.vue';
import MypageHeader from '@/components/layout/MypageHeader.vue';
    export default {
        name: 'LikeMypage',
        components: {
            MypageSidebar,
            MypageHeader,
        },
         data() {
    return {
      commentPosts: [
        {
          title: '댓글 단 글 제목',
          content: '댓글 단 글 내용',
          writer: '작성자',
          date: '2023-09-01',
          commentCnt: '10',
          likeCnt: '14',
          viewCnt: '24',
          selected: false, // 각 항목의 체크 상태를 저장
        },
        {
          title: '댓글 단 글 제목2',
          content: '댓글 단 글 내용',
          writer: '작성자',
          date: '2023-09-01',
          commentCnt: '10',
          likeCnt: '14',
          viewCnt: '24',
          selected: false, // 각 항목의 체크 상태를 저장
        },
      ],
      isAllSelected: false, // 전체 체크박스 상태를 추적
    };
  },
  methods: {
    selectAll() {
      // 전체 체크박스 상태에 따라 각 체크박스 선택 여부를 업데이트
      const isChecked = this.isAllSelected;
      this.commentPosts.forEach((post) => (post.selected = isChecked));
    },
    deselectAll() {
      // 모든 체크박스 선택 해제
      this.commentPosts.forEach((post) => (post.selected = false));
      // 전체 체크박스도 선택 해제
      this.isAllSelected = false;
    },
    deleteSelected() {
      // 선택된 항목 삭제
      const selectedPosts = this.commentPosts.filter((post) => post.selected);
      selectedPosts.forEach((post) => {
        // 삭제 로직 추가 (예: API 호출 또는 배열에서 제거)
        // 이 예제에서는 배열에서 제거만 수행
        const index = this.commentPosts.indexOf(post);
        if (index !== -1) {
          this.commentPosts.splice(index, 1);
        }
      });
      // 선택 해제 및 전체 체크박스 초기화
      this.deselectAll();
    },
  },
    }
</script>

<style lang="scss">
.scrap-mypage {
    display: flex;
    .admin-content-wrap {
        width: calc(100% - 270px);
        .admin-content {
            padding: 38px 0px;
            height: 100vh;
            background-color: #FCFCFE;
            .admin-content-inner {
            margin: 0 auto;
            width: 1480px;

            .page-title {    
                color: #373f57;
                font-size: 36px;
                font-weight: bold;
                letter-spacing: -3px;
                line-height: 56px;
            }
    .tabs {
        display: flex;
        justify-content: space-around;
        margin-top: 40px;
        padding: 10px;
        border-radius: 10px;
        background-color: var(--gray10);
        font-size: 14px;
          .tab-container {
            cursor: pointer;
            padding: 10px 10px;
            width: 100%;
            text-align: center;
            border-radius: 5px;

            &.active {
            background-color: #fff;
            font-weight: bold;
            }

           
          }
        }
      }
      .tab-content {
        .top-btns-wrap {
          display: flex;
          justify-content: space-between;
          padding: 30px 0px 14px 0px;
          height: 38px;
          .btn-wrap {
            display: flex;
            align-items: center;
            gap: 4px;

                label {
                    margin: 0px 14px 0px 30px;
                }
          
          button {
                padding: 8px 10px;
                border: none;
                background-color: #FFF;
                border-radius: 4px;
                  box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 2px 0px;
                &:hover {
                  background-color: var(--gray10);
                  cursor: pointer;
                }
              }
          } 

          .search-wrap {
            display: flex;
            align-items: center;
            padding: 0px 10px;
            border-radius: 4px;
              box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 2px 0px;

            input {
              width: 180px;
              border: none;
            }
            img {
              width: 16px;
              height: 16px;
            }
          }
        }
        .list-wrap {
          background-color:#FFF;
          border: 1px solid #F5F5F5;
          border-radius: 10px;
          li {
            display: flex;
            align-items: center;
            gap: 20px;
            padding: 30px;
            width: calc(100% - 40px);
            border-bottom: 1px solid var(--gray30);
            .writer-date-wrap {
              margin-bottom: 4px;
              font-size: 12px;
              color: #8694A9;
            }

            &:last-child {
              border-bottom: none;
            }
            .list-content {
              width: 100%;
                        .title {
            color: #383F57;
            font-size: 20px;
            &:hover {
              text-decoration: underline;
              cursor: pointer;
            }
          }

          .content {
            margin-top: 10px;
            color: #8694A9;
            font-size: 14px;
          }

          .detail-info-wrap {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
            width: 100%;
          .active-wrap {
            display: flex;
            gap: 10px;
            font-size: 12px;
            color: #8694A9;
          }
          }
            }
          }
        }
            }
        }
    }
}
</style>