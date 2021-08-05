import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddressAddComponentComponent } from './address-add-component.component';

describe('AddressAddComponentComponent', () => {
  let component: AddressAddComponentComponent;
  let fixture: ComponentFixture<AddressAddComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddressAddComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddressAddComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
