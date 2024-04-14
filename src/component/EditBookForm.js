import React, { useState, useEffect } from 'react';

const EditBookForm = ({ book, onUpdateBook }) => {
  const [title, setTitle] = useState('');
  const [author, setAuthor] = useState('');
  const [year, setYear] = useState('');
  const [isbn, setIsbn] = useState('');
  const [showForm, setShowForm] = useState(true);

  useEffect(() => {
    if (book) {
      setTitle(book.title);
      setAuthor(book.author);
      setYear(book.year);
      setIsbn(book.isbn);
    }
  }, [book]);

  const handleSubmit = (e) => {
    e.preventDefault();
    onUpdateBook({ ...book, title, author, year, isbn });
    setShowForm(false); // Hide the form after successful update
  };

  return (
    <>
      {showForm && (
        <form onSubmit={handleSubmit}>
          <label htmlFor="title">Title</label>
          <input id="title" type="text" value={title} onChange={(e) => setTitle(e.target.value)} />

          <label htmlFor="author">Author</label>
          <input id="author" type="text" value={author} onChange={(e) => setAuthor(e.target.value)} />

          <label htmlFor="year">Year</label>
          <input id="year" type="text" value={year} onChange={(e) => setYear(e.target.value)} />

          <label htmlFor="isbn">ISBN</label>
          <input id="isbn" type="text" value={isbn} onChange={(e) => setIsbn(e.target.value)} />

          <button type="submit">Update</button>
        </form>
      )}
    </>
  );
};

export default EditBookForm;

