<template>
  <div class="search-wrap">
    ddd
  </div>
</template>

<script>
export default {
  props: {
    borderColor: String, // 부모 컴포넌트로부터 전달되는 프로퍼티
  },
};
</script>

<style>
.search-wrap {
  position: relative;
  width: 200px;
  height: 200px;
  overflow: hidden;
}

.search-wrap::after {
  content: "";
  width: 100%;
  height: 100%;
  position: absolute;
  border: 2px solid transparent;
  background: linear-gradient(to right bottom, var(--borderColor)); /* 동적으로 전달된 값 사용 */
  background-origin: border-box;
  background-clip: border-box;
  z-index: -1;
  animation: borderBehavior 1s 1s forwards;
}

@keyframes borderBehavior {
  0% {
    height: 0%;
  }
  100% {
    height: 100%;
  }
}
</style>
