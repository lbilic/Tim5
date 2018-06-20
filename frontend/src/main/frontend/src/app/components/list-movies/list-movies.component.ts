import { Component, OnInit } from '@angular/core';
import {ShowService} from "../../services/show/show.service";
import {Show} from "../../models/show";
import {Router} from "@angular/router";
import {MovieScreeningCreate} from "../../models/movieScreeningCreate";
import * as moment from 'moment';

@Component({
  selector: 'app-list-movies',
  templateUrl: './list-movies.component.html',
  styleUrls: ['./list-movies.component.css']
})
export class ListMoviesComponent implements OnInit {
  
  movies : Array<Show>;
  projections: Array<MovieScreeningCreate>;

  constructor(private showService: ShowService, private router: Router) {
    this.showService.getAllShows().subscribe(data =>{
      this.movies = (data as Array<Show>).filter(item => item.movie);
      //this.movies = this.movies.filter(item => item.id);
      //console.log(this.movies);
    });

    this.showService.getPerformances("movie").subscribe(data => {
      this.projections = data as Array<MovieScreeningCreate>;
      console.log(this.projections);
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

}
