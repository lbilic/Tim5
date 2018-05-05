import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Injectable } from "@angular/core";
import { JwtService } from "../services/jwt.service";

@Injectable()
export class AnonymusGuard implements CanActivate {

  constructor(private router: Router, private jwtService: JwtService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if(!this.jwtService.tokenExist() || this.jwtService.isTokenExpired()) return true;

    // user is already logged in so redirect him to home page
    this.router.navigate(['add_show']);
    return false;
  }
}
