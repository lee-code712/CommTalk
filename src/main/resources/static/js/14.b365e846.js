"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[14],{8662:function(e,n,r){r.r(n),r.d(n,{default:function(){return j}});var s=r(6252),t=r(9963),a=r(3577),o=r(4427);const i={class:"login"},l=(0,s._)("img",{class:"logo",src:o},null,-1),d={class:"user-input"},p=(0,s._)("label",{for:"nickname"},"닉네임",-1),u={class:"user-input"},c=(0,s._)("label",{for:"password"},"비밀번호",-1),w=(0,s._)("div",null,[(0,s._)("button",{type:"submit",id:"loginBtn"},"로그인")],-1),m={class:"ask-join-wrap"},h=(0,s._)("div",null,"아직 회원이 아니신가요?",-1),k=(0,s._)("div",null,"회원가입",-1),_={key:0,class:"error"};function f(e,n,r,o,f,g){const v=(0,s.up)("router-link");return(0,s.wg)(),(0,s.iD)("div",i,[(0,s._)("form",{onSubmit:n[2]||(n[2]=(0,t.iM)(((...e)=>g.login&&g.login(...e)),["prevent"]))},[(0,s.Wm)(v,{class:"logo-wrap",to:"/"},{default:(0,s.w5)((()=>[l])),_:1}),(0,s._)("div",d,[p,(0,s.wy)((0,s._)("input",{type:"text",id:"nickname",placeholder:"닉네임을 입력해 주세요.","onUpdate:modelValue":n[0]||(n[0]=e=>f.nickname=e),required:""},null,512),[[t.nr,f.nickname]])]),(0,s._)("div",u,[c,(0,s.wy)((0,s._)("input",{type:"password",id:"password",placeholder:"비밀번호를 입력해 주세요.","onUpdate:modelValue":n[1]||(n[1]=e=>f.password=e),required:""},null,512),[[t.nr,f.password]])]),w,(0,s._)("div",m,[h,(0,s.Wm)(v,{class:"join-wrap",to:"/join"},{default:(0,s.w5)((()=>[k])),_:1})])],32),f.error?((0,s.wg)(),(0,s.iD)("div",_,(0,a.zw)(f.error),1)):(0,s.kq)("",!0)])}r(7658);var g=r(6154),v={data(){return{nickname:"",password:"",error:""}},methods:{login(){const e={nickname:this.nickname,password:this.password},n={"Content-type":"application/json; charset=UTF-8",Accept:"*/*"};g.Z.post("/api/auth/login",JSON.stringify(e),{headers:n}).then((e=>{const n=e.data.token;localStorage.setItem("token",n),this.$router.push("/")})).catch((e=>{this.error="로그인 실패: "+e.response.data,this.password="",alert("로그인 실패: "+e.response.data.message)}))}}},y=r(3744);const b=(0,y.Z)(v,[["render",f]]);var j=b}}]);
//# sourceMappingURL=14.b365e846.js.map