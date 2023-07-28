"use strict";(self.webpackChunkjn_website_frontend=self.webpackChunkjn_website_frontend||[]).push([[650],{4055:(C,_,o)=>{o.d(_,{c:()=>c});var d=o(5429),e=o(8256);let c=(()=>{class f extends d.JO{ngOnInit(){super.ngOnInit(),this.group&&(this.group.objRef=this.objRef),this.thOnInitDOM()}ngAfterViewInit(){this.thOnInitPrefab()}thOnInitDOM(){}}return f.\u0275fac=function(){let u;return function(v){return(u||(u=e.n5z(f)))(v||f)}}(),f.\u0275cmp=e.Xpm({type:f,selectors:[["ng-component"]],viewQuery:function(x,v){if(1&x&&e.Gf(d.JO,7),2&x){let y;e.iGM(y=e.CRH())&&(v.group=y.first)}},features:[e.qOj],decls:0,vars:0,template:function(x,v){},encapsulation:2}),f})()},2663:(C,_,o)=>{o.d(_,{F:()=>x});var d=o(5429),e=o(6682),c=o(1135),f=o(4055),u=o(8256);let x=(()=>{class v extends f.c{set grid(t){this.grid$.next({...this.grid$.value,...t})}get grid(){return this.grid$.value}set point(t){this.point$.next({...this.point$.value,...t})}get point(){return this.point$.value}set animation(t){this.animation$.next({...this.animation$.value,...t})}get animation(){return this.animation$.value}constructor(t,m){super(m),this.engine=t,this.grid$=new c.X({size:{x:10,y:10},padding:.5}),this.point$=new c.X({geometry:{radius:.02},material:{color:"#FFFFFF"}}),this.animation$=new c.X({enabled:!0,frame:0,wave:{speed:.01,height:.1},point:{inflate:.02}})}thOnInitPrefab(){const t=this.grid.padding,m=this.grid.size.x,p=this.grid.size.y,b=m*p,T=new Float32Array(3*b),R=new Float32Array(b);let E=0,l=0;for(let g=0;g<m;g++)for(let h=0;h<p;h++)T[E]=this.objRef.position.x+g*t-m*t/2,T[E+1]=this.objRef.position.y,T[E+2]=this.objRef.position.z+h*t-p*t/2,R[l]=this.point.geometry.radius,E+=3,l++;this.geometry&&(this.geometry.objRef.setAttribute("position",new e.TlE(T,3)),this.geometry.objRef.setAttribute("scale",new e.TlE(R,1))),this.engine.beforeRender$.subscribe(g=>{if(this.geometry&&this.animation.enabled)if(this.animation.onRender)this.animation.onRender(g,{grid:this.grid,point:this.point,animation:this.animation,points:this.points});else{const h=this.geometry.objRef.attributes.position.array,n=this.geometry.objRef.attributes.scale.array;let r=0,s=0;for(let i=0;i<this.grid.size.x;i++)for(let S=0;S<this.grid.size.y;S++){let O=Math.sin(.3*(i+this.animation.frame)),P=Math.sin(.5*(S+this.animation.frame));h[r+1]=O*this.animation.wave.height+P*this.animation.wave.height,h[r+1]=Math.max(0,h[r+1]),n[s]=(O<0?0:O*this.animation.point.inflate)+(P<0?0:P*this.animation.point.inflate)+this.point.geometry.radius,r+=3,s++}this.geometry.objRef.attributes.position.needsUpdate=!0,this.geometry.objRef.attributes.scale.needsUpdate=!0}this.animation.frame+=this.animation.wave.speed})}getShaderUniforms(){return{color:{value:new e.Ilk(this.point.material.color)}}}getShaderVertex(){return"\n        attribute float scale;\n\n        void main() {\n            vec4 mvPosition = modelViewMatrix * vec4( position, 1.0 );\n\n            gl_PointSize = scale * ( 300.0 / - mvPosition.z );\n            gl_Position = projectionMatrix * mvPosition;\n        }\n        "}getShaderFragment(){return"\n        uniform vec3 color;\n\n        void main() {\n\n            if ( length( gl_PointCoord - vec2( 0.5, 0.5 ) ) > 0.475 )\n                discard;\n\n            gl_FragColor = vec4( color, 1.0 );\n\n        }\n        "}}return v.\u0275fac=function(t){return new(t||v)(u.Y36(d.D9c),u.Y36(d.EZf))},v.\u0275cmp=u.Xpm({type:v,selectors:[["jn-th-prefab-wave-point"]],viewQuery:function(t,m){if(1&t&&(u.Gf(d.XHp,7),u.Gf(d.sI,7)),2&t){let p;u.iGM(p=u.CRH())&&(m.points=p.first),u.iGM(p=u.CRH())&&(m.geometry=p.first)}},inputs:{grid:"grid",point:"point",animation:"animation"},features:[u.qOj],decls:4,vars:3,consts:[[3,"vertexShader","fragmentShader","uniforms"]],template:function(t,m){1&t&&(u.TgZ(0,"th-group")(1,"th-points"),u._UZ(2,"th-bufferGeometry")(3,"th-shaderMaterial",0),u.qZA()()),2&t&&(u.xp6(3),u.Q6J("vertexShader",m.getShaderVertex())("fragmentShader",m.getShaderFragment())("uniforms",m.getShaderUniforms()))},dependencies:[d.XHp,d.JO,d.DOm,d.sI],encapsulation:2,changeDetection:0}),v})()},4650:(C,_,o)=>{o.r(_),o.d(_,{JnWebsiteThModule:()=>E});var d=o(8149),e=o(2767),c=o(8256),f=o(6895),u=o(5429);function x(l,g){1&l&&(c.ynx(0),c.Hsn(1),c.BQk())}function v(l,g){if(1&l&&(c.TgZ(0,"th-canvas",2),c._UZ(1,"router-outlet"),c.qZA()),2&l){const h=c.oxw();c.Q6J("thStats",h.debug)}}const y=["*"];let t=(()=>{class l{constructor(h){this.route=h,this.withContent=!1,this.debug=!1}ngOnInit(){this.route.queryParams.subscribe(h=>{this.debug="true"===h.debug})}}return l.\u0275fac=function(h){return new(h||l)(c.Y36(e.gz))},l.\u0275cmp=c.Xpm({type:l,selectors:[["jn-website-th"]],inputs:{withContent:"withContent",debug:"debug"},ngContentSelectors:y,decls:3,vars:2,consts:[[4,"ngIf","ngIfElse"],["routing",""],[3,"thStats"]],template:function(h,n){if(1&h&&(c.F$t(),c.YNc(0,x,2,0,"ng-container",0),c.YNc(1,v,2,1,"ng-template",null,1,c.W1O)),2&h){const r=c.MAs(2);c.Q6J("ngIf",n.withContent)("ngIfElse",r)}},dependencies:[e.lC,f.O5,u.lFy,u.I1v],encapsulation:2}),l})();var m=o(9590),p=o(4859);const b=[{path:"",component:t,children:[{path:"scenes",children:[{path:"environment",component:m.v,data:{testCase:0}},{path:"vr",component:p.M}]}]}];let T=(()=>{class l{}return l.\u0275fac=function(h){return new(h||l)},l.\u0275mod=c.oAB({type:l}),l.\u0275inj=c.cJS({imports:[e.Bz.forChild(b),e.Bz]}),l})();var R=o(2314);let E=(()=>{class l{}return l.\u0275fac=function(h){return new(h||l)},l.\u0275mod=c.oAB({type:l}),l.\u0275inj=c.cJS({imports:[T,R.l,d.y,d.y]}),l})()},9590:(C,_,o)=>{o.d(_,{v:()=>h});var d=o(9203),e=o(8256),c=o(2767),f=o(5429),u=o(6895),x=o(2663),v=o(4859);function y(n,r){1&n&&e._UZ(0,"jn-website-th-scene-vr")}const t=function(n){return[n,0,0]},m=function(){return[2e3,2e3]},p=function(){return{color:"#222222"}},b=function(){return[200,40,"#ffffff","#aaaaaa"]};function T(n,r){if(1&n&&(e.TgZ(0,"th-group")(1,"th-mesh",5),e._UZ(2,"th-planeGeometry",6)(3,"th-meshBasicMaterial",6),e.qZA(),e._UZ(4,"th-gridHelper",6),e.qZA()),2&n){const s=e.oxw();e.xp6(1),e.Q6J("rotation",e.VKq(5,t,-.5*s.Math.PI))("receiveShadow",!0),e.xp6(1),e.Q6J("args",e.DdM(7,m)),e.xp6(1),e.Q6J("args",e.DdM(8,p)),e.xp6(1),e.Q6J("args",e.DdM(9,b))}}const R=function(){return[0,0,0]},E=function(){return[5,5,5]},l=function(n){return[0,n,0]},g=function(){return[0,.2,0]};let h=(()=>{class n extends d.y{constructor(s,i){super(i),this.route=s,this.debug=!1,this.vr=!1,this.Math=Math,this.wavePoint={grid:{size:{x:100,y:100}},point:{geometry:{radius:.08}},animation:{point:{inflate:.02}}}}ngOnInit(){super.ngOnInit(),this.route.queryParams.subscribe(s=>{this.debug="true"===s.debug,this.vr="true"===s.vr})}}return n.\u0275fac=function(s){return new(s||n)(e.Y36(c.gz),e.Y36(f.EZf))},n.\u0275cmp=e.Xpm({type:n,selectors:[["jn-website-th-scene-environment"]],inputs:{debug:"debug",vr:"vr"},features:[e.qOj],decls:7,vars:15,consts:[[3,"position"],[3,"position","lookAt"],[4,"ngIf"],[3,"intensity"],[3,"grid","point","animation","position"],[3,"rotation","receiveShadow"],[3,"args"]],template:function(s,i){1&s&&(e.TgZ(0,"th-scene",0),e._UZ(1,"th-perspectiveCamera",1),e.YNc(2,y,1,0,"jn-website-th-scene-vr",2),e._UZ(3,"th-ambientLight",3),e.TgZ(4,"th-group"),e._UZ(5,"jn-th-prefab-wave-point",4),e.YNc(6,T,5,10,"th-group",2),e.qZA()()),2&s&&(e.Q6J("position",e.DdM(10,R)),e.xp6(1),e.Q6J("position",e.DdM(11,E))("lookAt",e.VKq(12,l,-1)),e.xp6(1),e.Q6J("ngIf",i.vr),e.xp6(1),e.Q6J("intensity",.3),e.xp6(2),e.Q6J("grid",i.wavePoint.grid)("point",i.wavePoint.point)("animation",i.wavePoint.animation)("position",e.DdM(14,g)),e.xp6(1),e.Q6J("ngIf",i.debug))},dependencies:[u.O5,x.F,f.RjQ,f.UG5,f.ITp,f.YRQ,f.JO,f.pN9,f.WB3,f.TfJ,v.M],encapsulation:2,changeDetection:0}),n})()},4859:(C,_,o)=>{o.d(_,{M:()=>h});var d=o(9203),e=o(5861);function c(n){return(c="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(r){return typeof r}:function(r){return r&&"function"==typeof Symbol&&r.constructor===Symbol&&r!==Symbol.prototype?"symbol":typeof r})(n)}let v=(()=>{class n{static createButton(s){const i=document.createElement("button");function O(){i.style.display="",i.style.cursor="auto",i.style.left="calc(50% - 75px)",i.style.width="150px",i.onmouseenter=null,i.onmouseleave=null,i.onclick=null}function w(a){a.style.position="absolute",a.style.bottom="20px",a.style.padding="12px 6px",a.style.border="1px solid #fff",a.style.borderRadius="4px",a.style.background="rgba(0,0,0,0.1)",a.style.color="#fff",a.style.font="normal 13px sans-serif",a.style.textAlign="center",a.style.opacity="0.5",a.style.outline="none",a.style.zIndex="999"}if("xr"in navigator)return i.id="VRButton",i.style.display="none",w(i),navigator.xr.isSessionSupported("immersive-vr").then(function(a){a?function S(){let a=null;function A(M){return I.apply(this,arguments)}function I(){return(I=(0,e.Z)(function*(M){M.addEventListener("end",W),yield s.xr.setSession(M),i.textContent="EXIT VR",a=M})).apply(this,arguments)}function W(){a.removeEventListener("end",W),i.textContent="ENTER VR",a=null}i.style.display="",i.style.cursor="pointer",i.style.left="calc(50% - 50px)",i.style.width="100px",i.textContent="ENTER VR",i.onmouseenter=function(){i.style.opacity="1.0"},i.onmouseleave=function(){i.style.opacity="0.5"},i.onclick=function(){null===a?navigator.xr.requestSession("immersive-vr",{optionalFeatures:["local-floor","bounded-floor","hand-tracking","layers"]}).then(A):a.end()}}():function P(){O(),i.textContent="VR NOT SUPPORTED"}(),a&&n.xrSessionIsGranted&&i.click()}).catch(function J(a){O(),console.warn("Exception when trying to call xr.isSessionSupported",a),i.textContent="VR NOT ALLOWED"}),i;{const a=document.createElement("a");return!1===window.isSecureContext?(a.href=document.location.href.replace(/^http:/,"https:"),a.innerHTML="WEBXR NEEDS HTTPS"):(a.href="https://immersiveweb.dev/",a.innerHTML="WEBXR NOT AVAILABLE"),a.style.left="calc(50% - 90px)",a.style.width="180px",a.style.textDecoration="none",w(a),a}}static registerSessionGrantedListener(){if("xr"in navigator){if(/WebXRViewer\//i.test(navigator.userAgent))return;navigator.xr.addEventListener("sessiongranted",()=>{n.xrSessionIsGranted=!0})}}}return function x(n,r,s){(r=function u(n){var r=function f(n,r){if("object"!==c(n)||null===n)return n;var s=n[Symbol.toPrimitive];if(void 0!==s){var i=s.call(n,r||"default");if("object"!==c(i))return i;throw new TypeError("@@toPrimitive must return a primitive value.")}return("string"===r?String:Number)(n)}(n,"string");return"symbol"===c(r)?r:String(r)}(r))in n?Object.defineProperty(n,r,{value:s,enumerable:!0,configurable:!0,writable:!0}):n[r]=s}(n,"xrSessionIsGranted",!1),n})();v.registerSessionGrantedListener();var y=o(1135),t=o(8256),m=o(2767),p=o(5429),b=o(6682),T=o(7579),R=o(6895);const E=function(){return[.1,.1,.1]};class l extends b.ZAu{constructor(){super(),this.beforeRender$=new T.x,this.onInit()}}let g=(()=>{class n extends d.y{constructor(s,i,S,O){super(O),this.changesDetector=s,this.route=i,this.engine=S,this.sampleCaseArray=[new class extends l{onInit(){let P=new b.Kj0(new b.DvJ,new b.xoR);P.material.color.setHex(2271914),P.position.set(0,.5,0),this.add(P);let J=new b.cek,w=new b.Kj0(new b.xo$(.1),new b.vBJ);J.position.set(1,2,0),J.add(w);let a=new b.ZAu;a.add(J),this.add(a),this.position.set(0,0,-2),this.beforeRender$.subscribe(A=>{a.rotation.y+=1*A.delta})}}],this.sampleCase=new y.X(null)}thOnInitDOM(){this.sampleCaseIndex?.subscribe(s=>{this.sampleCase.next(this.sampleCaseArray[s])})}thOnInitScene(){this.engine.beforeRender$.subscribe(s=>{this.sampleCase.value?.beforeRender$.next(s)})}}return n.\u0275fac=function(s){return new(s||n)(t.Y36(t.sBO),t.Y36(m.gz),t.Y36(p.D9c),t.Y36(p.EZf))},n.\u0275cmp=t.Xpm({type:n,selectors:[["jn-website-th-scene-vr-samples"]],inputs:{sampleCaseIndex:"sampleCaseIndex"},features:[t.qOj],decls:4,vars:6,consts:[[3,"objRef"],[3,"transform","scale"]],template:function(s,i){1&s&&(t.TgZ(0,"th-scene"),t._UZ(1,"th-group",0),t.ALo(2,"async"),t._UZ(3,"th-html",1),t.qZA()),2&s&&(t.xp6(1),t.Q6J("objRef",t.lcZ(2,3,i.sampleCase)),t.xp6(2),t.Q6J("transform",!0)("scale",t.DdM(5,E)))},dependencies:[p.JO,p.pN9,p.XBg,R.Ov],encapsulation:2,changeDetection:0}),n})(),h=(()=>{class n extends d.y{constructor(s,i,S){super(S),this.route=s,this.engine=i,this.sampleCaseIndex=new y.X(-1)}ngAfterViewInit(){super.ngAfterViewInit(),this.thOnInitXR()}thOnInitDOM(){this.route.data.subscribe(s=>{s.vrSampleCase&&this.sampleCaseIndex.next(s.vrSampleCase)}),this.route.queryParams.subscribe(s=>{s.vrSampleCase&&this.sampleCaseIndex.next(s.vrSampleCase)})}thOnInitXR(){document.body.append(v.createButton(this.engine.renderer)),this.engine.renderer.xr.enabled=!0,this.engine.beforeRender$.subscribe(s=>{}),this.engine.renderer.setAnimationLoop(()=>{this.engine.render()})}}return n.\u0275fac=function(s){return new(s||n)(t.Y36(m.gz),t.Y36(p.D9c),t.Y36(p.EZf))},n.\u0275cmp=t.Xpm({type:n,selectors:[["jn-website-th-scene-vr"]],features:[t.qOj],decls:2,vars:1,consts:[[3,"sampleCaseIndex"]],template:function(s,i){1&s&&(t.TgZ(0,"th-scene"),t._UZ(1,"jn-website-th-scene-vr-samples",0),t.qZA()),2&s&&(t.xp6(1),t.Q6J("sampleCaseIndex",i.sampleCaseIndex))},dependencies:[p.pN9,g],encapsulation:2,changeDetection:0}),n})()}}]);