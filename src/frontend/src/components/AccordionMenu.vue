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

<style scoped>
.accordion-menu {
  width: 300px;
  margin: 0 auto;
}

.accordion-item {
  border-radius: 4px;
}

.accordion-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  cursor: pointer;
  border-bottom: 1px solid #f0f0f0;
  border-radius: 4px 4px 0 0;
  font-size: 14px;
}

.accordion-icon {
  font-size: 18px;
}

.accordion-content {
  background-color: #fff;
  border-radius: 0 0 4px 4px;
  font-size: 12px;
  color: #9C9E9C;
}

.sub-items {
  display: flex;
}

.column {
  flex: 1;
  padding: 0 10px;
}

.sub-items ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sub-items li {
  margin: 16px 0;
  padding: 0px 10px;
}
</style>
