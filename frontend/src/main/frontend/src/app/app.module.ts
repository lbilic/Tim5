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
import {DatepickerModule} from "ngx-bootstrap";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
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
import {ReservationService} from "./services/reservation/reservation.service";

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
import {ReserveSeatsComponent} from './components/reserve-seats/reserve-seats.component';

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
import {MovieScreeningService} from "./services/movie-screening/movie-screening.service";
import {HallService} from "./services/hall/hall.service";
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
import { MyPropsComponent } from './components/my-props/my-props.component';
import { BoughtPropsComponent } from './components/bought-props/bought-props.component';
import { HandleBiddingComponent } from './components/handle-bidding/handle-bidding.component';
import { BidModalComponent } from './components/bid-modal/bid-modal.component';
import { MyBiddingsComponent } from './components/my-biddings/my-biddings.component';
import { ListMoviesComponent } from './components/list-movies/list-movies.component';
import { ChangeShowComponent } from './components/change-show/change-show.component'
import {AddMovieScreeningComponent} from "./components/add-movie-screening/add-movie-screening.component";
import { AddMovieComponent } from './components/add-movie/add-movie.component';
import {AgmCoreModule} from "@agm/core";
import {RateService} from "./services/rate/rate.service";
import { RateModalComponent } from './components/rate-modal/rate-modal.component';
import {ListProjectionsComponent} from "./components/list-projections/list-projections.component";
import {ListPerformancesComponent} from "./components/list-performances/list-performances.component";
import { AdminPanelComponent } from './components/admin-panel/admin-panel.component';

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
    AddMovieScreeningComponent,
    ProfilComponent,
    ShowCinetersComponent,
    ListShowsComponent,
    PropsDetailComponent,
    CineterDetailsComponent,
    ReserveSeatsComponent,
    UserViewPropsComponent,
    SellPropsComponent,
    RequestsComponent,
    MyPropsComponent,
    BoughtPropsComponent,
    HandleBiddingComponent,
    BidModalComponent,
    MyBiddingsComponent,
    ListMoviesComponent,
    ChangeShowComponent,
    AddMovieComponent,
    ListProjectionsComponent,
    ListPerformancesComponent,
    RateModalComponent,
    AdminPanelComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    HttpModule,
    ToasterModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBtEDXxVtj8B6Pe_w5S0C7rx8p8rMgaVPU'
    }),
    SharedModule,
    DatepickerModule,
    NgbModule.forRoot(),
    TabsModule.forRoot(),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    CoreModule,
    RouterModule.forRoot([
      {
        path: '',
        component: HomePageComponent
      },
      {
        path: 'admin_panel',
        component: AdminPanelComponent
      },
      {
        path: 'add_cinetar',
        component: AddCinetarComponent
      },
      {
        path: 'add_show',
        component: AddShowComponent
      },
      {
        path: 'add_movie',
        component: AddMovieComponent
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
        path: 'cineters',
        component: ShowCinetersComponent
      },

      {
        path: 'admin_shows',
        component: ListShowsComponent
      },
      {
        path: 'movies',
        component: ListMoviesComponent
      },
      {
        path: 'change_password',
        component: ChangePasswordComponent
      },
      {
        path: 'add_props',
        component: AddPropsComponent
      },
      {
        path:'add_performance',
        component:AddPerformanceComponent
      },{
        path:'add_performance/:id',
        component:AddPerformanceComponent
      },
      {
        path: 'add_movie_screening/:id',
        component: AddMovieScreeningComponent
      },

      {
        path: 'projections/:id',
        component: ListProjectionsComponent
      },

      {
        path: 'performances/:id',
        component: ListPerformancesComponent
      },

      {
        path: 'profile',
        component: ProfileComponent
      },
      {
        path: 'profil',
        component: ProfilComponent
      },
      {
        path: 'cineters/:id',
        component: CineterDetailsComponent
      },

      {
        path: 'shows/:id',
        component: ChangeShowComponent
      },

      {
        path: 'reserve/:id',
        component: ReserveSeatsComponent
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
      },
      {
        path: 'my_props',
        component: MyPropsComponent
      },
      {
        path: 'bought_props',
        component: BoughtPropsComponent
      },
      {
        path: 'bids_for_my_prop',
        component: HandleBiddingComponent
      },
      {
        path: 'my_biddings',
        component: MyBiddingsComponent
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
    MovieScreeningService, AuthService, FriendsService, HallService, RateService, ReservationService],
  bootstrap: [AppComponent],
  entryComponents: [BidModalComponent]
})

export class AppModule {
}
