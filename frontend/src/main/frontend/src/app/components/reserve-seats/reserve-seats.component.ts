import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {Show} from "../../models/show";
import {ShowService} from "../../services/show/show.service";
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import * as $ from "jquery";
import {ReservationService} from "../../services/reservation/reservation.service";
import {ReservationCreate} from "../../models/reservationCreate";

@Component({
  selector: 'app-reserve-seats',
  templateUrl: './reserve-seats.component.html',
  styleUrls: ['./reserve-seats.component.css']
})
export class ReserveSeatsComponent implements OnInit {

  model: NgbDateStruct;
  date: {year: number, month: number};
  minDate : NgbDateStruct;
  now = new Date();

  id : number;
  show : Show;
  rows = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'W', 'V', 'X', 'Y', 'Z'];
  cols = [1, 2, 3, 4, 5, 6, 7, 8];
  reserved = ['A2', 'A3', 'C5', 'C6', 'C7', 'C8', 'J1', 'J2', 'J3', 'J4'];
  selected = [];
  type: string;
  reservations: Array<ReservationCreate>;

  constructor(private showService : ShowService, private route : ActivatedRoute, private reservationService: ReservationService) {
    this.route.params.subscribe((param: Params) => {
      this.id = param['id'];
      //this.getShow();
    });
    this.getReservations(this.id);
  }

  getShow(){
    this.showService.getShowById(this.id).subscribe(data =>{
      this.show = data as Show;
      this.reserved = this.reserved.concat(this.show.fastReservationSeats.split(","));
      console.log(this.reserved)
    });
  }

  seatClicked(seatPos) {
    console.log("Selected Seat: " + seatPos);
    var index = this.selected.indexOf(seatPos);
    if(index != -1) {
      // seat already selected, remove
      this.selected.splice(index, 1)
    } else {
      // new seat, push
      this.selected.push(seatPos);
    }
  }

  getStatus(seatPos) {
    if(this.reserved.indexOf(seatPos) > -1) {
      return 'reserved';
    } else if(this.selected.indexOf(seatPos) > -1) {
      return 'selected';
    }
  }

  clearSelected() {
    this.selected = [];
  }

  showSelected() {
    if(this.selected.length > 0) {
      alert("Selected Seats: \n" + this.selected);
    } else {
      alert("No seats selected!");
    }
  }

  getReservations(id) {
    this.reservationService.getAll(this.id).subscribe(data => {
      this.reservations = data as Array<ReservationCreate>;
    });
    console.log(this.reservations);
    //let r = this.reservations.screening.hall.rows;
    //let c = this.reservations[0].screening.hall.columns;
    //this.rows = 
   }



  ngOnInit() {
  }

}
