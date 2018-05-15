import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AddPerformanceService} from "../../services/performance/add-performance.service";
import {PerformanceCreate} from "../../models/performanceCreate";

@Component({
  selector: 'app-add-performance',
  templateUrl: './add-performance.component.html',
  styleUrls: ['./add-performance.component.css']
})
export class AddPerformanceComponent implements OnInit {

  form : FormGroup;

  constructor(private fb : FormBuilder, private performanceService: AddPerformanceService)
  {
    this.form = this.fb.group({
      date: ['', [
      ]],
      price: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],
      seatLayout: ['', [
      ]],

      hall: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],
    });
  }

  get date()
  {
    return this.form.get('date');
  }
  get seatLayout()
  {
    return this.form.get('seatLayout');
  }

  get price()
  {
    return this.form.get('price');
  }




  get hall()
  {
    return this.form.get('hall');
  }

  ngOnInit() {
  }

  register() {
   /** let performance = new PerformanceCreate(this.date.value, this.price.value, this.seatLayout.value, this.hall.value);

    this.performanceService.registerPerformance(performance).subscribe(data => {
      console.log(data);
    });**/
    this.performanceService.registerPerformance(new PerformanceCreate(this.date.value,this.seatLayout.value,
      this.price.value,  this.hall.value));

}}
