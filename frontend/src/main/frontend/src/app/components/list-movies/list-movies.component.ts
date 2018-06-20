import {Component, OnInit} from '@angular/core';
import {ShowService} from "../../services/show/show.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {MovieScreeningCreate} from "../../models/movieScreeningCreate";
import * as moment from 'moment';
import {ShowCreate} from "../../models/showCreate";
import {PerformanceCreate} from "../../models/performanceCreate";
import {MovieScreeningService} from "../../services/movie-screening/movie-screening.service";

@Component({
  selector: 'app-list-movies',
  templateUrl: './list-movies.component.html',
  styleUrls: ['./list-movies.component.css']
})
export class ListMoviesComponent implements OnInit {

  projections: Array<Array<MovieScreeningCreate>>;
  movies: Array<ShowCreate>;


  performances: Array<PerformanceCreate>;
  cineter_id: number;
  id: number;

  constructor(private showService: ShowService, private router: Router,
              private route: ActivatedRoute, private movieScreeningService: MovieScreeningService) {

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
    this.projections = new Array<Array<MovieScreeningCreate>>();
    this.route.params.subscribe((param: Params) => {
      this.id = param['id'];
    });

    // lista filmova u odnosu na cineter id
    this.showService.getShowsByCineterId(this.id).subscribe(data => {
      this.movies = data as Array<ShowCreate>;
      for (let i of this.movies) {
        this.movieScreeningService.getProjectionsByMovieId(i.id).subscribe(result => {
          this.projections.push(result as Array<MovieScreeningCreate>);
        });
      }
      console.log(this.projections);
    });


  }

  ngOnInit() {
  }

  Reserve(id){
    this.router.navigate([`/reserve/id`])
  }

  getDate(i) {
    return (moment().add(i, 'days').format("DD.MM."));
  }

  checkDate(date, i) {
    return moment(date).isSame(moment().add(i, 'days'), 'day');
  }

  getTime(date) {
    return moment(date).format("HH:mm");
  }

   getPerformances(id){

  }

}
