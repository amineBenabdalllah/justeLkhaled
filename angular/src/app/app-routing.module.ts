import {NgModule, OnInit} from '@angular/core';
import {Routes, RouterModule, Router} from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ContactComponent } from './contact/contact.component';
import {AccountComponent} from './account/account.component';
import {SettingComponent} from './setting/setting.component';
import {AuthenticationService} from './authentication.service';
import {RequestComponent} from './request/request.component';
import {SearchComponent} from './search/search.component';
import {AddingComponent} from './adding/adding.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { TranslateComponent } from './translate/translate.component';
import { AnalyseComponent } from './analyse/analyse.component';
import { HybridComponent } from './hybrid/hybrid.component';
import { FullProjectComponent } from './full-project/full-project.component';


const routes: Routes = [
  { path: '',redirectTo: '/allProjects',pathMatch: 'full'},
  {path:"analyse",component:AnalyseComponent},
  {path:"welcome",component:WelcomeComponent},
  {path:"translate",component:TranslateComponent},
  { path:"request/:id", component:RequestComponent },
  { path:"addRequet/:testId", component:AddingComponent},
  { path:"allProjects", component:SearchComponent},
  {path:"setting",component:SettingComponent},
  {path:"account",component:AccountComponent},
  {path:"login",component:LoginComponent},
  {path:"register",component:RegisterComponent},
  {path:"contact",component:ContactComponent},
  {path:"newProject", component: HybridComponent},
  {path:"project/:id", component:FullProjectComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule implements OnInit{
  userlog:boolean=false;
  constructor(private autheservice:AuthenticationService,
              private router:Router) { }

  ngOnInit(): void {

    console.log('ok')
  }


}
