import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HrHomeService {

  private mainhrhome= "api/jwt/hr/home";
  private sendEmial = "api/jwt/hr/sendNotification";
  private upload = "api/jwt/hr/home/upload";
  private download = "api/jwt/hr/home/download";
  constructor(private http: HttpClient) { }


   HrHome( ):Observable<any>
  {

      return this.http.get(this.mainhrhome);

  }


  UploadFile(email:string,  I983 : File):Observable<any>
  {

    const formData: FormData = new FormData();


    formData.append('file', I983);
   
    formData.append("email", email);

    return this.http.post(this.upload, formData);
  }


  
  downloadFile(email:string):Observable<any>
  {
    const formData: FormData = new FormData();
    formData.append("email", email);

    return this.http.post(this.download, formData, 
      {
        responseType:'text'
      });
  }
  

  sendEmail(email:string):Observable<any>
  {
    const formData: FormData = new FormData();
    formData.append("email", email);

    return this.http.post(this.sendEmial, formData,
      {
        responseType:'text'
      }
      );
  }
}
