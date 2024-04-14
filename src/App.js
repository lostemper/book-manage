import React, { useState } from 'react';
import './App.css';
import AddBookForm from './component/AddBookForm';
import BookList from './component/BookList';
function App() {
  const [books, setBooks] = useState([]);

  const addBook = (book) => {
    setBooks([...books, book]);
  };
  const handleEditBook = (id) => {
    const editedBook = books.find(book => book.id === id);
    console.log(`Editing book: ${editedBook.title} (ID: ${id})`);
  };
  const handleDeleteBook = (id) => {
    setBooks(books.filter(book => book.id !== id));
  };




  return (
    <div className="App">
      <h1>Book Management System</h1>
      <AddBookForm onAddBook={addBook} />
      <div>
        <BookList books={books}  onEditBook={handleEditBook}  onDeleteBook={handleDeleteBook} />
      </div>
    </div>
  );
}

export default App;
