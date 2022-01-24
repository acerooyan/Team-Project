import { Component, OnInit } from '@angular/core';
import {HireService} from "../../services/hire.service";
import{ alldata} from 'src/app/PersonalnfoEntity/Info'
@Component({
  selector: 'app-step5',
  templateUrl: './step5.component.html',
  styleUrls: ['./step5.component.css']
})
export class step5Component implements OnInit {

  public data: alldata = new alldata();
  constructor(private service: HireService) { }

  ngOnInit(): void {

    this.data = this.service.getAlldata();
  

  }

}
