import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from "@angular/common/http";
// validator
import { PasswordValidator } from "./validators/password.validator";
import { UsernameUniqueValidator } from "./validators/username-unique.validator";

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
  ],
  providers: [
      PasswordValidator,
      UsernameUniqueValidator,
  ],
  declarations: [],
  exports: [
  ]
})
export class SharedModule { }
