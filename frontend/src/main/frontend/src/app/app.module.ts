import {BrowserModule} from '@angular/platform-browser';
import {ErrorHandler, NgModule} from '@angular/core';
import {HashLocationStrategy, LocationStrategy} from "@angular/common";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {HttpModule} from '@angular/http';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {SharedModule} from "./shared/shared.module";
import {AnonymusGuard} from "./core/guards/anonymus.guard";
import {CoreModule} from "./core/core.module";
import {ToasterModule} from "angular5-toaster/dist";
// error handler
import {AppErrorHandler} from "./core/error-handlers/app-error-handler";
// interceptor
import {JwtInterceptor} from "./core/interceptors/jwt-interceptor";

import {HttpClient, HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";
import { FormsModule } from '@angular/forms';
import {CineterService} from "./services/cineter/cineter.service";
import {ShowService} from "./services/show/show.service";
import {AdminService} from "./services/admin/admin.service";
import {AddCinetarServiceService} from "./services/cineter/add-cinetar-service.service"
import {AccountService} from "./services/account/account.service";

//Services
import {AuthService} from "./services/auth.service"
import {UsernameUniqueValidator} from "./shared/validators/username-unique.validator"

//Components
import {AppComponent} from './app.component';
import {AddCinetarComponent} from './components/add-cinetar/add-cinetar.component';
import {AddShowComponent} from './components/add-show/add-show.component';
import {AddCineterAdminComponent} from './components/add-cineter-admin/add-cineter-admin.component';
import {LoginComponent} from './components/login/login.component';
import {JwtService} from './services/jwt.service';
import {HomePageComponent} from './components/home-page/home-page.component';
import {NavbarComponent} from './components/navbar/navbar.component';
import {RegistrationComponent} from './components/registration/registration.component';

import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {TooltipModule} from 'ngx-bootstrap/tooltip';
import {ModalModule} from 'ngx-bootstrap/modal';
import {PropsComponent} from './components/props/props.component';
import {PropsService} from "./services/props/props.service";
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import {AddPropsComponent} from "./components/add-props/add-props.component";
import { ProfileComponent } from './components/profile/profile.component';
import { AddPerformanceComponent } from './components/add-performance/add-performance.component';
import {AddPerformanceService} from "./services/performance/add-performance.service";
import { ProfilComponent } from './components/profil/profil.component';
import { ShowCinetersComponent } from './components/show-cineters/show-cineters.component';
import { ListShowsComponent } from './components/list-shows/list-shows.component';
import { TabsModule } from 'ngx-bootstrap';
import { PropsDetailComponent } from './components/props-detail/props-detail.component';
import { CineterDetailsComponent } from './components/cineter-details/cineter-details.component';
import { FriendsService } from "./services/friends/friends.service";
import { UserViewPropsComponent } from './components/user-view-props/user-view-props.component';
import { SellPropsComponent } from './components/sell-props/sell-props.component';
import { RequestsComponent } from './components/requests/requests.component'

@NgModule({
  exports: [RouterModule],
  declarations: [
    AppComponent,
    AddCinetarComponent,
    AddShowComponent,
    AddCineterAdminComponent,
    LoginComponent,
    NavbarComponent,
    HomePageComponent,
    RegistrationComponent,
    PropsComponent,
    ChangePasswordComponent,
    AddPropsComponent,
    ProfileComponent,
    AddPerformanceComponent,
    ProfilComponent,
    ShowCinetersComponent,
    ListShowsComponent,
    PropsDetailComponent,
    CineterDetailsComponent,
    UserViewPropsComponent,
    SellPropsComponent,
    RequestsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    HttpModule,
    ToasterModule,
    SharedModule,
    TabsModule.forRoot(),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    CoreModule,
    RouterModule.forRoot([
      {
        path: 'add_cinetar',
        component: AddCinetarComponent
      },
      {
        path: 'add_show',
        component: AddShowComponent
      },
      {
        path: 'add_cineter_admin',
        component: AddCineterAdminComponent
      },
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'home',
        component: HomePageComponent
      },
      {
        path: 'register',
        component: RegistrationComponent
      },
      {
        path: 'props',
        component: PropsComponent
      },
      {
        path: 'props/:id',
        component: PropsDetailComponent
      },
      {
        path:'cineters',
        component:ShowCinetersComponent
      },

      {
        path:'shows',
        component:ListShowsComponent
      },
      {
        path: 'change_password',
        component: ChangePasswordComponent
      },
      {
        path:'add_props',
        component: AddPropsComponent
      },
      {
        path:'add_performance',
        component:AddPerformanceComponent
      },
      {
        path:'profile',
        component: ProfileComponent
      },
      {
        path:'profil',
        component:ProfilComponent
      },
      {
        path:'cineters/:id',
        component: CineterDetailsComponent
      },
      {
        path:'user_view_props/:id',
        component: UserViewPropsComponent
      },
      {
        path: 'sell_prop/:id',
        component: SellPropsComponent
      },
      {
        path: 'get_all_requests',
        component: RequestsComponent
      }

      ],

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
    HttpClientModule, CineterService, ShowService, AdminService, JwtService,
    AddCinetarServiceService, PropsService, AccountService, AddPerformanceService,
    AuthService, FriendsService],
  bootstrap: [AppComponent]
})

export class AppModule {
}
