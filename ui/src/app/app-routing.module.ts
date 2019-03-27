import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { AdminComponent } from "./admin/admin.component";
import { DebitorsComponent} from "./debitors/debitors.component";
import { NewcomersComponent } from "./newcomers/newcomers.component";
import { InhabitantsComponent } from "./inhabitants/inhabitants.component";

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
        },
        {
            path: 'admin/did_not_send',
            component: DebitorsComponent
        },
        {
            path: 'admin/newcomers',
            component: NewcomersComponent
        },
        {
            path: 'admin/edit_inhabitants',
            component: InhabitantsComponent
        }
    ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [],
  exports: [RouterModule]
})
export class AppRoutingModule { }
