"use strict";(self.webpackChunkjn_website_frontend=self.webpackChunkjn_website_frontend||[]).push([[650],{9649:(_,J,a)=>{a.d(J,{F:()=>P});var m=a(4183),t=a(6682),l=a(1135),c=a(8256);let R=(()=>{class d extends m.JO{ngOnInit(){super.ngOnInit(),this.group&&(this.group.objRef=this.objRef),this.thOnInitDOM()}ngAfterViewInit(){this.thOnInitPrefab()}thOnInitDOM(){}}return d.\u0275fac=function(){let v;return function(f){return(v||(v=c.n5z(d)))(f||d)}}(),d.\u0275cmp=c.Xpm({type:d,selectors:[["ng-component"]],viewQuery:function(e,f){if(1&e&&c.Gf(m.JO,7),2&e){let p;c.iGM(p=c.CRH())&&(f.group=p.first)}},features:[c.qOj],decls:0,vars:0,template:function(e,f){},encapsulation:2}),d})(),P=(()=>{class d extends R{set grid(e){this.grid$.next({...this.grid$.value,...e})}get grid(){return this.grid$.value}set point(e){this.point$.next({...this.point$.value,...e})}get point(){return this.point$.value}set animation(e){this.animation$.next({...this.animation$.value,...e})}get animation(){return this.animation$.value}constructor(e,f){super(f),this.engine=e,this.grid$=new l.X({size:{x:10,y:10},padding:.5}),this.point$=new l.X({geometry:{radius:.02},material:{color:"#FFFFFF"}}),this.animation$=new l.X({enabled:!0,frame:0,wave:{speed:.01,height:.1},point:{inflate:.02}})}thOnInitPrefab(){const e=this.grid.padding,f=this.grid.size.x,p=this.grid.size.y,b=f*p,y=new Float32Array(3*b),w=new Float32Array(b);let x=0,u=0;for(let g=0;g<f;g++)for(let h=0;h<p;h++)y[x]=this.objRef.position.x+g*e-f*e/2,y[x+1]=this.objRef.position.y,y[x+2]=this.objRef.position.z+h*e-p*e/2,w[u]=this.point.geometry.radius,x+=3,u++;this.geometry&&(this.geometry.objRef.setAttribute("position",new t.TlE(y,3)),this.geometry.objRef.setAttribute("scale",new t.TlE(w,1))),this.engine.beforeRender$.subscribe(g=>{if(this.geometry&&this.animation.enabled)if(this.animation.onRender)this.animation.onRender(g,{grid:this.grid,point:this.point,animation:this.animation,points:this.points});else{const h=this.geometry.objRef.attributes.position.array,n=this.geometry.objRef.attributes.scale.array;let o=0,s=0;for(let i=0;i<this.grid.size.x;i++)for(let T=0;T<this.grid.size.y;T++){let E=Math.sin(.3*(i+this.animation.frame)),S=Math.sin(.5*(T+this.animation.frame));h[o+1]=E*this.animation.wave.height+S*this.animation.wave.height,h[o+1]=Math.max(0,h[o+1]),n[s]=(E<0?0:E*this.animation.point.inflate)+(S<0?0:S*this.animation.point.inflate)+this.point.geometry.radius,o+=3,s++}this.geometry.objRef.attributes.position.needsUpdate=!0,this.geometry.objRef.attributes.scale.needsUpdate=!0}this.animation.frame+=this.animation.wave.speed})}getShaderUniforms(){return{color:{value:new t.Ilk(this.point.material.color)}}}getShaderVertex(){return"\n        attribute float scale;\n\n        void main() {\n            vec4 mvPosition = modelViewMatrix * vec4( position, 1.0 );\n\n            gl_PointSize = scale * ( 300.0 / - mvPosition.z );\n            gl_Position = projectionMatrix * mvPosition;\n        }\n        "}getShaderFragment(){return"\n        uniform vec3 color;\n\n        void main() {\n\n            if ( length( gl_PointCoord - vec2( 0.5, 0.5 ) ) > 0.475 )\n                discard;\n\n            gl_FragColor = vec4( color, 1.0 );\n\n        }\n        "}}return d.\u0275fac=function(e){return new(e||d)(c.Y36(m.D9c),c.Y36(m.EZf))},d.\u0275cmp=c.Xpm({type:d,selectors:[["jn-th-prefab-wave-point"]],viewQuery:function(e,f){if(1&e&&(c.Gf(m.XHp,7),c.Gf(m.sI,7)),2&e){let p;c.iGM(p=c.CRH())&&(f.points=p.first),c.iGM(p=c.CRH())&&(f.geometry=p.first)}},inputs:{grid:"grid",point:"point",animation:"animation"},features:[c.qOj],decls:4,vars:3,consts:[[3,"vertexShader","fragmentShader","uniforms"]],template:function(e,f){1&e&&(c.TgZ(0,"th-group")(1,"th-points"),c._UZ(2,"th-bufferGeometry")(3,"th-shaderMaterial",0),c.qZA()()),2&e&&(c.xp6(3),c.Q6J("vertexShader",f.getShaderVertex())("fragmentShader",f.getShaderFragment())("uniforms",f.getShaderUniforms()))},dependencies:[m.XHp,m.JO,m.DOm,m.sI],encapsulation:2,changeDetection:0}),d})()},4650:(_,J,a)=>{a.r(J),a.d(J,{JnWebsiteThModule:()=>x});var m=a(8149),t=a(2767),l=a(8256),c=a(6895),R=a(4183);function P(u,g){1&u&&(l.ynx(0),l.Hsn(1),l.BQk())}function d(u,g){if(1&u&&(l.TgZ(0,"th-canvas",2),l._UZ(1,"router-outlet"),l.qZA()),2&u){const h=l.oxw();l.Q6J("thStats",h.debug)}}const v=["*"];let e=(()=>{class u{constructor(h){this.route=h,this.withContent=!1,this.debug=!1}ngOnInit(){this.route.queryParams.subscribe(h=>{this.debug="true"===h.debug})}}return u.\u0275fac=function(h){return new(h||u)(l.Y36(t.gz))},u.\u0275cmp=l.Xpm({type:u,selectors:[["jn-website-th"]],inputs:{withContent:"withContent",debug:"debug"},ngContentSelectors:v,decls:3,vars:2,consts:[[4,"ngIf","ngIfElse"],["routing",""],[3,"thStats"]],template:function(h,n){if(1&h&&(l.F$t(),l.YNc(0,P,2,0,"ng-container",0),l.YNc(1,d,2,1,"ng-template",null,1,l.W1O)),2&h){const o=l.MAs(2);l.Q6J("ngIf",n.withContent)("ngIfElse",o)}},dependencies:[t.lC,c.O5,R.lFy,R.I1v],encapsulation:2}),u})();var f=a(9590),p=a(4859);const b=[{path:"",component:e,children:[{path:"scenes",children:[{path:"environment",component:f.v,data:{testCase:0}},{path:"vr",component:p.M}]}]}];let y=(()=>{class u{}return u.\u0275fac=function(h){return new(h||u)},u.\u0275mod=l.oAB({type:u}),u.\u0275inj=l.cJS({imports:[t.Bz.forChild(b),t.Bz]}),u})();var w=a(2314);let x=(()=>{class u{}return u.\u0275fac=function(h){return new(h||u)},u.\u0275mod=l.oAB({type:u}),u.\u0275inj=l.cJS({imports:[y,w.l,m.y,m.y]}),u})()},9590:(_,J,a)=>{a.d(J,{v:()=>h});var m=a(9203),t=a(8256),l=a(2767),c=a(4183),R=a(6895),P=a(9649),d=a(4859);function v(n,o){1&n&&t._UZ(0,"jn-website-th-scene-vr")}const e=function(n){return[n,0,0]},f=function(){return[2e3,2e3]},p=function(){return{color:"#222222"}},b=function(){return[200,40,"#ffffff","#aaaaaa"]};function y(n,o){if(1&n&&(t.TgZ(0,"th-group")(1,"th-mesh",5),t._UZ(2,"th-planeGeometry",6)(3,"th-meshBasicMaterial",6),t.qZA(),t._UZ(4,"th-gridHelper",6),t.qZA()),2&n){const s=t.oxw();t.xp6(1),t.Q6J("rotation",t.VKq(5,e,-.5*s.Math.PI))("receiveShadow",!0),t.xp6(1),t.Q6J("args",t.DdM(7,f)),t.xp6(1),t.Q6J("args",t.DdM(8,p)),t.xp6(1),t.Q6J("args",t.DdM(9,b))}}const w=function(){return[0,0,0]},x=function(){return[5,5,5]},u=function(n){return[0,n,0]},g=function(){return[0,.2,0]};let h=(()=>{class n extends m.y{constructor(s,i){super(i),this.route=s,this.debug=!1,this.vr=!1,this.Math=Math,this.wavePoint={grid:{size:{x:100,y:100}},point:{geometry:{radius:.08}},animation:{point:{inflate:.02}}}}ngOnInit(){super.ngOnInit(),this.route.queryParams.subscribe(s=>{this.debug="true"===s.debug,this.vr="true"===s.vr})}}return n.\u0275fac=function(s){return new(s||n)(t.Y36(l.gz),t.Y36(c.EZf))},n.\u0275cmp=t.Xpm({type:n,selectors:[["jn-website-th-scene-environment"]],inputs:{debug:"debug",vr:"vr"},features:[t.qOj],decls:7,vars:15,consts:[[3,"position"],[3,"position","lookAt"],[4,"ngIf"],[3,"intensity"],[3,"grid","point","animation","position"],[3,"rotation","receiveShadow"],[3,"args"]],template:function(s,i){1&s&&(t.TgZ(0,"th-scene",0),t._UZ(1,"th-perspectiveCamera",1),t.YNc(2,v,1,0,"jn-website-th-scene-vr",2),t._UZ(3,"th-ambientLight",3),t.TgZ(4,"th-group"),t._UZ(5,"jn-th-prefab-wave-point",4),t.YNc(6,y,5,10,"th-group",2),t.qZA()()),2&s&&(t.Q6J("position",t.DdM(10,w)),t.xp6(1),t.Q6J("position",t.DdM(11,x))("lookAt",t.VKq(12,u,-1)),t.xp6(1),t.Q6J("ngIf",i.vr),t.xp6(1),t.Q6J("intensity",.3),t.xp6(2),t.Q6J("grid",i.wavePoint.grid)("point",i.wavePoint.point)("animation",i.wavePoint.animation)("position",t.DdM(14,g)),t.xp6(1),t.Q6J("ngIf",i.debug))},dependencies:[R.O5,P.F,c.RjQ,c.UG5,c.ITp,c.YRQ,c.JO,c.pN9,c.WB3,c.TfJ,d.M],encapsulation:2,changeDetection:0}),n})()},4859:(_,J,a)=>{a.d(J,{M:()=>h});var m=a(9203),t=a(5861);function l(n){return(l="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(o){return typeof o}:function(o){return o&&"function"==typeof Symbol&&o.constructor===Symbol&&o!==Symbol.prototype?"symbol":typeof o})(n)}let d=(()=>{class n{static createButton(s){const i=document.createElement("button");function E(){i.style.display="",i.style.cursor="auto",i.style.left="calc(50% - 75px)",i.style.width="150px",i.onmouseenter=null,i.onmouseleave=null,i.onclick=null}function M(r){r.style.position="absolute",r.style.bottom="20px",r.style.padding="12px 6px",r.style.border="1px solid #fff",r.style.borderRadius="4px",r.style.background="rgba(0,0,0,0.1)",r.style.color="#fff",r.style.font="normal 13px sans-serif",r.style.textAlign="center",r.style.opacity="0.5",r.style.outline="none",r.style.zIndex="999"}if("xr"in navigator)return i.id="VRButton",i.style.display="none",M(i),navigator.xr.isSessionSupported("immersive-vr").then(function(r){r?function T(){let r=null;function A(O){return C.apply(this,arguments)}function C(){return(C=(0,t.Z)(function*(O){O.addEventListener("end",W),yield s.xr.setSession(O),i.textContent="EXIT VR",r=O})).apply(this,arguments)}function W(){r.removeEventListener("end",W),i.textContent="ENTER VR",r=null}i.style.display="",i.style.cursor="pointer",i.style.left="calc(50% - 50px)",i.style.width="100px",i.textContent="ENTER VR",i.onmouseenter=function(){i.style.opacity="1.0"},i.onmouseleave=function(){i.style.opacity="0.5"},i.onclick=function(){null===r?navigator.xr.requestSession("immersive-vr",{optionalFeatures:["local-floor","bounded-floor","hand-tracking","layers"]}).then(A):r.end()}}():function S(){E(),i.textContent="VR NOT SUPPORTED"}(),r&&n.xrSessionIsGranted&&i.click()}).catch(function I(r){E(),console.warn("Exception when trying to call xr.isSessionSupported",r),i.textContent="VR NOT ALLOWED"}),i;{const r=document.createElement("a");return!1===window.isSecureContext?(r.href=document.location.href.replace(/^http:/,"https:"),r.innerHTML="WEBXR NEEDS HTTPS"):(r.href="https://immersiveweb.dev/",r.innerHTML="WEBXR NOT AVAILABLE"),r.style.left="calc(50% - 90px)",r.style.width="180px",r.style.textDecoration="none",M(r),r}}static registerSessionGrantedListener(){if("xr"in navigator){if(/WebXRViewer\//i.test(navigator.userAgent))return;navigator.xr.addEventListener("sessiongranted",()=>{n.xrSessionIsGranted=!0})}}}return function P(n,o,s){(o=function R(n){var o=function c(n,o){if("object"!==l(n)||null===n)return n;var s=n[Symbol.toPrimitive];if(void 0!==s){var i=s.call(n,o||"default");if("object"!==l(i))return i;throw new TypeError("@@toPrimitive must return a primitive value.")}return("string"===o?String:Number)(n)}(n,"string");return"symbol"===l(o)?o:String(o)}(o))in n?Object.defineProperty(n,o,{value:s,enumerable:!0,configurable:!0,writable:!0}):n[o]=s}(n,"xrSessionIsGranted",!1),n})();d.registerSessionGrantedListener();var v=a(1135),e=a(8256),f=a(2767),p=a(4183),b=a(6682),y=a(7579),w=a(6895);const x=function(){return[.1,.1,.1]};class u extends b.ZAu{constructor(){super(),this.beforeRender$=new y.x,this.onInit()}}let g=(()=>{class n extends m.y{constructor(s,i,T,E){super(E),this.changesDetector=s,this.route=i,this.engine=T,this.sampleCaseArray=[new class extends u{onInit(){let S=new b.Kj0(new b.DvJ,new b.xoR);S.material.color.setHex(2271914),S.position.set(0,.5,0),this.add(S);let I=new b.cek,M=new b.Kj0(new b.xo$(.1),new b.vBJ);I.position.set(1,2,0),I.add(M);let r=new b.ZAu;r.add(I),this.add(r),this.position.set(0,0,-2),this.beforeRender$.subscribe(A=>{r.rotation.y+=1*A.delta})}}],this.sampleCase=new v.X(null)}thOnInitDOM(){this.sampleCaseIndex?.subscribe(s=>{this.sampleCase.next(this.sampleCaseArray[s])})}thOnInitScene(){this.engine.beforeRender$.subscribe(s=>{this.sampleCase.value?.beforeRender$.next(s)})}}return n.\u0275fac=function(s){return new(s||n)(e.Y36(e.sBO),e.Y36(f.gz),e.Y36(p.D9c),e.Y36(p.EZf))},n.\u0275cmp=e.Xpm({type:n,selectors:[["jn-website-th-scene-vr-samples"]],inputs:{sampleCaseIndex:"sampleCaseIndex"},features:[e.qOj],decls:4,vars:6,consts:[[3,"objRef"],[3,"transform","scale"]],template:function(s,i){1&s&&(e.TgZ(0,"th-scene"),e._UZ(1,"th-group",0),e.ALo(2,"async"),e._UZ(3,"th-html",1),e.qZA()),2&s&&(e.xp6(1),e.Q6J("objRef",e.lcZ(2,3,i.sampleCase)),e.xp6(2),e.Q6J("transform",!0)("scale",e.DdM(5,x)))},dependencies:[p.JO,p.pN9,p.XBg,w.Ov],encapsulation:2,changeDetection:0}),n})(),h=(()=>{class n extends m.y{constructor(s,i,T){super(T),this.route=s,this.engine=i,this.sampleCaseIndex=new v.X(-1)}ngAfterViewInit(){super.ngAfterViewInit(),this.thOnInitXR()}thOnInitDOM(){this.route.data.subscribe(s=>{s.vrSampleCase&&this.sampleCaseIndex.next(s.vrSampleCase)}),this.route.queryParams.subscribe(s=>{s.vrSampleCase&&this.sampleCaseIndex.next(s.vrSampleCase)})}thOnInitXR(){document.body.append(d.createButton(this.engine.renderer)),this.engine.renderer.xr.enabled=!0,this.engine.beforeRender$.subscribe(s=>{}),this.engine.renderer.setAnimationLoop(()=>{this.engine.render()})}}return n.\u0275fac=function(s){return new(s||n)(e.Y36(f.gz),e.Y36(p.D9c),e.Y36(p.EZf))},n.\u0275cmp=e.Xpm({type:n,selectors:[["jn-website-th-scene-vr"]],features:[e.qOj],decls:2,vars:1,consts:[[3,"sampleCaseIndex"]],template:function(s,i){1&s&&(e.TgZ(0,"th-scene"),e._UZ(1,"jn-website-th-scene-vr-samples",0),e.qZA()),2&s&&(e.xp6(1),e.Q6J("sampleCaseIndex",i.sampleCaseIndex))},dependencies:[p.pN9,g],encapsulation:2,changeDetection:0}),n})()}}]);