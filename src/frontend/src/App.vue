<template>
  <div id="main-app">
    <div class="content-area">
      <Sidebar/>

      <div class="main-section">
        <div class="header">
          <div class="page-name">게시판</div>
          <gnb>
              <div class="form-control">
                  <input type="search" placeholder="search for.."/>
                  <img src="@/assets/images/fi-rr-search.png"/>
              </div>

              <div class="icon-wrap">
                <img src="@/assets/images/fi-rr-envelope.png"/>
              </div>
              <div class="icon-wrap">
                <img src="@/assets/images/fi-rr-user.png"/>
              </div>
          </gnb>
        </div>

        <div class="main-content">
          <router-view class="router-view" />

          <div class="side-section-wrap">
            <SideSection/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Sidebar from '@/components/layout/SideBar.vue'
import SideSection from '@/components/layout/SideSection.vue'

export default {
  name: 'App',
  components: {
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