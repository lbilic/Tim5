import { Injectable } from '@angular/core';
import {CineterCreate} from "../../models/cineterCreate";
import {HttpClient} from "@angular/common/http";

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

}
