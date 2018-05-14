import {CineterCreate} from "./cineterCreate";
import {Account} from "./account";
import {Login} from "../shared/models/login";

export class CineterAdminCreate extends Account{

  constructor( name: string,  lastName: string,  email: string,
               password: string, public number:string,  username: string, public isFanZone : boolean,
              public cineter : CineterCreate, public changedPassword : boolean = false, public confirmed: boolean = false)
  {
    super(new Login(username, password), name, lastName, email);
  }
}
