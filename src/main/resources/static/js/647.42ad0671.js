"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[647],{8126:function(t,e,a){a.d(e,{Z:function(){return m}});var s=a(6252);const i={class:"mypage-header"},n=(0,s._)("div",{class:"admin-header-inner"},null,-1),l=[n];function o(t,e,a,n,o,c){return(0,s.wg)(),(0,s.iD)("div",i,l)}var c={name:"AdminHeader"},r=a(3744);const d=(0,r.Z)(c,[["render",o]]);var m=d},501:function(t,e,a){a.d(e,{Z:function(){return _}});var s=a(6252),i=a(3577),n=a(4427);const l={class:"mypage-sidebar"},o=(0,s._)("div",{class:"logo-wrap"},[(0,s._)("a",{href:"/"},[(0,s._)("img",{src:n})])],-1),c=(0,s._)("li",{style:{display:"none"}},"회원 탈퇴",-1);function r(t,e,a,n,r,d){const m=(0,s.up)("router-link");return(0,s.wg)(),(0,s.iD)("div",l,[o,(0,s._)("ul",null,[(0,s._)("li",{class:(0,i.C_)({active:"/mypage/info"===t.$route.path})},[(0,s.Wm)(m,{to:"/mypage/info"},{default:(0,s.w5)((()=>[(0,s.Uk)("개인정보 변경")])),_:1})],2),(0,s._)("li",{class:(0,i.C_)({active:"/mypage/comment"===t.$route.path})},[(0,s.Wm)(m,{to:"/mypage/comment"},{default:(0,s.w5)((()=>[(0,s.Uk)("내가 쓴 글 / 댓글 단 글")])),_:1})],2),(0,s._)("li",{class:(0,i.C_)({active:"/mypage/like"===t.$route.path})},[(0,s.Wm)(m,{to:"/mypage/like"},{default:(0,s.w5)((()=>[(0,s.Uk)("내 공감")])),_:1})],2),(0,s._)("li",{class:(0,i.C_)({active:"/mypage/scrap"===t.$route.path})},[(0,s.Wm)(m,{to:"/mypage/scrap"},{default:(0,s.w5)((()=>[(0,s.Uk)("내 스크랩")])),_:1})],2),(0,s._)("li",{style:{display:"none"},class:(0,i.C_)({active:"/mypage/report"===t.$route.path})},[(0,s.Wm)(m,{to:"/mypage/report"},{default:(0,s.w5)((()=>[(0,s.Uk)("내 신고")])),_:1})],2),c])])}var d={name:"MypageSidebar"},m=a(3744);const p=(0,m.Z)(d,[["render",r]]);var _=p},5647:function(t,e,a){a.r(e),a.d(e,{default:function(){return st}});var s=a(6252),i=a(3577),n=a(9963),l=a(4192),o=a(4268),c=a(7821);const r={class:"comment-mypage"},d={class:"admin-content-wrap"},m={class:"admin-content"},p={class:"admin-content-inner"},_=(0,s._)("strong",{class:"page-title"},"내가 쓴 글 / 댓글 단 글",-1),g={class:"tabs"},v=["onClick"],h={class:"tab-content"},w={class:"list-wrap"},u=["href"],y={class:"list-content"},k={class:"boardname-tit-wrap"},b={class:"boardname"},f={class:"title"},x={class:"content"},z={class:"detail-info-wrap"},C={class:"active-wrap"},U={class:"comment-box"},P=(0,s._)("img",{src:l,style:{width:"12px",height:"12px"}},null,-1),D={class:"like-box"},Z=(0,s._)("img",{src:o,style:{width:"14px",height:"14px"}},null,-1),H={class:"view-box"},W=(0,s._)("img",{src:c,style:{width:"14px",height:"14px"}},null,-1),M={class:"writer-date-wrap"},T={class:"date"},$={class:"list-wrap"},I=["href"],A={class:"list-content"},S={class:"boardname-tit-wrap"},K={class:"boardname"},Y={class:"title"},F={class:"content"},N={class:"detail-info-wrap"},j={class:"active-wrap"},B={class:"comment-box"},q=(0,s._)("img",{src:l,style:{width:"12px",height:"12px"}},null,-1),E={class:"like-box"},G=(0,s._)("img",{src:o,style:{width:"14px",height:"14px"}},null,-1),J={class:"view-box"},L=(0,s._)("img",{src:c,style:{width:"14px",height:"14px"}},null,-1),O={class:"writer-date-wrap"};function Q(t,e,a,l,o,c){const Q=(0,s.up)("MypageSidebar"),R=(0,s.up)("MypageHeader");return(0,s.wg)(),(0,s.iD)("div",r,[(0,s.Wm)(Q),(0,s._)("div",d,[(0,s.Wm)(R),(0,s._)("div",m,[(0,s._)("div",p,[_,(0,s._)("div",g,[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(o.tabs,((t,e)=>((0,s.wg)(),(0,s.iD)("div",{key:e,onClick:e=>o.activeTab=t,class:(0,i.C_)([{active:o.activeTab===t},"tab-container"])},(0,i.zw)(t),11,v)))),128))]),(0,s._)("div",h,[(0,s.wy)((0,s._)("div",null,[(0,s._)("ul",w,[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(o.mycomments,((t,e)=>((0,s.wg)(),(0,s.iD)("li",{key:e},[(0,s._)("a",{href:"/detail?postId="+t.postId},[(0,s._)("div",y,[(0,s._)("div",k,[(0,s._)("div",b,(0,i.zw)(t.board.boardName),1),(0,s._)("div",f,(0,i.zw)(t.title),1)]),(0,s._)("div",x,(0,i.zw)(t.content),1),(0,s._)("div",z,[(0,s._)("div",C,[(0,s._)("div",U,[P,(0,s.Uk)(" 댓글 "+(0,i.zw)(t.commentCnt),1)]),(0,s._)("div",D,[Z,(0,s.Uk)(" 공감 "+(0,i.zw)(t.likes),1)]),(0,s._)("div",H,[W,(0,s.Uk)(" 조회수 "+(0,i.zw)(t.views),1)])]),(0,s._)("div",M,[(0,s._)("div",T,(0,i.zw)(t.createdAt),1)])])])],8,u)])))),128))])],512),[[n.F8,"내가 쓴 글"===o.activeTab]]),(0,s.wy)((0,s._)("div",null,[(0,s._)("ul",$,[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(o.commentPosts,((t,e)=>((0,s.wg)(),(0,s.iD)("li",{key:e},[(0,s._)("a",{href:"/detail?postId="+t.postId},[(0,s._)("div",A,[(0,s._)("div",S,[(0,s._)("div",K,(0,i.zw)(t.board.boardName),1),(0,s._)("div",Y,(0,i.zw)(t.title),1)]),(0,s._)("div",F,(0,i.zw)(t.content),1),(0,s._)("div",N,[(0,s._)("div",j,[(0,s._)("div",B,[q,(0,s.Uk)(" 댓글 "+(0,i.zw)(t.commentCnt),1)]),(0,s._)("div",E,[G,(0,s.Uk)(" 공감 "+(0,i.zw)(t.likes),1)]),(0,s._)("div",J,[L,(0,s.Uk)(" 조회수 "+(0,i.zw)(t.views),1)])]),(0,s._)("div",O,(0,i.zw)(t.createdAt),1)])])],8,I)])))),128))])],512),[[n.F8,"댓글 단 글"===o.activeTab]])])])])])])}var R=a(6154),V=a(501),X=a(8126),tt={name:"CommentMypage",components:{MypageSidebar:V.Z,MypageHeader:X.Z},data(){return{headers:[],link:"",tabs:["내가 쓴 글","댓글 단 글"],activeTab:"내가 쓴 글",mycomments:[],commentPosts:[]}},created(){this.setupHeaders(),this.getPost(),this.getComment()},methods:{setupHeaders(){const t=localStorage.getItem("token");this.link="http://"+window.location.host,this.headers={Authorization:`Bearer ${t}`,"Content-Type":"application/json"}},getPost(){R.Z.get(this.link+"/api/myPage/getPosts/post",{headers:this.headers}).then((t=>{console.log("내가 쓴 글"),this.mycomments=t.data,console.log(this.mycomments)})).catch((t=>{console.log(t)}))},getComment(){R.Z.get(this.link+"/api/myPage/getPosts/comment",{headers:this.headers}).then((t=>{console.log("내가 쓴 댓글"),this.commentPosts=t.data,console.log(this.commentPosts)})).catch((t=>{console.log(t)}))}}},et=a(3744);const at=(0,et.Z)(tt,[["render",Q]]);var st=at}}]);
//# sourceMappingURL=647.42ad0671.js.map