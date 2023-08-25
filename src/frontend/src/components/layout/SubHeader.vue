<template>
    <div class="sub-header">
        <div class="sub-header-inner">
        카테고리
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
        margin-bottom: 20px;
        width: 100%;
        height: 40px;
        border-bottom: 1px solid #f1f1f1;
        .sub-header-inner {
            display: flex;
            align-items: center;
            margin: 0 auto;
            height: 100%;
            width: 1200px;
        }
    }
</style>