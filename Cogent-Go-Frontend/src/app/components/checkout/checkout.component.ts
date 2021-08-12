import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/common/product';
import { Order } from 'src/app/common/order';
import { DeliveryAddress } from 'src/app/common/delivery-address';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  order: Order;
  message: string;
  constructor(private gs: GoServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  checkout(user:any, product: any, quantity: number, price: number, pform:any){
    this.order.user = user;
    this.order.product = product;
    this.order.price = price;
    this.order.quantity = quantity;
    this.order.da = pform.value;
    this.gs.addAddress(JSON.stringify(pform.value)).subscribe(data=>{this.message=data});
    this.gs.addOrder(this.order).subscribe(data=>{this.message=data});
  }

}
