import {Component, OnInit} from '@angular/core';
import {CineterService} from "../../services/cineter/cineter.service";
import {Cineter} from "../../models/cineter";
import { JwtService } from "../../core/services/jwt.service";
@Component({
  selector: 'app-show-cineters',
  templateUrl: './show-cineters.component.html',
  styleUrls: ['./show-cineters.component.css']
})
export class ShowCinetersComponent implements OnInit {

  cineters : Array<Cineter>;

  constructor(private cineterService: CineterService, jwtutils :JwtService) {
    console.log(jwtutils.decodeToken());
    this.cineterService.getAllCineters().subscribe(data =>{
      this.cineters = data as Array<Cineter>;
    });
  }

  ngOnInit() {
  }

}
