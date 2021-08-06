import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/app/common/cart';
import { User } from 'src/app/common/user';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-cart-view',
  templateUrl: './cart-view.component.html',
  styleUrls: ['./cart-view.component.css']
})
export class CartViewComponent implements OnInit {

  cartList: Cart[];

  constructor(private gs: GoServiceService) { }

  ngOnInit(): void {
    this.getCart();
  }

  getCart()
  {
    /*
    this.gs.getCart(getCurrentUser().id).subscribe(data=>{
    this.cartList = data;
    });
    */
  }
}
