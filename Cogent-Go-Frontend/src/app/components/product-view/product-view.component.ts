import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/common/product';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent implements OnInit {

  categoryName: string;
  productList: Product[];

  constructor(private gs: GoServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.categoryName = this.route.snapshot.paramMap.get("categoryName");
      if(this.categoryName) this.findByCategory(this.categoryName);
      else this.viewProducts();
    })
  }
  
  viewProducts()
  {
    this.gs.getProducts().subscribe(data=>{
      this.productList = data;
    })
  }

  findByCategory(cName: string)
  {
    this.gs.getProductsByCategory(cName).subscribe(data=>{
      this.productList = data;
    })
  }
}
