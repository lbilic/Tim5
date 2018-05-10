import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
// service
import { JwtService } from "../../core/services/jwt.service";
import { AuthService } from "../../core/services/auth.service";
import { BsModalService } from "ngx-bootstrap";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  providers: [JwtService, AuthService, BsModalService]
})
export class NavbarComponent {
  constructor(protected jwtService: JwtService, private authService: AuthService,
              private router: Router) { }

  logout() {
    this.authService.logout();
    this.router.navigate(['login']);
  }

  notOnHomeRoute(): boolean {
    return this.router.url !== '/home';
  }

  notOnRegisterBuildingRoute(): boolean{
    return this.router.url !== '/register';
  }
}
