import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-order-add-component',
  templateUrl: './order-add-component.component.html',
  styleUrls: ['./order-add-component.component.css']
})
export class OrderAddComponentComponent implements OnInit {

  message: string;
  constructor(private gs: GoServiceService) { }

  ngOnInit(): void {
  }

  processForm(pform: any){
    this.gs.addOrder(JSON.stringify(pform.value)).subscribe(data=>{this.message=data});
  }

}
