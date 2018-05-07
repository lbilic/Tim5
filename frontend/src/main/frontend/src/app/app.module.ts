import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from "@angular/common";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
//import { SharedModule } from "./shared/shared.module";
import { AnonymusGuard } from "./core/guards/anonymus.guard";
import { CoreModule } from "./core/core.module";
import {ToasterModule} from "angular5-toaster/dist";
// error handler
import { AppErrorHandler } from "./core/error-handlers/app-error-handler";
// interceptor
import { JwtInterceptor } from "./core/interceptors/jwt-interceptor";

import {HttpClient, HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";
import {CineterService} from "./services/cineter/cineter.service";
import {ShowService} from "./services/show/show.service";
import {AdminService} from "./services/admin/admin.service";


//Components
import { AppComponent } from './app.component';
import { AddCinetarComponent } from './components/add-cinetar/add-cinetar.component';
import { AddShowComponent } from './components/add-show/add-show.component';
import { AddCineterAdminComponent } from './components/add-cineter-admin/add-cineter-admin.component';
import { LoginComponent } from './components/login/login.component';
import { JwtService } from './services/jwt.service';


@NgModule({
  exports: [ RouterModule ],
  declarations: [
    AppComponent,
    AddCinetarComponent,
    AddShowComponent,
    AddCineterAdminComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    ToasterModule,
    RouterModule.forRoot([
      {
        path:'add_cinetar',
        component: AddCinetarComponent
      },
      {
        path:'add_show',
        component: AddShowComponent
      },
      {
        path: 'add_cineter_admin',
        component: AddCineterAdminComponent
      },
      {
        path: 'login',
        component: LoginComponent
      }],
      )
  ],
  providers: [
    {
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
    HttpClientModule, CineterService, ShowService, AdminService, JwtService],
  bootstrap: [AppComponent]
})

export class AppModule { }
