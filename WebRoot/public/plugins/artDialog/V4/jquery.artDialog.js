/*!
 * artDialog 4.1.7
 * Date: 2013-03-03 08:04
 * http://code.google.com/p/artdialog/
 * (c) 2009-2012 TangBin, http://www.planeArt.cn
 *
 * This is licensed under the GNU LGPL, version 2.1 or later.
 * For details, see: http://creativecommons.org/licenses/LGPL/2.1/
 */
(function(d,l,g){d.noop=d.noop||function(){};var n,b,j,c,r=0,s=d(l),h=d(document),f=d("html"),m=document.documentElement,a=l.VBArray&&!l.XMLHttpRequest,p="createTouch" in document&&!("onmousemove" in m)||/(iPhone|iPad|iPod)/i.test(navigator.userAgent),o="artDialog"+ +new Date;var k=function(e,u,x){e=e||{};if(typeof e==="string"||e.nodeType===1){e={content:e,fixed:!p}}var v,y=k.defaults,w=e.follow=this.nodeType===1&&this||e.follow;for(var t in y){if(e[t]===g){e[t]=y[t]}}d.each({ok:"yesFn",cancel:"noFn",close:"closeFn",init:"initFn",okVal:"yesText",cancelVal:"noText"},function(z,A){e[z]=e[z]!==g?e[z]:e[A]});if(typeof w==="string"){w=d(w)[0]}e.id=w&&w[o+"follow"]||e.id||o+r;v=k.list[e.id];if(w&&v){return v.follow(w).zIndex().focus()}if(v){return v.zIndex().focus()}if(p){e.fixed=false}if(!d.isArray(e.button)){e.button=e.button?[e.button]:[]}if(u!==g){e.ok=u}if(x!==g){e.cancel=x}e.ok&&e.button.push({name:e.okVal,callback:e.ok,focus:true});e.cancel&&e.button.push({name:e.cancelVal,callback:e.cancel});k.defaults.zIndex=e.zIndex;r++;return k.list[e.id]=n?n._init(e):new k.fn._init(e)};k.fn=k.prototype={version:"4.1.7",closed:true,_init:function(e){var v=this,u,t=e.icon,w=t&&(a?{png:"icons/"+t+".png"}:{backgroundImage:"url('"+e.path+"/skins/icons/"+t+".png')"});v.closed=false;v.config=e;v.DOM=u=v.DOM||v._getDOM();u.wrap.addClass(e.skin);u.close[e.cancel===false?"hide":"show"]();u.icon[0].style.display=t?"":"none";u.iconBg.css(w||{background:"none"});u.se.css("cursor",e.resize?"se-resize":"auto");u.title.css("cursor",e.drag?"move":"auto");u.content.css("padding",e.padding);v[e.show?"show":"hide"](true);v.button(e.button).title(e.title).content(e.content,true).size(e.width,e.height).time(e.time);e.follow?v.follow(e.follow):v.position(e.left,e.top);v.zIndex().focus();e.lock&&v.lock();v._addEvent();v._ie6PngFix();n=null;e.init&&e.init.call(v,l);return v},content:function(v){var x,y,E,B,z=this,G=z.DOM,u=G.wrap[0],t=u.offsetWidth,F=u.offsetHeight,w=parseInt(u.style.left),C=parseInt(u.style.top),D=u.style.width,e=G.content,A=e[0];z._elemBack&&z._elemBack();u.style.width="auto";if(v===g){return A}if(typeof v==="string"){e.html(v)}else{if(v&&v.nodeType===1){B=v.style.display;x=v.previousSibling;y=v.nextSibling;E=v.parentNode;z._elemBack=function(){if(x&&x.parentNode){x.parentNode.insertBefore(v,x.nextSibling)}else{if(y&&y.parentNode){y.parentNode.insertBefore(v,y)}else{if(E){E.appendChild(v)}}}v.style.display=B;z._elemBack=null};e.html("");A.appendChild(v);v.style.display="block"}}if(!arguments[1]){if(z.config.follow){z.follow(z.config.follow)}else{t=u.offsetWidth-t;F=u.offsetHeight-F;w=w-t/2;C=C-F/2;u.style.left=Math.max(w,0)+"px";u.style.top=Math.max(C,0)+"px"}if(D&&D!=="auto"){u.style.width=u.offsetWidth+"px"}z._autoPositionType()}z._ie6SelectFix();z._runScript(A);return z},title:function(w){var u=this.DOM,t=u.wrap,v=u.title,e="aui_state_noTitle";if(w===g){return v[0]}if(w===false){v.hide().html("");t.addClass(e)}else{v.show().html(w||"");t.removeClass(e)}return this},position:function(z,F){var E=this,x=E.config,u=E.DOM.wrap[0],A=a?false:x.fixed,D=a&&E.config.fixed,y=h.scrollLeft(),H=h.scrollTop(),C=A?0:y,v=A?0:H,B=s.width(),t=s.height(),w=u.offsetWidth,G=u.offsetHeight,e=u.style;if(z||z===0){E._left=z.toString().indexOf("%")!==-1?z:null;z=E._toNumber(z,B-w);if(typeof z==="number"){z=D?(z+=y):z+C;e.left=Math.max(z,C)+"px"}else{if(typeof z==="string"){e.left=z}}}if(F||F===0){E._top=F.toString().indexOf("%")!==-1?F:null;F=E._toNumber(F,t-G);if(typeof F==="number"){F=D?(F+=H):F+v;e.top=Math.max(F,v)+"px"}else{if(typeof F==="string"){e.top=F}}}if(z!==g&&F!==g){E._follow=null;E._autoPositionType()}return E},size:function(v,C){var A,B,e,E,y=this,w=y.config,D=y.DOM,u=D.wrap,x=D.main,z=u[0].style,t=x[0].style;if(v){y._width=v.toString().indexOf("%")!==-1?v:null;A=s.width()-u[0].offsetWidth+x[0].offsetWidth;e=y._toNumber(v,A);v=e;if(typeof v==="number"){z.width="auto";t.width=Math.max(y.config.minWidth,v)+"px";z.width=u[0].offsetWidth+"px"}else{if(typeof v==="string"){t.width=v;v==="auto"&&u.css("width","auto")}}}if(C){y._height=C.toString().indexOf("%")!==-1?C:null;B=s.height()-u[0].offsetHeight+x[0].offsetHeight;E=y._toNumber(C,B);C=E;if(typeof C==="number"){t.height=Math.max(y.config.minHeight,C)+"px"}else{if(typeof C==="string"){t.height=C}}}y._ie6SelectFix();return y},follow:function(N){var e,B=this,O=B.config;if(typeof N==="string"||N&&N.nodeType===1){e=d(N);N=e[0]}if(!N||!N.offsetWidth&&!N.offsetHeight){return B.position(B._left,B._top)}var z=o+"follow",E=s.width(),u=s.height(),w=h.scrollLeft(),y=h.scrollTop(),x=e.offset(),J=N.offsetWidth,I=N.offsetHeight,A=a?false:O.fixed,v=A?x.left-w:x.left,G=A?x.top-y:x.top,C=B.DOM.wrap[0],M=C.style,t=C.offsetWidth,L=C.offsetHeight,D=v-(t-J)/2,H=G+I,K=A?0:w,F=A?0:y;D=D<K?v:(D+t>E)&&(v-t>K)?v-t+J:D;H=(H+L>u+F)&&(G-L>F)?G-L:H;M.left=D+"px";M.top=H+"px";B._follow&&B._follow.removeAttribute(z);B._follow=N;N[z]=O.id;B._autoPositionType();return B},button:function(){var x=this,z=arguments,w=x.DOM,v=w.buttons,u=v[0],t="aui_state_highlight",e=x._listeners=x._listeners||{},y=d.isArray(z[0])?z[0]:[].slice.call(z);if(z[0]===g){return u}d.each(y,function(C,E){var A=E.name,D=!e[A],B=!D?e[A].elem:document.createElement("button");if(!e[A]){e[A]={}}if(E.callback){e[A].callback=E.callback}if(E.className){B.className=E.className}if(E.focus){x._focus&&x._focus.removeClass(t);x._focus=d(B).addClass(t);x.focus()}B.setAttribute("type","button");B[o+"callback"]=A;B.disabled=!!E.disabled;if(D){B.innerHTML=A;e[A].elem=B;u.appendChild(B)}});v[0].style.display=y.length?"":"none";x._ie6SelectFix();return x},show:function(){this.DOM.wrap.show();!arguments[0]&&this._lockMaskWrap&&this._lockMaskWrap.show();return this},hide:function(){this.DOM.wrap.hide();!arguments[0]&&this._lockMaskWrap&&this._lockMaskWrap.hide();return this},close:function(){if(this.closed){return this}var x=this,w=x.DOM,v=w.wrap,y=k.list,u=x.config.close,e=x.config.follow;x.time();if(typeof u==="function"&&u.call(x,l)===false){return x}x.unlock();x._elemBack&&x._elemBack();v[0].className=v[0].style.cssText="";w.title.html("");w.content.html("");w.buttons.html("");if(k.focus===x){k.focus=null}if(e){e.removeAttribute(o+"follow")}delete y[x.config.id];x._removeEvent();x.hide(true)._setAbsolute();for(var t in x){if(x.hasOwnProperty(t)&&t!=="DOM"){delete x[t]}}n?v.remove():n=x;return x},time:function(e){var u=this,t=u.config.cancelVal,v=u._timer;v&&clearTimeout(v);if(e){u._timer=setTimeout(function(){u._click(t)},1000*e)}return u},focus:function(){try{if(this.config.focus){var t=this._focus&&this._focus[0]||this.DOM.close[0];t&&t.focus()}}catch(u){}return this},zIndex:function(){var v=this,u=v.DOM,t=u.wrap,w=k.focus,e=k.defaults.zIndex++;t.css("zIndex",e);v._lockMask&&v._lockMask.css("zIndex",e-1);w&&w.DOM.wrap.removeClass("aui_state_focus");k.focus=v;t.addClass("aui_state_focus");return v},lock:function(){if(this._lock){return this}var x=this,y=k.defaults.zIndex-1,u=x.DOM.wrap,w=x.config,z=h.width(),C=h.height(),A=x._lockMaskWrap||d(document.body.appendChild(document.createElement("div"))),v=x._lockMask||d(A[0].appendChild(document.createElement("div"))),t="(document).documentElement",e=p?"width:"+z+"px;height:"+C+"px":"width:100%;height:100%",B=a?"position:absolute;left:expression("+t+".scrollLeft);top:expression("+t+".scrollTop);width:expression("+t+".clientWidth);height:expression("+t+".clientHeight)":"";x.zIndex();u.addClass("aui_state_lock");A[0].style.cssText=e+";position:fixed;z-index:"+y+";top:0;left:0;overflow:hidden;"+B;v[0].style.cssText="height:100%;background:"+w.background+";filter:alpha(opacity=0);opacity:0";if(a){v.html('<iframe src="about:blank" style="width:100%;height:100%;position:absolute;top:0;left:0;z-index:-1;filter:alpha(opacity=0)"></iframe>')}v.stop();v.bind("click",function(){x._reset()}).bind("dblclick",function(){x._click(x.config.cancelVal)});if(w.duration===0){v.css({opacity:w.opacity})}else{v.animate({opacity:w.opacity},w.duration)}x._lockMaskWrap=A;x._lockMask=v;x._lock=true;return x},unlock:function(){var w=this,u=w._lockMaskWrap,e=w._lockMask;if(!w._lock){return w}var v=u[0].style;var t=function(){if(a){try{v.removeExpression("width");v.removeExpression("height");v.removeExpression("left");v.removeExpression("top")}catch(x){}}v.cssText="display:none";n&&u.remove()};e.stop().unbind();w.DOM.wrap.removeClass("aui_state_lock");if(!w.config.duration){t()}else{e.animate({opacity:0},w.config.duration,t)}w._lock=false;return w},_getDOM:function(){var x=document.createElement("div"),e=document.body;x.style.cssText="position:absolute;left:0;top:0";x.innerHTML=k._templates;e.insertBefore(x,e.firstChild);var u,w=0,y={wrap:d(x)},v=x.getElementsByTagName("*"),t=v.length;for(;w<t;w++){u=v[w].className.split("aui_")[1];if(u){y[u]=d(v[w])}}return y},_toNumber:function(e,u){if(!e&&e!==0||typeof e==="number"){return e}var t=e.length-1;if(e.lastIndexOf("px")===t){e=parseInt(e)}else{if(e.lastIndexOf("%")===t){e=parseInt(u*e.split("%")[0]/100)}}return e},_ie6PngFix:a?function(){var t=0,v,y,u,e,x=k.defaults.path+"/skins/",w=this.DOM.wrap[0].getElementsByTagName("*");for(;t<w.length;t++){v=w[t];y=v.currentStyle.png;if(y){u=x+y;e=v.runtimeStyle;e.backgroundImage="none";e.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+u+"',sizingMethod='crop')"}}}:d.noop,_ie6SelectFix:a?function(){var t=this.DOM.wrap,w=t[0],x=o+"iframeMask",v=t[x],u=w.offsetWidth,e=w.offsetHeight;u=u+"px";e=e+"px";if(v){v.style.width=u;v.style.height=e}else{v=w.appendChild(document.createElement("iframe"));t[x]=v;v.src="about:blank";v.style.cssText="position:absolute;z-index:-1;left:0;top:0;filter:alpha(opacity=0);width:"+u+";height:"+e}}:d.noop,_runScript:function(x){var e,v=0,y=0,u=x.getElementsByTagName("script"),w=u.length,t=[];for(;v<w;v++){if(u[v].type==="text/dialog"){t[y]=u[v].innerHTML;y++}}if(t.length){t=t.join("");e=new Function(t);e.call(this)}},_autoPositionType:function(){this[this.config.fixed?"_setFixed":"_setAbsolute"]()},_setFixed:(function(){a&&d(function(){var e="backgroundAttachment";if(f.css(e)!=="fixed"&&d("body").css(e)!=="fixed"){f.css({zoom:1,backgroundImage:"url(about:blank)",backgroundAttachment:"fixed"})}});return function(){var w=this.DOM.wrap,x=w[0].style;if(a){var A=parseInt(w.css("left")),z=parseInt(w.css("top")),v=h.scrollLeft(),u=h.scrollTop(),t="(document.documentElement)";this._setAbsolute();try{x.setExpression("left","eval("+t+".scrollLeft + "+(A-v)+') + "px"');x.setExpression("top","eval("+t+".scrollTop + "+(z-u)+') + "px"')}catch(y){}}else{x.position="fixed"}}}()),_setAbsolute:function(){var t=this.DOM.wrap[0].style;if(a){try{t.removeExpression("left");t.removeExpression("top")}catch(u){}}t.position="absolute"},_click:function(e){var u=this,t=u._listeners[e]&&u._listeners[e].callback;return typeof t!=="function"||t.call(u,l)!==false?u.close():u},_reset:function(y){var x,w=this,e=w._winSize||s.width()*s.height(),v=w._follow,t=w._width,A=w._height,u=w._left,z=w._top;if(y){x=w._winSize=s.width()*s.height();if(e===x){return}}if(t||A){w.size(t,A)}if(v){w.follow(v)}else{if(u||z){w.position(u,z)}}},_addEvent:function(){var e,w=this,t=w.config,u="CollectGarbage" in l,v=w.DOM;w._winResize=function(){e&&clearTimeout(e);e=setTimeout(function(){w._reset(u)},40)};s.bind("resize",w._winResize);v.wrap.bind("click",function(y){var z=y.target,x;if(z.disabled){return false}if(z===v.close[0]){w._click(t.cancelVal);return false}else{x=z[o+"callback"];x&&w._click(x)}w._ie6SelectFix()}).bind("mousedown",function(){w.zIndex()})},_removeEvent:function(){var t=this,e=t.DOM;e.wrap.unbind();s.unbind("resize",t._winResize)}};k.fn._init.prototype=k.fn;d.fn.dialog=d.fn.artDialog=function(){var e=arguments;this[this.live?"live":"bind"]("click",function(){k.apply(this,e);return false});return this};k.focus=null;k.get=function(e){return e===g?k.list:k.list[e]};k.list={};h.bind("keydown",function(u){var w=u.target,x=w.nodeName,e=/^INPUT|TEXTAREA$/,t=k.focus,v=u.keyCode;if(!t||!t.config.esc||e.test(x)){return}v===27&&t._click(t.config.cancelVal)});c=l._artDialog_path||(function(e,t,u){for(t in e){if(e[t].src&&e[t].src.indexOf("artDialog")!==-1){u=e[t]}}b=u||e[e.length-1];u=b.src.replace(/\\/g,"/");return u.lastIndexOf("/")<0?".":u.substring(0,u.lastIndexOf("/"))}(document.getElementsByTagName("script")));j=b.src.split("skin=")[1];if(j){var i=document.createElement("link");i.rel="stylesheet";i.href=c+"/skins/"+j+".css?"+k.fn.version;b.parentNode.insertBefore(i,b)}s.bind("load",function(){setTimeout(function(){if(r){return}k({left:"-9999em",time:9,fixed:false,lock:false,focus:false})},150)});try{document.execCommand("BackgroundImageCache",false,true)}catch(q){}k._templates='<div class="aui_outer"><table class="aui_border"><tbody><tr><td class="aui_nw"></td><td class="aui_n"></td><td class="aui_ne"></td></tr><tr><td class="aui_w"></td><td class="aui_c"><div class="aui_inner"><table class="aui_dialog"><tbody><tr><td colspan="2" class="aui_header"><div class="aui_titleBar"><div class="aui_title"></div><a class="aui_close" href="javascript:/*artDialog*/;">\xd7</a></div></td></tr><tr><td class="aui_icon"><div class="aui_iconBg"></div></td><td class="aui_main"><div class="aui_content"></div></td></tr><tr><td colspan="2" class="aui_footer"><div class="aui_buttons"></div></td></tr></tbody></table></div></td><td class="aui_e"></td></tr><tr><td class="aui_sw"></td><td class="aui_s"></td><td class="aui_se"></td></tr></tbody></table></div>';k.defaults={content:'<div class="aui_loading"><span>loading..</span></div>',title:"\u6d88\u606f",button:null,ok:null,cancel:null,init:null,close:null,okVal:"\u786E\u5B9A",cancelVal:"\u53D6\u6D88",width:"auto",height:"auto",minWidth:96,minHeight:32,padding:"20px 25px",skin:"",icon:null,time:null,esc:true,focus:true,show:true,follow:null,path:c,lock:false,background:"#000",opacity:0.7,duration:300,fixed:false,left:"50%",top:"38.2%",zIndex:1987,resize:true,drag:true};l.artDialog=d.dialog=d.artDialog=k}(this.art||this.jQuery&&(this.art=jQuery),this));(function(e){var h,b,a=e(window),d=e(document),i=document.documentElement,f=!("minWidth" in i.style),g="onlosecapture" in i,c="setCapture" in i;artDialog.dragEvent=function(){var k=this,j=function(l){var m=k[l];k[l]=function(){return m.apply(k,arguments)}};j("start");j("move");j("end")};artDialog.dragEvent.prototype={onstart:e.noop,start:function(j){d.bind("mousemove",this.move).bind("mouseup",this.end);this._sClientX=j.clientX;this._sClientY=j.clientY;this.onstart(j.clientX,j.clientY);return false},onmove:e.noop,move:function(j){this._mClientX=j.clientX;this._mClientY=j.clientY;this.onmove(j.clientX-this._sClientX,j.clientY-this._sClientY);return false},onend:e.noop,end:function(j){d.unbind("mousemove",this.move).unbind("mouseup",this.end);this.onend(j.clientX,j.clientY);return false}};b=function(j){var n,o,u,l,q,s,p=artDialog.focus,v=p.DOM,k=v.wrap,r=v.title,m=v.main;var t="getSelection" in window?function(){window.getSelection().removeAllRanges()}:function(){try{document.selection.empty()}catch(w){}};h.onstart=function(w,z){if(s){o=m[0].offsetWidth;u=m[0].offsetHeight}else{l=k[0].offsetLeft;q=k[0].offsetTop}d.bind("dblclick",h.end);!f&&g?r.bind("losecapture",h.end):a.bind("blur",h.end);c&&r[0].setCapture();k.addClass("aui_state_drag");p.focus()};h.onmove=function(z,F){if(s){var C=k[0].style,B=m[0].style,A=z+o,w=F+u;C.width="auto";B.width=Math.max(0,A)+"px";C.width=k[0].offsetWidth+"px";B.height=Math.max(0,w)+"px"}else{var B=k[0].style,E=Math.max(n.minX,Math.min(n.maxX,z+l)),D=Math.max(n.minY,Math.min(n.maxY,F+q));B.left=E+"px";B.top=D+"px"}t();p._ie6SelectFix()};h.onend=function(w,z){d.unbind("dblclick",h.end);!f&&g?r.unbind("losecapture",h.end):a.unbind("blur",h.end);c&&r[0].releaseCapture();f&&!p.closed&&p._autoPositionType();k.removeClass("aui_state_drag")};s=j.target===v.se[0]?true:false;n=(function(){var x,w,z=p.DOM.wrap[0],C=z.style.position==="fixed",B=z.offsetWidth,F=z.offsetHeight,D=a.width(),y=a.height(),E=C?0:d.scrollLeft(),A=C?0:d.scrollTop(),x=D-B+E;w=y-F+A;return{minX:E,minY:A,maxX:x,maxY:w}})();h.start(j)};d.bind("mousedown",function(m){var k=artDialog.focus;if(!k){return}var n=m.target,j=k.config,l=k.DOM;if(j.drag!==false&&n===l.title[0]||j.resize!==false&&n===l.se[0]){h=h||new artDialog.dragEvent();b(m);return false}})})(this.art||this.jQuery&&(this.art=jQuery));
