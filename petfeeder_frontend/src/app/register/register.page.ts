import { Component, OnInit } from '@angular/core';
import { ModalController, NavController } from '@ionic/angular';
import { LoginPage } from '../login/login.page';
import { AuthService } from 'src/app/services/auth.service';
import { NgForm } from '@angular/forms';
import { AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {

  name: string = "";
  email: string = "";
  password: string = "";
  confirm_password: string = "";  //make confirm password confirm password

  constructor() { }

  ngOnInit() { }

  onSubmit() {
     alert(
      this.name + ', ' + this.email + ', ' + this.password + ', ' + this.confirm_password //call on the proper service and reroute
     )
  }
}
