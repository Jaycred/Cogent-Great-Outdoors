import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-view',
  templateUrl: './category-view.component.html',
  styleUrls: ['./category-view.component.css']
})
export class CategoryViewComponent implements OnInit {

  categoryList: string[] = [
    "Mountaineering Equipment",
    "Camping Equipment",
    "Sports Equipment",
    "Sundries"
  ];

  constructor() { }

  ngOnInit(): void {
  }
}
