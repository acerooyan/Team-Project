import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class VerifyUserService {


  // private sso = "http://localhost:9999/auth/login";
  private sso = "http://localhost:8080/em/af";
  
  constructor(private http: HttpClient) { }


  verify(email: string, psw: string):Observable<any>
  {
    
    const body = {userName:email, 
      password: psw};

    


    return this.http.post(this.sso,  body, {
      responseType: 'text',
      withCredentials: true,
      
    }) 
  }

  
 
}
