import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder,FormGroup,Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { UsersService } from 'src/app/services/users.service';


@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.scss']
})
export class NewUserComponent implements OnInit {
  errMess: string="";
  
  formErrors = {
    'name': '',
    'lastname': '',
    'company':'',
    'phone':'',
    'email':''
  };
  validationMessages = {
    'name': {
      'required':      'Name is required.',
      'minlength':     'Name must be at least 2 characters long.',
      'pattern':"Name must be just alphanumeric"
    },
    'lastname': {
      'required':      'Comment is required.',
      'minlength':     'Comment must be at least 4 characters long.',
      'pattern':"Last name must be just alphanumeric"
    },
    'company':{
      'pattern':"Company must be just alphanumeric"
    },
    'email': {
      'required':      'Comment is required.',
    },
    'phone':{
      'pattern':'It must be a valid phone number'
    }
  };

  @ViewChild('uForm') feedBackFormDirective: any;
  userForm!: FormGroup;
  constructor(private formBuilder:FormBuilder,
    private dialog:MatDialogRef<NewUserComponent>,
    private userService:UsersService) {
    this.createForm();
  }

  ngOnInit(): void {
  }
  createForm(){
    this.userForm = this.formBuilder.group({
      name:['',[Validators.required,Validators.minLength(2),Validators.pattern('[a-zA-Z]*')]],
      lastname:['',[Validators.required,Validators.minLength(2),Validators.pattern('[a-zA-Z]*')]],
      company:['',[Validators.pattern('[a-zA-Z]*')]],
      phone:['',[Validators.required, Validators.pattern("[0-9]{10}")]],
      email:['',[Validators.required,Validators.email]]
    });
  }
  onSubmit(){
    console.log(this.userForm.value);
    this.userService.createUser(this.userForm.value).subscribe(Response=>{
      console.log(Response);
    });
    this.dialog.close()  
  }
}
