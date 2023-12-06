import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ProfileMenuPageRoutingModule } from './profile-menu-routing.module';

import { ProfileMenuPage } from './profile-menu.page';
import { ActivatedRoute } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ProfileMenuPageRoutingModule,
  ],
  declarations: [ProfileMenuPage],
  providers: [ActivatedRoute],
})
export class ProfileMenuPageModule {}
