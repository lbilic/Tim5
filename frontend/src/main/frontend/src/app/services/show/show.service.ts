import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ShowCreate} from "../../models/showCreate";
import {Show} from "../../models/show";


@Injectable()
export class ShowService {

  constructor(private http : HttpClient) {
  }

  registerShow(show : ShowCreate)
  {
      return this.http.post('http://localhost:8080/api/admin/create_show', show);
  }
  getAllShows(){
    return this.http.get('http://localhost:8080/api/show/get_all');
  }
  getShowById(id){
    return this.http.get('http://localhost:8080/api/show/get_show?id=' + id);
  }

  deleteShow(show){
    return this.http.post('http://localhost:8080/api/show/delete_show', show);
  }

  updateShow(show: Show){
    return this.http.post('http://localhost:8080/api/show/update_show', show);
  }

  getPerformances(id){
    return this.http.get('http://localhost:8080/api/show/get_performances?id=' + id);
  }

  getShowsByCineterId(id){
    return this.http.get('http://localhost:8080/api/show/get_shows?id='+id);

  }


}
