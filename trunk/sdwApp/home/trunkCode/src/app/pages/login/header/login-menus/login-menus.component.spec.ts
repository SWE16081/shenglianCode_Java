import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginMenusComponent } from './login-menus.component';

describe('LoginMenusComponent', () => {
  let component: LoginMenusComponent;
  let fixture: ComponentFixture<LoginMenusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginMenusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginMenusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
