import { Component, OnInit } from '@angular/core';
import {ShowService} from "../../services/show/show.service";
import {Show} from "../../models/show";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {MovieScreeningCreate} from "../../models/movieScreeningCreate";
import * as moment from 'moment';
import {ShowCreate} from "../../models/showCreate";
import {PerformanceCreate} from "../../models/performanceCreate";

@Component({
  selector: 'app-list-movies',
  templateUrl: './list-movies.component.html',
  styleUrls: ['./list-movies.component.css']
})
export class ListMoviesComponent implements OnInit {

  movies : Array<ShowCreate>;
  projections: Array<MovieScreeningCreate>;
  movies_cineter_id: Array<ShowCreate>;

  performances: Array<PerformanceCreate>;
  cineter_id: number;
  id: number;

  constructor(private showService: ShowService, private router: Router,
              private route : ActivatedRoute) {

    /**
    this.showService.getAllShows().subscribe(data =>{
      this.movies = (data as Array<ShowCreate>).filter(item => item.isMovie);
      //this.movies = this.movies.filter(item => item.id);
      //console.log(this.movies);
    });

    /*
    this.showService.getPerformances("movie").subscribe(data => {
      this.projections = data as Array<MovieScreeningCreate>;
      //console.log(this.projections);
    });*/

    this.route.params.subscribe((param: Params) => {
      this.id = param['id'];
    });

    // lista filmova u odnosu na cineter id
    this.showService.getShowsByCineterId(this.id).subscribe(data=>{
      this.movies_cineter_id= data as Array<ShowCreate>;
      console.log(this.movies_cineter_id);
    });

    this.showService.getPerformances("movie.id").subscribe(data => {
      this.performances = data as Array<PerformanceCreate>;
      console.log(this.performances);
    });

  }

  ngOnInit() {
  }

  Reserve(i){
    this.router.navigate([`/reserve/${i.id}`])
  }

  getDate(i) {
    return (moment().add(i, 'days').format("DD.MM."));
  }

   getPerformances(id){
    this.showService.getPerformances(id).subscribe(data => {
      this.performances = data as Array<PerformanceCreate>;
    });
  }

}
