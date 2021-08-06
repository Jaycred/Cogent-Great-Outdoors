import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/common/product';

@Component({
  selector: 'app-product-specs',
  templateUrl: './product-specs.component.html',
  styleUrls: ['./product-specs.component.css']
})
export class ProductSpecsComponent implements OnInit {

  product: Product;
  constructor() { }

  ngOnInit(): void {
  }

}
