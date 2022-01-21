import { Component, OnInit } from '@angular/core';
import {CarInfo} from "../../entity/car-info";
import {RegisterService} from "../../services/register.service";
import { Router } from '@angular/router';
@Component({
  selector: 'app-step4',
  templateUrl: './step4.component.html',
  styleUrls: ['./step4.component.css']
})
export class Step4Component implements OnInit {
  carInfo: CarInfo = new CarInfo();
  selectedFiles?: FileList;
  constructor(public registerService: RegisterService,  public router: Router) { }
  saveCarInfo() {
    this.registerService.setCarInfo(this.carInfo);
    this.registerService.setDriver(this.selectedFiles);
  }
  ngOnInit(): void {
  }
  haveDriverLicense! : String;
  haveLicense() {
    if (this.haveDriverLicense == "yes") {
      return true;
    } else {
      return false;
    }
  }
  carInfoRegister(){

    console.log(this.registerService.getBasicInfo());
    console.log(this.registerService.getContactInfo());
    console.log(this.carInfo);
  }

  selectFile(event: any): void {

    this.selectedFiles = event.target.files;
  }


  changePage() {
    this.router.navigate(["/regnav/step5"]);
  }
}
