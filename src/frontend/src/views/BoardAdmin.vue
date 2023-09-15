<template>
    <div class="board-admin">
        <AdminSidebar/>

        <div class="admin-content-wrap">
            <AdminHeader/>

            <div class="admin-content">
                <div class="admin-content-inner">
                    <strong class="page-title">게시판 관리</strong>

                    <div class="grid-table">
                        <div class="grid-head">
                            <div>번호</div>
                            <div>게시판 이름</div>
                            <div>등록일자</div>
                            <div>사용여부</div>
                            <div>삭제</div>
                        </div>
                         <div class="grid-body" v-for="(board, index) in boards" :key="index">
                              <div>{{ index + 1 }}</div>
                            <div>{{ board.name }}</div>
                            <div>{{ board.crdate }}</div>
                            <div>
                                <select>
                                    <option>Y</option>
                                    <option>N</option>
                                </select>
                            </div>
                            <div>
                                <button class="delete-btn">게시판 삭제</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import AdminSidebar from '@/components/layout/AdminSidebar.vue';
import AdminHeader from '@/components/layout/AdminHeader.vue';
    export default {
        name: 'ReportAdmin',
        components: {
            AdminSidebar,
            AdminHeader
        },
         data() {
        return {    
          boards: [
            {
                name: '자유게시판', 
                crdate: '2023-09-01',
            },
             {
                name: '자유게시판', 
                crdate: '2023-09-01',
            },
          ]
        };
      },
    }
</script>

<style lang="scss">
@import "@/assets/scss/boardadmin.scss";
</style>