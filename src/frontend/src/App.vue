<template>
  <div id="app">
    <HeaderLayout/>

    <div class="content-wrap">
      <div class="left-content">
        <router-view class="router-view" />
      </div>

      <div class="right-content">
        <RightContent/>
      </div>
    </div>
  </div>
</template>

<script>
import HeaderLayout from '@/components/layout/HeaderLayout.vue'
import RightContent from '@/components/layout/RightContent.vue'

export default {
  name: 'App',
  components: {
    HeaderLayout,
    RightContent,
  }
}
</script>

<style lang="scss">
  @import '@/assets/scss/common.scss';
</style>

<style scoped lang="scss">
  @import '@/assets/scss/app.scss';

  .content-wrap {
    display: flex;
    gap: 20px;
    margin: 0 auto;
    width: 1200px;

    .left-content {
      width: 70%;
    }
    .right-content {
      width: 30%;
    }
  }
</style>