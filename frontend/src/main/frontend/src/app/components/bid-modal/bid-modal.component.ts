import { Component, OnInit } from '@angular/core';
import {Props} from "../../models/props";
import {BsModalRef} from "ngx-bootstrap";
import {PropsService} from "../../services/props/props.service";

@Component({
  selector: 'app-bid-modal',
  templateUrl: './bid-modal.component.html',
  styleUrls: ['./bid-modal.component.css']
})
export class BidModalComponent implements OnInit {
  prop: Props;
  price: number;
  constructor(public bsModal : BsModalRef, private propsService: PropsService) { }

  ngOnInit() {
  }


  sendOffer(){
    this.propsService.bidForProp(this.prop.id, this.price, this.prop.version).subscribe(a => this.bsModal.hide());
  }
}
