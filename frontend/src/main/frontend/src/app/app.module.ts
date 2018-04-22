import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import { AddCinetarComponent } from './components/add-cinetar/add-cinetar.component';
import {ReactiveFormsModule} from "@angular/forms";
import {AddCinetarServiceService} from "./services/cineter/add-cinetar-service.service";
import { AddShowComponent } from './components/add-show/add-show.component';
import {ShowService} from "./services/show/show.service";


@NgModule({
  declarations: [
    AppComponent,
    AddCinetarComponent,
    AddShowComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {
        path:'add_cinetar',
        component: AddCinetarComponent
      },{
        path:'add_show',
        component: AddShowComponent
        }],
      )
  ],
  providers: [HttpClientModule, AddCinetarServiceService, ShowService],
  bootstrap: [AppComponent]
})
export class AppModule { }
