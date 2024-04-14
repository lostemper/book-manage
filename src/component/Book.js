import React from 'react';

const Book = ({ book, onEditClick }) => {
  const { id, title, author, year, isbn } = book;

  return (
    <div>
      <h3>{title}</h3>
      <p>Author: {author}</p>
      <p>Year: {year}</p>
      <p>ISBN: {isbn}</p>
      <button onClick={() => onEditClick(id)}>Edit</button>
    </div>
  );
};

export default Book;
