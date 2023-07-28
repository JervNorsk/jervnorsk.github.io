"use strict";(self.webpackChunkapp=self.webpackChunkapp||[]).push([[657],{657:(b,h,l)=>{l.r(h),l.d(h,{PcgSeApiModule:()=>W,PcgSeModule:()=>P,PcgSePokemonComponent:()=>I,PcgSeSoundAlert:()=>C,PcgSeSoundAlertComponent:()=>v,PcgSeSoundAlertGeneration:()=>s,PcgSeSoundAlertType:()=>d,PcgSeTimerComponent:()=>y});var i=l(946),f=l(814),w=l(592),T=l(829);const{isArray:M}=Array;var A=l(232),k=l(251),V=l(564);function g(...e){const r=(0,V.jO)(e),t=function S(e){return 1===e.length&&M(e[0])?e[0]:e}(e);return t.length?new w.y(n=>{let o=t.map(()=>[]),u=t.map(()=>!1);n.add(()=>{o=u=null});for(let a=0;!n.closed&&a<t.length;a++)(0,T.Xf)(t[a]).subscribe((0,k.x)(n,F=>{if(o[a].push(F),o.every(p=>p.length)){const p=o.map(m=>m.shift());n.next(r?r(...p):p),o.some((m,U)=>!m.length&&u[U])&&n.complete()}},()=>{u[a]=!0,!o[a].length&&n.complete()}));return()=>{o=u=null}}):A.E}var c=l(225);let y=(()=>{class e{ngOnInit(){window.location.replace("https://poketwitch.bframework.de/info/events/spawn_cooldown/")}}return e.\u0275fac=function(t){return new(t||e)},e.\u0275cmp=i.Xpm({type:e,selectors:[["ng-component"]],decls:0,vars:0,template:function(t,n){},encapsulation:2}),e})(),I=(()=>{class e{constructor(t){this.route=t}ngOnInit(){g([this.route.queryParamMap,this.route.data]).subscribe(([t,n])=>{const o=new URLSearchParams;t.keys.forEach(u=>{o.append(u,t.get(u))}),"show"===n.event&&"current"===n.target&&window.location.replace(`https://poketwitch.bframework.de/info/events/show_current_pokemon/?${o.toString()}`)})}}return e.\u0275fac=function(t){return new(t||e)(i.Y36(c.gz))},e.\u0275cmp=i.Xpm({type:e,selectors:[["ng-component"]],decls:0,vars:0,template:function(t,n){},encapsulation:2}),e})();var s=function(e){return e[e.I=1]="I",e[e.II=2]="II",e[e.III=3]="III",e[e.IV=4]="IV",e[e.V=5]="V",e[e.VI=6]="VI",e[e.VII=7]="VII",e[e.VIII=8]="VIII",e[e.IX=9]="IX",e}(s||{}),d=function(e){return e.BattleWild="wild_battle",e}(d||{});class C{constructor(r,t,n,o){this.generation=r,this.type=t,this.trackId=n,this.trackDuration=o}}let v=(()=>{class e{get soundCloudPlayerIFrame(){return document.querySelector("iframe")}get soundCloudPlayer(){return this.soundCloudPlayerWidget||(this.soundCloudPlayerWidget=window.SC.Widget(this.soundCloudPlayerIFrame)),this.soundCloudPlayerWidget}constructor(t){this.route=t,this.debug=!1,this.pcgBaseUrl="https://poketwitch.bframework.de",this.soundCloudPlayerBaseUrl="https://w.soundcloud.com/player",this.soundCloudApiBaseUrl="https://api.soundcloud.com"}ngOnInit(){this.loadSoundCloudPlayer()}getEnumByValue(t,n){return Object.keys(t).filter(o=>"number"==typeof t[o]&&t[o]===n).map(o=>t[o])[0]}ngAfterContentInit(){g([this.route.data,this.route.queryParams]).subscribe(([t,n])=>{const o={...t,...n};this.debug="true"===o.debug,this.debug&&console.log(o),"true"!==o.player&&(this.soundCloudPlayerIFrame.style.display="none"),this.soundAlert=this.getSoundAlert(this.getSoundAlertGeneration(o.gen),this.getSoundAlertType(o.type),o.duration),this.debug&&console.log(this.soundAlert),setTimeout(()=>{this.soundCloudPlayer.load(this.getSoundCloudPlayerTrackUrl(this.soundAlert.trackId)),this.simulateTimerMainLoop()},1e3)})}getSoundAlert(t,n,o){const u=this.getSoundCloudPlayerTrack(t,n,o);return new C(t,n,u.id,u.duration)}getSoundAlertGeneration(t){switch(t.toLowerCase()){default:case"i":return s.I;case"ii":return s.II;case"iii":return s.III;case"iv":return s.IV;case"v":return s.V;case"Vi":return s.VI;case"vii":return s.VII;case"viii":return s.VIII;case"ix":return s.IX}}getSoundAlertType(t){return t.toLowerCase(),d.BattleWild}getSoundCloudPlayerTrack(t,n,o){if(t===s.I){if(n===d.BattleWild)return{id:"1235778460",duration:o||4400}}else if(t===s.IV){if(n===d.BattleWild)return{id:"631141938",duration:o||4800}}else if(t===s.V){if(n===d.BattleWild)return{id:"1235709346",duration:o||5100}}else if(t===s.IX&&n===d.BattleWild)return{id:"1380417496",duration:o||3500};return{id:"1235778460",duration:o||4400}}getSoundCloudPlayerTrackUrl(t){return`${this.soundCloudApiBaseUrl}/tracks/${this.soundAlert.trackId}`}loadSoundCloudPlayer(){const t=document.createElement("script");t.src=`${this.soundCloudPlayerBaseUrl}/api.js`,document.body.appendChild(t)}simulateTimerSoundAlert(){this.debug&&console.log("play"),setTimeout(()=>{this.soundCloudPlayer.pause()},this.soundAlert.trackDuration),this.soundCloudPlayer.seekTo(0),this.soundCloudPlayer.play()}simulateTimerMainLoop(){this.debug?this.simulateTimerCooldownWait(10):fetch(`${this.pcgBaseUrl}/info/events/last_spawn/`).then(t=>t.json()).then(t=>{this.simulateTimerCooldownWait(t.next_spawn)})}simulateTimerCooldownWait(t){t-=1,this.debug&&console.log("[simulateTimer]",t),"0"==t.toString()&&this.simulateTimerSoundAlert(),t>0?setTimeout(()=>{this.simulateTimerCooldownWait(t)},1e3):setTimeout(()=>{this.simulateTimerMainLoop()},2e3)}}return e.\u0275fac=function(t){return new(t||e)(i.Y36(c.gz))},e.\u0275cmp=i.Xpm({type:e,selectors:[["ng-component"]],decls:1,vars:0,consts:[["width","100%","height","100","src","https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1328551417&auto_play=true"]],template:function(t,n){1&t&&i._UZ(0,"iframe",0)},encapsulation:2}),e})(),P=(()=>{class e{}return e.\u0275fac=function(t){return new(t||e)},e.\u0275mod=i.oAB({type:e}),e.\u0275inj=i.cJS({imports:[f.ez]}),e})();const B=[{path:"info",children:[{path:"events",children:[{path:"show_current_pokemon",component:I,data:{event:"show",target:"current"}},{path:"spawn_cooldown",component:y},{path:"spawn_alert",component:v,data:{gen:"i",type:"wild_battle"}}]}]}];let D=(()=>{class e{}return e.\u0275fac=function(t){return new(t||e)},e.\u0275mod=i.oAB({type:e}),e.\u0275inj=i.cJS({imports:[c.Bz.forChild(B),c.Bz]}),e})(),W=(()=>{class e{}return e.\u0275fac=function(t){return new(t||e)},e.\u0275mod=i.oAB({type:e}),e.\u0275inj=i.cJS({imports:[P,D,f.ez]}),e})()}}]);