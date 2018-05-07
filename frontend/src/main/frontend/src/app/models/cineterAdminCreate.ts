import {CineterCreate} from "./cineterCreate";

export class CineterAdminCreate{

  constructor(public name: string, public lastName: string, public email: string,
              public password: string, public number:string, public cineter : CineterCreate){}
}
