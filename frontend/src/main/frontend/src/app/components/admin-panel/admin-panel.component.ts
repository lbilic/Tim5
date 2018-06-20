import { Component, OnInit } from '@angular/core';
import { ToasterConfig, ToasterService } from "angular5-toaster/dist";
import { Validators, FormBuilder, FormGroup } from "@angular/forms";
import { Router, ActivatedRoute } from '@angular/router';

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

  constructor(private fb: FormBuilder, private router: Router, private route: ActivatedRoute,
              private toasterService: ToasterService) {
      this.form = this.fb.group({
        gold: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
        platinum: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
        diamond: ['', [Validators.required, Validators.pattern('^[0-9]*$')]]
      });
      this.toasterConfig = new ToasterConfig({timeout: 4000});
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
}
