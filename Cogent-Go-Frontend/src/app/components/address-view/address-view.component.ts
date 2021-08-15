import { Component, OnInit } from '@angular/core';
import { GoServiceService } from 'src/app/services/go-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-address-view',
  templateUrl: './address-view.component.html',
  styleUrls: ['./address-view.component.css']
})
export class AddressViewComponent implements OnInit {

  constructor(private gs: GoServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

}
