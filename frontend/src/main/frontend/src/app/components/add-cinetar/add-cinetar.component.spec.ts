import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCinetarComponent } from './add-cinetar.component';

describe('AddCinetarComponent', () => {
  let component: AddCinetarComponent;
  let fixture: ComponentFixture<AddCinetarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCinetarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCinetarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
