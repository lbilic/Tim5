import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {Show} from "../../models/show";
import {ShowService} from "../../services/show/show.service";
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import * as $ from "jquery";

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
  projectionDates : Array<NgbDateStruct>;

  id : number;
  show : Show;
  rows = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'];
  cols = [1, 2, 3, 4, 5, 6, 7, 8];
  reserved = ['A2', 'A3', 'C5', 'C6', 'C7', 'C8', 'J1', 'J2', 'J3', 'J4'];
  selected = [];

  constructor(private showService : ShowService, private route : ActivatedRoute) {
    this.route.params.subscribe((param: Params) => {
      this.id = param['id'];
      this.getShow();
    });
    this.projectionDates = new Array<NgbDateStruct>();
    this.minDate = { day: this.now.getUTCDate(), month: this.now.getUTCMonth() + 1, year: this.now.getUTCFullYear()};
    this.projectionDates.push({ day: this.now.getUTCDate(), month: this.now.getUTCMonth() + 1, year: this.now.getUTCFullYear()});
    this.projectionDates.push({ day: this.now.getUTCDate()+3, month: this.now.getUTCMonth() + 1, year: this.now.getUTCFullYear()});
    this.projectionDates.push({ day: this.now.getUTCDate()+7, month: this.now.getUTCMonth() + 1, year: this.now.getUTCFullYear()});
  }

  getShow(){
    this.showService.getShowById(this.id).subscribe(data =>{
      this.show = data as Show;
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

  hasProjection(date: NgbDateStruct) {
    const d = new Date(date.year, date.month - 1, date.day);
    var sel = { day: d.getUTCDay(), month: d.getUTCMonth() + 1, year: d.getUTCFullYear()}
    return $.inArray(sel, this.projectionDates);
  }

  pickDate(date: NgbDateStruct, event: MouseEvent) {
    event.preventDefault();
    event.stopPropagation();

    this.model = date;
  }



  ngOnInit() {
  }

}
