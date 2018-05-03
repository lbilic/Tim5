import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { JwtService } from './services/jwt.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  providers: [JwtService],
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor()
  {

  }
}
