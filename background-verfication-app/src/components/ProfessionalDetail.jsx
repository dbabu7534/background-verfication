import React from "react";
import { useNavigate, useLocation } from "react-router-dom";

import { useForm } from "react-hook-form";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import ApiService from "./service/ApiService";

// import { date } from "yup";

const ProfessionalDetail = () => {
  let {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm();

  const navigate = useNavigate();
  const location = useLocation();


  const remainingQueue  = location.state?.remainingQueue || []; // Get remaining queue


  const onSubmit = async (data) => {
    

    try {
      
      const id = localStorage.getItem("id");

      if (!id) {
        throw new Error("User ID not found in localStorage");
      }

      // Call the register method from ApiService
      const response = await ApiService.saveProfessional(id, {
        companyName: data.companyName,
        previousCompanyRole: data.previousCompanyRole,
        yearOfExperience: data.yearOfExperience,
        date: data.date 
      });

      if (response.statusCode === 200) {
        // Success message and redirection
        alert("User registered successfully!");
        reset(); // Clear form fields
        
        if(remainingQueue.length > 0) {
          const nextPage = remainingQueue.shift(); // Get next page
          navigate(`/${nextPage}`, { state: { remainingQueue } }); // Pass updated queue
        } else{
          navigate('/status')
        }

      //  navigate("/education"); // Redirect to login or home page
      }
    } catch (error) {
      alert(
        error.response?.data?.message ||
          "Something went wrong. Please try again later."
      );
    }
  };

  return (
    <div className="form-container">
      <h1> Professional Details </h1>

      <Form onSubmit={handleSubmit(onSubmit)}>
        <Form.Group className="mb-3" controlId="formBasicFirstName">
          <Form.Label>Previous Company Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your Previous Company Name"
            {...register("companyName")}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicDob">
          <Form.Label> Previous Company Role </Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your Previous Company Role"
            {...register("previousCompanyRole")}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicLastName">
          <Form.Label>Total Year Of Experience</Form.Label>
          <Form.Control
            type="number"
            placeholder="Enter your Total Year Of Experience"
            {...register("yearOfExperience")}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Date Of Joining</Form.Label>
          <Form.Control
            type="date"
            placeholder="Enter Your Joining Date"
            {...register("date")}
          />
        </Form.Group>

        <Button variant="primary" type="submit">
          Save
        </Button>
      </Form>
    </div>
  );
};

export default ProfessionalDetail;
