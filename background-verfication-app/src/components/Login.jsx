import React from 'react';
import { useForm } from 'react-hook-form';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import './Login.css';
import * as Yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup';
import ApiService from './service/ApiService';
import { useNavigate } from 'react-router-dom';


// Validation schema
const schema = Yup.object().shape({
  email: Yup.string()
    .required('Email is required')
    .matches(/^[a-z]+[1-9]*@gmail\.com$/, 'Enter a valid email format'),
  password: Yup.string()
    .required('Password is required')
    .min(6, 'Password must be at least 6 characters long'),
});

const LoginPage = () => {
  const {
    register,
    handleSubmit,
    formState: { errors }, reset
  } = useForm({ resolver: yupResolver(schema) });

  const navigate = useNavigate();

  // Handle form submission
  const handleData = async (data) => {

    // console.log(data);
    
    try {
      const response = await ApiService.loginUser({
        email: data.email,
        password: data.password,
      });

     // console.log(response.data)

      // const pageOrders = JSON.parse(response.pageOrders)
      // console.log("Page Orders : " + pageOrders);          
      

      if (response.statusCode === 200 && response.role === "USER") {

        
        console.log(response.pageOrders);

        const queue = []

        response.pageOrders.forEach( page => {
          queue.push(page);
        } );



        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);
        localStorage.setItem('id', response.id)
        reset()
        alert('Login successful');

        const page = queue.shift();
        const remainingQueue = queue;
        
        navigate(`/${page}`, { state : { remainingQueue } } )
      }

      
      if (response.statusCode === 200 && response.role === "ADMIN") {
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);
        reset()
        alert('Admin Login successful');
        navigate('/')
      } 
      
      
    } catch (error) {
      alert(
        error.response?.data?.message ||
          'Something went wrong. Please try again later.'
      );
    }
  };

  // const navigate = useNavigate()
  // let handleNavigate = () => {
  //   navigate("/login")
  // }

  return (
    <div className="login-container">
      <h1>Login</h1>

      <Form onSubmit={handleSubmit(handleData)}>
        {/* Email Field */}
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
          <Form.Text className="text-muted">
            We'll never share your email with anyone else.
          </Form.Text>
        </Form.Group>

        {/* Password Field */}
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

        {/* <p>
          Don't have an account?  <button onClick={ handleNavigate } >  Create one  </button>
        </p> */}

        <Button variant="primary" type="submit">
          Submit
        </Button>
      
      </Form>


    </div>
  );
};

export default LoginPage;
