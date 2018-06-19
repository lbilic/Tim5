import {Component, OnInit} from '@angular/core';
import {CineterService} from "../../services/cineter/cineter.service";
import {Cineter} from "../../models/cineter";
import { JwtService } from "../../core/services/jwt.service";
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

  cineters : Array<Cineter>;
  changeText : string;
  modalRef : BsModalRef;
  constructor(private modalService : BsModalService, private rateService : RateService,private cineterService: CineterService,private jwtService :JwtService, private router: Router) {
    this.changeText =
      this.jwtService.hasRole('ADMIN')  ? "Change" : "Details";


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

  AddShow(i){
    this.router.navigate([`/add_show`])
  }

  AddMovie(i){
    this.router.navigate([`/add_movie`])
  }

  ShowProps(i){
    this.router.navigate([`/user_view_props/${i.id}`])
  }

  SellProps(i){
    this.router.navigate([`/sell_prop/${i.id}`])
  }

  canRate(i :Cineter){
    this.rateService.canRate(i.id).subscribe(result =>{
      console.log('dobro');
      i.isRated = false;
    }, error2 => {
      console.log('lose');
      i.isRated = true;
    });
  }


  Rate(cineter : Cineter) {
    this.modalRef = this.modalService.show(RateModalComponent);
    this.modalRef.content.name = (cineter.name);
    this.modalRef.content.id = cineter.id;
    this.modalRef.content.rateSubmited.subscribe(result =>{
      this.modalRef.hide();
      location.reload();
    })
  }
}
