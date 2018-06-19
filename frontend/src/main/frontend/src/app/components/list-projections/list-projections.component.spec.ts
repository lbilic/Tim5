import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProjectionsComponent } from './list-projections.component';

describe('ListProjectionsComponent', () => {
  let component: ListProjectionsComponent;
  let fixture: ComponentFixture<ListProjectionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListProjectionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListProjectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
