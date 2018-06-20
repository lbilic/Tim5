import { Component, OnInit } from '@angular/core';
import { JwtService } from "../../services/jwt.service";
import { CineterCreate } from "../../models/cineterCreate"
import { CineterService } from "../../services/cineter/cineter.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  cineters: Array<CineterCreate>;

  constructor(protected jwtService: JwtService, private cineterService: CineterService) {
    this.cineterService.getAllCineters().subscribe(data =>{
      this.cineters = data as Array<CineterCreate>;
      console.log(this.cineters);
    });
  }

  ngOnInit() {
  }

}
