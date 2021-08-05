import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-address-add-component',
  templateUrl: './address-add-component.component.html',
  styleUrls: ['./address-add-component.component.css']
})
export class AddressAddComponentComponent implements OnInit {

  message: string;
  constructor(private gs: GoServiceService) { }

  ngOnInit(): void {
  }

  processForm(pform: any){
    this.gs.addAddress(JSON.stringify(pform.value)).subscribe(data=>{this.message=data});
  }

}
