import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;

  constructor(private token: GoServiceService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
  }
}
