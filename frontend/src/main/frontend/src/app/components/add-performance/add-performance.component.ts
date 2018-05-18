import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AddPerformanceService} from "../../services/performance/add-performance.service";
import {PerformanceCreate} from "../../models/performanceCreate";
import {Router, ActivatedRoute} from "@angular/router";
import {HallCreate} from "../../models/hallCreate";
import {HallService} from "../../services/hall/hall.service";

@Component({
  selector: 'app-add-performance',
  templateUrl: './add-performance.component.html',
  styleUrls: ['./add-performance.component.css']
})
export class AddPerformanceComponent implements OnInit {

  form : FormGroup;
  returnURL: string = '';
  halls: Array<HallCreate>;
  selected_index : number;

  constructor(private fb : FormBuilder,
              private performanceService: AddPerformanceService, private router: Router,
              private route: ActivatedRoute)
  {
    this.form = this.fb.group({
      date: ['', [
      ]],

      price: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],

    });

    //this.selected_index = 0;
/*
    this.hallService.getAllHalls().subscribe(data => {
      this.halls = data as Array<HallCreate>;
    });*/
  }

  get date()
  {
    return this.form.get('date');
  }

  get price()
  {
    return this.form.get('price');
  }


  ngOnInit() {
    this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  register() {
    this.performanceService.registerPerformance(new PerformanceCreate(this.date.value,
      this.price.value, /*this.halls[this.selected_index]*/)).subscribe(data => {
      this.router.navigateByUrl(this.returnURL);
    });
  }
   /* onChange(value){
      this.selected_index = value;
    }
*/
}