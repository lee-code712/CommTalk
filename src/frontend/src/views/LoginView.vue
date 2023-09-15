<template>
    <div class="login">
        <div class="login-inner">
            <img src="@/assets/images/logo.png"/>
            <input type="text" placeholder="로그인"/>
            <input type="password" placeholder="비밀번호"/>
            <button>로그인</button>
            <div class="ask-join-wrap">
                <div>회원가입</div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'LoginView',
    }
</script>

<style lang="scss">
@import "@/assets/scss/login.scss";
</style>