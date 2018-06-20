import { Component, OnInit } from '@angular/core';
import {PropsService} from "../../services/props/props.service";
import {Props} from "../../models/props";
import { JwtService } from "../../core/services/jwt.service";
import {Router, RouterModule} from "@angular/router";
import {ToasterConfig, ToasterService} from "angular5-toaster/dist";

@Component({
  selector: 'app-props',
  templateUrl: './props.component.html',
  styleUrls: ['./props.component.css']
})
export class PropsComponent implements OnInit {

  props : Array<Props>;
  toasterConfig: ToasterConfig;

  constructor(private propsService: PropsService, jwtutils :JwtService, private router: Router,
              private toasterService: ToasterService) {
    this.toasterConfig = new ToasterConfig({timeout: 4000});
      console.log(jwtutils.decodeToken());
      this.propsService.getAllProps().subscribe(data =>{
        this.props = data as Array<Props>;
      });
  }

  ngOnInit() {
  }

  DeleteProp(prop){
    this.propsService.deleteProp(prop).subscribe(data => {
      let index = this.props.indexOf(prop);
      this.props.splice(index, 1);
    });
    this.toasterService.pop('success', 'Success!','You have successfully deleted the prop!');
  }

  ChangeProp(prop: Props) {
    this.router.navigate([`/props/${prop.id}`]);

  }
}
