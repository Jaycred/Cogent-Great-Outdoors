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

  form: any = {
    quantity: null,
    productId: null,
    cartId: null
  }

  message:string;

  /*
  id: number;
  cart: Cart;
  product: Product;
  quantity: number;
  cartId: number;
*/

  //userId: number;
  cartList: Cart[];
  constructor(private gs: GoServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      /*
      this.cartId = +this.route.snapshot.paramMap.get("cartId");
      this.userId = +this.route.snapshot.paramMap.get("userId");
      */
      this.viewCarts();
    });
  }

  changeCart(productId: number, quantity: string, cartId: number){
    const newQuantity = parseInt(quantity);
    this.gs.changeCart(productId, newQuantity, cartId).subscribe(data=>{this.message=data;
   this.reloadPage()});
  }

  deleteCart(cartId: number){
    this.gs.deleteCart(cartId).subscribe(data=>{this.message=data;
   this.reloadPage()});
  }
  

  viewCarts(){
    this.gs.getCartsByUserId(this.gs.getUser().id).subscribe(data => {this.cartList = data});
  }

  reloadPage(): void
  {
    window.location.reload();
  }
  /*
  findById(cartId: number){
    this.gs.getCartsById(this.cartId).subscribe(data => {this.cartList = data;});
  }

  findByUserId(userId: number){
    this.gs.getCartsByUserId(this.userId).subscribe(data => {this.cartList = data;})
  }
  */

}
