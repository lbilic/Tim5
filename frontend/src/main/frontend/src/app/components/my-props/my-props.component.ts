import { Component, OnInit } from '@angular/core';
import {Props} from "../../models/props";
import {PropsService} from "../../services/props/props.service";
import {JwtService} from "../../core/services/jwt.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-my-props',
  templateUrl: './my-props.component.html',
  styleUrls: ['./my-props.component.css']
})
export class MyPropsComponent implements OnInit {

  props : Array<Props>;

  constructor(private propsService: PropsService, jwtutils :JwtService, private router: Router) {
    console.log(jwtutils.decodeToken());
    this.propsService.myProps().subscribe(data =>{
      this.props = data as Array<Props>;
    });
  }

  ngOnInit() {
  }

  DeleteProp(prop){
    this.propsService.deleteUserProp(prop.id).subscribe(data => {
      let index = this.props.indexOf(prop);
      this.props.splice(index, 1);
    });
  }

  ChangeProp(prop: Props) {
    this.DeleteProp(prop);
    this.router.navigate([`/sell_prop/${prop.id}`]);
  }

}
