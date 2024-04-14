import React, { useState } from 'react';
import Book from './Book';
import EditBookForm from './EditBookForm';

const BookList = ({ books, onEditBook }) => {
  const [editingBookId, setEditingBookId] = useState(null);

  const handleEditClick = (id) => {
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
              <Book book={book} onEditClick={() => handleEditClick(book.id)} />
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default BookList;
