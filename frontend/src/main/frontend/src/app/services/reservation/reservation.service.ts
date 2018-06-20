import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ReservationService {
  private readonly urlBase = '/api';

  constructor(private http: HttpClient) { }

  public getAllMovieReservations(){
    return this.http.get('http://localhost:8080/api/reservations/get_all_movie_reservations');
  }

  public getAllShowReservations(){
    return this.http.get('http://localhost:8080/api/reservations/get_all_show_reservations');
  }

  public getAll(id){
    return this.http.get('http://localhost:8080/api/reservations/get?id='+id);
  }

}
