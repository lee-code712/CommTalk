<template>
    <div class="board-mypage">
        <MypageSidebar/>

        <div class="mypage-content-wrap">
          <MypageHeader/>

          <div class="mypage-content">
            <h2>게시판 관리</h2>

            <div class="grid-table">
              <div class="grid-thead">
                <div>번호</div>
                <div>게시판 이름</div>
                <div>등록일자</div>
                <div>사용여부</div>
                <div>삭제</div>
              </div>

              <div class="grid-tbody" v-for="(board, index) in boards" :key="index">
                <div>{{ index + 1 }}</div>
                <div class="board-name">{{ board.name }}</div>
                <div>{{ board.crdate }}</div>
                <div>
                  <select>
                    <option>Y</option>
                    <option>N</option>
                  </select>
                </div>
                <div>
                  <button class="delete-btn">게시판 삭제</button>
                </div>
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
      name: 'BoardMypage',
      components: {
        MypageSidebar,
        MypageHeader,
      },
      data() {
        return {
          boards: [
            {name: '자유게시판', crdate: '2023-09-01'},
            {name: '자유게시판', crdate: '2023-09-01'}
          ]
        };
      }
    }
</script>

<style lang="scss">
  @import "@/assets/scss/pattern.scss";
  .board-mypage {
    display: flex;

    .mypage-content-wrap {
        margin: 0 auto;
        width: 1248px;
      .mypage-content {

        .grid-table {
  margin-top: 40px;
          border: 1px solid var(--gray30);
          border-radius: 8px;
        .grid-thead {
          display: grid;
          grid-template-columns: 8% 47% 15% 15% 15%;
          padding: 14px 0px;
          border-bottom: 1px solid var(--gray30);
          background-color: var(--gray10);
          border-radius: 8px 8px 0px 0px;
          font-size: 14px;
          color: #8391A7;
           div {
              padding: 0px 20px;
            }
        }

          .grid-tbody {
            display: grid;
            grid-template-columns: 8% 47% 15% 15% 15%;
            align-items: center;
            padding: 8px 0px;
            font-size: 14px;
            border-radius: 0px 0px 10px 10px;
            color: #5C667B;

            div {
              padding: 0px 20px;
            }

            .board-name {
              &:hover {
                text-decoration: underline;
                cursor: pointer;
              }
            }

          select {
              padding: 8px 16px;
            border: 1px solid var(--gray30);
            border-radius: 4px;
          }

            .delete-btn {
              padding: 8px 10px;
              background-color: var(--gray10);
              color: var(--gray90);
              border: 1px solid var(--gray30);
              border-radius: 4px;
              &:hover {
                background-color: var(--gray20);
                cursor: pointer;
              }
            }
          }
        }
      }
    }
  }
</style>