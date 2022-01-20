import { Component, OnInit } from '@angular/core';
import {ContactInfo} from "../../entity/contact-info";
import {BasicInfo} from "../../entity/basic-info";
import {RegisterService} from "../../services/register.service";
import { Router } from '@angular/router';
@Component({
  selector: 'app-step3',
  templateUrl: './step3.component.html',
  styleUrls: ['./step3.component.css']
})
export class Step3Component implements OnInit {
  contactInfo: ContactInfo = new ContactInfo();
  constructor(public registerService: RegisterService,  public router: Router) { }

  ngOnInit(): void {
  }
  saveContactInfo() {
    this.registerService.setContactInfo(this.contactInfo);
  }
  contactInfoRegister(){
    
    console.log(this.registerService.getBasicInfo());
    console.log(this.contactInfo);
  }
  changePage() {
    this.router.navigate(["/regnav/step4"]);
  }

}
