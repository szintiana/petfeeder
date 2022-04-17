import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '', redirectTo: 'pet-list', pathMatch: 'full'
  },
  {
    path: 'pet-create',
    loadChildren: () => import('./pet-create/pet-create.module').then( m => m.PetCreatePageModule)
  },
  {
    path: 'pet-edit',
    loadChildren: () => import('./pet-edit/pet-edit.module').then( m => m.PetEditPageModule)
  },
  {
    path: 'pet-list',
    loadChildren: () => import('./pet-list/pet-list.module').then( m => m.PetListPageModule)
  },
  {
    path: 'pet-detail',
    loadChildren: () => import('./pet-detail/pet-detail.module').then( m => m.PetDetailPageModule)
  },
  {
    path: 'landing',
    loadChildren: () => import('./landing/landing.module').then( m => m.LandingPageModule)
  },
  {
    path: 'login',
    loadChildren: () => import('./auth/login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'register',
    loadChildren: () => import('./auth/register/register.module').then( m => m.RegisterPageModule)
  },
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'register',
    loadChildren: () => import('./register/register.module').then( m => m.RegisterPageModule)
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
