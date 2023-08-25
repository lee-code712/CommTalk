<template>
  <div class="side-bar">
    <div class="login-wrap">
      <div class="login-desc">로그인 후 이용해주세요.</div>
      <button type="button" class="login-button">커톡커톡 로그인</button>
    </div>

    <div class="category-wrap">
      <div class="title-angle-wrap">
        <h3>카테고리</h3>
        <img src="@/assets/images/fi-rr-angle-small-up.png"/>
      </div>
    </div>

    <div class="popular-posts">
      <h3>실시간 인기 글</h3>
      <ul>
        <li v-for="(item, index) in items" :key="index">
        <div :class="['num', { 'on': index === 0 }]">
          {{ index + 1 }}
        </div>

          <div class="popular-posts-info">
            <div class="title-comment-wrap">
              <div class="title">{{ item.title }}</div> 
              <div class="comment">[{{ item.comment }}]</div>
            </div>
            <div class="view-like-wrap">
              조회수 {{ item.view }} | 공감수 {{ item.like }}
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'RightContent',
    data() {
      return {
        items: [
          {title: '실시간 인기 글 1', comment: '10', view: '3,200', like: '1,213'},
          {title: '실시간 인기 글 2', comment: '10', view: '3,200', like: '1,213'},
          {title: '실시간 인기 글 3', comment: '10', view: '3,200', like: '1,213'},
          {title: '실시간 인기 글 4', comment: '10', view: '3,200', like: '1,213'},
        ],
        categories: [
        {
          name: '메인 카테고리',
          expanded: true,
          items: ['자유게시판', '비밀게시판', '졸업생게시판', '컴과게시판'],
        },
        {
          name: '서브 카테고리',
          expanded: false,
          items: ['졸업생게시판', '취준생게시판', '연애게시판'],
        },
      ],
      }
    },
    methods: {
    updateCategory(index) {
      this.categories[index].expanded = !this.categories[index].expanded;
      this.categories = [...this.categories];
    },
  },
  }
</script>

<style scoped lang="scss">
  .right-content {
    .login-wrap {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      margin-bottom: 16px;
      padding: 20px;
      border: 1px solid #f1f1f1;
      border-radius: 10px;

      .login-desc {
        margin-bottom: 10px;
        font-size: 14px;
      }

      .login-button {
        padding: 12px 0px;
        width: 100%;
        background-color: #4976EF;
        color: #FFF;
        border: transparent;
        border-radius: 4px;
      }
    }

    .category-wrap {
      margin-bottom: 16px;
      border: 1px solid #f1f1f1;
      border-radius: 10px;

      .title-angle-wrap {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 20px;

        h3 {
          font-size: 16px;
        }

        img {
          width: 20px;
          height: 20px;
        }
      }
    }

    .popular-posts {
      border: 1px solid #f1f1f1;
      border-radius: 10px;

      h3 {
        padding: 20px;
        font-size: 16px;
      }

      ul {
        padding: 10px 20px;
        list-style: none;

        li {
          display: flex;
          align-items: center;
          gap: 18px;

          margin-bottom: 14px;

          .num {
            font-size: 24px;
            font-weight: bold;

            &.on {
              color: #4976EF;
            }
          }

          .popular-posts-info {
            display: flex;
            flex-direction: column;
            gap: 2px;
            font-size: 14px;

            .title-comment-wrap {
              display: flex;
              gap: 8px;
              .comment {
                font-size: 12px;
                color: #4976EF;
              }
            }

            .view-like-wrap {
              font-size: 12px;
              color: #606060;
            }
          }
        }
      }
    }
  }
</style>