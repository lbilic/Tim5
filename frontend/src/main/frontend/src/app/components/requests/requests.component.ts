import { Component, OnInit } from '@angular/core';
import {PropsRequest} from "../../models/props-request";
import {Router} from "@angular/router";
import {JwtService} from "../../core/services/jwt.service";
import {AdminService} from "../../services/admin/admin.service";

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  requests : Array<PropsRequest>;

  constructor(private adminService: AdminService, jwtutils :JwtService, private router: Router) {
    console.log(jwtutils.decodeToken());
    this.adminService.getAllRequests().subscribe(data =>{
      console.log(data);
      this.requests = data as Array<PropsRequest>;
    });
  }


  ngOnInit() {
  }

  DeleteRequest(request){//je l' ovo ok?
    this.adminService.deleteRequest(request.id).subscribe(data => {
      let index = this.requests.indexOf(request);
      this.requests.splice(index, 1);
    });
  }

  AcceptRequest(request) {
    this.adminService.acceptRequest(request.id).subscribe(data =>{

    });
  }

}
