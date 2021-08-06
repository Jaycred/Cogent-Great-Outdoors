import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductSpecsComponent } from './product-specs.component';

describe('ProductSpecsComponent', () => {
  let component: ProductSpecsComponent;
  let fixture: ComponentFixture<ProductSpecsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductSpecsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductSpecsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
