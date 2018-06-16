import { Component, OnInit } from '@angular/core';
import {PropsService} from "../../services/props/props.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Props} from "../../models/props";

@Component({
  selector: 'app-user-view-props',
  templateUrl: './user-view-props.component.html',
  styleUrls: ['./user-view-props.component.css']
})
export class UserViewPropsComponent implements OnInit {

  props : Array<Props> = [];
  id : number;
  prop : Props;

  constructor(private route : ActivatedRoute, private propsService: PropsService) {

    this.route.params.subscribe((param: Params) => {
      this.id = param['id'];
      this.getProps();
    });

  }

  getProps(){
    this.propsService.getAllPropsById(this.id).subscribe(data =>{
      this.props = data as Array<Props>;
    });
  }

  Reserve(i){
    this.propsService.reserveProp(i.id).subscribe(data =>{
      this.prop = data as Props;
    })
  }

  ngOnInit() {
  }

}
