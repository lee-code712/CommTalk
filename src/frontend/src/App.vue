<template>
  <div id="main-app">
    <Header/>
    <div class="content-area">
      <Sidebar/>
      <div class="main-section">
        <router-view/>
        <div class="side-section">
          <SideSection/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from '@/components/layout/HeaderComponent.vue'
import Sidebar from '@/components/layout/SideBar.vue'
import SideSection from '@/components/layout/SideSection.vue'

export default {
  name: 'App',
  components: {
    Header,
    Sidebar,
    SideSection
  }
}
</script>

<style lang="scss">
  @import '@/assets/scss/common.scss';
</style>

<style scoped lang="scss">
  @import '@/assets/scss/app.scss';
</style>