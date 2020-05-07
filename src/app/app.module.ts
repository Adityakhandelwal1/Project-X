import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ListComponent } from './list/list.component';
import { HttpClientModule } from '@angular/common/http';
import { FivetyTwoWkHighComponentComponent } from './fivety-two-wk-high-component/fivety-two-wk-high-component.component';
import { FivePLowComponentComponent } from './five-plow-component/five-plow-component.component';
import { FivePHighComponentComponent } from './five-phigh-component/five-phigh-component.component';
import { RefreshComponent } from './refresh/refresh.component';
import { AgGridModule } from 'ag-grid-angular';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ListComponent,
    FivetyTwoWkHighComponentComponent,
    FivePLowComponentComponent,
    FivePHighComponentComponent,
    RefreshComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AgGridModule.withComponents([])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
