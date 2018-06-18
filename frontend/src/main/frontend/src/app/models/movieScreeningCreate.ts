import {HallCreate} from "./hallCreate";

export class MovieScreeningCreate{

  constructor (public date: Date, public price : number, public type: string,
               public hall: HallCreate){}
}
