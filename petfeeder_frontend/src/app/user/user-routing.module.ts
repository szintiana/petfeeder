import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeListComponent } from './user-list/user-list.component';
const routes: Routes = [
{path: 'api/user', component: UserListComponent},
];
@NgModule({
imports: [RouterModule.forChild(routes)],
exports: [RouterModule]
})
export class EmployeeRoutingModule { }
