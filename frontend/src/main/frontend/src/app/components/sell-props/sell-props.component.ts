import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PropsService} from "../../services/props/props.service";
import {PropsCreate} from "../../models/propsCreate";
import {ActivatedRoute, Params} from "@angular/router";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";
import {Props} from "../../models/props";

@Component({
  selector: 'app-sell-props',
  templateUrl: './sell-props.component.html',
  styleUrls: ['./sell-props.component.css']
})
export class SellPropsComponent implements OnInit {

  form : FormGroup;
  cineter_id : any;
  toasterConfig: ToasterConfig;
  prop : PropsCreate;

  constructor(private fb : FormBuilder, private route : ActivatedRoute, private propsService: PropsService, private toasterService: ToasterService)
  {
    this.prop = new PropsCreate('', 0, '', 1 , 0);

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
      ]]
/*      date: ['', [
        Validators.required,
       // Validators.

      ]]*/
    });

    this.route.params.subscribe((param: Params) => {
      this.cineter_id = param['id'];
      if(this.cineter_id.endsWith('$'))
      {
        this.cineter_id = this.cineter_id.substring(0, this.cineter_id.length - 1);
        this.propsService.deleteUserProp(this.cineter_id).subscribe(data => {
              console.log(data);
              this.prop = data as PropsCreate;
              this.cineter_id = this.prop.cineterId;
        }, error2 => {

        });
      }
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

    this.propsService.sellProp(this.cineter_id, this.prop).subscribe(data => {
      this.toasterService.pop('success', 'Success!','Your prop has successfully been sent for approval!');

      console.log(data);
     });
   }


}
