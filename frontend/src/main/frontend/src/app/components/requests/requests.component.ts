import { Component, OnInit } from '@angular/core';
import {PropsRequest} from "../../models/props-request";
import {Router} from "@angular/router";
import {JwtService} from "../../core/services/jwt.service";
import {AdminService} from "../../services/admin/admin.service";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  requests : Array<PropsRequest>;
  toasterConfig: ToasterConfig;

  constructor(private adminService: AdminService, jwtutils :JwtService, private router: Router,
                private toasterService: ToasterService) {
    console.log(jwtutils.decodeToken());
    this.adminService.getAllRequests().subscribe(data =>{
      console.log(data);
      this.requests = data as Array<PropsRequest>;
    });
    this.toasterConfig = new ToasterConfig({timeout: 4000});
  }


  ngOnInit() {
  }

  DeleteRequest(request){
    this.adminService.deleteRequest(request.id).subscribe(data => {
      let index = this.requests.indexOf(request);
      this.requests.splice(index, 1);
    });
    this.toasterService.pop('success', 'Success!','You have successfully deleted the request!');
  }

  AcceptRequest(request) {
    this.adminService.acceptRequest(request.id).subscribe(data =>{

    });
    this.toasterService.pop('success', 'Success!','You have successfully accepted the request!');
  }

}
