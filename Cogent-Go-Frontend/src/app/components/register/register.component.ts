import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {
    firstName: null,
    lastName: null,
    email: null,
    password: null,
    phoneNumber: null,
    addressLine1: null,
    addressLine2: null,
    state: null,
    pincode: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private as: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const {firstName, lastName, email, password, phoneNumber, addressLine1,
            addressLine2, state, pincode} = this.form;

    this.as.register(firstName, lastName, email, password, phoneNumber, addressLine1,
      addressLine2, state, pincode).subscribe(
      data => {
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }
}
