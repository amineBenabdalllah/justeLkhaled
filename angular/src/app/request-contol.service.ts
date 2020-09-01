import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class RequestContolService {

  constructor(private http:HttpClient) { }
  host:string="http://localhost:8087";
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization':localStorage.getItem('token')
    })
  };

  public getRequestByName(name)
  {
    return this.http.get(this.host+"/projectSearch/"+name, this.httpOptions);
  }

  public getProjectBy(id)
  {
    return this.http.get(this.host+"/project/details/"+id,this.httpOptions);
  }

  public start(id)
  {
    let json={"projectId":id,"username":localStorage.getItem("username")};
    return this.http.post(this.host+"/launch/", json, this.httpOptions);
  }

  public getScraperBy(id)
  {
    return this.http.get(this.host+"/project/twitter/"+id,this.httpOptions);
  }

  public doLaunch(id)
  {
    return this.http.get(this.host+"/project/launch/"+id, this.httpOptions);
  }

  public doStop(id)
  {
    return this.http.get(this.host+"/project/stop/"+id, this.httpOptions);
  }


  public stop(id)
  {
    return this.http.get(this.host+"/project/stop/"+id, this.httpOptions);
  }

  public addHybrid(json)
  {
    return this.http.post(this.host+"/createHybrid", json, this.httpOptions);
  }

}
