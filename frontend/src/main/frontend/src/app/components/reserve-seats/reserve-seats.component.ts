import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {Props} from "../../models/props";
import {Show} from "../../models/show";
import {ShowService} from "../../services/show/show.service";

@Component({
  selector: 'app-reserve-seats',
  templateUrl: './reserve-seats.component.html',
  styleUrls: ['./reserve-seats.component.css']
})
export class ReserveSeatsComponent implements OnInit {

  id : number;
  show : Show;

  constructor(private showService : ShowService, private route : ActivatedRoute) {
    this.route.params.subscribe((param: Params) => {
      this.id = param['id'];
      this.getShow();
    });
  }

  getShow(){
    this.showService.getShowById(this.id).subscribe(data =>{
      this.show = data as Show;
    });
  }

  ngOnInit() {
  }

}
