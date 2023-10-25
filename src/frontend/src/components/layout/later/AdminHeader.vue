<template>
  <div class="admin-header">
    <div class="admin-header-inner">
      <ul>
        <li>
          <div class="user-profile" @click="toggleDropdown">
            <img src="@/assets/images/user.png" style="width:32px;"/>
            <div class="user-name">김혜란</div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AdminHeader',
};
</script>

<style lang="scss">
@import "@/assets/scss/later/admin-header.scss";
</style>