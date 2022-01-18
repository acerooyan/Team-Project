import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class VerifyUserService {


  // private sso = "http://localhost:9999/auth/login";
  private sso = "http://localhost:8080/";
  headers = { 'content-type': 'application/json'};
  constructor(private http: HttpClient) { }


  verify(email: string, psw: string):Observable<any>
  {
    
    const body = {userName:email, 
      password: psw};

    // return this.http.post(this.sso, { 
    //   headers: { 
    //   'Allow-Cross-Origin-Origin0': '*', 
    //   "Content-Type": "application/json"
      
    //   }, 
    //   withCredentials: true,
    //   responseType: 'application/json', 
    //   userName:email, 
    //   password: psw, 
    //   }) 


    return this.http.post(this.sso,  body, {
      withCredentials: true
    }) 
  }

  
  // verify(email: string, psw: string):Observable<any>
  // {
    

  //   return this.http.post(this.sso, { 
  //     headers: { 
  //     'Allow-Cross-Origin-Origin0': '*', 
  //     "Content-Type": "application/json"
      
  //     }, 
  //     withCredentials: true,
  //     responseType: 'text', 
  //     firstname: email,
  //     lastname: psw

  //     }) 
  // }

}
