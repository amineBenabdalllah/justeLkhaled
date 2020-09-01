import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {RequestModule} from '../request/request.module';
import {RequetAddingService} from '../requet-adding.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-adding',
  templateUrl: './adding.component.html',
  styleUrls: ['./adding.component.scss']
})
export class AddingComponent implements OnInit {


  //twitter:boolean;
  //facebook:boolean;
  //youtube:boolean;

  constructor(private service:RequetAddingService,  private router:Router, private route:ActivatedRoute) { }

  ngOnInit() {
    this.routeSub=this.route.params.subscribe(params =>
      {
        this.testId=params["testId"]
        console.log(this.testId);
      })
  }
  type:string;

  public onSelect(text)
  {
    this.requet.type=text;
    console.log(this.requet)
  }
  message: any;
  testId:string;
  routeSub:any;
  //twitter parameters
  twitterKey:string;
  twitterLanguage:string;
  //reddit parameters
  redditKey:string;
  redditLanguage:string;
  redditCount:string;

  requet: RequestModule = new RequestModule(this.testId, 1,"","", "",new Date(), "",localStorage.getItem("username"));



  public addNow()
  {
  let json={"hybridId": this.testId, "twitterKey":this.twitterKey, "twitterLanguage":this.twitterLanguage, "redditKey": this.redditKey, "redditLanguage":this.redditLanguage, "username": localStorage.getItem("username"), "count":this.redditCount};    
 
   if (!this.twitterKey){
      this.message="Error empty key words!!";
    }
   else if (!this.twitterLanguage){
      this.message="Error empty language!!";
    }
    if (!this.redditKey){
      this.message="Error empty key words!!";
    }
   else if (!this.redditLanguage){
      this.message="Error empty language!!";
    }
   else if (this.twitterKey&&this.twitterLanguage&&this.redditKey&&this.redditLanguage){
     this.requet.type;
      let response = this.service.doAdding(json);
      response.subscribe((data)=>this.message=data);
      this.router.navigateByUrl('/allRequest');
    }

  }

}
