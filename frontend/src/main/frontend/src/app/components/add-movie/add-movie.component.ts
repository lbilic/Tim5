import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ShowService} from "../../services/show/show.service";
import {ShowCreate} from "../../models/showCreate";

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent implements OnInit {

  form : FormGroup;

  constructor(private fb: FormBuilder, private show: ShowService) {
    this.form = this.fb.group({
      name: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],
      description: ['', [
        Validators.required,
        Validators.minLength(5)
      ]],

      stars: ['', [
        Validators.required,
      ]],

      genre: ['', [
        Validators.required,
      ]],

      director: ['', [
        Validators.required,
      ]],

      runtime: ['', [
        Validators.required,
      ]],

    });
  }
  get name(){
    return this.form.get('name');
  }

  get description(){
    return this.form.get('description');
  }
  get stars(){
    return this.form.get('stars');
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
  }

  register(){
    this.show.registerShow(new ShowCreate(this.name.value,
      this.description.value, true, this.director.value, this.runtime.value,
      this.genre.value, this.stars.value)).subscribe((data) =>{
      console.log(data);
    });
  }

}
