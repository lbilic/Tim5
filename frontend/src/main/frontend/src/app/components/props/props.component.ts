import { Component, OnInit } from '@angular/core';
import {PropsService} from "../../services/props/props.service";
import {Props} from "../../models/props";
import { JwtService } from "../../core/services/jwt.service";
import {Router, RouterModule} from "@angular/router";

@Component({
  selector: 'app-props',
  templateUrl: './props.component.html',
  styleUrls: ['./props.component.css']
})
export class PropsComponent implements OnInit {

  props : Array<Props>;

  constructor(private propsService: PropsService, jwtutils :JwtService, private router: Router) {
      console.log(jwtutils.decodeToken());
      this.propsService.getAllProps().subscribe(data =>{
        this.props = data as Array<Props>;
      });
  }

  ngOnInit() {
  }

  DeleteProp(prop){
    this.propsService.deleteProp(prop).subscribe(data => {
      let index = this.props.indexOf(prop);
      this.props.splice(index, 1);
    });
  }

  ChangeProp(prop: Props) {
    this.router.navigate([`/props/${prop.id}`]);
  }
}
