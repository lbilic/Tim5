import { Component, OnInit } from '@angular/core';
import {ShowService} from "../../services/show/show.service";
import { JwtService } from "../../core/services/jwt.service";
import {Show} from "../../models/show";
import {Router} from "@angular/router";



@Component({
  selector: 'app-list-shows',
  templateUrl: './list-shows.component.html',
  styleUrls: ['./list-shows.component.css']
})
export class ListShowsComponent implements OnInit {


  shows : Array<Show>;

  constructor(private showService: ShowService, jwtutils :JwtService,
              private router: Router) {
    console.log(jwtutils.decodeToken());
    this.showService.getAllShows().subscribe(data =>{
      //this.shows = (data as Array<Show>).filter(item => !item.movie);
      this.shows = data as Array<Show>;
      console.log(this.shows);
    });
  }

  ngOnInit() {
  }

  Delete(i){
    this.showService.deleteShow(i).subscribe(data =>{
      let index = this.shows.indexOf(i);
      this.shows.splice(index, 1);
    });
  }

  Update(i){
    this.router.navigate([`/shows/${i.id}`]);

  }

  AddProjection(i){
    this.router.navigate([`/add_movie_screening/${i.id}`]);

  }

  AddPerformance(i){
    this.router.navigate([`/add_performance/${i.id}`]);

  }

  ListProjections(i){
    this.router.navigate([`/projections/${i.id}`]);

  }

  ListPerformances(i){
    this.router.navigate([`/performances/${i.id}`]);

  }
}
