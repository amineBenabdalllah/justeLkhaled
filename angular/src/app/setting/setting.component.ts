import { Component, OnInit } from '@angular/core';
import {UserModule} from '../user/user.module';
import {Router} from '@angular/router';
import {AuthenticationService} from '../authentication.service';

@Component({
  selector: 'app-setting',
  templateUrl: './setting.component.html',
  styleUrls: ['./setting.component.scss']
})
export class SettingComponent implements OnInit {
  user:UserModule = new UserModule("", "","","", "",new Date());

  constructor(private service:AuthenticationService,private router:Router) { }

  ngOnInit() {
    let response = this.service.getUser();
    response.subscribe((data:UserModule)=>this.user=data);
  }
  public changepassword(data)
  {console.log(data);
    if(data["newpassword"]==data["confirmnewpassword"])
    {

      let response = this.service.changepassword(data);
      response.subscribe(resp=>{

        if(resp==400){
          alert("wrong password");
        }
        if(resp==200){
          alert("Password changed");

          this.router.navigateByUrl('/login');
        }

      },err=>{
      })
    }

  }
  public updateNow()
  {
    let response = this.service.doUpdate(this.user);
    response.subscribe((data)=>console.log(data));
    alert("User data saved");

  }
}
