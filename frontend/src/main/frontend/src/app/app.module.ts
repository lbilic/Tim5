import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from "@angular/common";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
//import { SharedModule } from "./shared/shared.module";
import { CoreModule } from "./core/core.module";
// error handler
import { AppErrorHandler } from "./core/error-handlers/app-error-handler";
// interceptor
import { JwtInterceptor } from "./core/interceptors/jwt-interceptor";

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
import { LoginComponent } from './components/login/login.component';
import { AddPropsComponent } from './components/add-props/add-props.component';
import {PropsService} from "./services/props/props.service";


@NgModule({
  declarations: [
    AppComponent,
    AddCinetarComponent,
    AddShowComponent,
    AddCineterAdminComponent,
    AddPropsComponent,
    LoginComponent
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
        },
        {
          path: 'login;',
          component: LoginComponent
        },
        {
          path: 'add_props',
          component: AddPropsComponent
        }],
      )
  ],
  providers: [{
    provide: ErrorHandler,
    useClass: AppErrorHandler
  },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy
    },
    HttpClientModule, AddCinetarServiceService, ShowService, AdminService, PropsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
