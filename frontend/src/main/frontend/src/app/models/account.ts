import { Login } from "./login"

export class Account{

  constructor(public loginAccount : Login, public name : string, public lastName : string, public email : string){}
}
