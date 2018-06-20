import { Component, OnInit } from '@angular/core';
import {Props} from "../../models/props";
import {PropsService} from "../../services/props/props.service";
import {JwtService} from "../../core/services/jwt.service";
import {Router} from "@angular/router";
import {BoughtProps} from "../../models/boughtProps";

@Component({
  selector: 'app-bought-props',
  templateUrl: './bought-props.component.html',
  styleUrls: ['./bought-props.component.css']
})
export class BoughtPropsComponent implements OnInit {

  props : Array<BoughtProps>;

  constructor(private propsService: PropsService, jwtutils :JwtService, private router: Router) {
    console.log(jwtutils.decodeToken());
    this.propsService.boughtProps().subscribe(data =>{
      this.props = data as Array<BoughtProps>;
    });
  }


  ngOnInit() {
  }

}
