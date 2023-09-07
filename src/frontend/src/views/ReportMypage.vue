<template>
    <div class="report-mypage">
    <MypageSidebar/>

        <div class="mypage-content-wrap">
          <MypageHeader/>

          <div class="mypage-content">
          <h2>신고 관리</h2>
            <div class="board-wrap">
              <div class="board-box on">자유 게시판</div>
              <div class="board-box">비밀 게시판</div>
            </div>
          </div>
        </div>
    </div>
</template>

<script>
import MypageHeader from '@/components/layout/MypageHeader.vue'
import MypageSidebar from '@/components/layout/MypageSidebar.vue'
    export default {
        name: 'ReportMypage',
      components: {
        MypageSidebar,
        MypageHeader,
      },
      data() {
        return {

        };
      }
    }
</script>

<style lang="scss">
@import "@/assets/scss/pattern.scss";
.report-mypage {
display: flex;

    .mypage-content-wrap {
        margin: 0 auto;
        width: 1248px;
      .mypage-content {
          .board-wrap {
            display: flex;
            margin-top: 40px;
            gap: 10px;
            .board-box {
              padding: 8px 10px;
              border: 1px solid var(--gray40);
              border-radius: 4px;
              color: var(--gray80);
            }
          }
      }
      }
      }
</style>