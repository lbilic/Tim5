import {Account} from "./account";
import {Props} from "./props";

export class PropsRequest{

  constructor(public id: number, public account: Account, public props : Props){}
}
