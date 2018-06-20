import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ShowService} from "../../services/show/show.service";
import {ShowCreate} from "../../models/showCreate";
import {ActivatedRoute, Router} from "@angular/router";
import {JwtService} from '../../services/jwt.service';

@Component({
  selector: 'app-add-show',
  templateUrl: './add-show.component.html',
  styleUrls: ['./add-show.component.css']
})
export class AddShowComponent implements OnInit {

  form : FormGroup;
  returnURL: string= "";
  constructor(private fb: FormBuilder, private show: ShowService,
              private router: Router, private jwtService : JwtService,
              private route : ActivatedRoute) {
    this.form = this.fb.group({
      name: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],

      description: ['', [
        Validators.required,
        Validators.minLength(5)
      ]],

      actors: ['', [
        Validators.required
      ]],

      genre: ['', [
        Validators.required
      ]],

      director: ['', [
        Validators.required
      ]],

      runtime: ['', [
        Validators.required
      ]],



    });

  }
  get name(){
    return this.form.get('name');
  }

  get description(){
    return this.form.get('description');
  }

  get actors(){
    return this.form.get('actors');
  }

  get genre(){
    return this.form.get('genre');
  }

  get runtime(){
    return this.form.get('runtime');
  }

  get director(){
    return this.form.get('director');
  }


  ngOnInit() {
    if (this.jwtService.hasRole('CINETER_ADMIN'))
      this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/cineter';
  }

  register(){
    this.show.registerShow(new ShowCreate(null, this.name.value,
      this.description.value, false, this.director.value, this.runtime.value,
      this.genre.value, this.actors.value)).subscribe((data) =>{
        this.router.navigateByUrl(this.returnURL);
    });
  }

}
