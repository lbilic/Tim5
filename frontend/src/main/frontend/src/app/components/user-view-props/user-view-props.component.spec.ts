import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserViewPropsComponent } from './user-view-props.component';

describe('UserViewPropsComponent', () => {
  let component: UserViewPropsComponent;
  let fixture: ComponentFixture<UserViewPropsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserViewPropsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserViewPropsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
