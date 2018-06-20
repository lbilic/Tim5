
export class ShowCreate{

  constructor(public id: number, public name : string, public description: string,
              public isMovie: boolean, public director : string,
              public runtime: string, public genre : string,
              public actors : string, version? : number){}
}
