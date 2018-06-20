import { Component, OnInit } from '@angular/core';
import {PropsService} from "../../services/props/props.service";
import {Bid} from "../../models/bid";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";

@Component({
  selector: 'app-handle-bidding',
  templateUrl: './handle-bidding.component.html',
  styleUrls: ['./handle-bidding.component.css']
})
export class HandleBiddingComponent implements OnInit {

  bids : Array<Bid>;
  toasterConfig: ToasterConfig;

  constructor(private propsService: PropsService, private toasterService: ToasterService) {
    this.propsService.bidsForMyProp().subscribe(data =>{
      console.log(data);
      this.bids = data as Array<Bid>;
    });
    this.toasterConfig = new ToasterConfig({timeout: 4000});
  }

  ngOnInit() {
  }

  Accept(id,  prop_id){
    console.log(id,);
    this.propsService.acceptBid(id, prop_id).subscribe();
    this.toasterService.pop('success', 'Success!','You have successfully accepted the bid!');

  }

}
