import { Component, OnInit } from '@angular/core';
import { ToasterConfig, ToasterService } from "angular5-toaster/dist";
import { Validators, FormBuilder, FormGroup } from "@angular/forms";
import { Router, ActivatedRoute } from '@angular/router';
import { Scale } from '../../models/scale';
import {SystemService} from "../../services/system/system.service"

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  providers: [ToasterService],
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {
  form: FormGroup;
  returnURL: string = '';
  toasterConfig: ToasterConfig;
  goldValue: Number;
  platinumValue: Number;
  diamondValue: Number;
  scale: Scale;

  constructor(private fb: FormBuilder, private router: Router, private route: ActivatedRoute,
              private toasterService: ToasterService, private systemService: SystemService) {
      this.form = this.fb.group({
        gold: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
        platinum: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
        diamond: ['', [Validators.required, Validators.pattern('^[0-9]*$')]]
      });
      this.toasterConfig = new ToasterConfig({timeout: 4000});
      this.systemService.getScale().subscribe(data => {
        this.scale = (data as Scale);
        this.goldValue = this.scale.scale.pop();
        this.platinumValue = this.scale.scale.pop();
        this.goldValue = this.scale.scale.pop();
      });
    }

  ngOnInit() {
    this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get gold() {
    return this.form.get('gold');
  }

  get platinum() {
    return this.form.get('platinum');
  }

  get diamond() {
    return this.form.get('diamond');
  }

  changeThresholds(){
    let array = new Array<Number>();
    array.push(this.gold.value);
    array.push(this.platinum.value);
    array.push(this.diamond.value);
    let scale = new Scale(array);
    console.log(array);
    console.log(scale);
    this.systemService.update(scale).subscribe(data => {
      this.router.navigateByUrl(this.returnURL);
    });
  }
  
}
