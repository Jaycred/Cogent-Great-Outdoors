import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-user-add-component',
  templateUrl: './user-add-component.component.html',
  styleUrls: ['./user-add-component.component.css']
})
export class UserAddComponentComponent implements OnInit {

  message: string;
  constructor(private gs: GoServiceService) { }

  ngOnInit(): void {
  }

  processForm(userForm: any){
    this.gs.addUser(JSON.stringify(userForm.value)).subscribe(data=>{this.message=data});
  }

}
