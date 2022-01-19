import { Component, OnInit } from '@angular/core';
import {ContactEmergency} from "../../entity/contact-emergency";
import {ContactReference} from "../../entity/contact-reference";
import {RegisterService} from "../../services/register.service";
import { Router } from '@angular/router';
@Component({
  selector: 'app-step6',
  templateUrl: './step6.component.html',
  styleUrls: ['./step6.component.css']
})
export class Step6Component implements OnInit {
  contactReference:ContactReference = new ContactReference();
  contactEmergency: ContactEmergency = new ContactEmergency();
  constructor(public registerService: RegisterService,  public router: Router) { }

  ngOnInit(): void {
  }
  saveStep6() {
    this.registerService.setContactReference(this.contactReference);
    this.registerService.setContactEmergency(this.contactEmergency);
  }
  step6Register() {
    console.log(this.registerService.getRegUser());
    console.log(this.registerService.getBasicInfo());
    console.log(this.registerService.getContactInfo());
    console.log(this.registerService.getCarInfo());
    console.log(this.registerService.getResidentialStatus());
    console.log(this.contactReference);
    console.log(this.contactEmergency);
  }

}
