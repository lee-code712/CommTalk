<template>
    <div class="join">
        <div class="join-inner">
            <img src="@/assets/images/logo.png"/>
            <input type="text" placeholder="아이디"/>
            <input type="password" placeholder="비밀번호"/>
            <input type="text" placeholder="이름"/>
            <input type="text" placeholder="닉네임"/>
            <input type="number" placeholder="전화번호"/>
            <button>회원가입</button>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'JoinView',
    }
</script>

<style lang="scss">
@import "@/assets/scss/join.scss";
</style>