import React, { useState } from 'react';
import Book from './Book';
import EditBookForm from './EditBookForm';

const BookList = ({ books, onEditBook, onDeleteBook }) => {
  const [editingBookId, setEditingBookId] = useState(null);

  const handleEditBook = (id) => {
    setEditingBookId(id);
  };
  const handleCancelEdit = () => {
    setEditingBookId(null);
  };

  return (
    <div>
      <h2>Books</h2>
      <ul>
        {books.map((book) => (
          <li key={book.id}>
            {editingBookId === book.id ? (
              <EditBookForm book={book} onUpdateBook={onEditBook} onCancelEdit={handleCancelEdit} />
            ) : (
              <Book book={book} onEditClick={() => handleEditBook(book.id)}   onDeleteClick={onDeleteBook}/>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default BookList;
