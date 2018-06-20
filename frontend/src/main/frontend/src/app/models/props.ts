export class Props{

  constructor(public id : number, public name : string,
              public price : number, public description: string, public amount: number,public  version:number,
              role:number = -1
              , public date: Date = new Date(2100-1-1)
){}
}
