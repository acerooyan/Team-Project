import { Component, OnInit } from '@angular/core';
import { ContactInfo} from "../../entity/contact-info";
import { BasicInfo} from "../../entity/basic-info";
import { RegisterService} from "../../services/register.service";
import { Router } from '@angular/router';
import { Address} from "../../entity/address";
import { AddressList} from "../../entity/address-list";

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

  addressList : any[] = [{
    address:Address
  }];
  // saveAddressList() {
  //   this.registerService.setAddressList(this.addressList);
  // }


  saveContactInfo() {
    this.registerService.setContactInfo(this.contactInfo);
  }
  contactInfoRegister(){
    console.log(this.registerService.getRegUser());
    console.log(this.registerService.getBasicInfo());
    console.log(this.contactInfo);
    // console.log(this.addressList);
  }
  changePage() {
    this.router.navigate(["/regnav/step4"]);
  }
  //addAddress() {
  //     this.addresses.push({
  //       address: '',
  //       street: '',
  //       city: '',
  //       country: ''
  //     });
  //   }
  addAddress() {
    this.contactInfo.addressDaoList.push({
      address:Address
    });
  }
  removeAddress(i: number) {
    this.contactInfo.addressDaoList.splice(i, 1);
  }
}
