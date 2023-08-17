<template>
    <div class="side-section">
        <!-- 실시간 인기 글 -->
        <div class="popular-posts-wrap">
            <div class="side-section-box-title">실시간 인기 글</div>
            
            <div class="side-section-box" v-for="(post, index) in popularPosts" :key="index">
                <div class="writer-date-wrap">
                    <div>작성자</div>
                    <div>{{ post.date }}</div>
                </div>
                <div class="title">{{ post.title }}</div>
                <div class="content">{{ post.content }}</div>
                <div class="board-action-wrap">
                    <div class="board-name">{{ post.boardName }}</div>
                    <div class="action-wrap">
                        <div class="action">
                            <img src="@/assets/images/fi-rr-thumbs-up.png"/> {{ post.likes }}
                        </div>
                        <div class="action">
                            <img src="@/assets/images/fi-rr-comment.png"/> {{ post.comments }}
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Hot 게시물 -->
        <div class="hot-posts-wrap">
            <div class="side-section-box-title">Hot 게시물</div>
            
            <div class="side-section-box" v-for="(post, index) in hotPosts" :key="index">
                <div class="title">{{ post.title }}</div>
                <div class="date">{{ post.date }}</div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'SideSection',
    data() {
        return {
            popularPosts: [
                {
                    writer: '작성자1',
                    date: '2023-08-10',
                    title: '게시판 제목1',
                    content: '게시판 내용1',
                    boardName: '자유게시판',
                    likes: 5,
                    comments: 5
                },
                {
                    writer: '작성자2',
                    date: '2023-08-10',
                    title: '게시판 제목2',
                    content: '게시판 내용2',
                    boardName: '자유게시판',
                    likes: 3,
                    comments: 7
                }
                // Add more posts here...
            ],
            hotPosts: [
                {
                    title: '게시물 제목1',
                    date: '2023-08-15'
                },
                {
                    title: '게시물 제목2',
                    date: '2023-08-15'
                },
                {
                    title: '게시물 제목3',
                    date: '2023-08-15'
                },
                {
                    title: '게시물 제목4',
                    date: '2023-08-15'
                },
            ]
        };
    }
};
</script>

<style scoped lang="scss">
  @import "@/assets/scss/sidesection.scss";
</style>