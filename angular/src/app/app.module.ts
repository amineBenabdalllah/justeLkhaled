import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

// For MDB Angular Free
import { NavbarModule, WavesModule, ButtonsModule } from 'angular-bootstrap-md';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ContactComponent } from './contact/contact.component';
import { TranslateComponent } from './translate/translate.component';
import { AnalyseComponent } from './analyse/analyse.component';
import { AccountComponent } from './account/account.component';
import { SettingComponent } from './setting/setting.component';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {RequestComponent} from './request/request.component';
import {SearchComponent} from './search/search.component';
import {AddingComponent} from './adding/adding.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { HybridComponent } from './hybrid/hybrid.component';
import { FullProjectComponent } from './full-project/full-project.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    ContactComponent,
    TranslateComponent,
    AnalyseComponent,
    AccountComponent,
    SettingComponent,
    AddingComponent,
    SearchComponent,
    RequestComponent,
    WelcomeComponent,
    HybridComponent,
    FullProjectComponent

  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MDBBootstrapModule.forRoot(),
        NavbarModule, WavesModule, ButtonsModule, FormsModule,
        HttpClientModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
