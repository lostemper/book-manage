import React, { useState } from 'react';
import './App.css';
import AddBookForm from './AddBookForm';

function App() {
  const [books, setBooks] = useState([]);

  const addBook = (book) => {
    setBooks([...books, book]);
  };

  return (
    <div className="App">
      <h1>Book Management System</h1>
      <AddBookForm onAddBook={addBook} />
      <div>
        <h2>Books</h2>
        <ul>
          {books.map((book, index) => (
            <li key={index}>
              <div>Title: {book.title}</div>
              <div>Author: {book.author}</div>
              <div>Year: {book.year}</div>
              <div>ISBN: {book.isbn}</div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default App;
