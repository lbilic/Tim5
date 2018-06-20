import { AbstractControl, ValidationErrors } from "@angular/forms";

export class ScaleValidator {

  constructor() { }

  static scaleValidator(control: AbstractControl) : ValidationErrors | null {
    //if((control.value as string).indexOf(' ') != -1)
    //  return { scaleValidator : true };

      if(!(control.value as string).match('^[0-9]*$'))
        return { scaleValidator : true };

    return null;
  }
}
