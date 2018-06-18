import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MovieScreeningService} from "../../services/movie-screening/movie-screening.service";
import {MovieScreeningCreate} from "../../models/movieScreeningCreate";
import {Router, ActivatedRoute, Params} from "@angular/router";
import {HallCreate} from "../../models/hallCreate";
import {HallService} from "../../services/hall/hall.service"

@Component({
  selector: 'app-add-movie-screening',
  templateUrl: './add-movie-screening.component.html',
  styleUrls: ['./add-movie-screening.component.css']
})
export class AddMovieScreeningComponent implements OnInit {

  form : FormGroup;
  returnURL: string = '';
  show_id: number;
  halls: Array<HallCreate>;
  selected_index : number;

  constructor(private fb : FormBuilder,
              private movieScreeningService: MovieScreeningService, private router: Router,
              private route: ActivatedRoute, private hallService: HallService)
  {
    this.form = this.fb.group({
      date: ['', [
      ]],

      price: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],

      type: ['', [
      ]],

    });

    this.route.params.subscribe((param: Params) => {
      this.show_id = param['id'];
    });

    this.selected_index = 0;

    this.hallService.getAllHalls(this.show_id).subscribe(data => {
      this.halls = data as (Array<HallCreate>);
    });

  }

  get date()
  {
    return this.form.get('date');
  }

  get price()
  {
    return this.form.get('price');
  }
  get type()
  {
    return this.form.get('type');
  }


  ngOnInit() {
    this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  register() {
    this.movieScreeningService.registerMovieScreening(new MovieScreeningCreate(this.date.value,
      this.price.value, this.type.value, this.halls[this.selected_index]), this.show_id).subscribe(data => {
      this.router.navigateByUrl(this.returnURL);
    });
  }
   onChange(value){
     this.selected_index = value;
   }

}
