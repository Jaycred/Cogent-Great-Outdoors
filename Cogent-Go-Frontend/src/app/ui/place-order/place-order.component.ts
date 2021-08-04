import { Component, OnInit } from '@angular/core';
import { GoService } from 'src/app/service/order.services';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.css']
})
export class PlaceOrderComponent implements OnInit {

  message: string = '';
  constructor(private gs: GoService) { }

  ngOnInit(): void {
  }

  processForm(prodForm: any)
  {
    this.gs.placeOrder(JSON.stringify(prodForm.value)).subscribe(data => {
      this.message = data;
      console.log(this.message);
    });
  }

}
