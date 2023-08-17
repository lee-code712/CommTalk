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
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-container {
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 90%;
  width: 400px;
}

.modal-content {
  padding: 12px 16px;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 16px;
  background-color: #f0f0f0;
  border-bottom: 1px solid #ccc;
  border-radius: 8px 8px 0px 0px;
}

.modal-title {
  font-size: 1.2rem;
  font-weight: bold;
}

.modal-close {
  cursor: pointer;
  font-size: 1.2rem;
  color: #aaa;
}
</style>