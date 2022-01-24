import { Component, OnInit, Input} from '@angular/core';
import {HireService} from "../../services/hire.service";
import{ alldata} from 'src/app/PersonalnfoEntity/Info'
@Component({
  selector: 'app-step2',
  templateUrl: './step2.component.html',
  styleUrls: ['./step2.component.css']
})
export class step2Component implements OnInit {
  public data: alldata = new alldata();
  constructor(private service: HireService) { }

  ngOnInit(): void {

    this.data = this.service.getAlldata();
   

  }

}
