import {Component, EventEmitter, OnInit} from '@angular/core';
import {PropsService} from "../../services/props/props.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Props} from "../../models/props";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {BidModalComponent} from "../bid-modal/bid-modal.component";
import {JwtService} from "../../core/services/jwt.service";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";

@Component({
  selector: 'app-user-view-props',
  templateUrl: './user-view-props.component.html',
  styleUrls: ['./user-view-props.component.css']
})
export class UserViewPropsComponent implements OnInit {

  props : Array<Props> = [];
  id : number;
  prop : Props;
  modal : BsModalRef;
  toasterConfig: ToasterConfig;

  constructor(private route : ActivatedRoute, private propsService: PropsService, private modalService : BsModalService,
              private jwtService: JwtService, private toasterService: ToasterService) {

    this.route.params.subscribe((param: Params) => {
      this.id = param['id'];
      this.getProps();
    });
    this.toasterConfig = new ToasterConfig({timeout: 4000});
  }

  getProps(){
    this.propsService.getAllPropsById(this.id).subscribe(data =>{
      this.props = data as Array<Props>;
      console.log(this.props);
    });
  }

  Reserve(i){
    this.propsService.reserveProp(i.id, i.version).subscribe(data =>{
      this.prop = data as Props;
    });
    this.toasterService.pop('success', 'Success!','Thank you for reserving this prop!');
  }

  Bid(i){
    this.modal = this.modalService.show(BidModalComponent);
    this.modal.content.prop = i;
    this.toasterService.pop('success', 'Success!','Thank you for bidding for this prop!');
  }

  ngOnInit() {
  }

}
