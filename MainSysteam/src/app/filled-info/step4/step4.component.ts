import { Component, OnInit } from '@angular/core';
import {HireService} from "../../services/hire.service";
import{ alldata} from 'src/app/PersonalnfoEntity/Info'
@Component({
  selector: 'app-step4',
  templateUrl: './step4.component.html',
  styleUrls: ['./step4.component.css']
})
export class step4Component implements OnInit {

  public data: alldata = new alldata();
  constructor(private service: HireService) { }

  ngOnInit(): void {

    this.data = this.service.getAlldata();
  

  }

}
