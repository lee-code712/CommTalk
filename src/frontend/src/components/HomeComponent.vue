<template>
  <div class="home-component">
    <img style="width: 960px; height: 200px" src="@/assets/images/banner.png"/>
  
    <div class="pinned-wrap">
      <div>게시물 고정</div>
      <button>나의 게시물 설정하기</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeComponent',
  props: {
    msg: String
  }
}
</script>

<style scoped lang="scss">
  @import "@/assets/scss/home.scss";
</style>