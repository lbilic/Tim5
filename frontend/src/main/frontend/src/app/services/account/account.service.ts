import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Account} from "../../models/account";
import {JwtToken} from "../../shared/models/jwt-token";
// errors
import { AppError } from "../../shared/errors/app-error";
import { BadRequestError } from "../../shared/errors/bad-request-error";
import { NotFoundError } from "../../shared/errors/not-found-error";
// service
import { JwtService } from "../jwt.service";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AccountService {
  private readonly urlBase = '/api';

  constructor(private http: HttpClient, private jwtService: JwtService) { }

  save(account : Account)
    {
        return this.http.post('http://localhost:8080/api/register', account)
          /*.map((token: JwtToken) => {
            if(token.value) {
              this.jwtService.setToken(token.value);
              return true;
            }
            else return false;
          }).catch(this.handleErrors);*/
    }

  private handleErrors(response: Response) {
    if(response.status === 400)
      return Observable.throw(new BadRequestError());
    else if(response.status === 404)
      return Observable.throw(new NotFoundError());
    return Observable.throw(new AppError(response));
  }

}
