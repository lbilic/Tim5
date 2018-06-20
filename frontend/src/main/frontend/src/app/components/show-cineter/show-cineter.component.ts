import { Component, OnInit } from '@angular/core';
import {Cineter} from "../../models/cineter";
import {JwtService} from "../../core/services/jwt.service";
import {CineterService} from "../../services/cineter/cineter.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-show-cineter',
  templateUrl: './show-cineter.component.html',
  styleUrls: ['./show-cineter.component.css']
})
export class ShowCineterComponent implements OnInit {

  cineter: Cineter;

  constructor(private cineterService: CineterService,
              private jwtService: JwtService, private router: Router) {

    this.cineterService.getCineterByAccount().subscribe(data => {
      this.cineter = data as Cineter;

    });
  }

  ngOnInit() {
  }

  Change(i) {
    this.router.navigate([`/cineters/${this.cineter.id}`]);
  }
  AddMovie(i) {
    this.router.navigate([`/add_movie`]);
  }
  AddShow(i) {
    this.router.navigate([`/add_show`]);
  }

  // dodati izlistavanje filmova i predstava
  ViewAllShows(i){
      this.router.navigateByUrl('movies/'+this.cineter.id);

  }
  ViewAllMovies(i){
    this.router.navigateByUrl('movies/'+this.cineter.id);
  }
}
