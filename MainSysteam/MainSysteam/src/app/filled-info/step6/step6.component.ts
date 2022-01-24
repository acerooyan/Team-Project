import { Component, OnInit } from '@angular/core';
import {HireService} from "../../services/hire.service";
import{ alldata} from 'src/app/PersonalnfoEntity/Info'
@Component({
  selector: 'app-step6',
  templateUrl: './step6.component.html',
  styleUrls: ['./step6.component.css']
})
export class step6Component implements OnInit {

  public data: alldata = new alldata();
  constructor(private service: HireService) { }

  ngOnInit(): void {

    this.data = this.service.getAlldata();
   

  }

}
