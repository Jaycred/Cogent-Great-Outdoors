import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cart } from 'src/app/common/cart';
import { Product } from 'src/app/common/product';
import { User } from 'src/app/common/user';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-product-specs',
  templateUrl: './product-specs.component.html',
  styleUrls: ['./product-specs.component.css']
})
export class ProductSpecsComponent implements OnInit {

  id: number;
  product: Product;
  message:string;
  constructor(private gs: GoServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.id = +this.route.snapshot.paramMap.get("id");
    this.findById(this.id);
    })
  }

  findById(id: number)
  {
    this.gs.getProductsById(id).subscribe(data=>{
      this.product = data[0];
    });
  }

  addToCart(){
    
  }

}
