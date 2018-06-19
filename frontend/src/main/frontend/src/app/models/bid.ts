import {Account} from "./account";
import {Props} from "./props";

export class Bid{

  constructor(public id : number, public price : number,
              public bidder: Account, public prop: Props
  ){}
}
