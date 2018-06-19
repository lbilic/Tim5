import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Cineter} from "../../models/cineter";

@Injectable()
export class RateService {

  constructor(private http: HttpClient) { }


  canRate(id : number){
    return this.http.get(`/api/rate/can_rate_cineter?cineterId=${id}`);
  }

  rate(id: number, rate: number) {
    return this.http.post(`/api/rate/cineter?id=${id}&rate=${rate}`, null);
  }
}
