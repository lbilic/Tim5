import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AddPerformanceService} from "../../services/performance/add-performance.service";
import {PerformanceCreate} from "../../models/performanceCreate";
import {Router, ActivatedRoute, Params} from "@angular/router";
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
  show_id : number;
  halls: Array<HallCreate>;
  selected_index : number;
  number_of_seats: number;

  constructor(private fb : FormBuilder,
              private performanceService: AddPerformanceService, private router: Router,
              private route: ActivatedRoute, private hallService: HallService)
  {
    this.form = this.fb.group({
      date: ['', [
        Validators.required

      ]],

      time: ['', [
        Validators.required,
        Validators.pattern('([01]?[0-9]|2[0-3]):[0-5][0-9]')
      ]],

      price: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],

      fastSeats:['', [
        Validators.required,
        Validators.pattern('^(,?[A-Z][1-9][0-9]?)*$')
      ]]


    });

    this.route.params.subscribe((param: Params) => {
      this.show_id = param['id'];
    });


    this.hallService.getAllHalls(this.show_id).subscribe((data:any) => {
      this.halls = data as Array<HallCreate>;
      console.log(this.halls);
    });

    this.selected_index = 0;
  }

  get date()
  {
    return this.form.get('date');
  }

  get time()
  {
    return this.form.get('time');
  }

  get price()
  {
    return this.form.get('price');
  }

  get fastSeats()
  {
    return this.form.get('fastSeats');
  }


  ngOnInit() {
    this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  register() {
    this.performanceService.registerPerformance(new PerformanceCreate(null, this.date.value,
      this.price.value, this.halls[this.selected_index].id,this.fastSeats.value),
      this.show_id, this.time.value).subscribe(data => {
      this.router.navigateByUrl(this.returnURL);
    });
  }
    onChange(value){
      this.selected_index = value;
    }

}
