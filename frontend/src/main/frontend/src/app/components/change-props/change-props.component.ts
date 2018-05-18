import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PropsService} from "../../services/props/props.service";
import {Props} from "../../models/props";

@Component({
  selector: 'app-change-props',
  templateUrl: './change-props.component.html',
  styleUrls: ['./change-props.component.css']
})
export class ChangePropsComponent implements OnInit {

  form : FormGroup;
  private prop: Props;

  constructor(private fb : FormBuilder, private propsService: PropsService)
  {

    this.prop = new Props(0,'',0,'');

    this.propsService.findProp(0).subscribe(data => {
      this.prop = data as Props;
    });

    this.form = this.fb.group({
      id:[{disabled: true, value: ''}, [
        Validators.required,
      ]],
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

  get id()
  {
    return this.form.get('id');
  }

  ngOnInit() {
  }

  register() {
    let props = new Props(this.id.value, this.name.value, this.price.value, this.description.value);
    this.propsService.changeProps(props).subscribe(data => {
      console.log(data);
    });

  }}


