import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MovieScreeningCreate} from "../../models/movieScreeningCreate";


@Injectable()
export class MovieScreeningService {

  constructor(private http : HttpClient) {
  }

  registerMovieScreening(movieScreening : MovieScreeningCreate, id)
  {
    return this.http.post('http://localhost:8080/api/admin/create_movie_screening?id='+id, movieScreening);
  }
}
