import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/common/product';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent implements OnInit {

  productList: Product[];

  constructor(private gs: GoServiceService) { }

  ngOnInit(): void {
    this.viewProducts();
  }
  
  viewProducts()
  {
    this.gs.getProducts().subscribe(data=>{
      this.productList = data;
    })
  }
}
