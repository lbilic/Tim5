import { Component, OnInit } from '@angular/core';
import {ShowService} from "../../services/show/show.service";
import { JwtService } from "../../core/services/jwt.service";
import {Show} from "../../models/show";


@Component({
  selector: 'app-list-shows',
  templateUrl: './list-shows.component.html',
  styleUrls: ['./list-shows.component.css']
})
export class ListShowsComponent implements OnInit {


  shows : Array<Show>;

  constructor(private showService: ShowService, jwtutils :JwtService) {
    console.log(jwtutils.decodeToken());
    this.showService.getAllShows().subscribe(data =>{
      this.shows = data as Array<Show>;
    });
  }

  ngOnInit() {
  }

}
