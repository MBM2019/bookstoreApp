import React from 'react';
import {useEffect, useState} from 'react';
import {getBooksApi} from '../api/BookstoreApiService';
import './ShopComponent.css';
import { BookComponent } from './BookComponent';

export default function ShopComponent() {

    const [bookList, setBookList] = useState([])

    useEffect(() => fetchData, [])

    function fetchData() {
        getBooksApi(1, 14)
            .then(response => {
                    setBookList(Array.from(response.data.content))
                    console.log('Book list: ', bookList)
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    return (
            <div className="container">
                <div className="shopTitle">
                    <h1>Bookstore Shop</h1>
                </div>
                <div className="products">
                        {bookList.map((book) => (
                          <BookComponent key={book.id} data={book} />
                        ))}
                </div>
            </div>
        )
}