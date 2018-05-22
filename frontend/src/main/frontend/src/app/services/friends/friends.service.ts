import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';
// errors
import { AppError } from "../../shared/errors/app-error";
import { BadRequestError } from "../../shared/errors/bad-request-error";
import { NotFoundError } from "../../shared/errors/not-found-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";

@Injectable()
export class FriendsService {

  constructor(private http : HttpClient) { }

  public sendRequest(username : String){
    return this.http.post('http://localhost:8080/api/send_request', username)
      .catch(this.handleErrors);
  }

  public getAllFriends() {
    return this.http.get('http://localhost:8080/api/get_friends');
  }

  public getAllRequests() {
    return this.http.get('http://localhost:8080/api/get_requests');
  }

  public acceptRequest(username : String){
    return this.http.post('http://localhost:8080/api/accept_request', username);
  }

  public declineRequest(username : String){
    return this.http.post('http://localhost:8080/api/decline_request', username);
  }

  public removeFriend(username : String) {
    return this.http.post('http://localhost:8080/api/remove_friend', username);
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
