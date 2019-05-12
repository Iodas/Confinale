import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "./user";
import {Item} from "./item";
import {Observable} from "rxjs";

@Component({
  selector: 'app-scratch',
  templateUrl: './scratch.component.html',
  styleUrls: ['./scratch.component.css']
})
export class ScratchComponent implements OnInit {


  items: Item[];



  loadedAt: string;

  ngOnInit() {
    this.loadAllItems();
  }

  item: Item = {
    username:"",
    itemname: "",
    price: null,
    modificationTime: null,
    creationTime: null,
    id: null
  };

  constructor( private httpClient:HttpClient) { }


  onAddItemButtonClick():void {
    this.httpClient.post("api/items/add", this.item).subscribe(
      resp => {
        location.reload();
      },
      err => {
        alert("Invalid input provided");
      }
    )

  }

  loadAllItems(){
    this.httpClient.get<Item[]>("api/items")
      .subscribe(resp => {
        this.items = resp;
      });

  }

  deleteItemButton(itemId):void {
    this.httpClient.delete("api/items/" + itemId + "/delete")
      .subscribe(resp => {
        location.reload();
      },
        err => {
        alert("This item wasn't deleted")
        })
  }

}
