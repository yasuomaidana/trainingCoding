import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {catchError} from 'rxjs/operators';
import { ErrHandlerService } from './err-handler.service';
import {User} from '../shared/user';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class WebRequestService {
  readonly ROOT_URL;
  constructor(private http:HttpClient,
    private errHandler:ErrHandlerService) {
    this.ROOT_URL = 'https://usersapptest.herokuapp.com';
  }
  get(uri:String):Observable<User[]>{
    return this.http.get<User[]>(`${this.ROOT_URL}/${uri}`)
    .pipe(catchError(this.errHandler.handleError));
  }
  post(uri:String,obj:Object){
    const httpOptions ={ 
      headers: new HttpHeaders({
      'Content-Type':'application/json'
      })
    };
    return this.http.post(`${this.ROOT_URL}/${uri}`,obj,httpOptions)
    .pipe(catchError(this.errHandler.handleError));;
  }
  delete(uri:String){
    return this.http.delete(`${this.ROOT_URL}/${uri}`);
  }
  put(uri:String,obj:Object){
    return this.http.put(`${this.ROOT_URL}/${uri}`,obj);
  }
}
