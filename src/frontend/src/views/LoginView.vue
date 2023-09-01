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
    .login {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 100vh;
        .login-inner {
            display: flex;
            flex-direction: column;
            gap: 10px;
            width: 320px;
            img {
                margin-bottom: 10px;
                width: 100px;
            }

            input {
                padding: 14px 20px;
                border: 1px solid #CFD3DD;
                border-radius: 4px;
            }

            button {
                padding: 14px 0px;
                background-color: #4976EF;
                color: #FFF;
                border: transparent;
                border-radius: 4px;

                &:hover {
                    background-color: #496EEF;
                    cursor: pointer;
                }
            }

            .ask-join-wrap {
                display: flex;
                align-items: center;
                height:34px;
                font-size: 12px;
                color: #c0c0c0;

                &:hover {
                    color: #555;
                    cursor: pointer;
                }
            }
        }
    }
</style>