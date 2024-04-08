import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {JnWebsiteHome} from "./features/home/jn-website-home.component";

const routes: Routes = [
  {
    path: '**',
    component: JnWebsiteHome
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: false})],
  exports: [RouterModule]
})
export class JnWebsiteAppRoutingModule {
}
