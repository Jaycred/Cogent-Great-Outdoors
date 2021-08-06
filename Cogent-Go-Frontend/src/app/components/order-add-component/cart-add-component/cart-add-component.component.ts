import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';
@Component({
  selector: 'app-cart-add-component',
  templateUrl: './cart-add-component.component.html',
  styleUrls: ['./cart-add-component.component.css']
})
export class CartAddComponentComponent implements OnInit {

  message: string;
  constructor(private gs: GoServiceService) { }

  ngOnInit(): void {
  }

  processForm(pform: any){
    this.gs.addCart(JSON.stringify(pform.value)).subscribe(data=>{this.message=data});
  }

}
