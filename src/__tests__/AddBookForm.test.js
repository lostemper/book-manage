import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import AddBookForm from '../component/AddBookForm';

describe('AddBookForm', () => {
  test('renders form inputs', () => {
    const { getByLabelText } = render(<AddBookForm />);

    expect(getByLabelText('Title')).toBeInTheDocument();
    expect(getByLabelText('Author')).toBeInTheDocument();
    expect(getByLabelText('Year')).toBeInTheDocument();
    expect(getByLabelText('ISBN')).toBeInTheDocument();
  });

  test('calls onAddBook with correct book data when form is submitted', () => {
    const onAddBook = jest.fn();
    const { getByLabelText, getByText } = render(<AddBookForm onAddBook={onAddBook} />);

    fireEvent.change(getByLabelText('Title'), { target: { value: 'Test Book' } });
    fireEvent.change(getByLabelText('Author'), { target: { value: 'Test Author' } });
    fireEvent.change(getByLabelText('Year'), { target: { value: '2024' } });
    fireEvent.change(getByLabelText('ISBN'), { target: { value: '1234567890' } });
    fireEvent.click(getByText('Submit'));

    expect(onAddBook).toHaveBeenCalledWith({
      title: 'Test Book',
      author: 'Test Author',
      year: '2024',
      isbn: '1234567890'
    });
  });
});
