import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import {AdminComponent} from "./admin/admin.component";

const routes: Routes = [
        {
            path: '',
            component: LoginComponent
        },
        {
            path: 'login',
            component: LoginComponent
        },
        {
            path: 'home',
            component: HomeComponent
        },

        {
            path: 'admin',
            component: AdminComponent
        },
        {
            path: 'register',
            component: RegisterComponent
        }

    ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [],
  exports: [RouterModule]
})
export class AppRoutingModule { }
