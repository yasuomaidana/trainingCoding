import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { NewUserComponent } from './users/new-user/new-user.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';

import {MatPaginatorModule} from '@angular/material/paginator';
import {MatListModule} from '@angular/material/list';
import {MatTableModule} from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
//Services
import { UsersService } from './services/users.service';
import { WebRequestService } from './services/web-request.service';
import { HttpClientModule } from '@angular/common/http';
import { CdkDetailRowDirective } from './direcives/cdk-detail-row.directive';
import { ModifyComponent } from './users/modify/modify.component';






@NgModule({
  declarations: [
    AppComponent,
    NewUserComponent,
    CdkDetailRowDirective,
    ModifyComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    FormsModule,
    MatFormFieldModule,
    MatAutocompleteModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatPaginatorModule,
    MatListModule,
    MatTableModule,
    HttpClientModule,
    MatSortModule
  ],
  providers: [UsersService,WebRequestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
