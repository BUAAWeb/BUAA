(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-59d8c841"],{"1dde":function(t,e,s){var i=s("d039"),o=s("b622"),r=s("2d00"),n=o("species");t.exports=function(t){return r>=51||!i((function(){var e=[],s=e.constructor={};return s[n]=function(){return{foo:1}},1!==e[t](Boolean).foo}))}},"25f0":function(t,e,s){"use strict";var i=s("6eeb"),o=s("825a"),r=s("d039"),n=s("ad6d"),a="toString",c=RegExp.prototype,l=c[a],u=r((function(){return"/a/b"!=l.call({source:"a",flags:"b"})})),d=l.name!=a;(u||d)&&i(RegExp.prototype,a,(function(){var t=o(this),e=String(t.source),s=t.flags,i=String(void 0===s&&t instanceof RegExp&&!("flags"in c)?n.call(t):s);return"/"+e+"/"+i}),{unsafe:!0})},"3b48":function(t,e,s){"use strict";s("aa26")},4882:function(t,e,s){"use strict";s.r(e);var i=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"wrapper"},[s("Header",{staticClass:"header_home",attrs:{status:"2"}}),s("div",{staticClass:"container"},[s("div",{staticClass:"content-right"},[t._v(" 这里是右侧 ")]),s("div",{staticClass:"content-left"},[s("div",{staticClass:"content-left-nav"},t._l(t.filter_list,(function(e,i){return s("div",{key:i,staticClass:"left-nav"},[s("filter-item",{attrs:{filter_item:e,c_filter_words:t.filter_words},on:{getFilter:t.handleSelect,cancelSelect:t.handleCancel}})],1)})),0),s("div",{staticClass:"content-left-rs"},[s("div",{staticClass:"rs-list"},[s("div",{directives:[{name:"show",rawName:"v-show",value:t.has_experts,expression:"has_experts"}],staticClass:"e_result"},[s("div",{staticClass:"e_border"},[s("div",{staticClass:"e_wrapper"},[s("p",{staticClass:"e_tips"},[t._v("为您找到"+t._s(t.experts_count)+"个学者：")]),s("div",t._l(t.e_result_list,(function(e,i){return s("div",{key:i,staticClass:"e_item"},[s("div",{staticClass:"e_avatar"},[s("el-avatar",{attrs:{size:40,src:t.sourceUrl}})],1),s("div",{staticClass:"e_info"},[s("div",{staticClass:"e_info_name",on:{click:function(s){return t.handleToAuthor(e.id)}}},[t._v(t._s(e.name))]),s("div",{staticClass:"e_info_aff"},[t._v(t._s(e.affiliate))])])])})),0)])])]),s("div",{staticClass:"toolbar"},[s("div",{staticClass:"sort-container"},[s("el-popover",{attrs:{placement:"top-start",width:"70",trigger:"hover"}},[s("div",{staticClass:"sort-item",on:{click:t.sortByViews}},[t._v("按浏览量")]),s("div",{staticClass:"sort-item",on:{click:t.sortByCited}},[t._v("按被引量")]),s("div",{staticClass:"sort-item",on:{click:t.sortByTime}},[t._v("按时间降序")]),s("el-button",{staticClass:"button-sort",attrs:{slot:"reference"},slot:"reference"},[s("i",{staticClass:"el-icon-sort"},[t._v("按"+t._s(this.getSortMethod))])])],1)],1),s("span",{staticClass:"nums"},[t._v("找到约"+t._s(this.total_rs)+"条相关结果")])]),t._l(t.result_list,(function(e,i){return s("div",{key:i},[s("academic-item",{attrs:{c_sc:e},on:{toAuthorPage:t.searchAuthor,toSourcePage:t.searchSource}})],1)}))],2)]),s("div",{staticStyle:{clear:"both"}}),s("p",{staticClass:"page"},[s("el-pagination",{attrs:{background:"","current-page":t.currentPage,"page-size":10,layout:"prev, pager, next, jumper",total:t.total_rs},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange,"update:currentPage":function(e){t.currentPage=e},"update:current-page":function(e){t.currentPage=e}}})],1)])])],1)},o=[],r=(s("d3b7"),s("25f0"),s("e592")),n=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("el-card",{staticClass:"card-body"},[s("div",{staticClass:"nav-title",on:{click:t.changeHide}},[s("i",{ref:"icon",staticClass:"el-icon-arrow-up",staticStyle:{float:"right"},attrs:{id:"icon"}}),t._v(" "+t._s(this.filter_item.title)+" ")]),t.isVisible?s("div",{staticClass:"nav-list"},t._l(this.showList,(function(e,i){return s("div",{key:i,staticClass:"nav-list-item is-hover",on:{click:function(s){return t.clickFilter(e.content)}}},[s("span",[t._v(t._s(e.content))]),s("span",{attrs:{id:"item-nums"}},[t._v("( "+t._s(e.count)+" )")])])})),0):t._e(),t.isClicked?s("div",{staticClass:"nav-select"},[s("span",{staticClass:"nav-select-item"},[t._v(t._s(this.select_item))]),s("i",{staticClass:"el-icon-close is-hover",on:{click:t.clickCancel}})]):t._e(),t.isVisible&&t.listLength>3?s("div",{ref:"show-more",staticClass:"list-show  is-hover",on:{click:t.changeShowNums}},[t._v(t._s(!1===t.showMore?"+":"-"))]):t._e()])},a=[],c=(s("fb6a"),{name:"FilterItem",props:{filter_item:{type:Object,default:null},c_filter_words:{}},data:function(){return{isClicked:!1,select_item:"",listLength:0,showList:[],showMore:!1,isVisible:!0}},methods:{clickFilter:function(t){console.log("点击了"+t),this.$emit("getFilter",t,this.filter_item.filter_name)},clickCancel:function(){console.log("取消了筛选"),this.$emit("cancelSelect",this.filter_item.filter_name)},changeHide:function(){!0===this.isVisible?(this.isVisible=!1,this.$refs.icon.className="el-icon-arrow-down"):!0!==this.isClicked&&(this.isVisible=!0,this.$refs.icon.className="el-icon-arrow-up")},changeShowNums:function(){!1===this.showMore?(this.showMore=!0,this.showList=this.filter_item.filter_itemList.slice(0,Math.min(10,this.listLength))):(this.showMore=!1,this.showList=this.filter_item.filter_itemList.slice(0,Math.min(3,this.listLength))),console.log(this.filter_item.filter_itemList.length)},handleSelect:function(){var t=this.filter_item.filter_name;""===this.c_filter_words[t]?(this.isClicked=!1,this.isVisible=!0,this.$refs.icon.className="el-icon-arrow-up",console.log("暂时无")):(this.isClicked=!0,this.isVisible=!1,this.select_item=this.c_filter_words[t],this.$refs.icon.className="el-icon-arrow-down",console.log(this.c_filter_words[t]))}},watch:{c_filter_words:{handler:function(){console.log("子组件监听到了filter_words的变化："),console.log(this.c_filter_words),this.handleSelect()},deep:!0}},mounted:function(){this.handleSelect(),this.showMore=!1,this.listLength=this.filter_item.filter_itemList.length,this.showList=this.filter_item.filter_itemList.slice(0,Math.min(3,this.listLength))}}),l=c,u=(s("7eae"),s("2877")),d=Object(u["a"])(l,n,a,!1,null,"5c5e84a8",null),h=d.exports,_=s("71c2"),f={name:"AcademicSearch",components:{Header:_["a"],FilterItem:h,AcademicItem:r["a"]},data:function(){return{sourceUrl:"https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",result_list:[],total_rs:1e3,result_length:0,filter_list:[],currentPage:1,sort:"views",search_words:{experts:"",origin:"",kw:"",startTime:"0",endTime:"0"},filter_words:{year:"",cate:"",level:"",savetype:"",keywords:"",type:"",authors:"",jnls:"",affs:""},e_result_list:[{name:"qefsdfadfa噶人",id:43514,affiliate:"艾弗森adfasdfad计达"},{name:"按adfadsfasdf对",id:17565,affiliate:"你头发把asdfasdfa对"}],has_experts:!1,experts_count:0}},methods:{handleSelect:function(t,e){console.log("父组件监听到点击了filter类别："+e+" 的选线： "+t),this.filter_words[e]=t},handleCancel:function(t){console.log(t+"父组件监听到取消了filter类别："+t),this.filter_words[t]=""},handleToAuthor:function(t){this.$router.push({name:"ScholarPage",params:{expertid:t}})},searchAuthor:function(t){var e={experts:t,origin:"",kw:"",startTime:"0",endTime:"0"};this.$router.push({name:"AcademicSearch",params:{search_words:encodeURIComponent(JSON.stringify(e))}})},searchSource:function(t){var e={experts:"",origin:"",kw:t,startTime:"0",endTime:"0"};this.$router.push({name:"AcademicSearch",params:{search_words:encodeURIComponent(JSON.stringify(e))}})},sortByViews:function(){console.log("按浏览量排序！"),this.sort="views"},sortByCited:function(){console.log("按被引量排序！"),this.sort="cited"},sortByTime:function(){console.log("按时间降序排序！"),this.sort="time"},handleSizeChange:function(t){console.log("每页 ".concat(t," 条"))},handleCurrentChange:function(t){console.log("当前页: ".concat(t))},loadSearchSc:function(){var t=this;this.$api.academic.getSearchResult({search_words:t.search_words,filter_words:t.filter_words,sort:t.sort,page:t.currentPage,userID:null===sessionStorage.getItem("userID")?-1:sessionStorage.getItem("userID")}).then((function(e){"200"===e.code?(t.result_list=e.data.result_list,t.filter_list=e.data.filter_list,t.total_rs=e.data.total,t.result_length=t.result_list.length,t.e_result_list=e.data.e_result_list,0===t.e_result_list.length?t.has_experts=!1:(t.has_experts=!0,t.experts_count=t.e_result_list.length)):(t.$message({message:e.message,type:"error"}),console.log("Request => getSearchResult : not 200"))}))}},computed:{getSortMethod:function(){return"views"===this.sort?"浏览量":"cited"===this.sort?"被引量":"时间降序"}},watch:{sort:function(){console.log("sort被修改"),sessionStorage.setItem("sort",this.sort),document.body.scrollTop=document.documentElement.scrollTop=0;var t=this;this.result_list=[],this.$api.academic.getSearchResult({search_words:t.search_words,filter_words:t.filter_words,sort:t.sort,page:t.currentPage,userID:null===sessionStorage.getItem("userID")?-1:sessionStorage.getItem("userID")}).then((function(e){"200"===e.code?(t.result_list=e.data.result_list,t.total_rs=e.data.total,t.result_length=t.result_list.length):(t.$message({message:e.message,type:"error"}),console.log("Request => getSearchResult : not 200"))}))},currentPage:function(){console.log("page被修改"),sessionStorage.setItem("current_page",this.currentPage.toString()),document.body.scrollTop=document.documentElement.scrollTop=0;var t=this;this.result_list=[],this.$api.academic.getSearchResult({search_words:t.search_words,filter_words:t.filter_words,sort:t.sort,page:t.currentPage,userID:null===sessionStorage.getItem("userID")?-1:sessionStorage.getItem("userID")}).then((function(e){"200"===e.code?(t.result_list=e.data.result_list,t.result_length=t.result_list.length,t.total_rs=e.data.total):(t.$message({message:e.message,type:"error"}),console.log("Request => getSearchResult : not 200"))}))},filter_words:{handler:function(){console.log("filters改变"),document.body.scrollTop=document.documentElement.scrollTop=0;var t=this;this.result_list=[],this.$api.academic.getSearchResult({search_words:t.search_words,filter_words:t.filter_words,sort:t.sort,page:t.currentPage,userID:null===sessionStorage.getItem("userID")?-1:sessionStorage.getItem("userID")}).then((function(e){"200"===e.code?(t.result_list=e.data.result_list,t.result_length=t.result_list.length,t.total_rs=e.data.total):(t.$message({message:e.message,type:"error"}),console.log("Request => getSearchResult : not 200"))}))},deep:!0}},mounted:function(){document.body.scrollTop=document.documentElement.scrollTop=0,this.search_words=JSON.parse(decodeURIComponent(this.$route.params.search_words)),1===window.performance.navigation.type?(console.log("页面被刷新"),this.currentPage=parseInt(sessionStorage.getItem("current_page")),this.sort=sessionStorage.getItem("sort"),null===this.sort&&(this.sort="views")):(console.log("首次被加载"),this.currentPage=1,sessionStorage.setItem("current_page","1"),this.sort="views",sessionStorage.setItem("sort","views")),console.log("获取关键词："+this.search_words),console.log("页码："+this.currentPage),console.log("排序方式"+this.sort),this.loadSearchSc()}},m=f,g=(s("da8d"),s("3b48"),Object(u["a"])(m,i,o,!1,null,"6d9944cc",null));e["default"]=g.exports},"4e87":function(t,e,s){},"70a0":function(t,e,s){},"71c2":function(t,e,s){"use strict";var i=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",[1==this.status?s("el-header",{staticClass:"header_1"},[s("el-input",{staticClass:"h_search",attrs:{placeholder:"请输入你要查找的内容"},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.goSearch(!1)}},model:{value:t.search_words.kw,callback:function(e){t.$set(t.search_words,"kw",e)},expression:"search_words.kw"}},[s("el-button",{directives:[{name:"popover",rawName:"v-popover:search",arg:"search"}],attrs:{slot:"append"},slot:"append"},[t._v("高级搜索")])],1),s("el-popover",{ref:"search",attrs:{placement:"bottom",width:"600",offset:-250,title:"高级搜索",trigger:"click"}},[s("el-form",{ref:"search_words",attrs:{model:t.search_words,"label-width":"80px"}},[s("el-form-item",{attrs:{label:"检索词"}},[s("el-input",{attrs:{placeholder:"多个检索词用空格分开"},model:{value:t.search_words.kw,callback:function(e){t.$set(t.search_words,"kw",e)},expression:"search_words.kw"}})],1),s("el-form-item",{attrs:{label:"作者"}},[s("el-input",{attrs:{placeholder:"多个作者用空格分开"},model:{value:t.search_words.experts,callback:function(e){t.$set(t.search_words,"experts",e)},expression:"search_words.experts"}})],1),s("el-form-item",{attrs:{label:"来源"}},[s("el-input",{model:{value:t.search_words.origin,callback:function(e){t.$set(t.search_words,"origin",e)},expression:"search_words.origin"}})],1),s("el-form-item",{attrs:{label:"发表时间"}},[s("el-col",{attrs:{span:11}},[s("el-date-picker",{staticStyle:{width:"90%"},attrs:{type:"date",placeholder:"选择起始日期"},model:{value:t.search_words.startTime,callback:function(e){t.$set(t.search_words,"startTime",e)},expression:"search_words.startTime"}})],1),s("el-col",{attrs:{span:11}},[s("el-date-picker",{staticStyle:{width:"90%"},attrs:{type:"date",placeholder:"选择截至日期"},model:{value:t.search_words.endTime,callback:function(e){t.$set(t.search_words,"endTime",e)},expression:"search_words.endTime"}})],1)],1),s("el-form-item",[s("el-button",{on:{click:function(e){return t.goSearch(!0)}}},[t._v("搜索")])],1)],1)],1),s("div",{staticClass:"r_con"},[s("el-button",{directives:[{name:"popover",rawName:"v-popover:popover",arg:"popover"}],staticClass:"r_con_mess",attrs:{type:"text"}},[t._v("消息")]),t.isLogin?s("el-button",{staticClass:"r_con_user",attrs:{type:"text"},on:{click:function(e){return t.goUser()}}},[t._v(t._s(this.userName))]):t._e(),t.isLogin?s("el-button",{staticClass:"r_con_reLogin",attrs:{type:"text"},on:{click:function(e){return t.reLogin()}}},[t._v("退出登录")]):t._e(),t.isLogin?t._e():s("el-button",{staticClass:"r_con_login",attrs:{type:"text"},on:{click:function(e){return t.goLogin()}}},[t._v("登录")]),t.isLogin?t._e():s("el-button",{staticClass:"r_con_Register",attrs:{type:"text"},on:{click:function(e){return t.goRegister()}}},[t._v("注册")])],1),s("el-popover",{ref:"popover",attrs:{title:"消息",placement:"bottom",width:"200",trigger:"click",content:"123456789"}},t._l(4,(function(e,i){return s("el-row",{key:i},[s("el-card",[t._v("123456")])],1)})),1)],1):t._e(),2==this.status?s("el-header",{staticClass:"header_2"},[s("div",{staticClass:"r_con"},[s("el-button",{directives:[{name:"popover",rawName:"v-popover:popover",arg:"popover"}],staticClass:"r_con_mess",attrs:{type:"text"}},[t._v("消息")]),t.isLogin?s("el-button",{staticClass:"r_con_user",attrs:{type:"text"},on:{click:function(e){return t.goUser()}}},[t._v(t._s(this.userName))]):t._e(),t.isLogin?s("el-button",{staticClass:"r_con_reLogin",attrs:{type:"text"},on:{click:function(e){return t.reLogin()}}},[t._v("退出登录")]):t._e(),t.isLogin?t._e():s("el-button",{staticClass:"r_con_login",attrs:{type:"text"},on:{click:function(e){return t.goLogin()}}},[t._v("登录")]),t.isLogin?t._e():s("el-button",{staticClass:"r_con_Register",attrs:{type:"text"},on:{click:function(e){return t.goRegister()}}},[t._v("注册")])],1),s("el-popover",{ref:"popover",attrs:{title:"消息",placement:"bottom",width:"200",trigger:"click",content:"123456789"}},t._l(4,(function(e,i){return s("el-row",{key:i},[s("el-card",[t._v("123456")])],1)})),1)],1):t._e()],1)},o=[],r={name:"header",data:function(){return{search_words:{kw:"",experts:"",origin:"",startTime:0,endTime:0},isLogin:!1,userName:""}},props:{status:{required:!1,default:"1"}},methods:{goSearch:function(t){t?""===this.search_words.kw&&""===this.search_words.experts&&""===this.search_words.origin&&0===this.search_words.startTime&&0===this.search_words.endTime?alert("搜索内容为空"):this.$router.push({name:"AcademicSearch",params:{search_words:encodeURIComponent(JSON.stringify(this.search_words))}}):""!==this.search_words.kw?this.$router.push({name:"AcademicSearch",params:{search_words:encodeURIComponent(JSON.stringify(this.search_words))}}):alert("搜索内容为空")},goUser:function(){this.$router.push({name:"PerInfo"})},goLogin:function(){this.$router.push({name:"Login"})},goRegister:function(){this.$router.push({name:"Register"})},reLogin:function(){sessionStorage.clear(),location.reload()}},mounted:function(){null==sessionStorage.getItem("userName")&&null==sessionStorage.getItem("userID")||(this.isLogin=!0,this.userName=sessionStorage.getItem("userName"))}},n=r,a=(s("acfe"),s("2877")),c=Object(a["a"])(n,i,o,!1,null,"179a65a8",null);e["a"]=c.exports},"7eae":function(t,e,s){"use strict";s("9385")},"806f":function(t,e,s){"use strict";s("70a0")},8418:function(t,e,s){"use strict";var i=s("c04e"),o=s("9bf2"),r=s("5c6c");t.exports=function(t,e,s){var n=i(e);n in t?o.f(t,n,r(0,s)):t[n]=s}},9385:function(t,e,s){},aa26:function(t,e,s){},acfe:function(t,e,s){"use strict";s("d278")},ad6d:function(t,e,s){"use strict";var i=s("825a");t.exports=function(){var t=i(this),e="";return t.global&&(e+="g"),t.ignoreCase&&(e+="i"),t.multiline&&(e+="m"),t.dotAll&&(e+="s"),t.unicode&&(e+="u"),t.sticky&&(e+="y"),e}},ae40:function(t,e,s){var i=s("83ab"),o=s("d039"),r=s("5135"),n=Object.defineProperty,a={},c=function(t){throw t};t.exports=function(t,e){if(r(a,t))return a[t];e||(e={});var s=[][t],l=!!r(e,"ACCESSORS")&&e.ACCESSORS,u=r(e,0)?e[0]:c,d=r(e,1)?e[1]:void 0;return a[t]=!!s&&!o((function(){if(l&&!i)return!0;var t={length:-1};l?n(t,1,{enumerable:!0,get:c}):t[1]=1,s.call(t,u,d)}))}},b0c0:function(t,e,s){var i=s("83ab"),o=s("9bf2").f,r=Function.prototype,n=r.toString,a=/^\s*function ([^ (]*)/,c="name";i&&!(c in r)&&o(r,c,{configurable:!0,get:function(){try{return n.call(this).match(a)[1]}catch(t){return""}}})},d278:function(t,e,s){},da8d:function(t,e,s){"use strict";s("4e87")},e592:function(t,e,s){"use strict";var i=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("el-card",{ref:"card",staticClass:"card-body",staticStyle:{"text-align":"left"}},[s("div",{staticClass:"sc-content"},[s("div",{staticClass:"title"},[s("span",{staticClass:"title-click",on:{click:t.toDetailPage}},[t._v(t._s(t.item.title))])]),s("div",{staticClass:"summary"},[t._v(t._s(t.item.summary))]),s("div",{staticClass:"info"},[t._l(t.item.authors,(function(e,i){return s("span",{key:i},[s("span",{staticClass:"name-click",on:{click:function(s){return t.toAuthorPage(e)}}},[t._v(t._s(e.name)+" ")])])})),s("span",[t._v(" - ")]),s("span",{staticClass:"source-click",on:{click:function(e){return t.toSourcePage(t.item.origin)}}},[t._v(t._s(t.item.origin))]),s("span",[t._v(" - 被引量：")]),s("span",{staticClass:"citation-click",on:{click:function(e){return t.toCitedPage()}}},[t._v(t._s(t.item.cited_quantity))]),s("span",[t._v(" - ")]),s("span",{staticClass:"grey-part"},[t._v(t._s(t.item.time.substring(0,4))+"年")])],2),s("div",{staticClass:"allVersion"},[s("span",{staticClass:"grey-part"},[t._v("来源：")]),s("span",{staticClass:"version-click",on:{click:function(e){return t.toLink(t.item.link)}}},[t._v(t._s(t.item.link))])])]),s("div",{staticClass:"sc_ext"},[this.is_favor?s("el-button",{staticClass:"ext-button",attrs:{id:"button-unfavor",type:"mini",round:"",icon:"el-icon-star-on"},on:{click:function(e){return t.favor()}}},[t._v("已收藏")]):t._e(),this.is_favor?t._e():s("el-button",{staticClass:"ext-button",attrs:{id:"button-favor",type:"mini",round:"",icon:"el-icon-star-off"},on:{click:function(e){return t.favor()}}},[t._v("收藏")]),s("el-button",{staticClass:"ext-button",attrs:{id:"button-quote",type:"mini",round:"",icon:"el-icon-share"},on:{click:function(e){return t.quote()}}},[t._v("引用")]),s("el-button",{staticClass:"ext-button",attrs:{id:"button-batchQuote",type:"mini",round:"",icon:"el-icon-folder-add"},on:{click:function(e){return t.batchQuote()}}},[t._v("批量引用")]),s("el-button",{staticClass:"ext-button",attrs:{id:"button-download",type:"mini",round:"",icon:"el-icon-download"},on:{click:function(e){return t.download()}}},[t._v("免费下载")])],1)])},o=[],r=(s("b0c0"),{name:"AcademicItem",props:{c_sc:{type:Object,default:null}},data:function(){return{item:{},is_favor:!1}},methods:{toDetailPage:function(){console.log("跳转文章详情页！");var t=window.location.origin+"/#/academicShow/"+this.item.id;null===window.open(t)&&(window.location.herf=t)},toAuthorPage:function(t){console.log("跳转学者搜索！"),null!==t.id?this.$router.push({name:"ScholarPage",params:{expertid:t.id}}):this.$emit("toAuthorPage",t.name)},toSourcePage:function(t){console.log("跳转来源搜索！"),this.$emit("toSourcePage",t)},toCitedPage:function(){console.log("跳转引用详情页！")},toLink:function(t){window.open(t,"_blank")},favor:function(){console.log("收藏/取消收藏文章！");var t=this;this.$api.academic.favorSc({document_id:t.item.documentid,user_id:sessionStorage.getItem("userID")}).then((function(e){"200"===e.code?(t.is_favor=e.data,!0===t.is_favor?t.$message({message:"成功收藏该资源！",type:"success"}):t.$message({message:"成功取消收藏该资源！",type:"success"})):t.$message({message:e.message,type:"error"})})),document.getElementById("button-favor").blur()},quote:function(){console.log("引用文章！"),document.getElementById("button-quote").blur()},batchQuote:function(){console.log("批量引用文章！"),document.getElementById("button-batchQuote").blur()},download:function(){console.log("免费下载！"),document.getElementById("button-download").blur()}},watch:{c_sc:{handler:function(){this.item=this.c_sc},deep:!0}},mounted:function(){this.item=this.c_sc,this.is_favor=this.item.is_favor}}),n=r,a=(s("806f"),s("2877")),c=Object(a["a"])(n,i,o,!1,null,"0fb68611",null);e["a"]=c.exports},e8b5:function(t,e,s){var i=s("c6b6");t.exports=Array.isArray||function(t){return"Array"==i(t)}},fb6a:function(t,e,s){"use strict";var i=s("23e7"),o=s("861d"),r=s("e8b5"),n=s("23cb"),a=s("50c4"),c=s("fc6a"),l=s("8418"),u=s("b622"),d=s("1dde"),h=s("ae40"),_=d("slice"),f=h("slice",{ACCESSORS:!0,0:0,1:2}),m=u("species"),g=[].slice,p=Math.max;i({target:"Array",proto:!0,forced:!_||!f},{slice:function(t,e){var s,i,u,d=c(this),h=a(d.length),_=n(t,h),f=n(void 0===e?h:e,h);if(r(d)&&(s=d.constructor,"function"!=typeof s||s!==Array&&!r(s.prototype)?o(s)&&(s=s[m],null===s&&(s=void 0)):s=void 0,s===Array||void 0===s))return g.call(d,_,f);for(i=new(void 0===s?Array:s)(p(f-_,0)),u=0;_<f;_++,u++)_ in d&&l(i,u,d[_]);return i.length=u,i}})}}]);
//# sourceMappingURL=chunk-59d8c841.7dbd56d0.js.map