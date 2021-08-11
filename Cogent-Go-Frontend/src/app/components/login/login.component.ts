import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {
    email: null,
    password: null
  }
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';


  constructor(private gs: GoServiceService) { }

  ngOnInit(): void {
    if (this.gs.getToken()) {
      this.isLoggedIn = true;

    }
  }

  onSubmit(): void {
    const { email, password } = this.form;

    this.gs.login(this.form).subscribe(
      data => {
        this.gs.saveToken(data.accessToken);
        this.gs.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;

        //this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }
}
