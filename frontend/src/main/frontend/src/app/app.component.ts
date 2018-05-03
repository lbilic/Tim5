import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Router } from "@angular/router";
import { Title } from "@angular/platform-browser";
import { JwtService } from "./core/services/jwt.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  providers: [JwtService],
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private jwtService: JwtService, private router: Router, private titleService: Title) {
      this.titleService.setTitle('ISA/MRS');
    }
}
