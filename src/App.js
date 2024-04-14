import React, { useState, useEffect } from 'react';
import './App.css';
import axios from 'axios';
import AddBookForm from './component/AddBookForm';
import BookList from './component/BookList';

function App() {
  const [books, setBooks] = useState([]);
  const [showAddForm, setShowAddForm] = useState(false); // 新增表单的显示状态

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
      setShowAddForm(false);
    } catch (error) {
      console.error('Error adding book:', error);
    }
  };

  const handleEditBook = async (updatedBook) => {
    try {
      const updatedBooks = books.map(book => (book.id === updatedBook.id ? updatedBook : book));
      setBooks(updatedBooks);
      await axios.put(`http://localhost:8080/books/${updatedBook.id}`, updatedBook);
      fetchBooks(); // Update the book list after updating a book
      //alert('Book updated successfully!'); // Show success message
    } catch (error) {
      console.error('Error updating book:', error);
      //alert('Failed to update book.'); // Show error message
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
    <div className="container">
      <div className="header">
        <h1>Book Management System</h1>
      </div>


      <div className="book-list">
        <div className="add-book">
          <div>
            <button onClick={() => setShowAddForm(!showAddForm)}>Add Book</button>
          </div>

          <div>
            {showAddForm && <AddBookForm onAddBook={addBook}/>}
          </div>



        </div>
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
