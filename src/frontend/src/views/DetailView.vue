<template>
  <div class="detail">
    <HeaderLayout />
    <SubHeader />

    <div class="full-container">
      <div class="left-content">
        <div class="board-content-wrap">
          <div class="board-content-header">
            <div class="board-title-wrap">
              <div class="label-title-wrap">
                <div class="board-label">{{ post.board.boardName }}</div>
                <strong class="title">{{ post.title }}</strong>
              </div>
    
              <div class="msg-notice-wrap" style="display: none;">
                <span>쪽지</span> 
                <span>신고</span>
              </div>
            </div>
            
            <div class="writer-date-wrap">
              <span>{{ post.author.nickname }}</span>
              <span style="margin-left: 4px;">{{ post.createdAt }}</span>
            </div>
          </div>
    
          <div class="board-content-body">
            <div class="board-content">{{ post.content }}</div>
    
            <div class="hashtags">
              <div class="hashtag" v-for="(tag, index) in post.hashtags" :key="index">#{{ tag.hashtag }}</div>
            </div>
            
            <div class="img-wrap">
              <img v-for="(imageurl, index) in post.attachments" :key="index" :src="getImageUrl(imageurl.fileName)" alt="Image" style="margin-bottom: 20px; width: 140px; height: 100px; border: 1px solid var(--gray40); border-radius: 10px;" />
            </div>
          </div>
        </div>
    
        <div class="other-comment-wrap">
          <div class="activity-wrap" :class="{ 'no-margin' : showComment }">
            <div class="comment-btn" v-if="commentOpen">
              <img style="width: 12px; height: 12px;" src="@/assets/images/fi-rr-comment.png"/>
              댓글 {{ post.commentCnt }}
              <div class="hr">|</div> 
              <span class="angle-icon" @click="toggleComment">
                <img 
                  style="margin-top: 3px; width: 16px; height: 16px;" 
                  :src="showComment ? angleUpImg : angleDownImg"
                />
              </span>
            </div>
            <div class="like-btn" @click="changeLikeImg()">
              <img
                style="width: 14px; height: 14px;"
                :src="post.liked ? likeImgActive : likeImg"
              />
              공감 {{ post.likes }}
            </div>
            <div class="scrap-btn" @click="changeScrapImg()">
              <img
                style="width: 14px; height: 14px;"
                :src="post.scraped ? scrapImgActive : scrapImg"
              />
              스크랩 {{ post.scraps }}
            </div>
          </div>

          <div class="comment-wrap" v-if="showComment">
            <div class="comment-box" v-for="(comment, commentIndex) in comments" :key="commentIndex">
              <div class="comment-box-inner">
                <div class="comment-header">
                  <div class="writer-date-wrap">
                    <span>{{ comment.writer.nickname }}</span>
                    <span style="margin-left: 4px;">{{ comment.createdAt}}</span>
                  </div>

                  <div class="msg-notice-wrap" style="display: none;">
                    <span>쪽지</span> 
                    <span>신고</span>
                  </div>
                </div>

                <div class="comment-body">
                  <div class="comment">
                    {{ comment.content }}
                  </div>

                  <div class="activity-wrap">
                    <div class="comment-btn" @click="toggleReply(commentIndex)">
                      <img style="width: 12px; height: 12px;" src="@/assets/images/fi-rr-comment.png"/>
                      대댓글 달기 {{ comment.childCnt }}
                    </div>
                    <div class="like-btn" @click="toggleLike(comment.commentId)">
                      <img
                        style="width: 14px; height: 14px;"
                        :src="comment.liked ? likeImgActive : likeImg"
                      />
                      공감하기 {{ comment.likes }}
                    </div>
                  </div>
                </div>
              </div>

              <div class="comment-box on reply" v-if="comment.showReply && comment.showReply !== 'undefined'">
                <div class="comment-box-inner">
                  <img style="width: 14px; height: 14px;" src="@/assets/images/fi-rr-chat-arrow-down.png"/>
                  <div class="my-comment-wrap">
                    <textarea v-model="replyData.reply" class="my-comment" placeholder="댓글을 입력하세요."></textarea>
                    <div class="my-comment-btn-wrap">
                      <div class="file-anonymous-wrap">
                        <div class="anonymous">
                           <div class="checkbox-container">
                            <input
                              type="checkbox"
                              :id="'checkbox-' + commentIndex"
                              v-model="replyData.isReplyAnonymous"
                            />
                            <label :for="'checkbox-' + commentIndex">
                              <span class="checkbox-icon"></span>
                                익명
                            </label>
                          </div>     
                        </div>
                      </div>
                      <button class="submit-btn" @click="createComment(postId, comment.commentId)">등록</button>
                    </div>
                  </div>
                </div>
              </div>
              <div class="comment-box on" v-for="(reply, replyIndex) in comment.childs" :key="replyIndex">
                  <div class="comment-box-inner">
                    <img style="width: 14px; height: 14px;" src="@/assets/images/fi-rr-chat-arrow-down.png"/>
                    <div class="detail-info">
                      <div class="comment-header">
                        <div class="writer-date-wrap">
                          <span>{{ reply.writer.nickname }}</span>
                          <span style="margin-left: 4px;">{{ reply.createdAt }}</span>
                        </div>
    
                        <div class="msg-notice-wrap" style="display: none;">
                          <span>쪽지</span> 
                          <span>신고</span>
                        </div>
                      </div>
    
                      <div class="comment-body">
                        <div class="comment">
                          {{ reply.content }}
                        </div>
    
                        <div class="activity-wrap">
                          <div class="like-btn" @click="toggleReplyLike(reply.commentId)">
                            <img
                              style="width: 14px; height: 14px;"
                              :src="reply.likeStatus ? likeImgActive : likeImg"
                            />
                            공감하기 {{ reply.likes }}
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
        </div>

        <div v-if="!commentOpen" class="commentDisableDesc">
          댓글 기능이 비활성화된 게시글입니다.
        </div>
        
        <div class="my-comment-wrap" v-if="commentOpen">
          <textarea v-model="commentData.myComment" class="my-comment" placeholder="댓글을 입력하세요."></textarea>
          <div class="my-comment-btn-wrap">
            <div class="file-anonymous-wrap">
              <div class="anonymous">
                <div class="checkbox-container">
                  <input
                    type="checkbox"
                    :id="'checkbox2-' + commentIndex"
                    v-model="commentData.isCommentAnonymous"
                  />
                  <label :for="'checkbox2-' + commentIndex">
                    <span class="checkbox-icon"></span>
                      익명
                  </label>
                </div>   
              </div>
            </div>
            <button class="submit-btn" @click="createComment(postId)">등록</button>
          </div>
        </div>

        <div class="btn-wrap">
          <a :href="'/list?boardId=' + boardId">
            <button type="button" class="list-btn"><img src="@/assets/images/fi-rr-list.png" style="width: 14px; heigth: 14px;" />목록보기</button>
          </a>
        </div>
      </div>
    </div>
    <FooterLayout/>
  </div>
</template>

<script>
import axios from 'axios';

import HeaderLayout from "@/components/layout/common/HeaderLayout.vue";
import SubHeader from "@/components/layout/common/SubHeader.vue";
import FooterLayout from "@/components/layout/common/FooterLayout.vue";

export default {
  name: 'DetailView',
  components: {
    HeaderLayout,
    SubHeader,
    FooterLayout
  },
  data() {
    return {
      headers: [],
      link: '',
      commentOpen: false,
      likeImg: require('@/assets/images/fi-rr-thumbs-up.png'),
      likeImgActive: require('@/assets/images/fi-sr-thumbs-up.png'),
      scrapImg: require('@/assets/images/fi-rr-bookmark.png'),
      scrapImgActive: require('@/assets/images/fi-sr-bookmark.png'),
      angleUpImg: require('@/assets/images/fi-rr-angle-small-up.png'),
      angleDownImg: require('@/assets/images/fi-rr-angle-small-down.png'),
      postId: this.$route.query.postId,
      showComment: true,
      boardId: '',
      post: [],
      comments: [],
      replies: [],
      commentData: {
        myComment: '',
        isCommentAnonymous: true
      },
      replyData: {
        reply: '',
        isReplyAnonymous: true
      }
    };
  },
  created() {
    this.setupHeaders();
    this.getPostDetail();
    this.getCommentsByPost();
  },
  methods: {
    setupHeaders() { /* http 요청 헤더를 설정하고 엔드포인트에 대한 인증 토큰을 포함 */
      const token = localStorage.getItem('token');
      
      this.link = 'http://' + window.location.host;
      this.headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      };
    },
    changeEngagementAction(refId, actionType) { /* 공감, 스크랩과 같은 상호 작용을 변경 */
      /*
        refId와 actionType의 사용 예시:
        
        1. 게시물 스크랩: 
        - refId: postId
        - actionType: scrap
        
        2. 댓글 공감:
        - refId: commentId
        - actionType: clike
        
        3. 게시물 공감:
        - refId: postId
        - actionType: plike
      */
      
      refId = eval(refId);
      
      const data = {
        "refId": refId,
        "actionType": actionType
      };
    
      axios
      .post(`/api/post/changeEngagementAction`, data, { headers: this.headers })
      .then((response) => {
        console.log(response.data);
        
        /* 현재 페이지를 리로드 */
        this.$router.go();
      })
      .catch((err) => {
        console.error(err);
      });
    },
    toggleLike(commentId) { /* 댓글 공감 상태 변경 */
      /* commentId와 일치하는 댓글을 this.comments 배열에서 찾음 */
      const comment = this.comments.find(comment => comment.commentId === commentId);
      
      if (comment) {
        /* 공감 상태 전환 ex. true -> false, false -> true로 변경 */
        comment.likeStatus = !comment.likeStatus;
        this.changeEngagementAction(commentId, "clike");
        
        /* 강제로 렌더링 */
        this.$forceUpdate(); 
      }
    },
    toggleReplyLike(commentId) { /* 대댓글 공감 상태 변경 */
    /* commentId와 일치하는 대댓글을 this.replies 배열에서 찾음 */
      const reply = this.replies.find(reply => reply.commentId === commentId);
      
      if (reply) {
        /* 공감 상태를 전환 */
        reply.likeStatus = !reply.likeStatus;
        this.changeEngagementAction(commentId, "clike");
        
        /* 강제로 렌더링 */
        this.$forceUpdate(); 
      }
    },
    toggleComment() { /* 댓글 노출 */
      /* 댓글 노출 상태를 전환 */
      this.showComment = !this.showComment;
    },
    toggleReply(index) { /* 대댓글 노출 */
      if (this.comments[index]) {
        /* 대댓글 노출 상태를 전환 */
        this.comments[index].showReply = !this.comments[index].showReply;
      }
    },
    changeLikeImg() { /* 게시물 공감 상태 전환 */
      const postId = this.$route.query.postId;
      console.log("postId");
      console.log(postId);
      /* 공감 상태를 전환 */
      this.post.liked = !this.post.liked;
      this.changeEngagementAction(postId, "plike");
      
      /* 강제로 렌더링 */
      this.$forceUpdate(); 
    },
    changeScrapImg() { /* 게시물 스크랩 상태 전환 */
      const postId = this.$route.query.postId;
      
      /* 스크랩 상태를 전환 */
      this.post.scraped = !this.post.scraped;
      this.changeEngagementAction(postId, "scrap");
      
      /* 강제로 렌더링 */
      this.$forceUpdate();
    },
    createComment(postId, parentId) { /* 새 댓글 생성 */
      /* 댓글 내용과 익명 여부 초기화 */
      let content;
      let isAnonymous;

      if (parentId) { /* 부모 댓글 id가 있을 경우, 대댓글 데이터 사용 */
        content = this.replyData.reply;
        isAnonymous = this.replyData.isReplyAnonymous ? 1 : 0;
      } else { /* 부모 댓글 id가 없을 경우, 일반 댓글 데이터 사용 */
        content = this.commentData.myComment;
        isAnonymous = this.commentData.isCommentAnonymous ? 1 : 0;
      }

      /* 서버에 전송할 데이터 */
      const data = {
        postId: postId,
        parentId: parentId,
        content: content,
        isAnonymous: isAnonymous,
      };

      axios
      .post(`/api/post/createComment`, data, { headers: this.headers })
      .then((response) => {
        console.log(response.data);
        
        /* 현재 페이지를 리로드 */
        this.$router.go();
      })
      .catch((err) => {
        console.error(err);
      });
    },
    getCommentsByPost() { /* 댓글 및 대댓글 데이터 가져옴 */
      const postId = this.$route.query.postId;
      
      axios
      .get(`/api/post/getCommentsByPost/${postId}`, { headers: this.headers })
      .then(response => {
        const commentsData = response.data;

        /* commentsData 배열읭 각 요소를 순회하면서 댓글 및 대댓글에 필요한 정보를 추출 */
        commentsData.forEach(comment => {
          const commentData = {
            commentId: comment.commentId,
            content: comment.content,
            createdAt: comment.createdAt,
            liked: comment.liked,
            likes: comment.likes,
            writer: comment.writer,
            childs: [],
            childCnt: comment.childCnt,
            showReply: false,
          };
        
          comment.childs.forEach(child => {
            const replyData = {
              commentId: child.commentId,
              content: child.content,
              createdAt: child.createdAt,
              liked: child.liked,
              likes: child.likes,
              writer: child.writer,
            };
        
            /* commentData.childs 배열에 대댓글 데이터 저장 */
            commentData.childs.push(replyData);
            /* this.replies 배열에 대댓글 데이터 저장 */
            this.replies.push(replyData);
          });
        
          /* this.comments 배열에 댓글 및 대댓글 데이터 저장 */
          this.comments.push(commentData);
        });

      })
      .catch(err => {
        console.error(err);
      });
    },
    getPostDetail() { /* 게시물의 세부 정보를 가져옴 */
      const postId = this.$route.query.postId;
      
      axios
      .get(`/api/post/getPostDetail/${postId}`, { headers: this.headers })
      .then(response => {
        /* this.post 배열에 게시물의 세부 정보를 저장 */
        this.post = response.data;
        console.log(this.post);
        
        if (this.post.commentable) {
          this.commentOpen = true;
        }
        
        this.boardId = this.post.board.boardId;
      })
      .catch(err => {
        console.error(err);
      });
    },
    getImageUrl(fileName) { /* 이미지 URL 가져옴 */
      const baseUrl = 'http://' + window.location.host;
      const apiUrl = '/api/file/load/' + fileName;
      const url = new URL(apiUrl, baseUrl);
      
      return url.href;
    },
  },
};
</script>

<style scoped lang="scss">
  @import "@/assets/scss/detail.scss";
</style>