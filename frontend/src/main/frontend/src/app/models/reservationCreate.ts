import { MovieScreeningCreate } from "./movieScreeningCreate";

export class ReservationCreate{
    constructor(public id: number, public screening : MovieScreeningCreate, public total_price : number) {}
}