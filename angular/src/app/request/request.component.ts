import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RequestContolService } from '../request-contol.service';
import {RequestModule} from './request.module';

@Component({
  selector: 'app-request-profil',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.scss']
})
export class RequestComponent implements OnInit, OnDestroy {

  constructor(private route:ActivatedRoute, private service: RequestContolService) { }

  private routeSub:any;
  id:string;
  requet:RequestModule;
  name:string;
  state:boolean;
  key:string;




  ngOnInit() {
    this.key=localStorage.getItem("token");
    this.key=this.key.replace("Bearer","");

    this.routeSub=this.route.params.subscribe(params =>
    {
      this.id=params["id"]
    })
    //let resp=this.service.getRequetById(this.id)
    console.log(this.id)
    //resp.subscribe((data:RequestModule)=>this.requet=data)
  }

  public launchRequet(id:number, name:string, cle:string)
  {
    let resp=this.service.doLaunch(id);
    this.state=true;
    resp.subscribe((data)=>this.requet);
  }

  public stopRequet(id:number)
  {
    let resp=this.service.doStop(id);
    this.state=false;
    resp.subscribe((data)=>this.requet);
    console.log(id);
  }

  ngOnDestroy()
  {
    this.routeSub.unsubscribe();
  }

}
