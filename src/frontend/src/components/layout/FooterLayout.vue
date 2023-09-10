<template>
    <div class="footer">
        E-mail: hr_0513@naver.com
    </div>
</template>

<script>
    export default {
        name: 'FooterLayout',
    }
</script>

<style lang="scss">
@import "@/assets/scss/pattern.scss";

    .footer {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 40px;
        width: 100%;
        height: 240px;
        text-align: center;
        background-color: var(--gray10);
        border-top: 1px solid var(--gray30);
        color: var(--gray60);
        font-size: 14px;
    }
</style>