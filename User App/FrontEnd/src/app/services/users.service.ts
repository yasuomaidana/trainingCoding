import { Injectable } from '@angular/core';
import { WebRequestService } from './web-request.service';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private webReqService:WebRequestService) { }
  getUsers(){
    return this.webReqService.get('users/');
  }
  createUser(user:Object){
    return this.webReqService.post('users/signup',user);
  }
  deleteUser(user:String){
    return this.webReqService.delete('users/'+user);
  }
  updateUser(user:String,mod:Object){
    return this.webReqService.put('users/'+user,mod);
  }
}
