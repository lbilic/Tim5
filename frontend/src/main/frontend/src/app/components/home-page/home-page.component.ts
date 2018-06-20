import { Component, OnInit } from '@angular/core';
import { JwtService } from "../../services/jwt.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(protected jwtService: JwtService) {}

  ngOnInit() {
  }

}
