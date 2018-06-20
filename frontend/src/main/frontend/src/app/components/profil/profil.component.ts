import { Component, OnInit } from '@angular/core';
import { BsModalService } from "ngx-bootstrap";
import { Router } from "@angular/router";
import { Validators, FormBuilder, FormGroup } from "@angular/forms";
import { FriendsService } from "../../services/friends/friends.service"
import { Account } from "../../models/account";
import { ToasterConfig, ToasterService } from "angular5-toaster/dist";
import {AccountService} from "../../services/account/account.service";
import {ReservationService} from "../../services/reservation/reservation.service";
import { AppError } from "../../shared/errors/app-error";
import { BadRequestError } from "../../shared/errors/bad-request-error";
import { NotFoundError } from "../../shared/errors/not-found-error";
import { ForbiddenError } from "../../shared/errors/forbidden-error";
import * as moment from "moment";

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css'],
  providers: [BsModalService]
})
export class ProfilComponent implements OnInit {
  friends: Array<any>;
  form: FormGroup;
  requests: Array<any>;
  toasterConfig: ToasterConfig;
  account: Account;
  movieReservations: Array<any>;
  showReservations: Array<any>;
  //reservations: Array<any>;
  pastMovieReservations: Array<any>;
  currentMovieReservations: Array<any>;
  pastShowReservations: Array<any>;
  currentShowReservations: Array<any>;

  constructor(private fb: FormBuilder, private router: Router,
              private friendsService: FriendsService, private toasterService: ToasterService,
              private accountService: AccountService, private reservationService: ReservationService) {
    this.form = this.fb.group({
      username: ['']
    });
    this.updateFriends();
    this.updateRequests();
    this.updateReservations();
    this.toasterConfig = new ToasterConfig({timeout: 4000});
    accountService.getCurrentUser().subscribe(data => {
      this.account = data as Account;
    });
   }

  ngOnInit() {
  }

  updateFriends() {
    this.friendsService.getAllFriends().subscribe(data =>{
      this.friends = data as Array<Account>;
    });
  }

  updateRequests() {
    this.friendsService.getAllRequests().subscribe(data =>{
      this.requests = data as Array<Account>;
    });
  }

  get username() {
    return this.form.get('username');
  }

  sendRequest() {
    let sender = this.username.value;
    this.friendsService.sendRequest(sender).subscribe(data => {
    }, (error: AppError) => {
        if (error instanceof BadRequestError)
          this.toasterService.pop('error', 'Error','Invalid format of given data!');
        else if (error instanceof ForbiddenError)
          this.toasterService.pop('error', 'Error', 'Account not confirmed!');
        else if (error instanceof NotFoundError)
          this.toasterService.pop('error', 'Error', 'User doesn\'t exist!');
        else {
          this.toasterService.pop('error', 'Error', 'Something unexpected happened! \nSee information about error in console.');
          throw error;
        }
    });
  }

  accept(username : String) {
    this.friendsService.acceptRequest(username).subscribe(data => {
      this.updateFriends();
      this.updateRequests();
      this.toasterService.pop('success', 'Request accepted', 'You are now friends with ' + username);
    });
  }

  decline(username : String) {
    this.friendsService.declineRequest(username).subscribe(data => {
      this.updateRequests();
      this.toasterService.pop('success', 'Request declined', 'Friend request from ' + username + ' declined.');
    });
  }

  remove(username : String) {
    this.friendsService.removeFriend(username).subscribe(data => {
      this.updateFriends();
      this.toasterService.pop('success', 'Friend removed',  username + ' removed from friends.');
    });
  }

  updateReservations() {
    this.reservationService.getAllMovieReservations().subscribe(data => {
      this.movieReservations = data as Array<any>;
      this.pastMovieReservations = this.movieReservations.filter(item => moment().isAfter(item.screening.date));
      this.currentMovieReservations = this.movieReservations.filter(item => !moment().isAfter(item.screening.date));
      //let d = this.movieReservations.pop().screening.date;
      //console.log(d, moment().isBefore(d));
    });

    this.reservationService.getAllShowReservations().subscribe(data => {
      this.showReservations = data as Array<any>;
      this.pastShowReservations = this.showReservations.filter(item => moment().isAfter(item.performance.date));
      this.currentShowReservations = this.showReservations.filter(item => !moment().isAfter(item.performance.date));
    });
  }

}
