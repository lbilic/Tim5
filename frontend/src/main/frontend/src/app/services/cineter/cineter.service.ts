import { Injectable } from '@angular/core';
import {CineterCreate} from "../../models/cineterCreate";
import {HttpClient} from "@angular/common/http";
import {Cineter} from "../../models/cineter";
import {Observable} from "rxjs/Observable";

@Injectable()
export class CineterService {

  constructor(private http: HttpClient) {
   }

   sendRegistration(cineter : CineterCreate)
   {
     return this.http.post("http://localhost:8080/api/admin/create_cinetar", cineter);
   }

   getAllCineters()
   {
     return this.http.get("http://localhost:8080/api/cineter/get_all");
   }

   deleteCineter(cineter){
      return this.http.post("http://localhost:8080/api/cineter/delete_cineter", cineter);
   }

   getCineterById(id)
   {
     return this.http.get("http://localhost:8080/api/cineter/get?id=" + id);
   }

  updateCineter(cineter: Cineter) {
    return this.http.post("http://localhost:8080/api/cineter/update_cineter", cineter);
  }

  getLocation(city: string, address: string) : Observable<any> {
    return this.http.get(`api/location?address=${city} ${address}`);
  }
}
