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
  bool : boolean = true;

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

    });
  }
  get name(){
    return this.form.get('name');
  }

  get description(){
    return this.form.get('description');
  }


  ngOnInit() {
  }

  register(){
    this.show.registerShow(new ShowCreate(this.name.value,
      this.description.value, this.bool)).subscribe((data) =>{
      console.log(data);
    });
  }

}
