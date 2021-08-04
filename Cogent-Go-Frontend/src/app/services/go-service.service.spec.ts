import { TestBed } from '@angular/core/testing';

import { GoServiceService } from './go-service.service';

describe('GoServiceService', () => {
  let service: GoServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GoServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
