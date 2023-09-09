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
.board-admin {
    display: flex;
    .admin-content-wrap {
        width: calc(100% - 270px);
        .admin-content {
            padding: 38px 0px;
            height: 100vh;
            background-color: #FCFCFE;
            .admin-content-inner {
            margin: 0 auto;
            width: 1480px;

            .page-title {    
                color: #373f57;
                font-size: 36px;
                font-weight: bold;
                letter-spacing: -3px;
                line-height: 56px;
            }

            .grid-table {
                margin-top: 30px;
                padding-top: 16px;
                width: 100%;
                background-color: #FFF;
                border-radius: 10px;
                border: 1px solid #F5F5F5;
                .grid-head {
                    display: grid;
                    grid-template-columns: 10% 40% 20% 20% 10%;
                    padding: 0px 0px 16px 20px;
                    color: #313A45;
                    font-size: 12px;
                    font-weight: bold;
                }

                .grid-body {
                    display: grid;
                    grid-template-columns: 10% 40% 20% 20% 10%;
                    align-items: center;
                    padding: 20px 20px;
                    border-top: 1px solid #F5F5F5;
                    font-size: 14px;
                    color: var(--gray80);
                  
                    .delete-btn{
                          display: inline-block;
                            text-align: center;
                            vertical-align: middle; 
                            width: 100px;
                            height: 30px;
                            border: 1px solid var(--coral110);
                            border-radius: 4px;
                            font-size: 14px;
                            line-height: 30px;
                            
                       background-color: #FFF;
                        color: var(--coral110);
                        &:hover {
                            cursor: pointer;
                       background-color: var(--coral10);
                        }
                    }

                    select {
                       padding: 0px 10px;
                            height: 30px;
                        background-color: #FFF;
                        border-radius: 4px;
                        border: 1px solid var(--gray40);
                        color: var(--gray80);
                        &:hover {
                            cursor: pointer;
                        }
                    }
                }
            }
            }
        }
    }
}
</style>