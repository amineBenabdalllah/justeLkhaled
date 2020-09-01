import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RequestContolService } from '../request-contol.service';


@Component({
  selector: 'app-hybrid',
  templateUrl: './hybrid.component.html',
  styleUrls: ['./hybrid.component.scss']
})
export class HybridComponent implements OnInit {

  constructor(private route:Router, private service: RequestContolService) { }
  title:string;
  message:any;
  ngOnInit() {
  }

  addHybrid()
  {
    let toSend={"username":localStorage.getItem("username"), "title":this.title};
    //let response= this.service.addHybrid(toSend)
    //response.subscribe((data)=>this.message=data);
    this.route.navigateByUrl("/addRequet/"+this.title);
  }


}
