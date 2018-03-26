import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { PedidoService } from './service/pedido.service';
import { HttpModule } from '@angular/http';
import { SanduicheService } from './service/sanduiche.service';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [PedidoService, SanduicheService],
  bootstrap: [AppComponent]
})
export class AppModule { }
