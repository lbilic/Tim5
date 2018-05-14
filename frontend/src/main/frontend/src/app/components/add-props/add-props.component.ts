import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PropsService} from "../../services/props/props.service";
import {PropsCreate} from "../../models/propsCreate";

@Component({
  selector: 'app-add-props',
  templateUrl: './add-props.component.html',
  styleUrls: ['./add-props.component.css']
})
export class AddPropsComponent implements OnInit {

  form : FormGroup;

  constructor(private fb : FormBuilder, private propsService: PropsService)
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
      ]],

      cineterId: ['', [
        Validators.required
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


  get cineterId()
  {
    return this.form.get('cineterId');
  }

  ngOnInit() {
  }

  register() {
    let props = new PropsCreate(this.name.value, this.price.value, this.description.value, this.cineterId.value);
    console.log(props.cineterId);
    this.propsService.registerProps(props).subscribe(data => {
      console.log(data);
    });

  }}

