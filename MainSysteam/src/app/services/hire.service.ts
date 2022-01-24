import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HireService {
  endPoint = 'auth/';
  private mainhrhome= "/api/jwt/hr/profile/byemail";
  private workfolow = "api/jwt/workflow"
  constructor(private http: HttpClient) {
  }

  addToken(email: string, duration: number): Observable<any> {
    return this.http.post(this.endPoint + 'createToken', {
      headers: {
        'Allow-Cross-Origin-Origin0': '*'
      },
      responseType: 'text',
      email: email,
      duration: duration
    });
  }


  GetAllInfobyemail(email:string):Observable<any>{
    const formData: FormData = new FormData();
    formData.append('model',email );
    return this.http.post(this.mainhrhome, formData
      
      );
  } 


  getWorkflow():Observable<any>{

    return this.http.get(this.workfolow,
      
      );
  } 
}
