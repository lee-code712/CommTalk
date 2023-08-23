<template>
  <div class="accordion-menu">
    <div v-for="(category, index) in categories" :key="index" class="accordion-item">
      <div class="accordion-header" @click="toggleAccordion(index)">
        {{ category.name }}
        <span :class="['accordion-icon', { 'expanded': category.expanded }]">
          {{ category.expanded ? '-' : '+' }}
        </span>
      </div>
      <div v-if="category.expanded" class="accordion-content">
        <div class="sub-items">
          <div class="column">
            <ul>
              <li v-for="(item, itemIndex) in category.items.slice(0, Math.ceil(category.items.length / 2))" :key="itemIndex">{{ item }}</li>
            </ul>
          </div>
          <div class="column">
            <ul>
              <li v-for="(item, itemIndex) in category.items.slice(Math.ceil(category.items.length / 2))" :key="itemIndex">{{ item }}</li>
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
