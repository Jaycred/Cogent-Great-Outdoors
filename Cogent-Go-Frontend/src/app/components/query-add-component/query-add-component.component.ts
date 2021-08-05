import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';

@Component({
  selector: 'app-query-add-component',
  templateUrl: './query-add-component.component.html',
  styleUrls: ['./query-add-component.component.css']
})
export class QueryAddComponentComponent implements OnInit {

  message: string;
  constructor(private gs: GoServiceService) { }

  ngOnInit(): void {
  }

  processForm(pform: any){
    this.gs.addQuery(JSON.stringify(pform.value)).subscribe(data=>{this.message=data});
  }

}
