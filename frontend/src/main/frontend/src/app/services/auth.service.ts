import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';
// models
import { Login } from "../shared/models/login";
import { JwtToken } from "../shared/models/jwt-token";
// errors
import { AppError } from "../shared/errors/app-error";
import { BadRequestError } from "../shared/errors/bad-request-error";
import { NotFoundError } from "../shared/errors/not-found-error";
// service
import { JwtService } from "./jwt.service";
import {ForbiddenError} from "../shared/errors/forbidden-error";


@Injectable()
export class AuthService {
  private readonly urlBase = '/api';

  constructor(private http: HttpClient, private jwtService: JwtService) { }

  login(credentials: Login): Observable<boolean> {
    return this.http.post(`http://localhost:8080/api/login`, credentials)
      .map((token: JwtToken) => {
        if(token.value) {
          this.jwtService.setToken(token.value);
          return true;
        }
        else return false;
      }).catch(this.handleErrors);
  }

  logout() {
    this.jwtService.removeToken();
  }

  checkUsername(username: string): Observable<boolean> {
    let params: HttpParams = new HttpParams()
      .append('username', username);
    return this.http.get<boolean>(`http://localhost:8080/api/check_username`, {params})
      .catch(this.handleErrors);
  }

  currentUser(): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/current_user`)
      .catch(this.handleErrors);
  }

  private handleErrors(response: Response) {
    if(response.status === 400)
      return Observable.throw(new BadRequestError());
    else if(response.status === 404)
      return Observable.throw(new NotFoundError());
    else if(response.status === 403)
      return Observable.throw(new ForbiddenError());
    return Observable.throw(new AppError(response));
  }
}
