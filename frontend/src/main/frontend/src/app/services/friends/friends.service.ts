import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class FriendsService {

  constructor(private http : HttpClient) { }

  public sendRequest(username : String){
    return this.http.post('http://localhost:8080/api/send_request', username);
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
}
