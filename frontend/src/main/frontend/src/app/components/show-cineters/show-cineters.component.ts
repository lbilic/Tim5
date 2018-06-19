import {Component, OnInit} from '@angular/core';
import {CineterService} from "../../services/cineter/cineter.service";
import {Cineter} from "../../models/cineter";
import {JwtService} from "../../core/services/jwt.service";
import {Router} from "@angular/router";
import {RateService} from "../../services/rate/rate.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {RateModalComponent} from "../rate-modal/rate-modal.component";

@Component({
  selector: 'app-show-cineters',
  templateUrl: './show-cineters.component.html',
  styleUrls: ['./show-cineters.component.css']
})
export class ShowCinetersComponent implements OnInit {

  cineters: Array<Cineter>;
  changeText: string;
  bsModalRef: BsModalRef;

  constructor(private rateService: RateService, private modalService: BsModalService, private cineterService: CineterService, private jwtService: JwtService, private router: Router) {
    this.changeText = this.jwtService.hasRole('ADMIN') ? "Change" : "Details";

    this.cineterService.getAllCineters().subscribe(data => {
      this.cineters = data as Array<Cineter>;

      for (let i of this.cineters) this.canRate(i);
    });
  }

  ngOnInit() {
  }

  ChangeCineter(i) {
    this.router.navigate([`/cineters/${i.id}`]);
  }
  AddMovie(i) {
    this.router.navigate([`/add_movie`]);
  }
  AddShow(i) {
    this.router.navigate([`/add_show`]);
  }


  DeleteCineter(i) {
    this.cineterService.deleteCineter(i).subscribe(data => {
      let index = this.cineters.indexOf(i);
      this.cineters.splice(index, 1);
    });
  }


  ShowProps(i) {
    this.router.navigate([`/user_view_props/${i.id}`])
  }

  SellProps(i) {
    this.router.navigate([`/sell_prop/${i.id}`])
  }

  canRate(i: Cineter) {
    this.rateService.canRate(i.id).subscribe(result =>{
      i.canRate = true;
    }, err => i.canRate = false);
  }
}
