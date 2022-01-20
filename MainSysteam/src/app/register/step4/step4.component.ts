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
  constructor(public registerService: RegisterService,  public router: Router) { }
  saveCarInfo() {
    this.registerService.setCarInfo(this.carInfo);
  }
  ngOnInit(): void {
  }
  haveDriverLicense() {
    if (this.carInfo.driverlicense == "yes") {
      return true;
    } else {
      return false;
    }
  }
  carInfoRegister(){
    console.log(this.registerService.getRegUser());
    console.log(this.registerService.getBasicInfo());
    console.log(this.registerService.getContactInfo());
    console.log(this.carInfo);
  }
  changePage() {
    this.router.navigate(["/regnav/step5"]);
  }
}
