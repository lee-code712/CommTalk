"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[877],{8877:function(e,t,a){a.r(t),a.d(t,{default:function(){return W}});var s=a(6252),o=a(3577),i=a(9963),A=a(6652),r=a(4192),d=a(4268),g=a(7821);const l=e=>((0,s.dD)("data-v-0b71dd7a"),e=e(),(0,s.Cn)(),e),n={class:"list-view"},c={class:"full-container"},p={class:"sub-top-wrap"},h={class:"board-name"},w={class:"search-wrap"},C={class:"list-wrap"},m=["onClick"],u=["href"],B={class:"list-title-wrap"},b={class:"boardname-title-wrap"},I={key:0,class:"board-name"},v={class:"title"},Q=["src","onClick"],Y={class:"list-content"},F={class:"list-detail-info"},f={class:"comment-like-wrap"},k={class:"comment-box"},y=l((()=>(0,s._)("img",{src:r,style:{width:"12px",height:"12px"}},null,-1))),G={class:"like-box"},E=l((()=>(0,s._)("img",{src:d,style:{width:"14px",height:"14px"}},null,-1))),S={class:"view-box"},P=l((()=>(0,s._)("img",{src:g,style:{width:"14px",height:"14px"}},null,-1))),U={class:"writer-date-wrap"},K={class:"writer"},D={class:"date"},J={class:"paging-wrap"},L={class:"paging-inner"},N=["disabled"],R={class:"paging-num-wrap"},q=["onClick"],j=["disabled"],M={class:"btn-wrap"},Z=l((()=>(0,s._)("button",{class:"write-btn"},"글쓰기",-1)));function x(e,t,a,r,d,g){const l=(0,s.up)("HeaderLayout"),x=(0,s.up)("SubHeader"),O=(0,s.up)("router-link"),z=(0,s.up)("FooterLayout");return(0,s.wg)(),(0,s.iD)("div",n,[(0,s.Wm)(l),(0,s.Wm)(x),(0,s._)("div",c,[(0,s._)("div",p,[(0,s._)("strong",h,(0,o.zw)(d.boardName),1),(0,s._)("div",w,[(0,s.wy)((0,s._)("input",{type:"search","onUpdate:modelValue":t[0]||(t[0]=t=>e.keyword=t),placeholder:"내용을 입력해주세요."},null,512),[[i.nr,e.keyword]]),(0,s._)("img",{src:A,onClick:t[1]||(t[1]=(...e)=>g.fetchPosts&&g.fetchPosts(...e))})])]),(0,s._)("div",C,[(0,s._)("ul",null,[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(d.boards,(e=>((0,s.wg)(),(0,s.iD)("li",{class:"list",key:e.postId,onClick:t=>g.updateViews(e.postId)},[(0,s._)("a",{href:"/detail?postId="+e.postId},[(0,s._)("div",B,[(0,s._)("div",b,[d.isAllList?((0,s.wg)(),(0,s.iD)("div",I,(0,o.zw)(e.board.boardName),1)):(0,s.kq)("",!0),(0,s._)("div",v,(0,o.zw)(e.title),1)]),(0,s._)("img",{src:e.scraped?d.scrapImgActive:d.scrapImg,onClick:t=>g.changeImg(e,e.postId)},null,8,Q)]),(0,s._)("div",Y,(0,o.zw)(e.content),1),(0,s._)("div",F,[(0,s._)("div",f,[(0,s._)("div",k,[y,(0,s.Uk)(" 댓글 "+(0,o.zw)(e.commentCnt),1)]),(0,s._)("div",G,[E,(0,s.Uk)(" 공감 "+(0,o.zw)(e.likes),1)]),(0,s._)("div",S,[P,(0,s.Uk)(" 조회수 "+(0,o.zw)(e.views),1)])]),(0,s._)("div",U,[(0,s._)("div",K,(0,o.zw)(e.author.nickname),1),(0,s._)("div",D,(0,o.zw)(e.createdAt),1)])])],8,u)],8,m)))),128))])]),(0,s._)("div",J,[(0,s._)("div",L,[(0,s._)("div",{class:"prev-btn",onClick:t[2]||(t[2]=e=>g.loadPage("previous")),disabled:0===d.pageNumber},"이전",8,N),(0,s._)("div",R,[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(d.totalPages,(e=>((0,s.wg)(),(0,s.iD)("div",{key:e-1,class:(0,o.C_)({on:e-1===d.pageNumber}),onClick:t=>g.gotoPage(e-1)},(0,o.zw)(e),11,q)))),128))]),(0,s._)("div",{class:"next-btn",onClick:t[3]||(t[3]=e=>g.loadPage("next")),disabled:d.pageNumber===d.totalPages-1},"다음",8,j)])]),(0,s._)("div",M,[(0,s.Wm)(O,{to:"/edit?boardId="+d.boardId,class:"router-link-class"},{default:(0,s.w5)((()=>[Z])),_:1},8,["to"])])]),(0,s.Wm)(z)])}var O=a(6154),z=a(9937),H=a(2915),T=a(4014),X={name:"ListView",components:{HeaderLayout:z.Z,SubHeader:H.Z,FooterLayout:T.Z},data(){return{headers:[],link:"",boards:[],boardName:"",scrapImg:a(9090),scrapImgActive:a(3561),pageNumber:0,totalPages:"",isAllList:!1,boardId:this.$route.query.boardId}},created(){this.setupHeaders(),this.fetchData(),this.getBoardName()},methods:{setupHeaders(){const e=localStorage.getItem("token");this.link="http://"+window.location.host,this.headers={Authorization:`Bearer ${e}`,"Content-Type":"application/json"}},changeImg(e,t){event.preventDefault(),console.log(e),e.scraped=!e.scraped;const a={refId:t,actionType:"scrap"};O.Z.post("/api/post/changeEngagementAction",a,{headers:this.headers}).then((e=>{console.log(e.data.status),this.$forceUpdate()})).catch((e=>{console.error(e)}))},getBoardName(){const e=this.$route.query.boardId;O.Z.get(`/api/post/getBoard/${e}`,{headers:this.headers}).then((e=>{this.boardName=e.data.boardName})).catch((e=>{console.error(e)}))},updateViews(e){O.Z.post("/api/post/updateViews",{postId:e},{headers:this.headers}).then((e=>{console.log("views: "+e.data)})).catch((e=>{console.error(e)}))},fetchData(){const e=this.$route.query.boardId,t=this.$route.query.commonKeyword;console.log("pageNumber"+this.pageNumber);const a={params:{page:this.pageNumber,size:10}};var s="";t&&(s="/api/common/getPosts/"+t,this.boardName="전체",this.isAllList=!0),e&&(s="/api/post/getPostsByBoard/"+e),console.log(this.headers),O.Z.get(s,a,{headers:this.headers}).then((e=>{console.log(e.data.posts),this.boards=e.data.posts,this.totalPages=e.data.totalPages})).catch((e=>{console.error(e)}))},fetchPosts(){if(!this.keyword)return;const e=this.$route.query.boardId;O.Z.get(`/api/post/getPosts/${e}/${this.keyword}`,{headers:this.headers}).then((e=>{this.boards=e.data.posts,this.totalPages=e.data.totalPages})).catch((e=>{console.error(e)}))},loadPage(e){"previous"===e&&this.pageNumber>0?this.pageNumber--:"next"===e&&this.pageNumber<this.totalPages-1&&this.pageNumber++,this.fetchData()},gotoPage(e){e>=0&&e<=this.totalPages-1&&(this.pageNumber=e,this.fetchData())}}},V=a(3744);const _=(0,V.Z)(X,[["render",x],["__scopeId","data-v-0b71dd7a"]]);var W=_},9090:function(e){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGAAAABgCAYAAADimHc4AAAACXBIWXMAACxLAAAsSwGlPZapAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAASxSURBVHgB7Z2xctNAEIY3gYIylFQxHV2cji6mS0fyBkpHF1JSIT8B5AlwOqgSSipEB51TUkU8AUkHHbvR3YywJd2eLGnv7P1mbuSRTrL9f9JJcjLaLeiIJEkmOBljOzDTHdPWgVtsuWnX2LLZbJZBB2zBCmDoFPApttewPmFzybFl2KYoI4eWtBJggn8LRfAKwAxaingAnmD4FPoltgkoFhpyj8bj8d0c8VnRSwCG/w4nKbZHoCxCowJJ2EEHX7grsYYgM+ToXs+HjoIXOCTdujpuA4+voOH7QEPSJaejcwgyw84RKL6MOMNR4xCE4Sc4+QB86JA7h+LyLF/l8iwkzBB8f6LF9hLbyGP1M8zhfd3CrYY3HUEx9IzATY7tpKubk9AxOyZdho8Y3WmnfFp3Pmg6B6TMN6A9fn9Twifwu85wsg/Fd3dh75kqqTwCzN5/A27o5iOFDQazSqEh4BKPq46CuiMgBTfnmx4+YTLgHAmVvxrUCTiAZnLgSdoUUigyaeK0auaSAPOr5giamXJuMjYFk8WJo9uOyfY/qo6ACTSTm5OQUsJchLh2yvHijG1OpwU+g1KH61wwWZxRJWAXmrkCpY7MsXxvcUaVgBE04/Vz64aRO5Yv/dFqm9OpjJ5862H89MISoAyIChBGBQijAoRRAcKoAGFUgDAqQBgVIIwKEEYFCKMChFEBwqgAYVSAMCpAGBUgjAoQRgUIowKEUQHCqABhVIAwKkAYFSCMChBGBQijAoRRAcKoAGFUgDAqQBgVIIwKEEYFCKMChFEBwqgAYR5CpJinWNlHqWWxPp0ryiMAw6fn8/yG4nFq1G7MvOiIToAJOq1YlMYoISoBDeFbopMQjQBG+JaoJEQhwCN8C0nwedymGMELaBF+adXwJQQtYIXwS5sIW0KwAjoIv7SpcCUEKaDD8EubDFNCcAI8w78wjbnp8CQEJcAElDK7X+DPDwk1iFhCMAJMMAmz+4UJ/p6YJQQhYJXwLbFKEBfQRfiWGCWICugyfEtsEsQE9BG+JSYJIgL6DN8Si4TBBQwRviUGCYMKGDJ8S+gSBhMgEb4lZAmDCJAM3xKqhN4FhBC+JUQJvQoIKXyLeQ9O0R2idwm9CQgxfAu+F1U0mjK79yqhFwGe4Z8PGb7FlJ8Sl9C5AM/wp2ZvFCEECd4CkqLKXt0y3/BTEKZLCU3Z1FEl4FfzKtUFyWIM39JCwmVS/G/qIqlj3aX6O0v1hMcINJeyeo5dqFbu9Xw+v8UPMsHXFD635nCQ9Sfxu2T4Pai25oTR/Rm2Q+z/E9fLac/H1/Sn1FeO9X5g/4/lGUvFPHFjFCSrGnQLgi/+mfCLc7bhZLEIXtUQlIG7Ilwboqi86jkc+ZAvhk8sDUF4iPzBw+kvvjyE7oiq7K3ncMTlDLfrPgeYD/AdP8AE/Ep31xFlzeGOJVxhBm+qFjRdhh7D6lXzoi743NFwRBnWVlp9ULtWMRR9wpdPwF3gs4q1qLa94pFAdTePm6oPbgEDuvAFfhH7b9jSdasxb26yKNA9Rvccih1w5urIElD6EHSJSm1c+iBkl27eMijGugzWmKQoykwZ0HQXivqQlMEdFBnMfDL4Bz9L8zEcSPpcAAAAAElFTkSuQmCC"},3561:function(e){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGAAAABgCAYAAADimHc4AAAACXBIWXMAACxLAAAsSwGlPZapAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAPwSURBVHgB7Z1BTttAFIbfOC2iXVSxuAA9Adyg6S60G25AOAGpqtBlw5KgquEEwAmgq0TqgvQG5QTNAYrCrlVFZzoOngpiO7YTO28m/J9kCBMHj//PbybOIiOoIOofRjUhaYME1fSfm3qrhtsycKO3YbApJa/I8wb9Q39ABSBoDmrNUfX5Cu0poiYtT9hZGQpFA+nRgZYxpBmZSUAQ/LMV+kh3wT96tIjTWUXkFvB2f7Qjibr0+K74NIZ6JGj3O/5ZnhflErC1P/pMuOrT6PY6/rusO2cSMB5yntJ5OMGCdC5+/aHdQde/SdvxCWVAj/cn+leNQFa29QUbDNGv03b00nYIh51tAvnQo0WYXcpuU6i3fjaEqJwQmBlFlUa/8yJxYk4UoG+s1vXbq0v9cJ3APNzo+eBl0nyQOASJv7JNCL8IzD1TLLEVEF79PwgUhq4CP64KYisgvPpBgaw+lbH3T/FDkOe9IlAoQnh7ce0RAcGnmoSxvwyq9ffjbB8QrQApawRKQVTk5mRbRIAulQ0CpaCUqE22RStAYfgpCyFE5OKOChAQUCKRj/C9LDuBwsgkACwQCGAGApiBAGYggBkIYAYCmIEAZiCAGQhgBgKYgQBmIIAZCGAGApiBAGYggBkIYAYCmIEAZiCAGQhgBgKYgQBmIIAZCGAGApiBAGYggBkIYAYCmIEAZiCAGQhgBgKYgQBmIIAZCGAGApiBAGYggBkIYAYCmIEAZiCAGQhgBgKYgQBmnBagiM6IZK4FE2wj09fX20gQfr/jN4LHW/vX+qe3Qw7iZAXcDz+g11lruFoJzgmYDN/gqgSnBCSFb3BRgjMC0sI3uCbBCQFZwze4JMF6AXnDN7giwWoBs4ZvcEGCtQLmDd9guwQrBRQVvsFmCdYJKDp8g60SrBJQVvgGGyVYI6Ds8A22SbBCwKLCN9gkgV3AosM3jCUoeUzMxAlIXYK1KJSiA47wDb2jtWbQB1ocGRZyU+PF60tnHP6R3yZmgj4sSoI+zvfJtpgKkN+o/I5YEb5hURKEJ68m2yIClPQuqERsC9+wCAm30judbIsI6H/yB3oYGlAJ2Bq+oUwJ+v8Ovh75WYagoAqK74Tt4RtKk+DRbnxzXCd0FRTZCVfCNxQtYXz+h/4w7rmpS5pvta67lLAKaK6DOxT+feqtUVuI5MWYMyHouHfoN5OennojNu/7ZJfDD5i3EoLXTgs/YGoFGN60RptK0DllXOY2mHBIzyPjCX0JCFcYv6QSzj+TgP8dCdbDrciGoPGCz+sPDkr6Bu7uwGfLEvwkaecvhPyibr2LPOefS8B9as1RdTVcG/G3vsWOWy99mSnq/P8Bm4yBUFLTjbAAAAAASUVORK5CYII="}}]);
//# sourceMappingURL=877.57f09eb7.js.map