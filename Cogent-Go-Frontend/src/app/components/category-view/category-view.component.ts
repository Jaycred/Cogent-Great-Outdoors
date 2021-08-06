import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-view',
  templateUrl: './category-view.component.html',
  styleUrls: ['./category-view.component.css']
})
export class CategoryViewComponent implements OnInit {

  categoryList: string[] = [
    "MountaineeringEquipment",
    "CampingEquipment",
    "SportsEquipment",
    "Sundries"
  ];

  constructor() { }

  ngOnInit(): void {
  }
}
