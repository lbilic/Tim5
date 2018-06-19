import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {MovieScreeningCreate} from "../../models/movieScreeningCreate";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';

//errors
import { AppError } from "../../shared/errors/app-error";
import { BadRequestError } from "../../shared/errors/bad-request-error";
import { NotFoundError } from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";

@Injectable()
export class MovieScreeningService {

  constructor(private http : HttpClient) {
  }

  registerMovieScreening(movieScreening : MovieScreeningCreate, id, time)
  {
    console.log(movieScreening);
    return this.http.post('http://localhost:8080/api/admin/create_movie_screening?id='+id + '&time='+time,
      movieScreening, /*{headers: new HttpHeaders({"Content-Type": "text/plain"})}*/)
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
