import { Component ,OnInit} from '@angular/core';
import {AuthenticationService} from './authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'angular';
  userlog:boolean=false;
  constructor(private autheservice:AuthenticationService,
              private router:Router) { }

   ngOnInit(): void {
     this.autheservice.userRequest()
       .subscribe(resp => {
         this.userlog = true;}
           ,err=>{
           this.router.navigateByUrl('/login');
         });
   }


}
