import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import EditBookForm from '../component/EditBookForm';

describe('EditBookForm', () => {
  const mockBook = {
    id: 1,
    title: 'Test Book',
    author: 'Test Author',
    year: 2024,
    isbn: '1234567890',
  };

  it('renders the form with correct initial values', () => {
    const onUpdateBook = jest.fn();
    const { getByLabelText } = render(<EditBookForm book={mockBook} onUpdateBook={onUpdateBook} />);

    expect(getByLabelText('Title')).toHaveValue(mockBook.title);
    expect(getByLabelText('Author')).toHaveValue(mockBook.author);
    expect(getByLabelText('Year')).toHaveValue(mockBook.year.toString());
    expect(getByLabelText('ISBN')).toHaveValue(mockBook.isbn);
  });

  it('calls onUpdateBook with updated book when form is submitted', () => {
    const onUpdateBook = jest.fn();
    const { getByLabelText, getByText } = render(<EditBookForm book={mockBook} onUpdateBook={onUpdateBook} />);

    const newTitle = 'New Title';
    const newAuthor = 'New Author';
    const newYear = '2024';
    const newIsbn = '9876543210';

    fireEvent.change(getByLabelText('Title'), { target: { value: newTitle } });
    fireEvent.change(getByLabelText('Author'), { target: { value: newAuthor } });
    fireEvent.change(getByLabelText('Year'), { target: { value: newYear } });
    fireEvent.change(getByLabelText('ISBN'), { target: { value: newIsbn } });

    fireEvent.submit(getByText('Update'));

    expect(onUpdateBook).toHaveBeenCalledWith({
      ...mockBook,
      title: newTitle,
      author: newAuthor,
      year: parseInt(newYear),
      isbn: newIsbn,
    });
  });
});
