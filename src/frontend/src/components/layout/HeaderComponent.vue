<template>
    <div class="header-component">
        <div class="logo-wrap">
            <img src="@/assets/images/logo.png"/>
        </div>

        <gnb>
            <div class="form-control">
                <input type="search" placeholder="search for.."/>
                <img src="@/assets/images/fi-rr-search.png"/>
            </div>

            <img src="@/assets/images/fi-rr-envelope.png"/>
            <img src="@/assets/images/fi-rr-user.png"/>
        </gnb>
    </div>
</template>

<script>
    export default {
        name: 'HeaderComponent',
    }
</script>

<style scoped lang="scss">
    @import "@/assets/scss/header.scss";
</style>