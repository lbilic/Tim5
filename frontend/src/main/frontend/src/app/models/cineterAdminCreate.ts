import {CineterCreate} from "./cineterCreate";

export class CineterAdminCreate{

  constructor(public name: string, public lastName: string, public email: string,
              public password: string, public number:string, public username: string, public isFanZone : boolean,
              public cineter : CineterCreate, public changedPassword : boolean = false, public confirmed: boolean = false){}
}
