import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
  baseUrl="http://localhost:8081/user";
  constructor(private httpClient: HttpClient) { }

  regUser: RegUser = new RegUser();
  basicInfo: BasicInfo = new BasicInfo();
  contactInfo: ContactInfo = new ContactInfo();
  carInfo: CarInfo = new CarInfo();
  residentialStatus: ResidentialStatus = new ResidentialStatus();
  contactReference: ContactReference = new ContactReference();
  contactEmergency: ContactEmergency = new ContactEmergency();
  // data.append("a",regUser)
  setRegUser(regUser: RegUser) {
    this.regUser = regUser;
  }
  getRegUser() {
    return this.regUser;
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
  // data = [this.regUser,this.basicInfo,this.carInfo,this.residentialStatus,this.contactReference,this.contactEmergency]
  // formData: FormData = new FormData();
  // formData.append()

  registerBasicInfo(basicInfo: BasicInfo): Observable<Object> {
    console.log(basicInfo);
    return this.httpClient.post(`${this.baseUrl}`,basicInfo);
  }
  // registerContactInfo(contactInfo: ContactInfo) Observable<Object> {
  //   console.log(contactInfo);
  //   return this.httpClient.post(`${this.baseUrl}`,contactInfo);
  // }
}
