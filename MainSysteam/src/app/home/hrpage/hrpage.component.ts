import { Component, OnInit } from '@angular/core';
import { HrHomeService } from 'src/app/services/hr-home.service';
import{HomeProfile} from 'src/app/PersonalnfoEntity/Info';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hrpage',
  templateUrl: './hrpage.component.html',
  styleUrls: ['./hrpage.component.css']
})
export class HrpageComponent implements OnInit {
 
  public HomeProfile: HomeProfile[] = [];
  public file?: File; 
  public showUpload = false;
  public fileName = "1";
  constructor(private service: HrHomeService, private router: Router ) {
  
  }


  ngOnInit(): void {

     



    //   [
    //     {
    //         "fullName": "James Huang",
    //         "visa": "OPT",
    //         "startDate": "2012-12-31",
    //         "endDate": "2012-12-31",
    //         "dayLeft": -3310,
    //         "email": "jamesh970327@gmail.com"
    //     }
    // ]

      
      
      this.service.HrHome().subscribe(
        {
          next: data => {
              console.log(data);

              for(let d of data)
              {
                let item = new HomeProfile();
                item.dayLeft = d.dayLeft;
                item.email = d.email;
                item.endDate = d.endDate;
                item.visa = d.visa;
                item.fullName = d.fullName;
                this.HomeProfile.push(item);
              }
              
          },
          error: e => {
              
            console.log(e);
              
          }
      })
  }


  download(email?:string){
    if(!email)
    {
      return;
    }
    this.service.downloadFile(email).subscribe(
      (data:any)=>{
        console.log(data);
        window.open("https://beaconfire-project-document.s3.us-west-1.amazonaws.com/"+data)
        this.fileName = data;
      },
      (error: any)=>
      {
        
        if (error.status === 401) {
          this.router.navigate([""]);
        }
        else{
          console.log(error);
        }
      }
    )
  }


  changeShowUpload()
  {
    this.showUpload = !this.showUpload;
  }
  onChange(event:any, email?: string) {
  
    this.file = event.target.files[0];
    
    if(this.file && email)
    {
     
      this.service.UploadFile(email,this.file).subscribe(
        (data:any) =>{
          alert("File upload successfully");
        },
        (error: any) =>
        {
          if (error.status === 401) {
            this.router.navigate([""]);
          }
          else{
            console.log(error);
          }
        }
      )
      
      this.changeShowUpload();
    }
    
    
}

  SendNotification(email: string|undefined){
    if(email)
    {
      this.service.sendEmail(email).subscribe(
        (data:any) =>{
          alert("Email Notification Send");
        },
        (error: any) =>
        {
          if (error.status === 401) {
            this.router.navigate([""]);
          }
          else{
            console.log(error);
          }
        }
      )
    }
  
   
  }

}
