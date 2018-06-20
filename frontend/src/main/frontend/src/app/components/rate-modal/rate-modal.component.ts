import {Component, EventEmitter, OnInit} from '@angular/core';
import {RateService} from "../../services/rate/rate.service";
import {BsModalRef} from "ngx-bootstrap";

@Component({
  selector: 'app-rate-modal',
  templateUrl: './rate-modal.component.html',
  styleUrls: ['./rate-modal.component.css']
})
export class RateModalComponent implements OnInit {

  name : string;
  rate : number;
  id : number;
  rateSubmited : EventEmitter<any>;
  type : string = "cineter";

  constructor(public bsModal : BsModalRef, private rateService: RateService) {

  }

  ngOnInit() {
  }

  Rate()
  {
    if(this.type == "cineter") {
      this.rateService.rateCineter(this.id, this.rate).subscribe(result => {
        this.rateSubmited.emit(null);
      });
    }
    else {

      this.rateService.rateShow(this.id, this.rate).subscribe(result => {
        this.rateSubmited.emit(null);
      });
    }
  }
}
