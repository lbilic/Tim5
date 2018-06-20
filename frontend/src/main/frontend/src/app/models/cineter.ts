export class Cineter {
  constructor(public id : number, public name : string,
              public address : string, public city: string,
              public isTheater: boolean, public canRate : boolean = false,public rate = 0){}
}
