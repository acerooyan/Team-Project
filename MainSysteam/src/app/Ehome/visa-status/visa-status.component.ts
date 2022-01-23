import { Component, OnInit } from '@angular/core';
import { HttpClient ,  HttpRequest, HttpEvent} from '@angular/common/http';
import { Observable, Observer } from 'rxjs';
@Component({
  selector: 'app-visa-status',
  templateUrl: './visa-status.component.html',
  styleUrls: ['./visa-status.component.css']
})
class StatusInfo{
  currentStep?: String;
  nextStep?: String;
  comment?: String;

}
export class VisaStatusComponent implements OnInit {
  public statusInfo: StatusInfo = {};
  // currentStep
  // nextStep
  // comment

  // documents
  private statusInfoUrl = "";
  constructor(private httpClient: HttpClient) { }
  ngOnInit(): void {
    this.getStatusInfo().subscribe(
      (data:any) =>{
        console.log(data);
        this.statusInfo = {currentStep:data.}
      }
    )
  }
  selectedFiles?: FileList;
  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }
  getStatusInfo():Observable<any>{
    return this.httpClient.get(this.statusInfoUrl,
    );
  }
}
