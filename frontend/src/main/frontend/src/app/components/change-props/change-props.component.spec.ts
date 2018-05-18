import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangePropsComponent } from './change-props.component';

describe('ChangePropsComponent', () => {
  let component: ChangePropsComponent;
  let fixture: ComponentFixture<ChangePropsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangePropsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangePropsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
