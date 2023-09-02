<template>
    <div class="mypage-header">
        <div>쪽지</div>
        <div>작성자</div>
    </div>
</template>

<script>
    export default {
        name: 'MypageHeader',
    }
</script>

<style lang="scss">
    .mypage-header {
        display: flex;
        justify-content: flex-end;
        align-items: center;
        height: 80px;
        font-size: 14px;
    }
</style>