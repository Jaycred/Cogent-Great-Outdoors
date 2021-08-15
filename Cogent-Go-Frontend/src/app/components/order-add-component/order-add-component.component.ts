import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-order-add-component',
  templateUrl: './order-add-component.component.html',
  styleUrls: ['./order-add-component.component.css']
})
export class OrderAddComponentComponent implements OnInit {

  orderId: number;
  order: any;
  message: string;
  constructor(private gs: GoServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  processForm(pform: any){
    this.gs.addOrder(JSON.stringify(pform.value)).subscribe(data=>{this.message=data});
  }

}
