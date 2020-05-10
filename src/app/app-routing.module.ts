import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ListComponent } from './list/list.component';
import { FivePHighComponentComponent } from './five-phigh-component/five-phigh-component.component';
import { FivePLowComponentComponent } from './five-plow-component/five-plow-component.component';
import { RefreshComponent } from './refresh/refresh.component';
import { FivetyTwoWkHighComponentComponent } from './fivety-two-wk-high-component/fivety-two-wk-high-component.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: '52wklow', component: ListComponent}, 
  {path: '52wkhigh', component: FivetyTwoWkHighComponentComponent}, 
  {path: '5plow', component: FivePLowComponentComponent}, 
  {path: '5phigh', component: FivePHighComponentComponent}, 
  {path: 'addorrefresh', component: RefreshComponent}, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
