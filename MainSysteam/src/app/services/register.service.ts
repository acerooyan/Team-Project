import { Injectable } from '@angular/core';
import { HttpClient ,  HttpRequest, HttpEvent} from '@angular/common/http';
import { Observable, Observer } from 'rxjs';
import {RegUser} from "../entity/reg-user";
import {BasicInfo} from "../entity/basic-info";
import {ContactInfo} from "../entity/contact-info";
import {Address} from "../entity/address";
import {CarInfo} from "../entity/car-info";
import {ResidentialStatus} from "../entity/residential-status";
import {ContactReference} from "../entity/contact-reference";
import {ContactEmergency} from "../entity/contact-emergency";
import { Routes, RouterModule} from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  
  constructor(private httpClient: HttpClient) { }

  Avatar = new File(["foo"], "foo.txt", {
    type: "text/plain",
  });

  workAuthorization = new File(["foo"], "foo.txt", {
    type: "text/plain",
  });
  
  basicInfo: BasicInfo = new BasicInfo();
  contactInfo: ContactInfo = new ContactInfo();
  carInfo: CarInfo = new CarInfo();
  residentialStatus: ResidentialStatus = new ResidentialStatus();
  contactReference: ContactReference = new ContactReference();
  contactEmergency: ContactEmergency = new ContactEmergency();


  private sso = "auth/registration";
  private MainSever= "api";



  setRegUser(regUser: RegUser):Observable<any> {
 
    const body = {
      userName:regUser.userName, 
      password: regUser.password,
      email: regUser.email,
      
    };

    return this.httpClient.post(this.sso,  body, {
      responseType: 'text',
      withCredentials: true,
      
    }) 
  
  }


  sumbitAll(){
    const formData: FormData = new FormData();

    formData.append('Avator', this.Avatar);
    formData.append('Work', this.workAuthorization);
    formData.append("model",JSON.stringify(this.basicInfo ));
    
   

    const req = new HttpRequest('POST', `${this.MainSever}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.httpClient.request(req);
  }






  setAvatar(file: any) {
    this.Avatar = file;
    console.log(" in service avatar" );
    console.log(this.Avatar);
  }


  setWorkFile(file: any) {
   
    this.workAuthorization = file;
  }


  

  setContactEmergency(contactEmergency: ContactEmergency) {
    this.contactEmergency = contactEmergency;
  }
  getContactEmergency() {
    return this.contactEmergency;
  }
  setContactReference(contactReference: ContactReference) {
    this.contactReference = contactReference;
  }
  getContactReference() {
    return this.contactReference;
  }
  setResidentialStatus(residentialStatus: ResidentialStatus) {
    this.residentialStatus = residentialStatus;
  }
  getResidentialStatus() {
    return this.residentialStatus;
  }
  setBasicInfo(basicInfo:BasicInfo) {
    this.basicInfo = basicInfo;
  }
  getBasicInfo() {
    return this.basicInfo;
  }
  setContactInfo(contactInfo:ContactInfo) {
    this.contactInfo = contactInfo;
  }
  getContactInfo() {
    return this.contactInfo;
  }
  setCarInfo(carInfo:CarInfo) {
    this.carInfo = carInfo;
  }
  getCarInfo() {
    return this.carInfo;
  }

}
