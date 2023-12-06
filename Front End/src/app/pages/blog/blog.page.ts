import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { AppServiceService } from 'src/app/services/app-service.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.page.html',
  styleUrls: ['./blog.page.scss'],
})
export class BlogPage implements OnInit {
  blogs = [];
  totalItemsCart: number = 0;
  constructor(
    private service: AppServiceService,
    public loadingController: LoadingController,
    private router: Router,
    private cartService: CartService
  ) {}

  ngOnInit() {
    this.totalItemsCart = this.cartService.getTotalItemsCount();
    this.presentLoading().then(() => {
      this.service.getAllBlog().subscribe((res) => {
        this.blogs = res.document.records;
        console.log(this.blogs);
        this.loadingController.dismiss();
      });
    });
  }

  async presentLoading() {
    const loading = await this.loadingController.create({
      message: 'Please Wait .. ',
    });
    await loading.present();
  }

  goToBlog(id) {
    console.log(id);
    this.router.navigate(['/blog-post', id]);
  }
}
