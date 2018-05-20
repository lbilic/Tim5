import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {Props} from "../../models/props";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PropsService} from "../../services/props/props.service";
import {PropsCreate} from "../../models/propsCreate";

@Component({
  selector: 'app-props-detail',
  templateUrl: './props-detail.component.html',
  styleUrls: ['./props-detail.component.css']
})
export class PropsDetailComponent implements OnInit {

  id : number;
  prop: Props;
  form : FormGroup;


  constructor(private fb : FormBuilder, private route : ActivatedRoute, private propsService: PropsService)
  {
    this.prop = new Props(0, '', 0, '');
    this.form = this.fb.group({
      name: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],
      price: ['', [
        Validators.required,
        Validators.minLength(5)
      ]],
      description: ['', [
        Validators.required,
        Validators.minLength(5)
      ]],
    });

    this.route.params.subscribe((param: Params) => {
      this.id = param['id'];
      this.getProp();
    });
  }

  getProp(){
    this.propsService.findProp(this.id).subscribe(data =>{
      this.prop = data as Props;
    });
  }

  get name()
  {
    return this.form.get('name');
  }

  get price()
  {
    return this.form.get('price');
  }

  get description()
  {
    return this.form.get('description');
  }

  ngOnInit(){

  }

  change(){
    this.propsService.changeProps(this.prop).subscribe(data =>{

    });
  }

}
