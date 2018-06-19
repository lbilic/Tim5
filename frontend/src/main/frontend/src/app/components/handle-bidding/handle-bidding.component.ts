import { Component, OnInit } from '@angular/core';
import {PropsService} from "../../services/props/props.service";
import {Bid} from "../../models/bid";

@Component({
  selector: 'app-handle-bidding',
  templateUrl: './handle-bidding.component.html',
  styleUrls: ['./handle-bidding.component.css']
})
export class HandleBiddingComponent implements OnInit {

  bids : Array<Bid>;

  constructor(private propsService: PropsService) {
    this.propsService.bidsForMyProp().subscribe(data =>{
      console.log(data);
      this.bids = data as Array<Bid>;
    });
  }

  ngOnInit() {
  }

  Accept(id,  prop_id){
    console.log(id,);
    this.propsService.acceptBid(id, prop_id).subscribe();
  }

}
