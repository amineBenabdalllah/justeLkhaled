import { Component, OnInit } from '@angular/core';
import { RequetAddingService } from '../requet-adding.service';
import { AuthenticationService } from '../authentication.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  requets:any;
  name:string;
  

  constructor(private service:RequetAddingService, private router:Router) {
    let resp=this.service.getAllRequest();
    resp.subscribe((data)=>this.requets=data);
    console.log(this.requets);

   }
  ngOnInit() {
    let resp=this.service.getAllRequest();
    resp.subscribe((data)=>this.requets=data);
    console.log(this.requets);

  }

  public deleteRequet(id:number)
  {
    let resp= this.service.deleteRequest(id);
    resp.subscribe(data=>{
      this.requets=data;
      window.location.reload();
    });
  }

  public launchRequet(id:number, name:string, cle:string)
  {
    let resp=this.service.doLaunch(id, name, cle);
    resp.subscribe((data)=>this.requets);
  }


  public getRequestByName()
  {
    let resp= this.service.getRequestByName(this.name);
    resp.subscribe(data=>{
      this.requets=data;
    });
  }


allRequests(){
  let resp=this.service.getAllRequest();
  resp.subscribe((data)=>this.requets=data);
  console.log(this.requets);
}
}
