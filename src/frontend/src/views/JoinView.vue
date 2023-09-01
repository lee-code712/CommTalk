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
    .join {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        .join-inner {
            display: flex;
            flex-direction: column;
            gap: 10px;
            width: 320px;
            input {
                padding: 14px 20px;
                border: 1px solid #CFD3DD;
                border-radius: 4px;
            }

            img {
                margin-bottom: 10px;
                width: 100px;
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
        }
    }
</style>