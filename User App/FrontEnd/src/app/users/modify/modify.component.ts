import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UsersService } from 'src/app/services/users.service';
import { User } from 'src/app/shared/user';

export interface DialogData { id: String; user: User; };

@Component({
  selector: 'app-modify',
  templateUrl: './modify.component.html',
  styleUrls: ['./modify.component.scss']
})
export class ModifyComponent implements OnInit {
  errMess: string="";
  
  formErrors = {
    'name': '',
    'lastname': '',
    'company':'',
    'phone':'',
    'email':''
  };

  @ViewChild('uForm') feedBackFormDirective: any;
  userForm!: FormGroup;  
  constructor(private dialog:MatDialogRef<ModifyComponent>,
    @Inject(MAT_DIALOG_DATA) public data:DialogData,
    private formBuilder:FormBuilder,
    private userService:UsersService) { 
      this.createForm();
      console.log(this.data.user);
    }

  ngOnInit(): void {
  }
  createForm(){
    this.userForm = this.formBuilder.group({
      name:[this.data.user.name,[Validators.required,Validators.minLength(2),Validators.pattern('[a-zA-Z]*')]],
      lastname:[this.data.user.lastname,[Validators.required,Validators.minLength(2),Validators.pattern('[a-zA-Z]*')]],
      company:[this.data.user.company,[Validators.pattern('[a-zA-Z]*')]],
      phone:[this.data.user.phone,[Validators.required, Validators.pattern("[0-9]{10}")]],
      email:[this.data.user.email,[Validators.required,Validators.email]]
    });
  }
  onSubmit(){
    this.userService.updateUser(this.data.id,this.userForm.value).subscribe(Response=>{
      console.log(Response);
    });
    this.dialog.close()  
  }
}
