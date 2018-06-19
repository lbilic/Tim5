import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPerformancesComponent } from './list-performances.component';

describe('ListPerformancesComponent', () => {
  let component: ListPerformancesComponent;
  let fixture: ComponentFixture<ListPerformancesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListPerformancesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListPerformancesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
