import { Injectable } from '@angular/core';
import { HttpClient ,  HttpRequest, HttpEvent} from '@angular/common/http';
import { Observable, Observer } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PersonalInfoService {

  private mainhrhome= "api//jwt/hr/home";


  // private test= "api//test4";
  constructor(private httpClient: HttpClient) { }




  IsApproved():Observable<any>{

    return this.httpClient.get(this.mainhrhome);
  } 



  GetAllInfo():Observable<any>{

    return this.httpClient.get(this.mainhrhome);
  } 


  EditInfo(info:any):Observable<any>{

    const body = {
      data:info
    };
    return this.httpClient.put(this.mainhrhome, body, {
      responseType: 'text',
     
      
    });
  } 
}
