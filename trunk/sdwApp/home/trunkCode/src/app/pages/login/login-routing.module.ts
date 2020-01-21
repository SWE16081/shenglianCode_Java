import { LoginComponent } from './login.component';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

const routes : Routes = [{
    path : 'main',
    component : LoginComponent
}];
@NgModule({
    imports : [ RouterModule.forChild(routes)],
    exports : [RouterModule]
})
export class LoginRoutingModule{}