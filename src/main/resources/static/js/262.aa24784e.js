"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[262],{8526:function(e,t,n){n.d(t,{Z:function(){return _}});var o=n(6252),l="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABMAAAATCAYAAAByUDbMAAAACXBIWXMAACxLAAAsSwGlPZapAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAJjSURBVHgBlVTNbtNAEJ7ZrE1IimTewH2Cuk/Q5Ama3hCKhJFoxS3JAan8SHGlIjiaWwQ9uFKQegOeIMkTNHkC8gg+0B5iZ4cZB4vUcaj4Dvbu7O58MzvfLEIJWn7X2dG1DiA2eOoBggMEMY+nBpYzk0B4FX2cF89h0ckju94ngC4SxgZogkhTJJgTgkuEngI8ICSHjAm/XnzolTp74p+6FVuNFCmHD37SNR1GYRAX2f2XgZua1AcgJqX5cmGaeZSYR1S3a9fMChWyjqIvwRTugThdmmTEDmO9uG1GURgrWZDUENCtKKtZdPT0+JXXPnndkv+6PRoE8yUTczxeqmvdLDJJT9uVn2DwbHhxHqzf345V/8bBNnIbEUVWctuTKHJb++QNn8G+Xtw8VtpSXamUTn+F68x1/TBcd5QxI/p5FDn0wg6BiyV2BQr3OO/JOlt2UKlnUAbEzp10Iy6SoinbDxSn5yHCnXvy/cCBbRDNFUE04a/IhhzDOtpgAyivKG2xM4mSfBULcmNRYW9jP+/VYD2HchKWBudLBF5xbTg4H2tl7fLwkndOuCsuK6CbZRpcnacZZqUl7OjkZrdYBIFIpFqtOleDzV4U5NLi9uppKW1qJ/0/JQ9kQ/vFacMgtCqoDgnQBSN6egvSPvwZLxNzlreQtldVt/SD71k7tY/fsaaoY4j2+f4O+XYCuA+GApPCD2XjdS74zJlIgaMbMatbWvptkGcJYT78/H5fpllvihT4so9YbzH8D9iRrlnNv9MC8pT/6YMlUvZMYdnm7HlJkxYpaiGpPXkMVw7MjJMZb3vrfgOFNRkcCe7+IAAAAABJRU5ErkJggg==";const r={class:"admin-header"},s={class:"admin-header-inner"},d=(0,o._)("img",{src:l,style:{width:"32px"}},null,-1),a=(0,o._)("div",{class:"user-name"},"김혜란",-1),i=[d,a];function c(e,t,n,l,d,a){return(0,o.wg)(),(0,o.iD)("div",r,[(0,o._)("div",s,[(0,o._)("ul",null,[(0,o._)("li",null,[(0,o._)("div",{class:"user-profile",onClick:t[0]||(t[0]=(...t)=>e.toggleDropdown&&e.toggleDropdown(...t))},i)])])])])}var u={name:"AdminHeader"},p=n(3744);const v=(0,p.Z)(u,[["render",c]]);var _=v},4013:function(e,t,n){n.d(t,{Z:function(){return p}});var o=n(6252),l=n(3577),r=n(4427);const s={class:"admin-sidebar"},d=(0,o._)("div",{class:"logo-wrap"},[(0,o._)("img",{src:r})],-1);function a(e,t,n,r,a,i){const c=(0,o.up)("router-link");return(0,o.wg)(),(0,o.iD)("div",s,[d,(0,o._)("ul",null,[(0,o._)("li",{class:(0,l.C_)({active:"/admin/board"===e.$route.path})},[(0,o.Wm)(c,{to:"/admin/board"},{default:(0,o.w5)((()=>[(0,o.Uk)("게시판 관리")])),_:1})],2),(0,o._)("li",{class:(0,l.C_)({active:"/admin/report"===e.$route.path})},[(0,o.Wm)(c,{to:"/admin/report"},{default:(0,o.w5)((()=>[(0,o.Uk)("신고 관리")])),_:1})],2)])])}var i={name:"AdminSidebar"},c=n(3744);const u=(0,c.Z)(i,[["render",a]]);var p=u},7262:function(e,t,n){n.r(t),n.d(t,{default:function(){return ne}});var o=n(6252),l=n(3577),r=n(9963);const s={class:"report-admin"},d={class:"admin-content-wrap"},a={class:"admin-content"},i={class:"admin-content-inner"},c=(0,o._)("strong",{class:"page-title"},"신고 관리",-1),u=(0,o._)("div",{class:"report-options-container"},[(0,o._)("select",null,[(0,o._)("option",null,"게시판 전체"),(0,o._)("option",null,"자유게시판")]),(0,o._)("select",null,[(0,o._)("option",null,"상태"),(0,o._)("option",null,"대기중"),(0,o._)("option",null,"처리됨"),(0,o._)("option",null,"거부됨")])],-1),p={class:"grid-table"},v=(0,o._)("div",{class:"grid-head"},[(0,o._)("div",null,"게시판"),(0,o._)("div",null,"신고대상/정보"),(0,o._)("div",null,"신고대상"),(0,o._)("div",null,"신고내용"),(0,o._)("div",null,"내용"),(0,o._)("div",null,"상태")],-1),_=["onClick"],m={class:"board-box"},g=(0,o._)("br",null,null,-1),A=(0,o._)("br",null,null,-1),b=(0,o._)("br",null,null,-1),w=(0,o._)("br",null,null,-1),U=(0,o._)("br",null,null,-1),C=(0,o._)("br",null,null,-1),k=(0,o._)("div",{class:"modal-title"},"신고 관리",-1),I={class:"selected-report-wrap"},f={class:"report-box"},R=(0,o._)("div",{class:"report-title"},"게시판",-1),h={class:"report-content"},M={class:"report-box"},z=(0,o._)("div",{class:"report-title"},"신고대상/정보",-1),D={class:"report-content"},x=(0,o._)("div",null,"post id:",-1),S=(0,o._)("div",null,"comment id:",-1),Z={class:"report-box"},B=(0,o._)("div",{class:"report-title"},"신고대상",-1),J={class:"report-content"},T=(0,o._)("div",null,"target user:",-1),H=(0,o._)("div",null,"report user:",-1),Y={class:"report-box"},N=(0,o._)("div",{class:"report-title"},"신고내용",-1),X={class:"report-content"},F={class:"report-box"},O=(0,o._)("div",{class:"report-title"},"내용",-1),V={class:"report-content"},W={class:"report-box"},j=(0,o._)("div",{class:"report-title"},"상태",-1),y={class:"report-content"},E=["selected"],G=["selected"],Q=["selected"];function K(e,t,n,K,q,L){const P=(0,o.up)("AdminSidebar"),$=(0,o.up)("AdminHeader"),ee=(0,o.up)("ModalComponent");return(0,o.wg)(),(0,o.iD)("div",s,[(0,o.Wm)(P),(0,o._)("div",d,[(0,o.Wm)($),(0,o._)("div",a,[(0,o._)("div",i,[c,u,(0,o._)("div",p,[v,((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(q.reports,((e,t)=>((0,o.wg)(),(0,o.iD)("div",{class:"grid-body",key:t,onClick:t=>L.openModal(e)},[(0,o._)("div",null,[(0,o._)("div",m,(0,l.zw)(e.board),1)]),(0,o._)("div",null,[(0,o.Uk)(" post id: "),g,(0,o.Uk)(" "+(0,l.zw)(e.postId)+" ",1),A,(0,o.Uk)(" comment id: "),b,(0,o.Uk)(" "+(0,l.zw)(e.commentId),1)]),(0,o._)("div",null,[(0,o.Uk)(" target user: "),w,(0,o.Uk)(" "+(0,l.zw)(e.targetUser)+" ",1),U,(0,o.Uk)(" report user: "),C,(0,o.Uk)(" "+(0,l.zw)(e.reportUser),1)]),(0,o._)("div",null,(0,l.zw)(e.reportContent),1),(0,o._)("div",null,(0,l.zw)(e.content),1),(0,o._)("div",null,[(0,o._)("div",{class:(0,l.C_)(L.getStatusClass(e.status))},(0,l.zw)(e.status),3)])],8,_)))),128))])])])]),q.showModal?((0,o.wg)(),(0,o.j4)(ee,{key:0,report:q.selectedReport,onClose:t[2]||(t[2]=e=>q.showModal=!1)},{header:(0,o.w5)((()=>[k,(0,o._)("div",{class:"modal-close",onClick:t[0]||(t[0]=e=>q.showModal=!1)},"×")])),default:(0,o.w5)((()=>[(0,o._)("div",null,[(0,o._)("div",I,[(0,o._)("div",f,[R,(0,o._)("div",h,(0,l.zw)(q.selectedReport.board),1)]),(0,o._)("div",M,[z,(0,o._)("div",D,[x,(0,o._)("div",null,(0,l.zw)(q.selectedReport.postId),1),S,(0,o._)("div",null,(0,l.zw)(q.selectedReport.commentId),1)])]),(0,o._)("div",Z,[B,(0,o._)("div",J,[T,(0,o._)("div",null,(0,l.zw)(q.selectedReport.targetUser),1),H,(0,o._)("div",null,(0,l.zw)(q.selectedReport.userInfo),1)])]),(0,o._)("div",Y,[N,(0,o._)("div",X,(0,l.zw)(q.selectedReport.reportContent),1)]),(0,o._)("div",F,[O,(0,o._)("div",V,(0,l.zw)(q.selectedReport.content),1)]),(0,o._)("div",W,[j,(0,o._)("div",y,[(0,o.wy)((0,o._)("select",{"onUpdate:modelValue":t[1]||(t[1]=e=>q.selectedReport.status=e)},[(0,o._)("option",{value:"대기중",selected:"대기중"===q.selectedReport.status},"대기중",8,E),(0,o._)("option",{value:"차단됨",selected:"차단됨"===q.selectedReport.status},"차단됨",8,G),(0,o._)("option",{value:"거부됨",selected:"거부됨"===q.selectedReport.status},"거부됨",8,Q)],512),[[r.bM,q.selectedReport.status]])])])])])])),_:1},8,["report"])):(0,o.kq)("",!0)])}var q=n(937),L=n(4013),P=n(8526),$={name:"ReportAdmin",components:{AdminSidebar:L.Z,AdminHeader:P.Z,ModalComponent:q.Z},data(){return{showModal:!1,selectedReport:null,reports:[{board:"자유게시판",userInfo:"2023-09-01",status:"대기중",postId:"1234",commentId:"0123",targetUser:"김혜란",reportUser:"이유리",reportContent:"aaa",content:"aasss"},{board:"자유게시판",userInfo:"2023-09-01",status:"차단됨",postId:"bbb",commentId:"1222",targetUser:"이유리",reportUser:"김혜란",reportContent:"aaa",content:"vddd"},{board:"자유게시판",userInfo:"2023-09-01",status:"거부됨",postId:"bbb",commentId:"1222",targetUser:"이유리",reportUser:"김혜란",reportContent:"aaa",content:"vddd"}]}},methods:{getStatusClass(e){switch(e){case"대기중":return"pending-box";case"차단됨":return"blocked-box";case"거부됨":return"denied-box";default:return""}},openModal(e){const{board:t,userInfo:n,status:o,postId:l,commentId:r,targetUser:s,reportUser:d,reportContent:a,content:i}=e,c={board:t,userInfo:n,status:o,postId:l,commentId:r,targetUser:s,reportUser:d,reportContent:a,content:i};console.log(c),this.selectedReport=c,this.showModal=!0}}},ee=n(3744);const te=(0,ee.Z)($,[["render",K]]);var ne=te}}]);
//# sourceMappingURL=262.aa24784e.js.map