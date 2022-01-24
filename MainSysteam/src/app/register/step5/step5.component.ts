import { Component, OnInit } from '@angular/core';
import {ResidentialStatus} from "../../entity/residential-status";
import {RegisterService} from "../../services/register.service";
import { Router } from '@angular/router';
@Component({
  selector: 'app-step5',
  templateUrl: './step5.component.html',
  styleUrls: ['./step5.component.css']
})
export class Step5Component implements OnInit {
  residentialStatus: ResidentialStatus = new ResidentialStatus();
  selectedFiles?: FileList;


  constructor(public registerService: RegisterService,  public router: Router) { }

  ngOnInit(): void {
  }
  saveResidentialStatus() {
    this.registerService.setResidentialStatus(this.residentialStatus);
    this.registerService.setWorkFile(this.selectedFiles);
  }
  residentialStatusRegister() {
    
    console.log(this.registerService.getBasicInfo());
    console.log(this.registerService.getContactInfo());
    console.log(this.registerService.getCarInfo());
    console.log(this.residentialStatus);
  }
  isCitizenOrResident() {
    if (this.residentialStatus.isCitizenOrResident == "yes") {
      return true;
    } else {
      return false;
    }
  }
  changePage() {
    this.router.navigate(["/regnav/step6"]);
  }

  selected() {
    if (this.residentialStatus.isCitizenOrResident == "yes" || this.residentialStatus.isCitizenOrResident == "no") {
      return true;
    } else {
      return false;
    }
  }
  selectFile(event: any): void {
    
    this.selectedFiles = event.target.files;
  }
}
