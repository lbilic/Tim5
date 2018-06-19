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

  reserveProp(id, version){
    return this.http.get(`http://localhost:8080/api/props/reserve_props?id=${id}&version=${version}`);
  }

  sellProp(id, prop){
    return this.http.post('http://localhost:8080/api/props/send_props_request?id='+id, prop);
  }

  myProps(){
    return this.http.get('http://localhost:8080/api/props/my_props');
  }

  boughtProps(){
    return this.http.get('htt://localhost:8080/api/props/bought_props');
  }

  bidForProp(id, price, version){
    return this.http.get(`http://localhost:8080/api/props/bid_for_prop?prop_id=${id}&price=${price}&version=${version}`);
  }

  bidsForMyProp(){
    return this.http.get('http://localhost:8080/api/props/bids_for_my_prop');
  }

  acceptBid(id, prop_id){
    return this.http.get(`http://localhost:8080/api/props/accept_bid?id=${id}&prop_id=${prop_id}`);
  }

  myBiddings(){
    return this.http.get('http://localhost:8080/api/props/my_biddings');
  }

  deleteUserProp(id){
    return this.http.get('http://localhost:8080/api/props/delete_user_prop?id='+id);
  }

}
