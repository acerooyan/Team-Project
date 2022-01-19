import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class VerifyUserService {



  // private sso = "auth/login";
   private sso = "api/hr/home";

  
  constructor(private http: HttpClient) { }


  verify(email: string, psw: string):Observable<any>
  {
    
    const body = {userName:email, 
      password: psw};



      return this.http.get(this.sso);

  //   return this.http.post(this.sso,  body, {
  //     responseType: 'text',
  //     withCredentials: true,
      
  //   }) 
  }

  
 
}
