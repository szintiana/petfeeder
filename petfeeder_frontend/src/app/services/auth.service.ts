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
          value: token.toString()
      })
        .then(
          () => {
            console.log('token stored');
          },
          error => console.error('Error storing item', error)
        );
        this.token = token;
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
    return this.http.post(this.env.LOGOUT + "/" + this.token["token_type"]+" "+this.token["access_token"], null)
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
    return this.http.get<User>(this.env.GET_USER_BY_TOKEN + "/" + this.token["token_type"]+" "+this.token["access_token"])
    .pipe(
      tap(user => {
        return user;
      })
    )
  }

  getToken() {
    return Storage.get({key: "token"}).then(
      data => {
        this.token = JSON.parse(data.value);
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
