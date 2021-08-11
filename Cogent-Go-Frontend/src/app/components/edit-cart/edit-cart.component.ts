import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/app/common/cart';
import { GoServiceService } from 'src/app/services/go-service.service';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/common/product';

@Component({
  selector: 'app-edit-cart',
  templateUrl: './edit-cart.component.html',
  styleUrls: ['./edit-cart.component.css']
})
export class EditCartComponent implements OnInit {

  id: number;
  cart: Cart;
  product: Product;
  quantity: number;
  message:string;

  cartId: number;
  userId: number;
  cartList: Cart[];
  constructor(private gs: GoServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.cartId = +this.route.snapshot.paramMap.get("cartId");
      this.userId = +this.route.snapshot.paramMap.get("userId");
      if(this.cartId != 0) this.findById(this.cartId);
      else if(this.userId != 0) this.findByUserId(this.userId);
      else this.viewCarts();
    });
  }


  changeCart(){
    this.gs.changeCart(this.product.productId, this.quantity, this.cart.cartId).subscribe(data=>{this.message=data});
  }

  deleteCart(){
    this.gs.deleteCart(this.cart.cartId).subscribe(data=>{this.message=data});
  }

  

  viewCarts(){
    this.gs.getCarts().subscribe(data => {this.cartList = data;});
  }

  findById(cartId: number){
    this.gs.getCartsById(this.cartId).subscribe(data => {this.cartList = data;});
  }

  findByUserId(userId: number){
    this.gs.getCartsByUserId(this.userId).subscribe(data => {this.cartList = data;})
  }
}
