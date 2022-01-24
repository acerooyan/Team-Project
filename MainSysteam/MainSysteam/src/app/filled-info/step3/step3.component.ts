import { Component, OnInit } from '@angular/core';
import{ alldata} from 'src/app/PersonalnfoEntity/Info'
import {HireService} from "../../services/hire.service";
@Component({
  selector: 'app-step3',
  templateUrl: './step3.component.html',
  styleUrls: ['./step3.component.css']
})
export class step3Component implements OnInit {

  public data: alldata = new alldata();
  constructor(private service: HireService) { }

  ngOnInit(): void {

    this.data = this.service.getAlldata();
   

  }


}
