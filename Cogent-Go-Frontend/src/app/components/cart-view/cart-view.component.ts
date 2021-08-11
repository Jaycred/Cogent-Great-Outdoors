import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/app/common/cart';
import { GoServiceService } from 'src/app/services/go-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cart-view',
  templateUrl: './cart-view.component.html',
  styleUrls: ['./cart-view.component.css']
})
export class CartViewComponent implements OnInit {

  cart: Cart;
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
