import { Component, OnInit } from '@angular/core';
import { BsModalService } from "ngx-bootstrap";
import { Router } from "@angular/router";
import { Validators, FormBuilder, FormGroup } from "@angular/forms";
import { FriendsService } from "../../services/friends/friends.service"
import { Account } from "../../models/account";

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
  constructor(private fb: FormBuilder, private router: Router, 
              private friendsService: FriendsService) {
    this.form = this.fb.group({
      username: ['']
    });
    this.friendsService.getAllFriends().subscribe(data =>{
      this.friends = data as Array<Account>;
    });
    this.friendsService.getAllRequests().subscribe(data =>{
      this.requests = data as Array<Account>;
    });
  console.log(this);
   }

  ngOnInit() {
  }

  get username() {
    return this.form.get('username');
  }

  sendRequest() {
    let sender = this.username.value;
    this.friendsService.sendRequest(sender).subscribe(data => {
    });
  }

  accept(username : String) {
    this.friendsService.acceptRequest(username).subscribe(data => {
    });
  }

  decline(username : String) {
    this.friendsService.declineRequest(username).subscribe(data => {
    });
  }

  remove(username : String) {
    this.friendsService.removeFriend(username).subscribe(data => {
    });
  }

}
