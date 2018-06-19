import { Component, OnInit } from '@angular/core';
import {Show} from "../../models/show";
import {ActivatedRoute, Params} from "@angular/router";
import {ShowService} from "../../services/show/show.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";


@Component({
  selector: 'app-change-show',
  templateUrl: './change-show.component.html',
  styleUrls: ['./change-show.component.css']
})
export class ChangeShowComponent implements OnInit {

  show : Show;
  id : number;
  form : FormGroup;
  returnURL: string = '';


  constructor(private fb: FormBuilder, private route : ActivatedRoute,
              private showService: ShowService, private router: Router) {
    this.route.params.subscribe((param: Params) => {
      this.id = param['id'];
      this.getShow();
    });

    this.form = this.fb.group({
      name: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],
      description: ['', [
        Validators.required,
        Validators.minLength(5)

      ]],

    });
  }

  ngOnInit() {
    this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/shows';
  }


  get name() {
    return this.form.get('name');
  }

  get description() {
    return this.form.get('description');
  }


  private getShow() {
    this.showService.getShowById(this.id).subscribe(data => {
      this.show = data as Show;
      console.log(this.show);
    });
  }

  private change()
  {
    console.log(this.show);
    this.showService.updateShow(this.show).subscribe(data =>{
      this.router.navigateByUrl(this.returnURL);
    });
  }
}
