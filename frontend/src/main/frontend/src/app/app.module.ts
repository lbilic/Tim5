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
import { AddCineterAdminComponent } from './components/add-cineter-admin/add-cineter-admin.component';
import {AdminService} from "./services/admin/admin.service";


@NgModule({
  declarations: [
    AppComponent,
    AddCinetarComponent,
    AddShowComponent,
    AddCineterAdminComponent,
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
        },
        {
          path: 'add_cineter_admin',
          component: AddCineterAdminComponent
        }],
      )
  ],
  providers: [HttpClientModule, AddCinetarServiceService, ShowService, AdminService],
  bootstrap: [AppComponent]
})
export class AppModule { }
