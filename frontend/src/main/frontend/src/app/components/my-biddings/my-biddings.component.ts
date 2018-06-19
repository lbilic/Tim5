import { Component, OnInit } from '@angular/core';
import {Bid} from "../../models/bid";
import {PropsService} from "../../services/props/props.service";

@Component({
  selector: 'app-my-biddings',
  templateUrl: './my-biddings.component.html',
  styleUrls: ['./my-biddings.component.css']
})
export class MyBiddingsComponent implements OnInit {

  bids : Array<Bid>;

  constructor(private propsService: PropsService) {
    this.propsService.myBiddings().subscribe(data =>{
      this.bids = data as Array<Bid>;
    });
  }


  ngOnInit() {
  }

}
