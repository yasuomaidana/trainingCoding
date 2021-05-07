import { Component, OnInit,ViewChild} from '@angular/core';
import { FormControl} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { UsersService } from './services/users.service';
import { NewUserComponent } from './users/new-user/new-user.component';
import {MatPaginator} from '@angular/material/paginator';
import { User } from './shared/user';
import {animate, state, style, transition, trigger} from '@angular/animations';
import { ModifyComponent } from './users/modify/modify.component';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('void', style({ height: '0px', minHeight: '0', visibility: 'hidden' })),
      state('*', style({ height: '*', visibility: 'visible' })),
      transition('void <=> *', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class AppComponent{
  
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  
  nameF = new FormControl();
  lnameF = new FormControl();
  nameOp: string[] = [];
  lastnameOp: string[] = [];
  users : User[]=[];
  user : User | undefined;
  nusers : String="0";
  displayedColumns: string[] = ['name', 'lastname', 'phone', 'email'];
  dataSource!: MatTableDataSource<User>;
  
  
  constructor(public matdialog:MatDialog,
    private userService:UsersService){
      this.userService.getUsers().subscribe(resp=>{
        this.users=resp;
        this.nusers = this.users.length.toString();
        this.dataSource = new MatTableDataSource(this.users);
        this.nameOp = this.users.map(a=>a.name!);
        this.lastnameOp = this.users.map(a=>a.lastname!);
        console.log(this.lastnameOp);
        this.dataSource.paginator = this.paginator;
        
        console.log(this.users)
      },err=>{console.log(err)});
    }
  ngOnInit(){

  }
  
  changes(){
    //this.lnameF.valueChanges.subscribe(val=>alert(val));
  }
  openNewUser(){
    this.matdialog.open(NewUserComponent)
    .afterClosed()
    .subscribe(res=>{
      this.userService.getUsers().subscribe(resp=>{
        this.users=resp;
        this.nusers = this.users.length.toString();
        this.dataSource = new MatTableDataSource(this.users);
        this.nameOp = this.users.map(a=>a.name!);
        this.lastnameOp = this.users.map(a=>a.lastname!);
        this.dataSource.paginator = this.paginator;

        console.log(this.users.length);
        console.log(this.users);
      },err=>{console.log(err)});
    });
  }

  modifyUser(id:String,userE:User){
    console.log(userE);
    this.matdialog.open(ModifyComponent,
      {data:{id:id,user:userE}});
    //alert(id);
    //console.log(userE);
  }
  deleteUser(id:String){
    this.userService.deleteUser(id).subscribe(a=>{
      this.userService.getUsers().subscribe(resp=>{
        this.users=resp;
        this.nusers = this.users.length.toString();
        this.dataSource = new MatTableDataSource(this.users);
        this.nameOp = this.users.map(a=>a.name!);
        this.lastnameOp = this.users.map(a=>a.lastname!);
        this.dataSource.paginator = this.paginator;

        console.log(this.users.length);
        console.log(this.users);
      },err=>{console.log(err)});
    });
  }
}
