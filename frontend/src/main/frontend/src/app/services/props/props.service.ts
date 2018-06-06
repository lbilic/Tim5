import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PropsCreate} from "../../models/propsCreate";
import {Props} from "../../models/props";
import {Cineter} from "../../models/cineter";

@Injectable()
export class PropsService {

  constructor(private http: HttpClient) {
  }

  registerProps(props: PropsCreate) {
    return this.http.post('http://localhost:8080/api/admin/create_props', props);
  }

  getAllProps(){
    return this.http.get('http://localhost:8080/api/props/get_all');
  }

  changeProps(props: Props){
    return this.http.post('http://localhost:8080/api/props/change_prop', props);
  }

  findProp(id){
    return this.http.get('http://localhost:8080/api/props/find_prop?id='+ id);
  }

  deleteProp(prop){
    return this.http.post('http://localhost:8080/api/props/delete_prop', prop);
  }

  getAllPropsById(id){
    return this.http.get('http://localhost:8080/api/props/user_view_props?id=' + id);
  }

  reserveProp(id){
    return this.http.get('http://localhost:8080/api/props/reserve_props?id='+id);
  }

  sellProp(id, prop){
    return this.http.post('http://localhost:8080/api/props/send_props_request?id='+id, prop);
  }

}
