import { TestBed, inject } from '@angular/core/testing';

import { AuthService } from './auth.service';
import {JwtService} from "./jwt.service";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";

describe('AuthService', () => {
  let authService: AuthService;
  let jwtService: JwtService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      providers: [ AuthService, JwtService ]
    });
  });

  authService = TestBed.get(AuthService);
  jwtService = TestBed.get(JwtService);
  httpMock = TestBed.get(HttpTestingController);


  // make sure that there are no outstanding requests
  afterEach(() => {
    httpMock.verify();
  });


  it('should be created', inject([AuthService], (service: AuthService) => {
    expect(service).toBeTruthy();
  }));


  it('should create jwt service', inject([JwtService], (service: JwtService) => {
    expect(service).toBeTruthy();
  }));


  it('should return flag is login successfully done', () => {
    const credentials = {
      'username': 'luka',
      'password': 'sifra'
    };

    const response = {
      'value': 'some.token.value'
    };

    authService.login(credentials)
      .subscribe((successfullyLoggedIn: boolean) => {
        expect(successfullyLoggedIn).toEqual(true);
      });
    const httpRequest = httpMock.expectOne('/api/login');
    expect(httpRequest.request.method).toEqual('POST');
    httpRequest.flush(response);
  });


  it('should return current user', () => {
    const response = {
      'id': 1,
      'firstName': 'Luka',
      'lastName': 'Bilic',
      'birthDate': new Date(),
      'email': 'phoenix-023@hotmail.com',
      'phoneNumber': '1234567'
    };

    authService.currentUser()
      .subscribe((user: TenantProfile) => {
        expect(user).toEqual(response);
      });
    const httpRequest = httpMock.expectOne('/api/current_user');
    expect(httpRequest.request.method).toEqual('GET');
    httpRequest.flush(response);
  });
});
