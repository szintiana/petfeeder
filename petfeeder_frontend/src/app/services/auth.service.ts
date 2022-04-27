import { EnvService } from './env.service';
import { tap } from 'rxjs/operators';
import { Storage } from '@capacitor/storage';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedIn = false;
  token: any;

  constructor(
    private http: HttpClient,
    private env: EnvService,
  ) {}

  login(identifier: String, password: String) {
    return this.http.post(this.env.LOGIN + '/' + identifier + '/' + password, null).pipe(
      tap(token => {
        Storage.set({
          key: "token",
          value: JSON.parse(JSON.stringify(token, null, 5)).token
      })
        .then(
          async () => {
            console.log((await Storage.get({key: "token"})).value);
            console.log('token stored');
          },
          error => console.error('Error storing item', error)
        );
        this.token = JSON.parse(JSON.stringify(token, null, 5)).token;
        this.isLoggedIn = true;
        return token;
      }),
    );
  }

  register (firstName: String, lastName: string, username: String, email: String, password: String) {
    return this.http.post(this.env.REGISTER_USER, 
      {
        firstName: firstName,
        lastName: lastName,
        email: email,
        username: username,
        password: password
      }); //return error if username or email already exists
  }

  //fix this as well please
  // fixed??
  logout() {
    return this.http.post(this.env.LOGOUT + "/" + this.token, null)
    .pipe(
      tap(data => {
        Storage.remove({key: "token"});
        this.isLoggedIn = false;
        delete this.token;
        return data;
      })
    )
  }

  // needs to be fixed
  // I think it is fixed??
  user() {
    return this.http.get<User>(this.env.GET_USER_BY_TOKEN + "/" + this.token)
    .pipe(
      tap(user => {
        console.log("auth.service.ts getting user with token");
        console.log(this.token);
        return user;
      })
    )
  }

  getToken() {
    return Storage.get({key: "token"}).then(
      data => {
        this.token = data.value;
        console.log("we are getting this token while authentication: ");
        console.log(this.token);
        if(this.token != null) {
          this.isLoggedIn = true;
        } else {
          this.isLoggedIn = false;
        }
      },
      error => {
        this.token = null;
        this.isLoggedIn = false;
      }
    );
  }
}
