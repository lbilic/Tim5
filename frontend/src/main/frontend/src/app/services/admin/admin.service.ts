import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CineterAdminCreate} from "../../models/cineterAdminCreate";

@Injectable()
export class AdminService {

  constructor(private http: HttpClient) {}

  registerCineterAdmin(admin : CineterAdminCreate){
    return this.http.post('http://localhost:8080/api/admin/create_cineter_admin', admin);
  }

}
