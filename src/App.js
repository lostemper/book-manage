import React, { useState, useEffect } from 'react';
import './App.css';
import axios from 'axios';
import AddBookForm from './component/AddBookForm';
import BookList from './component/BookList';

function App() {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    try {
      const response = await axios.get('http://localhost:8080/books');
      setBooks(response.data);
    } catch (error) {
      console.error('Error fetching books:', error);
    }
  };

  const addBook = async (book) => {
    try {
      const response = await axios.post('http://localhost:8080/books', book);
      setBooks([...books, response.data]);
    } catch (error) {
      console.error('Error adding book:', error);
    }
  };

  const handleEditBook = async (updatedBook) => {
    try {
      await axios.put(`http://localhost:8080/books/${updatedBook.id}`, updatedBook);
      fetchBooks(); // Update the book list after updating a book
      alert('Book updated successfully!'); // Show success message
    } catch (error) {
      console.error('Error updating book:', error);
      alert('Failed to update book.'); // Show error message
    }
  };


  const handleDeleteBook = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/books/${id}`);
      setBooks(books.filter(book => book.id !== id));
    } catch (error) {
      console.error('Error deleting book:', error);
    }
  };

  return (
    <div className="App">
      <h1>Book Management System</h1>
      <AddBookForm onAddBook={addBook} />
      <div>
        <BookList
          books={books}
          onEditBook={handleEditBook}
          onDeleteBook={handleDeleteBook}
        />
      </div>
    </div>
  );
}

export default App;
