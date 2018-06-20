import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class SystemService {
  private readonly urlBase = '/api';

  constructor(private http: HttpClient) { }

  public update(scale){
    return this.http.post('http://localhost:8080/api/admin/update_scale', scale);
  }

  public getScale() {
    return this.http.get('http://localhost:8080/api/admin/get_scale');
  }

}
