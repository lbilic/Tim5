export class MovieScreeningCreate{

  constructor (public id : number, public date: Date, public price : number, public type: string,
               public hall: number, public fastReservationSeats : string){}
}
