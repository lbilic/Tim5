import { Component, OnInit } from '@angular/core';
import {Props} from "../../models/props";
import {PropsService} from "../../services/props/props.service";
import {JwtService} from "../../core/services/jwt.service";
import {Router} from "@angular/router";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";

@Component({
  selector: 'app-my-props',
  templateUrl: './my-props.component.html',
  styleUrls: ['./my-props.component.css']
})
export class MyPropsComponent implements OnInit {

  props : Array<Props>;
  toasterConfig: ToasterConfig;

  constructor(private propsService: PropsService, jwtutils :JwtService, private router: Router,
              private toasterService: ToasterService) {
    console.log(jwtutils.decodeToken());
    this.propsService.myProps().subscribe(data =>{
      this.props = data as Array<Props>;
    });
    this.toasterConfig = new ToasterConfig({timeout: 4000});
  }

  ngOnInit() {
  }

  DeleteProp(prop){
    this.propsService.deleteUserProp(prop.id).subscribe(data => {
      let index = this.props.indexOf(prop);
      this.props.splice(index, 1);
    });
    this.toasterService.pop('success', 'Success!','You have successfully deleted your prop!');

  }

  ChangeProp(prop: Props) { //ovo jos uvek ne radi...
    this.router.navigate([`/sell_prop/${prop.id}$`]);
  }

}
