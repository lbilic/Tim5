import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CineterAdminCreate} from "../../models/cineterAdminCreate";

@Injectable()
export class AdminService {

  constructor(private http: HttpClient) {}

  registerCineterAdmin(admin : CineterAdminCreate){
    return this.http.post('http://localhost:8080/api/admin/create_cineter_admin', admin);
  }

  getAllRequests(){
    return this.http.get('http://localhost:8080/api/admin/get_all_requests');
  }

  acceptRequest(id){
    return this.http.get('http://localhost:8080/api/admin/accept_request?id=' + id);
  }

  deleteRequest(){
    return this.http.get('http://localhost:8080/api/admin/delete_request')
  }

}
