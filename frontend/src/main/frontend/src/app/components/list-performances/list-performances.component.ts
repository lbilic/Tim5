import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {ShowService} from "../../services/show/show.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {PerformanceCreate} from "../../models/performanceCreate";

@Component({
  selector: 'app-list-performances',
  templateUrl: './list-performances.component.html',
  styleUrls: ['./list-performances.component.css']
})
export class ListPerformancesComponent implements OnInit {

  id : number;
  performances : Array<PerformanceCreate>;

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
      this.performances = data as Array<PerformanceCreate>;

    });
  }

}
