import {HallCreate} from "./hallCreate";

export class PerformanceCreate{

  constructor (public id: number, public date: Date, public price : number,
                public hall: number, public fastReservationSeats : string){}
}
