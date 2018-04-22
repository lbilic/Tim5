import { Injectable } from '@angular/core';
import {CineterCreate} from "../../models/cineterCreate";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class AddCinetarServiceService {

  constructor(private http: HttpClient) {
   }

   sendRegistration(cineter : CineterCreate)
   {
     this.http.post("http://localhost:8080/api/admin/create_cinetar", cineter).subscribe(data =>{
       console.log(data);
     });
   }

}
