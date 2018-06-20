import {Component, EventEmitter, OnInit} from '@angular/core';
import {ShowService} from "../../services/show/show.service";
import { JwtService } from "../../core/services/jwt.service";
import {Show} from "../../models/show";
import {Router} from "@angular/router";
import {RateService} from "../../services/rate/rate.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {RateModalComponent} from "../rate-modal/rate-modal.component";



@Component({
  selector: 'app-list-shows',
  templateUrl: './list-shows.component.html',
  styleUrls: ['./list-shows.component.css']
})
export class ListShowsComponent implements OnInit {


  shows : Array<Show>;
  bsModalRef : BsModalRef;
  constructor(private showService: ShowService, jwtutils :JwtService,
              private router: Router, private rateSertvice: RateService,private modalService : BsModalService) {
    console.log(jwtutils.decodeToken());
    this.showService.getAllShows().subscribe(data =>{
      //this.shows = (data as Array<Show>).filter(item => !item.movie);
      this.shows = data as Array<Show>;
      for(let i of this.shows)
        this.rateSertvice.getShowRate(i.id).subscribe(result => i.rate = result.rate);
    });
  }

  ngOnInit() {
  }

  Delete(i){
    this.showService.deleteShow(i).subscribe(data =>{
      let index = this.shows.indexOf(i);
      this.shows.splice(index, 1);
    });
  }

  Update(i){
    this.router.navigate([`/shows/${i.id}`]);

  }

  AddProjection(i){
    this.router.navigate([`/add_movie_screening/${i.id}`]);

  }

  AddPerformance(i){
    this.router.navigate([`/add_performance/${i.id}`]);

  }

  ListProjections(i){
    this.router.navigate([`/projections/${i.id}`]);

  }

  ListPerformances(i){
    this.router.navigate([`/performances/${i.id}`]);

  }

  Rate(i : Show){
    this.bsModalRef = this.modalService.show(RateModalComponent);
    console.log(i.id);
    this.bsModalRef.content.id = i.id;
    this.bsModalRef.content.name = i.name;
    this.bsModalRef.content.type = "show";
    let a : EventEmitter<any> = new EventEmitter();

    a.subscribe(result =>{
      this.bsModalRef.hide();
      window.location.reload();
    });
    this.bsModalRef.content.rateSubmited = a;
  }
}
