import React from 'react';
import { useForm } from 'react-hook-form';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import './Login.css'; // Reusing the CSS
import * as Yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup';
import ApiService from './service/ApiService';
import { useNavigate } from 'react-router-dom';

// Validation Schema
const schema = Yup.object().shape({
  firstName: Yup.string().required('First Name is required'),
  lastName: Yup.string().required('Last Name is required'),
  email: Yup.string()
    .email('Invalid email address')
    .required('Email is required'),
  password: Yup.string()
    .required('Password is required')
    .min(6, 'Password must be at least 6 characters long'),
});

const RegisterForm = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm({
    resolver: yupResolver(schema),
  });

  const navigate = useNavigate();

  const onSubmit = async (data) => {
    try {
      // Call the register method from ApiService
      const response = await ApiService.registerUser(data);

      if (response.statusCode === 200) {
        // Success message and redirection
        alert('User registered successfully!');
        reset(); // Clear form fields
        navigate('/login'); // Redirect to login or home page
      }
    } catch (error) {
      alert(
        error.response?.data?.message ||
          'Something went wrong. Please try again later.'
      );
    }
  };

  return (
    <div className="form-container">
      <h1>Register for ABC Company</h1>
      <Form onSubmit={handleSubmit(onSubmit)}>
        {/* First Name */}
        <Form.Group className="mb-3" controlId="formBasicFirstName">
          <Form.Label>First Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your first name"
            {...register('firstName')}
          />
          {errors.firstName && (
            <p className="text-danger">{errors.firstName.message}</p>
          )}
        </Form.Group>


        {/* Last Name */}
        <Form.Group className="mb-3" controlId="formBasicLastName">
          <Form.Label>Last Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your last name"
            {...register('lastName')}
          />
          {errors.lastName && (
            <p className="text-danger">{errors.lastName.message}</p>
          )}
        </Form.Group>

        {/* Email */}
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter email"
            {...register('email')}
          />
          {errors.email && (
            <p className="text-danger">{errors.email.message}</p>
          )}
        </Form.Group>

        {/* Password */}
        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            {...register('password')}
          />
          {errors.password && (
            <p className="text-danger">{errors.password.message}</p>
          )}
        </Form.Group>

        <Button variant="primary" type="submit">
          Register
        </Button>
      </Form>
    </div>
  );
};

export default RegisterForm;
