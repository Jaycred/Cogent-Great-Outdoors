import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: any = {
    email: null,
    password: null,
    firstName: null,
    lastName: null,
    phoneNumber: null,
    addressLine1: null,
    addressLine2: null,
    state: null,
    pincode: 0
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private gs: GoServiceService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const {email, password, firstName, lastName, phoneNumber, addressLine1, addressLine2, state, pincode } = this.form;

    this.gs.register(email, password, firstName, lastName, phoneNumber, addressLine1, addressLine2, state, pincode).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

}
