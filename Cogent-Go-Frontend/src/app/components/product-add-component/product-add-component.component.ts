import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-product-add-component',
  templateUrl: './product-add-component.component.html',
  styleUrls: ['./product-add-component.component.css']
})
export class ProductAddComponentComponent implements OnInit {
  message: string;
  constructor(private gs: GoServiceService) { }

  ngOnInit(): void {
  }

  processForm(pform: any){
    this.gs.addProduct(JSON.stringify(pform.value)).subscribe(data=>{this.message=data});
  }

}
