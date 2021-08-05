import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QueryAddComponentComponent } from './query-add-component.component';

describe('QueryAddComponentComponent', () => {
  let component: QueryAddComponentComponent;
  let fixture: ComponentFixture<QueryAddComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QueryAddComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QueryAddComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
