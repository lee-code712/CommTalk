<template>
    <div class="admin-sidebar">
        <div class="logo-wrap">
          <img src="@/assets/images/logo.png"/>
        </div>
        <ul>
            <li :class="{ 'active': $route.path === '/admin/board' }"><router-link to="/admin/board">게시판 관리</router-link></li>
            <li :class="{ 'active': $route.path === '/admin/report' }"><router-link to="/admin/report">신고 관리</router-link></li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: 'AdminSidebar',
    }
</script>

<style lang="scss">
  @import "@/assets/scss/later/admin-sidebar.scss";
</style>