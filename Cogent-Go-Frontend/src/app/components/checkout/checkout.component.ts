import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/common/product';
import { Order } from 'src/app/common/order';
import { DeliveryAddress } from 'src/app/common/delivery-address';
import { Cart } from 'src/app/common/cart';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  address: any = {addressLine1: null, addressLine2: null, state: null, pincode: null, order: null};
  order: any = {user: null, product: null, quantity: 0, price: 0, da: null };
  cartId: number;
  cart: Cart;
  message: string;
  constructor(private gs: GoServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.cartId = +this.route.snapshot.paramMap.get("cartId");
      this.gs.getCartsById(this.cartId).subscribe(data=>{this.cart=data[0]});
    });

  }

  checkout(addressForm:any){
    this.order.user = this.cart.user;
    this.order.product = this.cart.product;
    this.order.quantity = this.cart.quantity;
    this.order.price = this.cart.price;
    this.order.da = JSON.stringify(addressForm.value);
    this.gs.addOrder(JSON.stringify(this.order)).subscribe(data=>{this.message=data});
    addressForm.value.order = this.order;
    this.gs.addAddress(JSON.stringify(addressForm.value)).subscribe(data=>{this.message=data});
  }

}
