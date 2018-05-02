import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
// service
import { JwtService } from "./jwt.service";
import { AuthService } from './auth.service';


describe('AuthService', () => {
  let authService: AuthService;
  let jwtService: JwtService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      providers: [ AuthService, JwtService ]
    });

    authService = TestBed.get(AuthService);
    jwtService = TestBed.get(JwtService);
    httpMock = TestBed.get(HttpTestingController);
  });

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
      'password': 'test'
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
      'email': 'luka@gmail.com',
      'phoneNumber': '1234567'
    };

    const httpRequest = httpMock.expectOne('/api/current_user');
    expect(httpRequest.request.method).toEqual('GET');
    httpRequest.flush(response);
  });
});
