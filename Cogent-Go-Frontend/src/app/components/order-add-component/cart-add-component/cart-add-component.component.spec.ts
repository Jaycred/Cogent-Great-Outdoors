import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartAddComponentComponent } from './cart-add-component.component';

describe('CartAddComponentComponent', () => {
  let component: CartAddComponentComponent;
  let fixture: ComponentFixture<CartAddComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CartAddComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CartAddComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
