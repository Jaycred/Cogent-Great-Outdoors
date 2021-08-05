import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/common/product';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-product-view',
  templateUrl: './category-view.component.html',
  styleUrls: ['./category-view.component.css']
})
export class CategoryViewComponent implements OnInit {

  categoryList: string[] = [
    "Mountaineering Equipment",
    "Camping Equipment",
    "Sports Equipment"
  ];

  constructor(private gs: GoServiceService) { }

  ngOnInit(): void {
    //this.viewProducts();
  }
  
  viewProducts()
  {
    this.gs.getProducts().subscribe(data=>{
      //this.productList = data;
    })
  }
}
