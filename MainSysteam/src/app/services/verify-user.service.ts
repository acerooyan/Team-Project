import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class VerifyUserService {


  //private sso = "http://localhost:9999/auth/login";
   private sso = "http://localhost:8080//hr/home";
  
  constructor(private http: HttpClient) { }


  verify(email: string, psw: string):Observable<any>
  {
    
    const body = {userName:email, 
      password: psw};


      console.log()
      //let headers1 = new Headers();
      //headers1.append('authentication', 'eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6IkhSIiwiaWF0IjoxNjQyNTU4NzIyLCJleHAiOjE2NTQ1NTg3MjJ9.a14_CL3fDG-d-g7pfEU5cdfsarM6J884iujrvXPrgugtoken');
      return this.http.get(this.sso, {
         headers: {'authentication': 'Bearer '+ 'eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6IkhSIiwiaWF0IjoxNjQyNTU4NzIyLCJleHAiOjE2NTQ1NTg3MjJ9.a14_CL3fDG-d-g7pfEU5cdfsarM6J884iujrvXPrgugtoken'}
      });

    // return this.http.post(this.sso,  body, {
    //   responseType: 'text',
    //   withCredentials: true,
      
    // }) 
  }

  
 
}
