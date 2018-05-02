import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PropsCreate} from "../../models/propsCreate";

@Injectable()
export class PropsService {

  constructor(private http: HttpClient) {
  }

  registerProps(props: PropsCreate) {
    console.log(props.cineterId + " " + props.price + " " + props.description)
    return this.http.post('http://localhost:8080/api/admin/create_props', props);

  }
}
