import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  constructor(private gs: GoServiceService) { }

  ngOnInit(): void {
  }

  processForm(userForm: any){
    this.gs.login(JSON.stringify(userForm.value));
  }
}
