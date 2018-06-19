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

  constructor(public bsModal : BsModalRef, private rateService: RateService) {

  }

  ngOnInit() {
  }

  Rate()
  {
    this.rateService.rate(this.id, this.rate).subscribe(result => {
        this.rateSubmited.emit(null);
    });
  }
}
