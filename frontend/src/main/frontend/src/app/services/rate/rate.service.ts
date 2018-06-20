import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Cineter} from "../../models/cineter";
import {Observable} from "rxjs/Observable";

@Injectable()
export class RateService {

  constructor(private http: HttpClient) { }


  canRate(id : number){
    return this.http.get(`/api/rate/can_rate_cineter?cineterId=${id}`);
  }

  rateCineter(id: number, rate: number) {
    return this.http.post(`/api/rate/cineter?id=${id}&rate=${rate}`, null);
  }

  rateShow(id: number, rate: number){
    return this.http.post(`/api/rate/show?id=${id}&rate=${rate}`, null);

  }

  getCinterRate(id: number) : Observable<any>{
    return this.http.get(`/api/rate/cineter?id=${id}`);
  }

  getShowRate(id: number) : Observable<any> {
    return this.http.get(`/api/rate/show?id=${id}`);
  }
}
