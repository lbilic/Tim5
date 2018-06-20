import {Component, OnInit, ViewChild} from '@angular/core';
import {Cineter} from "../../models/cineter";
import {ActivatedRoute, Params} from "@angular/router";
import {CineterService} from "../../services/cineter/cineter.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AgmMap} from "@agm/core";
import {JwtService} from "../../core/services/jwt.service";

@Component({
  selector: 'app-cineter-details',
  templateUrl: './cineter-details.component.html',
  styleUrls: ['./cineter-details.component.css']
})
export class CineterDetailsComponent implements OnInit {
  cineter : Cineter;
  id : number;
  form : FormGroup;
  returnURL: string = '';
  @ViewChild(AgmMap) map: AgmMap;
  latitude: number;
  longitude: number;

  constructor(private fb: FormBuilder, private route : ActivatedRoute,
              private cineterService: CineterService, private router: Router, private jwtService : JwtService) {
    this.cineter = new Cineter(0, '', '', '', false);
    this.route.params.subscribe((param: Params) => {
      this.id = param['id'];
      this.getCineter();
    });

    this.form = this.fb.group({
      name: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],
      address: ['', [
        Validators.required,
        Validators.minLength(5)

      ]],
      city: ['', [
        Validators.required,
      ]],

    });
  }

  ngOnInit() {
    if (this.jwtService.hasRole('CINETER_ADMIN'))
      this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/cineter';
    else
      this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/cineters';
  }


  get name() {
    return this.form.get('name');
  }

  get address() {
    return this.form.get('address');
  }

  get city() {
    return this.form.get('city');
  }

  private getCineter() {
    this.cineterService.getCineterById(this.id).subscribe(data => {
      this.cineter = data as Cineter;

      this.getMap();

    });
  }

  getMap(){
    this.cineterService.getLocation(this.cineter.city, this.cineter.address).subscribe(response =>{
      if (response.status != 'OK') return;
      this.latitude = response.results[0].geometry.location.lat;
      this.longitude = response.results[0].geometry.location.lng;
      this.map.triggerResize();

    });
  }

  clicked()
  {
    this.cineter.isTheater = !this.cineter.isTheater;

    console.log(this.cineter.isTheater);
  }

  private change()
  {
    //Iz nekog razloga dodaje se polje theater u klasu cineter (samo od sebe) pa ga na ovaj nacin uklanjam.
    delete this.cineter['theater'];
    console.log(this.cineter);
    this.cineterService.updateCineter(this.cineter).subscribe(data =>{
      this.router.navigateByUrl(this.returnURL);
    });
  }
}
