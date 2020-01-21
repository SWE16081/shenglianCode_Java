import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { HeaderComponent } from './header/header.component';
import { ContentsComponent } from './contents/contents.component';
import { FooterComponent } from './footer/footer.component';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { LoginMenusComponent } from './header/login-menus/login-menus.component';



@NgModule({
  declarations: [LoginComponent, HeaderComponent, ContentsComponent, FooterComponent, LoginMenusComponent],
  imports: [
    CommonModule,
    NzLayoutModule 
  ]
})
export class LoginModule { }
