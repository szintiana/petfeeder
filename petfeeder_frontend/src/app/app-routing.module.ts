import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guard/auth.guard';

const routes: Routes = [
  {
    path: '', redirectTo: 'login', pathMatch: 'full'
  },
  {
    path: 'pet-create',
    loadChildren: () => import('./pet-create/pet-create.module').then( m => m.PetCreatePageModule), 
    canActivate: [AuthGuard]
  },
  {
    path: 'pet-edit',
    loadChildren: () => import('./pet-edit/pet-edit.module').then( m => m.PetEditPageModule), 
    canActivate: [AuthGuard]
  },
  {
    path: 'pet-list',
    loadChildren: () => import('./pet-list/pet-list.module').then( m => m.PetListPageModule), 
    canActivate: [AuthGuard]
  },
  {
    path: 'pet-detail',
    loadChildren: () => import('./pet-detail/pet-detail.module').then( m => m.PetDetailPageModule), 
    canActivate: [AuthGuard]
  },
  {
    path: 'landing',
    loadChildren: () => import('./landing/landing.module').then( m => m.LandingPageModule), 
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'register',
    loadChildren: () => import('./register/register.module').then( m => m.RegisterPageModule), 
    canActivate: [AuthGuard]
  },
  {
    path: 'user-edit',
    loadChildren: () => import('./user-edit/user-edit.module').then( m => m.UserEditPageModule), 
    canActivate: [AuthGuard]
  },
  {
    path: 'pet-calendar',
    loadChildren: () => import('./pet-calendar/pet-calendar.module').then( m => m.PetCalendarPageModule), 
    canActivate: [AuthGuard]
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
