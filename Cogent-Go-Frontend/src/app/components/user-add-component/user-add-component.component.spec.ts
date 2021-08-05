import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAddComponentComponent } from './user-add-component.component';

describe('UserAddComponentComponent', () => {
  let component: UserAddComponentComponent;
  let fixture: ComponentFixture<UserAddComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserAddComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAddComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
