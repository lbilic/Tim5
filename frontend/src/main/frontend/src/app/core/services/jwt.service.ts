import { Injectable } from '@angular/core';
import { JwtHelper } from 'angular2-jwt';
// model
import { Token } from "../../shared/models/token";


@Injectable()
export class JwtService {
  private jwtHelper: JwtHelper;

  constructor() {
    this.jwtHelper = new JwtHelper();
  }

  tokenExist(): boolean {
    return !!(localStorage.getItem('Authentication-Token'));
  }

  isTokenExpired(): boolean {
    return this.jwtHelper.isTokenExpired(this.getToken());
  }

  hasRole(role : string){
    return this.decodeToken().roles.indexOf(role) != -1;
  }

  getUsernameFromToken(): string {
    let tokenDecoded: Token = this.decodeToken();
    return tokenDecoded.sub;
  }

  getIdFromToken(): number {
    let tokenDecoded: Token = this.decodeToken();
    return tokenDecoded.id;
  }

  getToken(): string {
    return localStorage.getItem('Authentication-Token');
  }

  setToken(token: string): void {
    localStorage.setItem('Authentication-Token', token);
  }

  removeToken(): void {
    localStorage.removeItem('Authentication-Token');
  }

  public decodeToken(): Token {
    return this.jwtHelper.decodeToken(this.getToken());
  }
}
