import {Component, OnInit} from '@angular/core';
import {CineterService} from "../../services/cineter/cineter.service";
import {Cineter} from "../../models/cineter";
import { JwtService } from "../../core/services/jwt.service";
import {Router} from "@angular/router";
@Component({
  selector: 'app-show-cineters',
  templateUrl: './show-cineters.component.html',
  styleUrls: ['./show-cineters.component.css']
})
export class ShowCinetersComponent implements OnInit {

  cineters : Array<Cineter>;

  constructor(private cineterService: CineterService, jwtutils :JwtService, private router: Router) {
    this.cineterService.getAllCineters().subscribe(data =>{
      this.cineters = data as Array<Cineter>;
    });
  }

  ngOnInit() {
  }

  ChangeCineter(i){
    this.router.navigate([`/cineters/${i.id}`]);
  }

  DeleteCineter(i){
    this.cineterService.deleteCineter(i).subscribe(data =>{
        let index = this.cineters.indexOf(i);
        this.cineters.splice(index, 1);
    });
  }

  ShowProps(i){
    this.router.navigate([`/user_view_props/${i.id}`])
  }

  SellProps(i){
    this.router.navigate([`/sell_prop/${i.id}`])
  }


}
