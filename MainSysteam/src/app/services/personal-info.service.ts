import { Injectable } from '@angular/core';
import { HttpClient ,  HttpRequest, HttpEvent} from '@angular/common/http';
import { Observable, Observer } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PersonalInfoService {

  private mainhrhome= "/api/jwt/profile";
  private editBase = "/api/jwt/em/profile/"
  
  // private test= "api//test4";
  constructor(private httpClient: HttpClient) { }




  IsApproved():Observable<any>{

    return this.httpClient.get(this.mainhrhome);
  } 



  GetAllInfo():Observable<any>{

    return this.httpClient.get(this.mainhrhome,
      
      );
  } 



  EditInfo(url:string, info:any):Observable<any>{
    const formData: FormData = new FormData();
    formData.append('model',JSON.stringify(info) );
    const body = {
      data:info
    };
    return this.httpClient.put(this.editBase+ url, formData, {
      responseType: 'text',
     
      
    });
  } 
}
