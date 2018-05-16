import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class HallService {

  constructor(private http: HttpClient) {
  }

  getAllHalls(){
    return this.http.get('http://localhost:8080/api/halls/get_all');
  }
}
