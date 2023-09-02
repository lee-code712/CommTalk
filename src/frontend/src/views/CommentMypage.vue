<template>
  <div class="comment-mypage">
    <MypageSidebar />

    <div class="mypage-content-wrap">
      <MypageHeader />
      <div class="mypage-content">
        <h2>{{ activeTab }}</h2>
        <div class="tabs">
          <div
            v-for="(tab, index) in tabs"
            :key="index"
            @click="activeTab = tab"
            :class="{ 'active': activeTab === tab }"
            class="tab-container"
          >
            {{ tab }}
          </div>
        </div>

        <div class="tab-content">
          <div v-show="activeTab === '내가 쓴 글'">
            <div class="top-btns-wrap">
              <div class="btn-wrap">
                <button type="button">전체선택</button>
                <button type="button">선택해제</button>
                <button type="button">선택삭제</button>
                <button type="button">전체삭제</button>
              </div>

              <div class="search-wrap">
                <input type="text" placeholder="내용을 입력하세요." />
                <img src="@/assets/images/fi-rr-search.png" />
              </div>
            </div>
            <ul class="list-wrap">
              <li v-for="(mycomment, index) in mycomments" :key="index">
                <label>
                  <input type="checkbox" />
                </label>
                <div class="list-content">
                  <div class="title">{{ mycomment.title }}</div>
                  <div class="content">{{ mycomment.content }}</div>
                  <div class="detail-info-wrap">
                  <div class="active-wrap">
                    <div>댓글 10</div>
                    <div>공감 14</div>
                    <div>조회수 20</div>
                  </div>
                  <div class="writer-date-wrap">
                    <div class="date">{{ mycomment.date }}</div>
                  </div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
          <div v-show="activeTab === '댓글 단 글'">
            <div class="top-btns-wrap">
              <div class="btn-wrap">
                <button type="button">전체선택</button>
                <button type="button">선택해제</button>
                <button type="button">선택삭제</button>
                <button type="button">전체삭제</button>
              </div>

              <div class="search-wrap">
                <input type="text" placeholder="내용을 입력하세요." />
                <img src="@/assets/images/fi-rr-search.png" />
              </div>
            </div>
            <ul class="list-wrap">
              <li v-for="(commentPost, index) in commentPosts" :key="index">
                <label>
                  <input type="checkbox" />
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
</template>

<script>
import MypageHeader from '@/components/layout/MypageHeader.vue'
import MypageSidebar from '@/components/layout/MypageSidebar.vue'

export default {
  name: 'CommentMypage',
  components: {
    MypageSidebar,
    MypageHeader,
  },
  data() {
    return {
      tabs: ['내가 쓴 글', '댓글 단 글'],
      activeTab: '내가 쓴 글',
      mycomments: [
        { title: '기깔나는 제목이 없을까 생각 중', content: '불안한 마음을 컨트롤하기 위해선 게임이 최고', date: '2023-09-01' },
        { title: '내가 쓴 글 제목', content: '내가 쓴 글 내용', date: '2023-09-02' }
      ],
      commentPosts: [
        {
          title: '댓글 단 글 제목',
          content: '댓글 단 글 내용',
          writer: '작성자',
          date: '2023-09-01',
          commentCnt: '10',
          likeCnt: '14',
          viewCnt: '24'
        },
      ]
    };
  },
};
</script>


<style lang="scss">
  @import "@/assets/scss/common.scss";
  @import "@/assets/scss/pattern.scss";

.comment-mypage {
  display: flex;
  .mypage-content-wrap {

        margin: 0 auto;
        width: 1248px;
      .mypage-content {
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
          border-bottom: 1px solid var(--gray40);
          height: 38px;
          .btn-wrap {
            display: flex;
            gap: 4px;
          
          button {
                padding: 8px 10px;
                border: 1px solid var(--gray40);
                background-color: #FFF;
                border-radius: 4px;
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
            border: 1px solid var(--gray40);
            border-radius: 4px;

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
</style>
