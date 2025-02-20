import React from "react";
import { useForm } from "react-hook-form";
import { useNavigate, useLocation } from "react-router-dom";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import ApiService from "./service/ApiService";


const PersonalDetail = () => {
  let {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm();

  const navigate = useNavigate();
  const location = useLocation();
  
  const remainingQueue  = location.state?.remainingQueue || []; // Get remaining queue


  const handleData = async (data) => {
    
    console.log(data);

    try {

      const id = localStorage.getItem("id");

      if (!id) {
        throw new Error("User ID not found in localStorage");
      }

      const response = await ApiService.savePersonal(id, {
        name: data.name,
        age: data.age,
        idProof: data.idProof,
        dateOfBirth: data.dateOfBirth,
      });

    
      

        
      if (response.statusCode === 200) {

        // Success message and redirection
        reset(); // Clear form fields
        alert('Personal Details Filled successfully');

        if(remainingQueue.length > 0) {
          const nextPage = remainingQueue.shift(); // Get next page
          navigate(`/${nextPage}`, { state: { remainingQueue } }); // Pass updated queue
        } else{
          navigate('/upload-details')
        }

       // navigate("/professional"); // Redirect to login or home page
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
      <h1> Personal Details for ABC Company </h1>

      <Form onSubmit={handleSubmit(handleData)}>
        <Form.Group className="mb-3" controlId="formBasicFirstName">
          <Form.Label>Full Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your Full Name"
            {...register("name")}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicLastName">
          <Form.Label>Age</Form.Label>
          <Form.Control
            type="number"
            placeholder="Enter your Age"
            {...register("age")}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>PAN ID-Proof</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter Your PAN-Number"
            {...register("idProof")}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicDob">
          <Form.Label>Date of Birth</Form.Label>
          <Form.Control
            type="date"
            placeholder="Enter your Date of Birth"
            {...register("dateOfBirth")}
          />
        </Form.Group>

        <Button variant="primary" type="submit">
          Save
        </Button>
      </Form>
    </div>
  );
};

export default PersonalDetail;
