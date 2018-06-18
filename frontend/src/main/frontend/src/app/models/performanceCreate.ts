import {HallCreate} from "./hallCreate";

export class PerformanceCreate{

  constructor (public date: Date, public price : number,
                public hall: HallCreate){}
}
