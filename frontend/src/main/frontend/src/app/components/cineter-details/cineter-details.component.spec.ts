import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CineterDetailsComponent } from './cineter-details.component';

describe('CineterDetailsComponent', () => {
  let component: CineterDetailsComponent;
  let fixture: ComponentFixture<CineterDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CineterDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CineterDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
