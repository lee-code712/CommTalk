<template>
  <div id="app">
    <HeaderLayout v-if="showHeader" />

    <div class="content-wrap">
      <div class="left-content">
        <router-view class="router-view" />
      </div>

      <div class="right-content" v-if="showRightContent">
        <RightContent />
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
  },
  data() {
    return {
      showHeader: true,
      showRightContent: true,
    };
  },
  watch: {
    '$route'(to) {
      this.showHeader = !['/login', '/join'].includes(to.path);
      this.showRightContent = !['/login', '/join'].includes(to.path);
    },
  },
}
</script>

<style lang="scss">
  @import '@/assets/scss/common.scss';
</style>

<style scoped lang="scss">
  @import '@/assets/scss/app.scss';
</style>
