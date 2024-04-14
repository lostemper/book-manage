import React, { useState } from 'react';
const AddBookForm = ({ onAddBook }) => {
  const [title, setTitle] = useState('');
  const [author, setAuthor] = useState('');
  const [year, setYear] = useState('');
  const [isbn, setIsbn] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    onAddBook({ title, author, year, isbn });
  };

  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="title">Title</label>
      <input id="title" type="text" value={title} onChange={(e) => setTitle(e.target.value)} />

      <label htmlFor="author">Author</label>
      <input id="author" type="text" value={author} onChange={(e) => setAuthor(e.target.value)} />

      <label htmlFor="year">Year</label>
      <input id="year" type="text" value={year} onChange={(e) => setYear(e.target.value)} />

      <label htmlFor="isbn">ISBN</label>
      <input id="isbn" type="text" value={isbn} onChange={(e) => setIsbn(e.target.value)} />

      <button type="submit">Submit</button>
    </form>
  );
};

export default AddBookForm;
