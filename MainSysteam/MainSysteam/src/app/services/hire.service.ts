import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import{ alldata} from 'src/app/PersonalnfoEntity/Info'


@Injectable({
  providedIn: 'root'
})
export class HireService {

  public alldata: alldata = new alldata();
  endPoint = 'auth/';
  private mainhrhome= "/api/jwt/hr/profile/byemail";
  private workfolow = "api/jwt/workflow";


  public email:string = "";
  constructor(private http: HttpClient) {
  }



  setEmail(email:string)
  {
    this.email = email;
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



  getDetail():Observable<any>{

    const formData: FormData = new FormData();


    formData.append('model',this.email );
    return this.http.post(this.mainhrhome, formData
      
      );
  } 



  getWorkflow():Observable<any>{

    return this.http.get(this.workfolow,
      
      );
  } 


  setAlldata(dta: alldata){
    this.alldata = dta;

  }

  getAlldata(): alldata
 {
   return this.alldata;
 }


}
