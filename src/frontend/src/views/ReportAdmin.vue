<template>
    <div class="report-admin">
        <AdminSidebar/>

        <div class="admin-content-wrap">
            <AdminHeader/>

            <div class="admin-content">
                <div class="admin-content-inner">
                    <strong class="page-title">신고 관리</strong>

                    <div class="report-options-container">
                            <select>
                                <option>게시판 전체</option>
                                <option>자유게시판</option>
                            </select>
                            <select>
                                <option>상태</option>
                                <option>대기중</option>
                                <option>처리됨</option>
                                <option>거부됨</option>
                            </select>
                    </div>

                    <div class="grid-table">
                        <div class="grid-head">
                            <div>게시판</div>
                            <div>신고대상/정보</div>
                            <div>신고대상</div>
                            <div>신고내용</div>
                            <div>내용</div>
                            <div>상태</div>
                        </div>
                         <div class="grid-body" v-for="(report, index) in reports" :key="index" @click="openModal(report)">
                            <div>
                                <div class="board-box">
                                    {{ report.board }}
                                </div>
                            </div>
                            <div>
                                post id: <br/>
                                {{ report.postId }} <br/>
                                comment id: <br/>
                                {{ report.commentId }}
                            </div>
                            <div>
                                target user: <br/>
                                {{ report.targetUser }} <br/>
                                report user: <br/>
                                {{ report.reportUser }}
                            </div>
                            <div>{{ report.reportContent }}</div>
                            <div>{{ report.content }}</div>
                            <div>
                                <div :class="getStatusClass(report.status)">
                                    {{ report.status }}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
          <ModalComponent v-if="showModal" :report="selectedReport" @close="showModal = false">
        <template #header>
          <div class="modal-title">신고 관리</div>
          <div class="modal-close" @click="showModal = false">×</div>
        </template>
        <div>
            <div class="selected-report-wrap">
                 <div class="report-box">
                    <div class="report-title">게시판</div>
                    <div class="report-content">
                    {{ selectedReport.board }}
                    </div>
                </div>

                <div class="report-box">
                    <div class="report-title">신고대상/정보</div>
                    <div class="report-content">
                        <div>post id:</div>
                        <div>
                            {{ selectedReport.postId }}
                        </div>
                        <div>comment id:</div>
                        <div>         
                            {{ selectedReport.commentId }}
                        </div>
                    </div>
                </div>
                
                <div class="report-box">
                    <div class="report-title">신고대상</div>
                    <div class="report-content">
                        <div>target user:</div>
                        <div>{{ selectedReport.targetUser }}</div>
                        <div>report user:</div>
                        <div>{{ selectedReport.userInfo }}</div>
                    </div>
                </div>

                <div class="report-box">
                    <div class="report-title">신고내용</div>
                    <div class="report-content">{{ selectedReport.reportContent }}</div>
                </div>

                <div class="report-box">
                    <div class="report-title">내용</div>
                    <div class="report-content">{{ selectedReport.content }}</div>
                </div>

                <div class="report-box">
                    <div class="report-title">상태</div>
                    <div class="report-content">
                        <select v-model="selectedReport.status">
                        <option value="대기중" :selected="selectedReport.status === '대기중'">대기중</option>
                        <option value="차단됨" :selected="selectedReport.status === '차단됨'">차단됨</option>
                        <option value="거부됨" :selected="selectedReport.status === '거부됨'">거부됨</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
      </ModalComponent>
    </div>
</template>

<script>
import ModalComponent from "@/components/ModalComponent.vue";
import AdminSidebar from '@/components/layout/AdminSidebar.vue';
import AdminHeader from '@/components/layout/AdminHeader.vue';
    export default {
        name: 'ReportAdmin',
        components: {
            AdminSidebar,
            AdminHeader,
            ModalComponent
        },
         data() {
        return {    
            showModal: false,
      selectedReport: null,
          reports: [
            {
                board: '자유게시판', 
                userInfo: '2023-09-01', 
                status: '대기중', 
                postId: '1234', 
                commentId: '0123',
                targetUser: '김혜란',
                reportUser: '이유리',
                reportContent: 'aaa',
                content: 'aasss'
            },
            {
                board: '자유게시판', 
                userInfo: '2023-09-01', 
                status: '차단됨', 
                postId: 'bbb', 
                commentId: '1222', 
                targetUser: '이유리',
                reportUser: '김혜란',
                reportContent: 'aaa',
                content: 'vddd'
            },
            {
                board: '자유게시판', 
                userInfo: '2023-09-01', 
                status: '거부됨', 
                postId: 'bbb', 
                commentId: '1222', 
                targetUser: '이유리',
                reportUser: '김혜란',
                reportContent: 'aaa',
                content: 'vddd'
            }
          ]
        };
      },
      methods: {
        getStatusClass(status) {
            switch(status) {
                case '대기중':
                    return 'pending-box';
                case '차단됨':
                    return 'blocked-box';
                case '거부됨':
                    return 'denied-box';
                default:
                    return '';
            }
        },
        openModal(report) {
            // 프록시 객체에서 데이터 추출
  const { board, userInfo, status, postId, commentId, targetUser, reportUser, reportContent, content } = report;

  // 데이터를 객체로 다시 생성
  const realData = {
    board,
    userInfo,
    status,
    postId,
    commentId,
    targetUser,
    reportUser,
    reportContent,
    content
  };
console.log(realData);
  // 모달에 전달할 데이터 설정
  this.selectedReport = realData;
  this.showModal = true;
    },
      }
    }
</script>

<style lang="scss">
.report-admin {
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

            .report-options-container {
                display: flex;
                align-items: center;
                gap: 10px;
                margin-top: 40px;
                border-radius: 10px;

                    select {
                        padding: 8px 10px;
                        border: none;
                        border-radius: 8px;
                        background-color: #FFF;
                        box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 2px 0px;
                    }
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
                    grid-template-columns: 10% 20% 20% 20% 20% 10%;
                    padding: 0px 0px 16px 20px;
                    color: #313A45;
                    font-size: 12px;
                    font-weight: bold;
                }

                .grid-body {
                    display: grid;
                    grid-template-columns: 10% 20% 20% 20% 20% 10%;
                    align-items: center;
                    padding: 20px 20px;
                    border-top: 1px solid #F5F5F5;
                    font-size: 14px;
                    color: var(--gray80);

                    &:hover {
                        background-color: var(--gray10);
                        cursor: pointer;
                    }

                    .board-box,
                    .pending-box,
                    .blocked-box,
                    .denied-box {
                          display: inline-block;
                            text-align: center;
                            vertical-align: middle; 
                            width: 80px;
                            height: 30px;
                            border: 1px solid;
                            border-radius: 8px;
                            font-size: 14px;
                            line-height: 30px;
                            
                        border: 1px solid var(--gray40);
                    }

                    
                    .board-box {
                        width: 90px;
                        background-color: var(--blue10);
                        border: none;
                        color: var(--blue80);
                    }

                    .pending-box {
                        color: #51894A;
                        &:hover{
                            background-color: var(--gray10);
                            cursor: pointer;
                        }
                    }

                    .blocked-box {
                        color: #C3821A;
                        &:hover{
                            background-color: var(--coral10);
                            cursor: pointer;
                        }
                    }

                    .denied-box {
                        color: #C31A8A;
                        &:hover{
                            background-color: var(--blue10);
                            cursor: pointer;
                        }
                    }
                }
            }
            }
        }
    }

    .selected-report-wrap {
         .board-name {
            display: inline-block;
            padding: 4px 10px;
            background-color: var(--blue10);
            border: none;
            border-radius: 4px;
            color: var(--blue80);
        }

        .report-box {
            margin-top: 4px;
            
                padding: 10px;
                border: 1px solid var(--gray30);
                border-radius: 10px;
            .report-title {
                margin-bottom: 4px;
                padding-bottom: 10px;
                font-size: 12px;
                color: var(--color_primary);
                border-bottom: 1px dashed var(--gray30);
            }
            .report-content {
                font-size: 14px;
                color: var(--color_secondary);
            }
        }
    }
}
</style>