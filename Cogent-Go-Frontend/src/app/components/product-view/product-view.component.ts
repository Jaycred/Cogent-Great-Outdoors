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
  id: number;
  productList: Product[];

  constructor(private gs: GoServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.id = +this.route.snapshot.paramMap.get("id");
      this.categoryName = this.route.snapshot.paramMap.get("categoryName");
      if(this.categoryName.length != 0) this.findByCategory(this.categoryName);
      //viewProducts or findByName based on keyword value
      else if(this.id != 0) this.findById(this.id);
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

  findById(id: number)
  {
    this.gs.getProductsById(id).subscribe(data=>{
      this.productList = data;
    })
  }
}
