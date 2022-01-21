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

export class mergeDomain  {
  basicInfo?: BasicInfo;
  contactInfo?: ContactInfo;
  carInfo?: CarInfo;
  residentialStatus?: ResidentialStatus;
  contactReference?: ContactReference;
  contactEmergency?: ContactEmergency;

}

@Injectable({
  providedIn: 'root'
})



export class RegisterService {
  
  constructor(private httpClient: HttpClient) { }

  Avatar ?: FileList;

  workAuthorization  ?: FileList;

  driverLi  ?: FileList;
  
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

  
  
  sumbitAll():Observable<HttpEvent<any>>{
    const formData: FormData = new FormData();
    
    let obj = new mergeDomain ();

    obj.basicInfo = this.basicInfo;
    obj.carInfo = this.carInfo;
    obj.contactEmergency = this.contactEmergency;
    obj.contactInfo = this.contactInfo;
    obj.contactReference = this.contactReference;
    obj.residentialStatus = this.residentialStatus;

    if (this.Avatar) {
      
      var file: File | null = this.Avatar.item(0);
      if(file)
      {
        console.log(1111111111);
        console.log(file);
        formData.append('Avatar', file);
      }
    }

    if (this.workAuthorization) {
      
      var file: File | null = this.workAuthorization.item(0);
      if(file)
      {
        formData.append('Work', file);
      }
    }

    if (this.driverLi) {
      
      var file: File | null = this.driverLi.item(0);
      if(file)
      {
        formData.append('Driver', file);
      }
    }


   
  
   
    formData.append("model",JSON.stringify(obj));
    
   

    const req = new HttpRequest('POST', `${this.MainSever}/em/register/test`, formData, {
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


  setDriver(file: any) {
    this.driverLi = file;
    
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
