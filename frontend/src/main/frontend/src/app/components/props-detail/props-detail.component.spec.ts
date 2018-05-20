import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PropsDetailComponent } from './props-detail.component';

describe('PropsDetailComponent', () => {
  let component: PropsDetailComponent;
  let fixture: ComponentFixture<PropsDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PropsDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropsDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
