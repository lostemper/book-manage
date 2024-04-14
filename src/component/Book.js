import React, { useState } from 'react';

const Book = ({ book, onEditClick, onDeleteClick }) => {
  const { id, title, author, year, isbn } = book;
  const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(false);

  const handleDeleteConfirmation = () => {
    setShowDeleteConfirmation(true);
  };

  const handleDelete = () => {
    onDeleteClick(id);
    setShowDeleteConfirmation(false);
  };

  const handleEditBook = (id) => {
    onEditClick(id);
  };


  return (
    <div>
      <h3>{title}</h3>
      <p>Author: {author}</p>
      <p>Year: {year}</p>
      <p>ISBN: {isbn}</p>
      <button onClick={() => handleEditBook(id)}>Edit</button>
      <button onClick={handleDeleteConfirmation}>Delete</button>
      {showDeleteConfirmation && (
        <div>
          <p>Are you sure you want to delete this book?</p>
          <button onClick={handleDelete}>Yes</button>
          <button onClick={() => setShowDeleteConfirmation(false)}>No</button>
        </div>
      )}
    </div>
  );
};

export default Book;
