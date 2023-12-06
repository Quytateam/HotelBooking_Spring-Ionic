import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PopoverController } from '@ionic/angular';

@Component({
  selector: 'app-profile-menu',
  templateUrl: './profile-menu.page.html',
  styleUrls: ['./profile-menu.page.scss'],
})
export class ProfileMenuPage implements OnInit {
  @Input() id: number;

  constructor(
    public popoverController: PopoverController,
    private router: Router
  ) {}

  ngOnInit() {}

  viewChefDetail() {
    this.router.navigate(['/chef-details', this.id]);
    this.close();
    // console.log(this.id);
  }

  close() {
    this.popoverController.dismiss();
  }
}
