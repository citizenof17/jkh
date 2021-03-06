import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './register/register.component';
import { AdminComponent } from './admin/admin.component';
import {CookieService} from "ngx-cookie-service";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { DebitorsComponent } from './debitors/debitors.component';
import { NewcomersComponent } from './newcomers/newcomers.component';
import { InhabitantsComponent } from './inhabitants/inhabitants.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    AdminComponent,
    DebitorsComponent,
    NewcomersComponent,
    InhabitantsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
