import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';


@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrls: ['./user-account.component.css']
})
export class UserAccountComponent implements OnInit {

  isLoggedIn: boolean = false;

  constructor(private gs: GoServiceService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.gs.getToken();
  }

  signOut()
  {
    this.gs.signOut();
  }

}
