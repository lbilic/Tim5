import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {ShowService} from "../../services/show/show.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {MovieScreeningCreate} from "../../models/movieScreeningCreate";



@Component({
  selector: 'app-list-projections',
  templateUrl: './list-projections.component.html',
  styleUrls: ['./list-projections.component.css']
})
export class ListProjectionsComponent implements OnInit {

  id : number;
  moviescreenings : Array<MovieScreeningCreate>;

  constructor(private fb: FormBuilder, private route : ActivatedRoute,
              private router: Router, private showService: ShowService) {

    this.route.params.subscribe((param: Params) => {
      this.id = param['id'];
      this.getAll();
    });

  }

  ngOnInit() {
  }

  getAll(){
      this.showService.getPerformances(this.id).subscribe(data => {
        this.moviescreenings = data as Array<MovieScreeningCreate>;

      });
  }

}
