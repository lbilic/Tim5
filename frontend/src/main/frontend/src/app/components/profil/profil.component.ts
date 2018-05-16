import { Component, OnInit } from '@angular/core';
import { BsModalService } from "ngx-bootstrap";
import { Router } from "@angular/router";

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css'],
  providers: [BsModalService]
})
export class ProfilComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

}
