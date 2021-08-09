import { TestBed } from '@angular/core/testing';
import { TokenStorageService } from '../services/token-storage.service';
import { AuthInterceptor } from './auth-interceptor';

describe('AuthInterceptor', () => {
  let interceptor: AuthInterceptor;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    interceptor = TestBed.inject(AuthInterceptor);
  });

  it('should create an instance', () => {
    expect(interceptor).toBeTruthy();
  });
});
