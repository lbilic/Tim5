import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeShowComponent } from './change-show.component';

describe('ChangeShowComponent', () => {
  let component: ChangeShowComponent;
  let fixture: ComponentFixture<ChangeShowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeShowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
