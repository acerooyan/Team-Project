import { Component, OnInit } from '@angular/core';
import {BasicInfo} from "../../entity/basic-info";
import {RegisterService} from "../../services/register.service";

import { Router } from '@angular/router';

@Component({
  selector: 'app-step2',
  templateUrl: './step2.component.html',
  styleUrls: ['./step2.component.css']
})
export class Step2Component implements OnInit {
  basicInfo: BasicInfo = new BasicInfo();
  constructor(public registerService: RegisterService, public router: Router) { }
  
  selectedFiles?: FileList;
  
  
 
 
  ngOnInit(): void {

  }
  saveBasicInfo() {
    this.registerService.setBasicInfo(this.basicInfo);
    this.registerService.setAvatar(this.selectedFiles);
  }
  basicInfoRegister() {
    
    console.log(this.basicInfo);
   

  }

  changePage() {
    this.router.navigate(["/regnav/step3"]);
  }


  selectFile(event: any): void {
    
    this.selectedFiles = event.target.files;
  }

  
}
