<template>
  <div class="about">
    <div class="board-content-wrap">
      <div class="board-content-header">
        <div class="label-title-wrap">
          <div class="board-label">자유 게시판</div>
          <div class="title">벌써 8월 말이라닝..</div>
        </div>

        <div class="msg-notice-wrap">
          <span>쪽지</span> <span>신고</span>
        </div>
      </div>

      <div class="writer-date-wrap">
        <span>작성자</span>
        <span>2023-08-25</span>
      </div>

      <div class="board-content-body">
        <div class="board-content">
          여러분 벌써 9월이 다가와요.. 말도 안됩니당ㅠㅠ
          시간이 너무 빨라요 계속 20살이면 좋겠어요
        </div>

        <div class="hashtags">
          <div class="hashtag"># 가을</div>
          <div class="hashtag"># 스무살</div>
          <div class="hashtag"># 내년 반오십</div>
        </div>

        <div class="activity-wrap">
          <div class="comment-btn">댓글 20</div>
          <div class="like-btn">공감 10</div>
        </div>
      </div>

      <div class="comment-wrap">
        <div class="comment-box">
          <div class="comment-header">
            <div class="writer-date-wrap">
              <span>작성자</span>
              <span>2023-08-25</span>
            </div>

            <div class="msg-notice-wrap">
              <span>쪽지</span> <span>신고</span>
            </div>
          </div>

          <div class="comment-body">
            <div class="comment">
              시간이 정말 빨리 가는 것 같아여 속은 고딩인뎅
            </div>

            <div class="activity-wrap">
              <div class="comment-btn">대댓글 달기</div>
              <div class="like-btn">공감하기</div>
            </div>
          </div>
        </div>
        <div class="comment-box on">
          <div class="comment-header">
            <div class="writer-date-wrap">
              <span>작성자</span>
              <span>2023-08-25</span>
            </div>

            <div class="msg-notice-wrap">
              <span>쪽지</span> <span>신고</span>
            </div>
          </div>

          <div class="comment-body">
            <div class="comment">
              시간이 정말 빨리 가는 것 같아여 속은 고딩인뎅
            </div>

            <div class="activity-wrap">
              <div class="like-btn">공감하기</div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="my-comment-wrap">
      <textarea class="my-comment" placeholder="댓글을 입력하세요."></textarea>
      <div class="my-comment-btn-wrap">
        <div class="file-wrap">이미지 첨부</div>
        <button class="submit-btn">등록</button>
      </div>
    </div>

    <button type="button" class="list-btn">목록보기</button>
  </div>
</template>

<script>
  export default {
    name: 'AboutView',
  }
</script>

<style scoped lang="scss">
  .about {
    .board-content-wrap {
      border: 1px solid #f1f1f1;
      border-radius: 10px;

      .board-content-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
        padding: 28px 28px 0px 28px;

        .label-title-wrap {
          display: flex;
          align-items: center;
          gap: 14px;
          .board-label {
            padding: 6px 10px;
            background-color: #E8EEFF;
            color: #4976EF;
            border-radius: 4px;
            font-size: 12px;
          }

          .title {
            font-size: 20px;
          }
        }
        .msg-notice-wrap {
            display: flex;
            gap: 14px;
            font-size: 12px;
            color: #606060;
          }
      }

      .writer-date-wrap {
        display: flex;
        gap: 10px;
        padding: 0px 28px;
        font-size: 12px;
        color: #606060;
      }

      .board-content-body {
        padding: 0px 28px;
        .board-content {
          margin: 30px 0px;
          font-size: 14px;
          color: #383F57;
        }
      }

      .hashtags {
        display: flex;
        align-items: center;
        gap: 10px;
        margin-bottom: 20px;
        .hashtag {
          padding: 8px 10px;
          font-size: 12px;
          background-color: #F4F6FA;
          border-radius: 50px;
          color: #383F57;
        }
      }

      .activity-wrap {
        display: flex;
        align-items: center;
        gap: 10px;
        .comment-btn,
        .like-btn {
          padding: 6px 10px;
          border: 1px solid #f1f1f1;
          color: #383F57;
          font-size: 12px;
          border-radius: 4px;
          background-color: #FFF;
        }
      }

      .comment-wrap {
        .comment-box {
          &.on {
            background-color: #F8FAFC;
          }
          padding: 28px 28px;
          border-bottom: 1px solid #f1f1f1;
          .comment-header{
            display: flex;
            justify-content: space-between;
            margin-bottom: 8px;
            font-size: 12px;
            color: #606060;
            .msg-notice-wrap {
              display: flex;
              gap: 14px;
            }

            .writer-date-wrap {
              padding: 0px;
            }
          }

          .comment-body {
            .comment {
              margin-bottom: 10px;
              color: #383F57;
              font-size: 14px;
            }
          }
        }
        .comment-box:last-child {
          border-bottom: none;
          border-radius: 0px 0px 10px 10px;
        }
      }
    }

    .my-comment-wrap {
      margin-top: 20px;
      border: 1px solid #f1f1f1;
      border-radius: 10px; 
      .my-comment {
        padding: 28px;
        width: calc(100% - 56px);
        height: 90px;
        border: none;
        border-radius: 10px;
      }

      .my-comment-btn-wrap {
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-top: 1px solid #f1f1f1;
        .file-wrap {
          padding: 10px 28px;
          font-size: 14px;
        }
        .submit-btn {
          padding: 10px 28px;
          font-size: 14px;
          border-radius: 0px 0px 10px 0px;
          background-color: #FFF;
          border: none;
          border-left: 1px solid #f1f1f1;

          &:hover{
            background-color: #F1f1f1;
            cursor: pointer;
          }
        }
      }
    }

    .list-btn {
      margin-top :20px;
      padding: 10px 20px;
      border: 1px solid #f1f1f1;
      border-radius: 4px;
      background-color: #FFF;
    }
  }
</style>
