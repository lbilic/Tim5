import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ShowCreate} from "../../models/showCreate";

@Injectable()
export class ShowService {

  constructor(private http : HttpClient) {
  }

  registerShow(show : ShowCreate)
  {
      return this.http.post('http://localhost:8080/api/admin/create_show', show);
  }

}
