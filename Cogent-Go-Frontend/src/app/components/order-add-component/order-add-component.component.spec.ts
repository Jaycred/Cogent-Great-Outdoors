import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderAddComponentComponent } from './order-add-component.component';

describe('OrderAddComponentComponent', () => {
  let component: OrderAddComponentComponent;
  let fixture: ComponentFixture<OrderAddComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrderAddComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderAddComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
