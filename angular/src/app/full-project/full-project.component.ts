import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RequestContolService } from '../request-contol.service';

@Component({
  selector: 'app-full-project',
  templateUrl: './full-project.component.html',
  styleUrls: ['./full-project.component.scss']
})
export class FullProjectComponent implements OnInit {

 
  constructor(private route:ActivatedRoute, private service: RequestContolService) {

  
   }

  key:any;
  private routeSub:any;
  id:string;
  name:string;
  state:boolean;
  twitter:any;
  message:any;
  scraper:any;
  ngOnInit() {
    this.routeSub=this.route.params.subscribe(params =>
      {
        this.id=params["id"]
      })
      let resp=this.service.getProjectBy(this.id);
      console.log(this.id);
      resp.subscribe((data)=>this.scraper=data);

      let resp1=this.service.getScraperBy(this.id);
      console.log(this.id);
      resp1.subscribe((data1)=>this.twitter=data1);
  }
  afficheur()
  {
    console.log(this.scraper);
    console.log(this.twitter);

  }
lancer()
{
  let resp=this.service.start(this.id);
  console.log(this.id);
  resp.subscribe((data)=>this.message=data);

}
}