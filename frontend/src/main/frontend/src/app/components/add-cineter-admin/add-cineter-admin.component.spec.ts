import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCineterAdminComponent } from './add-cineter-admin.component';

describe('AddCineterAdminComponent', () => {
  let component: AddCineterAdminComponent;
  let fixture: ComponentFixture<AddCineterAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCineterAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCineterAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
