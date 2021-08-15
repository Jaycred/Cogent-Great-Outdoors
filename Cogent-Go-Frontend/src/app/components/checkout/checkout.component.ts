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
  address: any;
  addressId: number;
  form: any = {
    addressId: 0
  };
  order: any = {user: null, product: null, quantity: 0, price: 0, da: null };
  cartId: number;
  cart: Cart;
  message1: string;
  message2: string;
  constructor(private gs: GoServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.cartId = +this.route.snapshot.paramMap.get("cartId");
      this.gs.getCartsById(this.cartId).subscribe(data=>{this.cart=data[0]});
    });
  }

  addAddress(addressForm:any){
    this.gs.addAddress(JSON.stringify(addressForm.value)).subscribe(data=>{this.message1=data});
  }

  getAddressIdAndCreateOrder(form: any){
    this.addressId = +form.value.addressId;
    console.log(this.addressId);
    this.order.user = this.cart.user;
    this.order.product = this.cart.product;
    this.order.quantity = this.cart.quantity;
    this.order.price = this.cart.price;
    this.gs.getAddressById(this.addressId).subscribe(data=>{this.address=data});
    console.log(this.address.firstName);
    this.order.da = this.address;
  }

  addOrder(){
    this.gs.addOrder(this.order).subscribe(data=>{this.message2=data});
  }

}
