import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HrEmployeeProfileService {
  private endPoint = "api/jwt"

  constructor(private httpClient: HttpClient) {
  }

  getEmployeeProfiles(curPage: number, totalNum: number, maxResult: number, email: string): Observable<any> {



    const body = {

      curPage: curPage,
      totalNum: totalNum,
      email: email,
      maxResult: maxResult
    };


      console.log(curPage);
      console.log(email);
  
    
    return this.httpClient.post(this.endPoint + '/hr/employee/profiles', body, 
    {
      responseType: 'text',
   
    });
  }
}