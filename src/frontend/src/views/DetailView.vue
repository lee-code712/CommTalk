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
                <div class="hashtag" v-for="(tag, index) in hashtags" :key="index">#{{ tag.hashtag }}</div>
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
                      <span>{{ comment.writer.nickname }}</span>
                      <span>{{ comment.createdAt}}</span>
                    </div>

                    <div class="msg-notice-wrap">
                      <span>쪽지</span> 
                      <span>신고</span>
                    </div>
                  </div>

                  <div class="comment-body">
                    <div class="comment">
                      {{ comment.content }}
                    </div>

                    <div class="activity-wrap">
                       <div class="comment-btn" @click="toggleReply(index)">
                        <img style="width: 12px; height: 12px;" src="@/assets/images/fi-rr-comment.png"/>
                        대댓글 달기 {{ comment.commentCount }}
                      </div>
                      <div class="like-btn">
                        <img style="width: 14px; height: 14px;" src="@/assets/images/fi-rr-thumbs-up.png"/>
                        공감하기 {{ comment.likes }}
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
                        <span>{{ reply.writer.nickname }}</span>
                        <span>{{ reply.createdAt }}</span>
                      </div>

                      <div class="msg-notice-wrap">
                        <span>쪽지</span> 
                        <span>신고</span>
                      </div>
                    </div>

                    <div class="comment-body">
                      <div class="comment">
                        {{ reply.content }}
                      </div>

                      <div class="activity-wrap">
                        <div class="like-btn">
                          <img style="width: 14px; height: 14px;" src="@/assets/images/fi-rr-thumbs-up.png"/>
                          공감하기 {{ reply.likes }}
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

            <a :href="'/list?boardId=' + boardId">
          <button type="button" class="list-btn">목록보기</button>
          </a>
        </div>
      </div>
      <FooterLayout/>
  </div>
</template>

<script>
import axios from 'axios';
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
      boardLabel: '',
      boardId: '',
      title: '',
      writer: '',
      date: '',
      content: ``,
      hashtags: [],
      commentCount: '',
      likeCount: '',
      scrapCount: '',
      scrapImgName: 'fi-rr-bookmark',
      likeImgName: 'fi-rr-thumbs-up',
      comments: [],
      replies: []
    };
  },
 created() {
    this.getPostDetail();
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
      },
      getPostDetail() {
          const postId = this.$route.query.postId;
          console.log(postId);
          
           axios
        .get(`/api/post/getPostDetail/${postId}`, { headers: this.headers })
        .then(response => {
            console.log(response.data);
            const post = response.data;
            this.boardLabel = post.board.boardName;
            this.boardId = post.board.boardId;
            this.title = post.title;
            this.writer = post.author.nickname;
            this.date = post.createdAt;
            this.content = post.content;
            this.hashtags = post.hashtags;
            this.commentCount = post.commentCnt;
            this.likeCount = post.likes;
            this.scrapCount = post.scraps;
            post.comments.map(comment => {
                if (!comment.parentId) {
                    this.comments.push(comment);
                } else {
                    this.replies.push(comment);
                }
            });
        })
        .catch(err => {
          console.error(err);
        });
      }
    },
};
</script>

<style scoped lang="scss">
  @import "@/assets/scss/detail.scss";
</style>