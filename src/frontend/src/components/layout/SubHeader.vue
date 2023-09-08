<template>
    <div class="sub-header">
        <div class="sub-header-inner">
            <div class="category-wrap">
                <div class="category-box">
                    <span class="depth1">
                        <span class="txt">커뮤니티</span>
                    </span>
                    <ul class="depth2">
                        <li>자유게시판</li>
                        <li>비밀게시판</li>
                        <li>비밀게시판</li>
                        <li>시사.이슈</li>
                        <li>정보게시판</li>
                    </ul>
                </div>

                <div class="category-box">
                    <span class="depth1">
                        <span class="txt">생활</span>
                    </span>
                    <ul class="depth2">
                        <li>여행</li>
                        <li>요리</li>
                        <li>알바.과외</li>
                        <li>자취</li>
                        <li>건강</li>
                        <li>패션</li>
                    </ul>
                </div>

                <div class="category-box">
                    <span class="depth1">
                        <span class="txt">취미</span>
                    </span>
                    <ul class="depth2">
                        <li>연예.방송</li>
                        <li>게임</li>
                        <li>스포츠</li>
                        <li>독서</li>
                        <li>음악</li>
                        <li>사진</li>
                    </ul>
                </div>

                <div class="category-box">
                    <span class="depth1">
                        <span class="txt">진로</span>
                    </span>
                    <ul class="depth2">
                        <li>개발자</li>
                        <li>공무원</li>
                        <li>교직</li>
                        <li>로스쿨</li>
                        <li>취업상담</li>
                        <li>기타</li>
                    </ul>
                </div>

                <div class="category-box">
                    <span class="depth1">
                        <span class="txt">반려동물</span>
                    </span>
                    <ul class="depth2">
                        <li>강아지</li>
                        <li>고양이</li>
                        <li>토끼</li>
                        <li>너구리</li>
                        <li>고슴도치</li>
                        <li>기타</li>
                    </ul>
                </div>

                <div class="category-banner-wrap">
                    <img src="@/assets/images/category-banner.png" class="category-banner" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'SubHeader',
    }
</script>

<style lang="scss">
.sub-header {
    margin-bottom: 32px;
    padding: 14px 0px;
    width: 100%;
        background-color: var(--gray10);
    .sub-header-inner {
        margin: 0 auto;
        width: 1200px;
        color: var(--gray70);
        font-size: 14px;

        .category-wrap {
            display: flex;
            .category-box {
                padding: 0px 30px;
                border-right: 1px solid var(--gray30);

                &:first-child {
                    border-left: 1px solid var(--gray30);
                }
                .depth1 {
                    display: inline-block;
                    margin-bottom: 16px;
                    color: #292e41;
                    font-size: 16px;
                    font-weight: bold;
                    line-height: 22px;
                    pointer-events: none;
                }
                .depth2 {
                    display: grid;
                    grid-template-columns: repeat(auto-fill, minmax(100px, 1fr));
                    gap: 8px 20px;
                    justify-content: start;
                }
            }

            .category-banner-wrap {
                display: flex;
                justify-content: center;
                width: 400px;
                .category-banner {
                    padding: 10px;
                    width: 340px;
                }
            }
        }
    }
}
</style>