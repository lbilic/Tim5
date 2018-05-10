import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Account} from "../../models/account";

@Injectable()
export class AccountService {

  constructor(private http : HttpClient) { }

  save(account : Account)
    {
        return this.http.post('http://localhost:8080/api/admin/register', account);
    }

}
