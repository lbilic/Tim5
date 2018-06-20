export class PropsCreate{

  constructor(public name : string, public price : number, public description: string, public amount: number,
              public cineterId: number
    , public date: Date = new Date(2100-1-1)){}
}
