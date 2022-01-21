import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class VerifyUserService {



   private sso = "auth/login";
   private mainhrhome= "api//jwt/hr/home";

  
  constructor(private http: HttpClient) { }


  verify(email: string, psw: string, loginAsHr: boolean):Observable<any>
  {
    
    
    const body = {userName:email, 
      password: psw, 
      role: loginAsHr ? "HR": "employee"
    };



    return this.http.post(this.sso,  body, {
      responseType: 'text',
      withCredentials: true,
      
    }) 
  

}


HrHome( ):Observable<any>
{
 
    return this.http.get(this.mainhrhome);

}

  
 
}
