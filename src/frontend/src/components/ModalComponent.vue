<template>
  <div class="modal-component">
    <div class="modal-overlay" @click="closeModal">
      <div class="modal-container" @click.stop>
        <div class="modal-header">
          <slot name="header">
            <span class="modal-title"></span>
            <span class="modal-close" @click="closeModal">×</span>
          </slot>
        </div>
        <div class="modal-content">
          <slot></slot>
        </div>
        <div class="modal-footer">
          <slot name="footer">
            <span class="modal-save" @click="saveModal">저장</span>
          </slot>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModalComponent",
  methods: {
    closeModal() {
      this.$emit("close");
    }
  }
};
</script>

<style scoped>
  @import "@/assets/scss/modal.scss";
</style>