(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4ae76608"],{"0e6a":function(e,t,s){},1348:function(e,t,s){"use strict";s.r(t);var r=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"full"},[s("el-form",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"Register-container",attrs:{"label-position":"left","label-width":"80px"}},[s("h3",{staticClass:"Register_title"},[e._v("注册")]),s("el-form-item",{attrs:{prop:"account",label:"用户名"}},[s("el-input",{attrs:{type:"text","auto-complete":"off",placeholder:"用户名"},model:{value:e.RegisterForm.userName,callback:function(t){e.$set(e.RegisterForm,"userName",t)},expression:"RegisterForm.userName"}})],1),s("el-form-item",{attrs:{prop:"checkPass",label:"密码"}},[s("el-input",{attrs:{type:"password","auto-complete":"off",placeholder:"请输入您的密码"},model:{value:e.RegisterForm.passwd1,callback:function(t){e.$set(e.RegisterForm,"passwd1",t)},expression:"RegisterForm.passwd1"}})],1),s("el-form-item",{attrs:{prop:"checkPass"}},[s("el-input",{attrs:{type:"password","auto-complete":"off",placeholder:"请重复输入密码"},model:{value:e.RegisterForm.passwd2,callback:function(t){e.$set(e.RegisterForm,"passwd2",t)},expression:"RegisterForm.passwd2"}})],1),s("el-form-item",{attrs:{prop:"checkPass",label:"邮箱"}},[s("el-input",{staticStyle:{float:"left",width:"160px"},attrs:{type:"email","auto-complete":"off",placeholder:"请输入您的邮箱"},model:{value:e.RegisterForm.email,callback:function(t){e.$set(e.RegisterForm,"email",t)},expression:"RegisterForm.email"}}),s("el-button",{directives:[{name:"show",rawName:"v-show",value:e.show,expression:"show"}],staticStyle:{float:"right","font-size":"5px",width:"95px"},attrs:{type:"primary"},on:{click:e.sendEmail}},[e._v("发送验证码")]),s("el-button",{directives:[{name:"show",rawName:"v-show",value:!e.show,expression:"!show"}],staticStyle:{float:"right","font-size":"5px",width:"95px"},attrs:{type:"primary",disabled:"disabled"}},[e._v(e._s(e.count))])],1),s("el-form-item",{attrs:{prop:"checkPass",label:"验证码"}},[s("el-input",{attrs:{type:"text","auto-complete":"off",placeholder:"请输入验证码"},model:{value:e.RegisterForm.code,callback:function(t){e.$set(e.RegisterForm,"code",t)},expression:"RegisterForm.code"}})],1),s("br"),s("el-form-item",{staticStyle:{width:"80%"}},[s("el-button",{staticStyle:{width:"100%"},attrs:{type:"primary"},on:{click:e.submit}},[e._v("注册")])],1)],1)],1)},a=[],i=(s("a9e3"),{data:function(){return{checked:!0,RegisterForm:{userName:"",passwd1:"",passwd2:"",email:"",code:""},show:!0,count:"",timer:null,verification:"",loading:!1}},methods:{submit:function(){var e=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;if(null!=sessionStorage.getItem("userName")||null!=sessionStorage.getItem("userID"))this.$message({message:"您已登录",type:"warning"}),this.$router.push("/home");else if(this.RegisterForm.userName)if(this.RegisterForm.passwd1)if(this.RegisterForm.passwd1!==this.RegisterForm.passwd2)this.$message({message:"两次密码不一致，请重新输入",type:"warning"});else if(e.test(this.RegisterForm.email))if(this.RegisterForm.code)if(this.verification!==this.RegisterForm.code)this.$message({message:"验证码错误",type:"warning"});else{var t=this.$md5(this.RegisterForm.passwd1),s=this;this.$api.user.postRegisterForm({userName:s.RegisterForm.userName,passwd:t,email:s.RegisterForm.email,code:s.RegisterForm.code}).then((function(e){200===Number(e.code)?(sessionStorage.setItem("userName",s.RegisterForm.userName),sessionStorage.setItem("userID",e.data.userID),s.$message({message:"注册成功",type:"success"}),s.$router.push("/login")):s.$message.error({message:e.msg,type:"danger"})}))}else this.$message({message:"请输入验证码",type:"warning"});else this.$message({message:"请输入正确的邮箱",type:"warning"});else this.$message({message:"密码不能为空",type:"warning"});else this.$message({message:"用户名不能为空",type:"warning"})},sendEmail:function(){var e=this,t=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;if(t.test(this.RegisterForm.email)){var s=this,r=60;this.timer||(this.$api.user.sendEmail({email:s.RegisterForm.email}).then((function(e){200===Number(e.code)?(s.verification=e.data.verification,s.$message({message:"已发送",type:"success"})):s.$message.error("发送失败")})),this.count=r,this.show=!1,this.timer=setInterval((function(){e.count>0&&e.count<=r?e.count--:(e.show=!0,clearInterval(e.timer),e.timer=null)}),1e3))}else this.$message({message:"请输入正确的邮箱",type:"warning"})}}}),o=i,n=(s("6ca9"),s("2877")),l=Object(n["a"])(o,r,a,!1,null,"5e280da8",null);t["default"]=l.exports},5899:function(e,t){e.exports="\t\n\v\f\r                　\u2028\u2029\ufeff"},"58a8":function(e,t,s){var r=s("1d80"),a=s("5899"),i="["+a+"]",o=RegExp("^"+i+i+"*"),n=RegExp(i+i+"*$"),l=function(e){return function(t){var s=String(r(t));return 1&e&&(s=s.replace(o,"")),2&e&&(s=s.replace(n,"")),s}};e.exports={start:l(1),end:l(2),trim:l(3)}},"6ca9":function(e,t,s){"use strict";s("0e6a")},7156:function(e,t,s){var r=s("861d"),a=s("d2bb");e.exports=function(e,t,s){var i,o;return a&&"function"==typeof(i=t.constructor)&&i!==s&&r(o=i.prototype)&&o!==s.prototype&&a(e,o),e}},a9e3:function(e,t,s){"use strict";var r=s("83ab"),a=s("da84"),i=s("94ca"),o=s("6eeb"),n=s("5135"),l=s("c6b6"),c=s("7156"),m=s("c04e"),u=s("d039"),p=s("7c73"),g=s("241c").f,f=s("06cf").f,d=s("9bf2").f,h=s("58a8").trim,w="Number",v=a[w],F=v.prototype,R=l(p(F))==w,b=function(e){var t,s,r,a,i,o,n,l,c=m(e,!1);if("string"==typeof c&&c.length>2)if(c=h(c),t=c.charCodeAt(0),43===t||45===t){if(s=c.charCodeAt(2),88===s||120===s)return NaN}else if(48===t){switch(c.charCodeAt(1)){case 66:case 98:r=2,a=49;break;case 79:case 111:r=8,a=55;break;default:return+c}for(i=c.slice(2),o=i.length,n=0;n<o;n++)if(l=i.charCodeAt(n),l<48||l>a)return NaN;return parseInt(i,r)}return+c};if(i(w,!v(" 0o1")||!v("0b1")||v("+0x1"))){for(var N,y=function(e){var t=arguments.length<1?0:e,s=this;return s instanceof y&&(R?u((function(){F.valueOf.call(s)})):l(s)!=w)?c(new v(b(t)),s,y):b(t)},I=r?g(v):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger".split(","),$=0;I.length>$;$++)n(v,N=I[$])&&!n(y,N)&&d(y,N,f(v,N));y.prototype=F,F.constructor=y,o(a,w,y)}}}]);
//# sourceMappingURL=chunk-4ae76608.727c5a13.js.map