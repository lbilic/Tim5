import { Component, OnInit } from '@angular/core';
import {PropsService} from "../../services/props/props.service";
import {Props} from "../../models/props";
import { JwtService } from "../../core/services/jwt.service";

@Component({
  selector: 'app-props',
  templateUrl: './props.component.html',
  styleUrls: ['./props.component.css']
})
export class PropsComponent implements OnInit {

  props : Array<Props>;

  constructor(private propsService: PropsService, jwtutils :JwtService) {
      console.log(jwtutils.decodeToken());
      this.propsService.getAllProps().subscribe(data =>{
        this.props = data as Array<Props>;
      });
  }

  ngOnInit() {
  }

}
