import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PerformanceCreate} from "../../models/performanceCreate";

@Injectable()
export class AddPerformanceService {

  constructor(private http : HttpClient) {
  }

  registerPerformance(performance : PerformanceCreate, id, time)
  {
    return this.http.post('http://localhost:8080/api/admin/create_performance?id='+id+'&time='+time, performance);
  }


}
