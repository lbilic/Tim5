import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PropsService} from "../../services/props/props.service";
import {PropsCreate} from "../../models/propsCreate";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";

@Component({
  selector: 'app-add-props',
  templateUrl: './add-props.component.html',
  styleUrls: ['./add-props.component.css']
})
export class AddPropsComponent implements OnInit {

  form : FormGroup;
  toasterConfig: ToasterConfig;

  constructor(private fb : FormBuilder, private propsService: PropsService, private toasterService: ToasterService)
  {
    this.toasterConfig = new ToasterConfig({timeout: 4000});
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
      amount: ['', [
        Validators.required,
        Validators.min(1)
      ]]
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

  get amount()
  {
    return this.form.get('amount');
  }

  ngOnInit() {
  }

  register() {
    let props = new PropsCreate(this.name.value, this.price.value, this.description.value, this.amount.value, 0);
    this.propsService.registerProps(props).subscribe(data => {
      console.log(data);
    });
    this.toasterService.pop('success', 'Success!','You have successfully added a new prop!');
  }
}


