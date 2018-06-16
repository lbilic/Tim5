import { Component, OnInit } from '@angular/core';
import {ShowService} from "../../services/show/show.service";
import {Show} from "../../models/show";
import {Router} from "@angular/router";

@Component({
  selector: 'app-list-movies',
  templateUrl: './list-movies.component.html',
  styleUrls: ['./list-movies.component.css']
})
export class ListMoviesComponent implements OnInit {

  movies : Array<Show>;

  constructor(private showService: ShowService, private router: Router) {
    this.showService.getAllShows().subscribe(data =>{
      this.movies = (data as Array<Show>).filter(item => item.movie);
      //this.movies = this.movies.filter(item => item.isMovie == false);
      console.log(this.movies);
    });

  }

  ngOnInit() {
  }

  Reserve(i){
    this.router.navigate([`/reserve/${i.id}`])
  }

}
