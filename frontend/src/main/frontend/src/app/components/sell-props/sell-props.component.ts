import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PropsService} from "../../services/props/props.service";
import {PropsCreate} from "../../models/propsCreate";
import {ActivatedRoute, Params} from "@angular/router";

@Component({
  selector: 'app-sell-props',
  templateUrl: './sell-props.component.html',
  styleUrls: ['./sell-props.component.css']
})
export class SellPropsComponent implements OnInit {

  form : FormGroup;
  id : number;

  constructor(private fb : FormBuilder, private route : ActivatedRoute, private propsService: PropsService)
  {
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
      ]]
/*      date: ['', [
        Validators.required,
       // Validators.

      ]]*/
    });

    this.route.params.subscribe((param: Params) => {
      this.id = param['id'];
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

  ngOnInit() {
  }

  register(i) {
    let props = new PropsCreate(this.name.value, this.price.value, this.description.value, 1);
    this.propsService.sellProp(this.id, props).subscribe(data => {
      console.log(data);
    });

  }


}
