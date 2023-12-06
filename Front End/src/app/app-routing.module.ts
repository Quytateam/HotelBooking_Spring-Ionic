import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from './services/auth-guard.service';

const routes: Routes = [
  // {
  //   path: '',
  //   redirectTo: 'swipper',
  //   pathMatch: 'full'
  // },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'folder/:id',
    loadChildren: () =>
      import('./folder/folder.module').then((m) => m.FolderPageModule),
  },
  {
    path: 'search-chef',
    loadChildren: () =>
      import('./pages/search-chef/search-chef.module').then(
        (m) => m.SearchChefPageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'profile-menu',
    loadChildren: () =>
      import('./pages/popup/profile-menu/profile-menu.module').then(
        (m) => m.ProfileMenuPageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'setting',
    loadChildren: () =>
      import('./pages/setting/setting.module').then((m) => m.SettingPageModule),
    canActivate: [AuthGuardService],
  },
  {
    path: 'faq',
    loadChildren: () =>
      import('./pages/faq/faq.module').then((m) => m.FaqPageModule),
    canActivate: [AuthGuardService],
  },
  {
    path: 'payment',
    loadChildren: () =>
      import('./pages/payment/payment.module').then((m) => m.PaymentPageModule),
    canActivate: [AuthGuardService],
  },
  {
    path: 'profile-page',
    loadChildren: () =>
      import('./pages/profile-page/profile-page.module').then(
        (m) => m.ProfilePagePageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'my-orders',
    loadChildren: () =>
      import('./pages/my-orders/my-orders.module').then(
        (m) => m.MyOrdersPageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'order',
    loadChildren: () =>
      import('./pages/popup/order/order.module').then((m) => m.OrderPageModule),
    canActivate: [AuthGuardService],
  },
  {
    path: 'messages',
    loadChildren: () =>
      import('./pages/messages/messages.module').then(
        (m) => m.MessagesPageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'filters',
    loadChildren: () =>
      import('./pages/filters/filters.module').then((m) => m.FiltersPageModule),
    canActivate: [AuthGuardService],
  },
  {
    path: 'book-achef',
    loadChildren: () =>
      import('./pages/book-achef/book-achef.module').then(
        (m) => m.BookAchefPageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'change-address',
    loadChildren: () =>
      import('./pages/change-address/change-address.module').then(
        (m) => m.ChangeAddressPageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'thankyou',
    loadChildren: () =>
      import('./pages/components/thankyou/thankyou.module').then(
        (m) => m.ThankyouPageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'my-favourite',
    loadChildren: () =>
      import('./pages/my-favourite/my-favourite.module').then(
        (m) => m.MyFavouritePageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'your-order',
    loadChildren: () =>
      import('./pages/your-order/your-order.module').then(
        (m) => m.YourOrderPageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'chef-details/:id',
    loadChildren: () =>
      import('./pages/chef-details/chef-details.module').then(
        (m) => m.ChefDetailsPageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'preferences',
    loadChildren: () =>
      import('./pages/preferences/preferences.module').then(
        (m) => m.PreferencesPageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'swipper',
    loadChildren: () =>
      import('./pages/swipper/swipper.module').then((m) => m.SwipperPageModule),
  },
  {
    path: 'login',
    loadChildren: () =>
      import('./pages/login/login.module').then((m) => m.LoginPageModule),
  },
  {
    path: 'register',
    loadChildren: () =>
      import('./pages/register/register.module').then(
        (m) => m.RegisterPageModule
      ),
  },
  {
    path: 'forget-password',
    loadChildren: () =>
      import('./pages/forget-password/forget-password.module').then(
        (m) => m.ForgetPasswordPageModule
      ),
  },
  {
    path: 'touch-id',
    loadChildren: () =>
      import('./pages/touch-id/touch-id.module').then(
        (m) => m.TouchIdPageModule
      ),
  },
  {
    path: 'face-id',
    loadChildren: () =>
      import('./pages/face-id/face-id.module').then((m) => m.FaceIdPageModule),
  },
  {
    path: 'verify-phone',
    loadChildren: () =>
      import('./pages/verify-phone/verify-phone.module').then(
        (m) => m.VerifyPhonePageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'otp-page',
    loadChildren: () =>
      import('./pages/otp-page/otp-page.module').then(
        (m) => m.OtpPagePageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'events/:id',
    loadChildren: () =>
      import('./pages/events/events.module').then((m) => m.EventsPageModule),
    canActivate: [AuthGuardService],
  },
  {
    path: 'event-details',
    loadChildren: () =>
      import('./pages/event-details/event-details.module').then(
        (m) => m.EventDetailsPageModule
      ),
    canActivate: [AuthGuardService],
  },
  {
    path: 'gallery',
    loadChildren: () =>
      import('./pages/gallery/gallery.module').then((m) => m.GalleryPageModule),
    canActivate: [AuthGuardService],
  },
  {
    path: 'support',
    loadChildren: () =>
      import('./pages/support/support.module').then((m) => m.SupportPageModule),
    canActivate: [AuthGuardService],
  },
  {
    path: 'blog',
    loadChildren: () =>
      import('./pages/blog/blog.module').then((m) => m.BlogPageModule),
    canActivate: [AuthGuardService],
  },
  {
    path: 'blog-post/:id',
    loadChildren: () =>
      import('./pages/blog-post/blog-post.module').then(
        (m) => m.BlogPostPageModule
      ),
    canActivate: [AuthGuardService],
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
