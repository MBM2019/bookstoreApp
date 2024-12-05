import React from 'react';
import './ShopComponent.css';

export const BookComponent = (props) => {
  const { id, name, title, bookPrice } = props.data;

  return (
    <div className="product">
      <div className="description">
        <p>
          <b>{title}</b>
        </p>
        <p> {name}</p>
        <p> ${bookPrice}</p>
      </div>
    </div>
  );
};