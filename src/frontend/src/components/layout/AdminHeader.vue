<template>
  <div class="admin-header">
    <div class="admin-header-inner">
      <ul>
        <li>
          <div class="user-profile" @click="toggleDropdown">
            <img src="@/assets/images/user.png" style="width:32px;"/>
            <div class="user-name">김혜란</div>
            <img class="caret-down-image" src="@/assets/images/fi-sr-caret-down.png" />
          </div>
          <div class="dropdown" >
            <!-- 드롭다운 내용 -->
            <ul>
              <li>옵션 1</li>
              <li>옵션 2</li>
              <li>옵션 3</li>
            </ul>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AdminHeader',
  data() {
    return {
      isDropdownVisible: false, // 드롭다운 표시 여부를 저장하는 변수
    };
  },
  methods: {
    toggleDropdown() {
        console.log("@@@@");
      // 드롭다운 표시 여부를 토글
      this.isDropdownVisible = !this.isDropdownVisible;
    },
  },
};
</script>

<style lang="scss">
.admin-header {
    display: flex;
    align-items: center;
    height: 80px;
    width: 100%;
    border-bottom: 1px solid #f1f1f1;
    .admin-header-inner {
        display: flex;
        justify-content: flex-end;
        align-items: center;
        margin: 0 auto;
        width: 1480px;

        .user-profile {
            display: flex;
            align-items: center;
            gap: 4px;
            font-size: 14px;
            color: var(--gray70);

            .caret-down-image {
                width: 16px;
                height: 16px;
            }
        }
        
.dropdown {
  display: none; // 기본적으로 드롭다운은 숨겨진 상태
  position: absolute;
  top: 100%;
  left: 0;
  background-color: white;
  border: 1px solid #ccc;
  width: 150px;
}

.dropdown ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.dropdown ul li {
  padding: 8px 12px;
  cursor: pointer;
}

/* 드롭다운을 표시할 때 display를 변경해줌 */
.dropdown.visible {
  display: block;
}
    }
}
</style>