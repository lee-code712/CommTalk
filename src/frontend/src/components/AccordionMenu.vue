<template>
  <div class="accordion-menu">
    <div v-for="(category, index) in categories" :key="index" class="accordion-item">
      <div class="accordion-header" @click="toggleAccordion(index)">
        {{ category.categoryName }}
        <span :class="['accordion-icon', { 'expanded': expanded[index] }]">
          {{ expanded[index] ? '-' : '+' }}
        </span>
      </div>
      <div v-if="expanded[index]" class="accordion-content">
        <div class="sub-items">
          <div class="column">
            <ul>
              <li v-for="(board, itemIndex) in category.boards.slice(0, Math.ceil(category.boards.length / 2))" :key="itemIndex">{{ board.boardName }}</li>
            </ul>
          </div>
          <div class="column">
            <ul>
              <li v-for="(board, itemIndex) in category.boards.slice(Math.ceil(category.boards.length / 2))" :key="itemIndex">{{ board.boardName }}</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AccordionMenu',
  props: {
    categories: Array,
    updateCategory: Function,
    expanded: Array
  },
  methods: {
    toggleAccordion(index) {
      this.updateCategory(index);
    },
  },
};
</script>

<style scoped lang="scss">
 @import "@/assets/scss/accordionmenu.scss";
</style>
