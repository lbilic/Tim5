import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import { AddCinetarComponent } from './components/add-cinetar/add-cinetar.component';
import {ReactiveFormsModule} from "@angular/forms";
import {AddCinetarServiceService} from "./services/add-cinetar-service.service";


@NgModule({
  declarations: [
    AppComponent,
    AddCinetarComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {
        path:'add_cinetar',
        component: AddCinetarComponent
      }])
  ],
  providers: [HttpClientModule, AddCinetarServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
