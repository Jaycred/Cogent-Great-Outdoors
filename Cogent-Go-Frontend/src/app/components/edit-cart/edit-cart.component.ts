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
  constructor(private gs: GoServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  changeCart(){
    this.gs.changeCart(this.product.productId, this.quantity, this.cart.cartId).subscribe(data=>{this.message=data});
  }

  deleteCart(){
    this.gs.deleteCart(this.cart.cartId).subscribe(data=>{this.message=data});
  }

}
