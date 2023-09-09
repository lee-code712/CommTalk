<template>
  <div class="about">
    <HeaderLayout />
    <SubHeader />

    <div class="full-container">
        <div class="left-content">
          <div class="board-content-wrap">
            <div class="board-content-header">
              <div class="board-title-wrap">
                <div class="label-title-wrap">
                  <div class="board-label">{{ boardLabel }}</div>
                  <strong class="title">{{ title }}</strong>
                </div>

                <div class="msg-notice-wrap">
                  <span>쪽지</span> 
                  <span>신고</span>
                </div>
              </div>
              
              <div class="writer-date-wrap">
                <span>{{ writer }}</span>
                <span>{{ date }}</span>
              </div>
            </div>

            <div class="board-content-body">
              <div class="board-content">{{ content }}</div>

              <div class="hashtags">
                <div class="hashtag" v-for="(tag, index) in hashtags" :key="index">{{ tag }}</div>
              </div>

              
            </div>
          </div>

          <div class="other-comment-wrap">
              <div class="activity-wrap" :class="{ 'no-margin' : showComment.open }">
                <div class="comment-btn">
                  <img style="width: 12px; height: 12px;" src="@/assets/images/fi-rr-comment.png"/>
                  댓글 {{ commentCount }}
                  <div class="hr">|</div> 
                  <span class="angle-icon" @click="toggleComment">
                    <img style="width: 16px; height: 16px;" v-bind:src="angleIconSrc" />
                  </span>
                </div>
                <div class="like-btn" @click="changeLikeImg()">
                  <img style="width: 14px; height: 14px;" :src="require(`@/assets/images/${likeImgName}.png`)"/>
                  공감 {{ likeCount }}
                </div>
                <div class="scrap-btn" @click="changeScrapImg()">
                  <img style="width: 14px; height: 14px;" :src="require(`@/assets/images/${scrapImgName}.png`)"/>
                  스크랩 {{ scrapCount }}
                </div>
              </div>

              <div class="comment-wrap" v-if="showComment.open">
              <div class="comment-box" v-for="(comment, index) in comments" :key="index">
                <div class="comment-box-inner">
                  <div class="comment-header">
                    <div class="writer-date-wrap">
                      <span>{{ comment.writer }}</span>
                      <span>{{ comment.commentDate }}</span>
                    </div>

                    <div class="msg-notice-wrap">
                      <span>쪽지</span> 
                      <span>신고</span>
                    </div>
                  </div>

                  <div class="comment-body">
                    <div class="comment">
                      {{ comment.commentContent }}
                    </div>

                    <div class="activity-wrap">
                       <div class="comment-btn" @click="toggleReply(index)">
                        <img style="width: 12px; height: 12px;" src="@/assets/images/fi-rr-comment.png"/>
                        대댓글 달기 {{ comment.commentCount }}
                      </div>
                      <div class="like-btn">
                        <img style="width: 14px; height: 14px;" src="@/assets/images/fi-rr-thumbs-up.png"/>
                        공감하기 {{ comment.likeCount }}
                      </div>
                    </div>
                  </div>
                </div>
                  
                <div class="comment-box on reply" v-if="comment.showReply">
                    <div class="comment-box-inner">
                      <img style="width: 14px; height: 14px;" src="@/assets/images/fi-rr-chat-arrow-down.png"/>
                      <div class="my-comment-wrap">
                        <textarea class="my-comment" placeholder="댓글을 입력하세요."></textarea>
                        <div class="my-comment-btn-wrap">
                          <div class="file-anonymous-wrap">
                            <div class="anonymous">
                              <label>
                                <input type="checkbox"/>
                                <span>익명</span>
                              </label>
                            </div>
                          </div>
                          <button class="submit-btn">등록</button>
                        </div>
                      </div>
                    </div>
                </div>
              </div>

              <div class="comment-box on" v-for="(reply, index) in replies" :key="index">
                <div class="comment-box-inner">
                  <img style="width: 14px; height: 14px;" src="@/assets/images/fi-rr-chat-arrow-down.png"/>
                  <div class="detail-info">
                    <div class="comment-header">
                      <div class="writer-date-wrap">
                        <span>{{ reply.writer }}</span>
                        <span>{{ reply.replyDate }}</span>
                      </div>

                      <div class="msg-notice-wrap">
                        <span>쪽지</span> 
                        <span>신고</span>
                      </div>
                    </div>

                    <div class="comment-body">
                      <div class="comment">
                        {{ reply.replyContent }}
                      </div>

                      <div class="activity-wrap">
                        <div class="like-btn">
                          <img style="width: 14px; height: 14px;" src="@/assets/images/fi-rr-thumbs-up.png"/>
                          공감하기 {{ reply.likeCount }}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="my-comment-wrap">
            <textarea class="my-comment" placeholder="댓글을 입력하세요."></textarea>
            <div class="my-comment-btn-wrap">
              <div class="file-anonymous-wrap">
                <div class="anonymous">
                  <label>
                    <input type="checkbox"/>
                    <span>익명</span>
                  </label>
                </div>
              </div>
              <button class="submit-btn">등록</button>
            </div>
          </div>

          <button type="button" class="list-btn">목록보기</button>
        </div>
      </div>
      <FooterLayout/>
  </div>
</template>

<script>
import HeaderLayout from "@/components/layout/HeaderLayout.vue";
import SubHeader from "@/components/layout/SubHeader.vue";
import FooterLayout from "@/components/layout/FooterLayout.vue";
export default {
  name: 'AboutView',
  components: {
    HeaderLayout,
    SubHeader,
    FooterLayout
  },
  data() {
    return {
      showComment: {
        open: 'true'
      },
      boardLabel: '자유 게시판',
      title: '벌써 8월 말이라닝..',
      writer: '작성자',
      date: '2023-08-25',
      content: `이미 학교에서 국문법을 우리는 배웠다. 국문법은 왜 필요할까? 그 때나 지금이나 잘 이해하지 못해 국문법을 어렵게 생각한 것이다. 무엇이든 알고 나면 쉽다. 모르는 것은 언제나 어렵다고 생각하기 때문에 어려운 것이다.

이제는 누구나 글을 쓰거나 써야하는 시대가 되었다. 그 국문법 즉 문법이 문제다. 짧은 글이든 긴 글이든 문장은 문법에 맞게 써야한다. 그래야 전달하고자 하는 의미를 독자가 쉽게 이해할 수 있기 때문이다. 그래서 우리는 무엇을 배우든 왜 내가 이것을 배우는가? 왜 써야 하는가? 그 필요성을 먼저 이해하는 태도가 필요하다.

 

 다음은 한글 문장의 기본 형식이다. 오늘은 그 두 번째를 살펴보려한다.

 

주어+서술어 : 개가 짓는다.

주어+목적어+서술어 : 개가 밥을 먹는다.

주어+ 보어+서술어 : 이것은 개가 아니다.

주어+목적어+보어+서술어 : 개는 사람을 주인으로 생각한다. 『인터넷 글쓰기 의 달인』 최기호·김미형·이영숙·강옥희·임소영·김슬웅 지음, 세종서적, 28

 

  주어 + 목적어 + 서술어 : 개가 밥을 먹는다.

 

  한글 문장의 기본 2형식은 1형식의 주어+서술어, 즉 서술어 앞에 목적어가 추가 되는 것이다. 다시 말해 개가(주어) 밥을(목적어) 먹는다(서술어). 가 되는 것이다. 목적어의 목적격 조사의 사전적 의미를 보자. 목적격 조사란 “체언 뒤에 붙어, 그 체언을 주어의 동작이나 작용의 목적물이 되게 하는 조사로 ‘를 · 을’이 있다. 체언 뒤에 ‘를 · 을’이 붙으면 목적어(문장에서 동사의 동작의 대상이 되는 말. 곧 타동사의 목적이 되는 말)가 된다는 것을 기억하자. 다음 예문을 보자.`,
      hashtags: ['# 가을', '# 스무살', '# 내년 반오십'],
      commentCount: 20,
      likeCount: 10,
      scrapCount: 10,
      scrapImgName: 'fi-rr-bookmark',
      likeImgName: 'fi-rr-thumbs-up',
      comments: [
        {
          writer: '작성자',
          commentDate: '2023-08-25',
          commentContent: '시간이 정말 빨리 가는 것 같아여 속은 고딩인뎅',
          commentCount: 10,
          likeCount: 14
        },
        {
          writer: '작성자',
          commentDate: '2023-08-25',
          commentContent: '시간이 정말 빨리 가는 것 같아여 속은 고딩인뎅',
          commentCount: 10,
          likeCount: 14
        },
      ],
      replies: [
        {
          writer: '작성자',
          replyDate: '2023-08-26',
          replyContent: '씻기 귀찮넹',
          likeCount: 3
        }
      ]
    };
  },
   computed: {
      angleIconSrc() {
        return this.showComment.open
          ? require('@/assets/images/fi-rr-angle-small-up.png')
          : require('@/assets/images/fi-rr-angle-small-down.png');
      },
    },
    methods: {
      toggleComment() {
        this.showComment.open = !this.showComment.open;
      },
       toggleReply(index) {
        this.comments[index].showReply = !this.comments[index].showReply;
      },
      changeScrapImg() {
        if (this.scrapImgName === 'fi-rr-bookmark') {
            this.scrapImgName = 'fi-sr-bookmark';
        } else {
            this.scrapImgName = 'fi-rr-bookmark';
        }
      },
      changeLikeImg() {
        if (this.likeImgName === 'fi-rr-thumbs-up') {
            this.likeImgName = 'fi-sr-thumbs-up';
        } else {
            this.likeImgName = 'fi-rr-thumbs-up';
        }
      }
    },
};
</script>

<style scoped lang="scss">
  @import "@/assets/scss/detail.scss";
</style>