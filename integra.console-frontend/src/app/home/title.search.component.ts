import { Component, OnInit, Input } from '@angular/core';

@Component({
    selector: 'title-search-component',
    templateUrl: './title.search.component.html',
    styleUrls: ['./title.search.component.css']
})

export class TitleSearchComponent implements OnInit {
    
    @Input() userFullName: string;
    @Input() profileDescription: string;

    constructor() { }

    ngOnInit() {

    }

}